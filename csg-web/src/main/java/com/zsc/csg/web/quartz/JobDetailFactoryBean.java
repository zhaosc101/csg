package com.zsc.csg.web.quartz;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.impl.JobDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.util.MethodInvoker;

import com.zsc.csg.web.common.exception.CsgAppException;

/**
 * spring的quartz调度类org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean的替换类
 * 定制增加了一些启动判断等功能
 * 
 * @author Lan xiang
 * @date 2014-12-18
 */
/**
 * Description: 解决原先static造成的bug，jobQuartzDataSource和quartzSQL改成用jobDataMap处理
 * Copyright:   ©2015 Vbill Payment Co. Ltd. All rights reserved.
 * Created on:  2016年5月3日 下午6:36:45 
 * @author xing_xin@suixingpay.com
 */
public class JobDetailFactoryBean extends MethodInvokingJobDetailFactoryBean{
	private static final Logger logger = LoggerFactory.getLogger(JobDetailFactoryBean.class);
			
	private DataSource jobQuartzDataSource;
	
	private String quartzSQL;
	
	@Resource
	public void setJobQuartzDataSource(DataSource jobQuartzDataSource) {
		this.jobQuartzDataSource = jobQuartzDataSource;
	}

	@Resource
	public void setQuartzSQL(String quartzSQL) {
		this.quartzSQL = quartzSQL;
	}
	
	@Override
	protected void postProcessJobDetail(JobDetail jobDetail) {
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		jobDataMap.put("jobQuartzDataSource", jobQuartzDataSource);
		jobDataMap.put("quartzSQL", quartzSQL);
	}
	
	@Override
	public void afterPropertiesSet() throws ClassNotFoundException,NoSuchMethodException {
		super.afterPropertiesSet();

		String clazzName = super.getObject().getJobClass().getName();
		logger.info("origin jobClass : " + clazzName);
				
		// Consider the concurrent flag to choose between stateful and stateless job.
		String invokeName = MethodInvokingJob.class.getName();
		logger.info("MethodInvokingJob.name : " + invokeName);
		if(invokeName.equals(clazzName)) {
			logger.info("jobClass : " + clazzName);
			((JobDetailImpl)super.getObject()).setJobClass(AppsMethodInvokingJob.class);
		} else {
			((JobDetailImpl)super.getObject()).setJobClass(AppsStatefulMethodInvokingJob.class);
		}
		logger.info("new jobClass : " + clazzName);
		
		if(this.jobQuartzDataSource == null || 
				this.quartzSQL == null || "".equals(this.quartzSQL)){
			logger.info("jobQuartzDataSource = " + this.jobQuartzDataSource);	
			logger.info("dataSource or init select sql not found ! Pls check your config !");	
			throw new CsgAppException(10001,"dataSource or init select sql not found ! Pls check your config !","");
		}
		logger.info("init success !");
	}
	
	public static class AppsMethodInvokingJob extends MethodInvokingJob{
		protected static final Logger logger2 = LoggerFactory.getLogger(AppsMethodInvokingJob.class);
		private MethodInvoker methodInvoker;
		private String errorMessage;
		
		/**
		 * Set the MethodInvoker to use.
		 */
		@Override
		public void setMethodInvoker(MethodInvoker methodInvoker) {
			this.methodInvoker = methodInvoker;
		}

		private List<String> getIPs() throws SocketException {
			logger.info("getIPs() start");
			List<String> ips = new ArrayList<>();
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();

					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
						String ip = inetAddress.getHostAddress();
						if(ip != null && !ip.startsWith("127.0")){
							ips.add(ip);
							logger2.info("a ip addr -> " + ip);
						}
					}
				}
			}
			return ips;
		}

		/**
		 * Invoke the method via the MethodInvoker.
		 */
		@Override
		protected void executeInternal(JobExecutionContext context) throws JobExecutionException {		
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
				DataSource jobQuartzDataSource = (DataSource)jobDataMap.get("jobQuartzDataSource");
				String quartzSQL = (String)jobDataMap.get("quartzSQL");
				logger.info(quartzSQL);	
				List<String> ips = this.getIPs();

				//根据当前服务器主机名或者IP判断是否需要执行该任务				
				conn = jobQuartzDataSource.getConnection();
				//根据IP地址查数据库				
				pstmt = conn.prepareStatement(String.valueOf(quartzSQL));
				
				rs = pstmt.executeQuery();   
				boolean flag = false;
				while(rs.next()){
					String ip = rs.getString("instid").trim();
					if(ips.contains(ip)){
						flag = true;
						break;
					}
				}
				
				if(flag) {//数据库里的IP地址匹配
					logger.info("IP right , running the quartz task!");	
					
					//调用具体task执行method代码
					this.methodInvoker.invoke();					
				}else {//数据库里没有和IP地址匹配的数据
					logger.info("IP not right , cannot running the quartz task!");						
				}	
				pstmt.close();
			} catch (Exception ex) {
				logger.error(" accounted a error : " + this.errorMessage, ex);
				throw new JobExecutionException(this.errorMessage, ex, false);
			} finally{
				try{
					if(rs != null)
						rs.close();
					if(pstmt != null)
						pstmt.close();
					if(conn != null)
						conn.close();
				}catch(Exception e){
					logger2.error("executeInternal",e);
				}
			}
		}
	}
	
	public static class AppsStatefulMethodInvokingJob extends AppsMethodInvokingJob {
	}
}
