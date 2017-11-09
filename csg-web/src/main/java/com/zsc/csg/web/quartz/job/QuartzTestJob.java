package com.zsc.csg.web.quartz.job;

import org.springframework.stereotype.Component;

/**
 * 定时任务测试类
 * @author zsc
 *
 */
@Component
public class QuartzTestJob {

	public void execute() {
		System.out.println("-----------测试定时开始执行-----------");
	}
}
