package com.zsc.csg.web.common.codegen;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suixingpay.cgm.exception.CodegenException;
import com.suixingpay.cgm.model.ConfigModel;
import com.suixingpay.cgm.model.ConfigModel.Files;
import com.suixingpay.cgm.model.ConfigModel.Table;
import com.suixingpay.cgm.model.ConfigModel.Table.SubTable;
import com.suixingpay.cgm.util.FileHelper;
import com.suixingpay.cgm.util.StringUtil;

public class CodeDelete {
	private static final Logger logger = LoggerFactory.getLogger(CodeDelete.class);
	private static String xmlPath;

	public static void setXmlPath(String path) {
		xmlPath = path;
	}
	
	/**
	 * 根据给定的schema校验xml,并返回是否校验成功。
	 * @param xml
	 * @param schema
	 * @return
	 */
	public String validXmlBySchema(String xsdPath) {
		 // 建立schema工厂
       SchemaFactory schemaFactory = SchemaFactory
               .newInstance("http://www.w3.org/2001/XMLSchema");

       // 建立验证文档文件对象，利用此文件对象所封装的文件进行schema验证。
       File schemaFile = new File(xsdPath);
       
       // 利用schema工厂，接收验文档文件对象生成Schema对象
       Schema schema = null;
       try {
            schema = schemaFactory.newSchema(schemaFile);
           // 通过Schema产生针对此Schema的验证器，利用schenaFile进行验证
           Validator validator = schema.newValidator();

           // 得到验证的数据源
           Source source = new StreamSource(FileHelper.getInputStream(xmlPath));

           // 开始验证，成功输出success!!!，是不输出fail
         
           validator.validate(source);
       }
       catch (Exception ex) {
           return ex.getMessage();
       }
       return null;

	}
	
	public ConfigModel loadXml(String xmlPath)throws Exception{
		// xsd文件路径
		String xsdPath=new File("").getAbsolutePath()+"\\codegen.xsd";
		// 验证XML
		String result= validXmlBySchema(xsdPath);
		
		if (result!=null) {
			throw new CodegenException("XML文件通过XSD文件校验失败:"+result);
		}
		// 验证通过，开始读取XML
		ConfigModel cm = new ConfigModel();

		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(new File(xmlPath));
		} catch (DocumentException e) {
			throw new CodegenException("读XML出错!", e);
		}
		Element root = document.getRootElement();
		// 从XML中读取table
		Properties prop=new Properties();
		String pathBase=new File("").getAbsolutePath();
		InputStream in=new BufferedInputStream(new FileInputStream(pathBase+"\\"+"codegen.properties"));
		prop.load(in);
		String charset=prop.getProperty("charset");
		String system=prop.getProperty("system");
		cm.setCharset(charset);
		for (Iterator i = root.elementIterator("table"); i.hasNext();) {
			Element tableEl = (Element) i.next();
			String tableName = tableEl.attributeValue("tableName");
		
			Table table = cm.new Table(tableName);
			for (Iterator j = tableEl.elementIterator("variable"); j.hasNext();) {
				Element fileEl = (Element) j.next();
				String name = fileEl.attributeValue("name");
				String value = fileEl.attributeValue("value");
				table.getVariable().put(name, value);
			}
			table.getVariable().put("tabname", tableName);
			//添加子表
			for (Iterator m = tableEl.elementIterator("subtable"); m.hasNext();) {
				Element subEl = (Element) m.next();
				String tablename = subEl.attributeValue("tablename");
				String foreignKey = subEl.attributeValue("foreignKey");
				Map<String,String >vars=new HashMap<String, String>();
				
				for (Iterator var = subEl.elementIterator("variable"); var.hasNext();) {			
					Element varEl = (Element) var.next();
					String name = varEl.attributeValue("name");
					String value = varEl.attributeValue("value");
					vars.put(name, value);
				}
				vars.put("tabname", tablename);
				
				//添加子表变量
				table.addSubTable(tablename, foreignKey, vars);
			}
			cm.getTables().add(table);
		
		}
		//从xml读取文件模板
		Element filesEl= root.element("files");
		Files files=cm.new Files();
		cm.setFiles(files);
		
		String baseDir = filesEl.attributeValue("baseDir");
		files.setBaseDir(baseDir);
		for (Iterator j = filesEl.elementIterator("file"); j.hasNext();) {
			
			Element fileEl = (Element) j.next();
			String refTemplate = fileEl.attributeValue("refTemplate");
			String filename = fileEl.attributeValue("filename");
			String dir = StringUtil.replaceVariable(fileEl.attributeValue("dir"),system);
			
			String sub=fileEl.attributeValue("sub");
			String override=fileEl.attributeValue("override");
			boolean isOverride=false;
			if(StringUtil.isNotEmpty(override)&& override.equals("true")){
				isOverride=true;
			}
			String append=fileEl.attributeValue("append");
			if(append!=null && append.toLowerCase().equals("true")){
				String insertTag=fileEl.attributeValue("insertTag");
				String startTag=fileEl.attributeValue("startTag");
				String endTag=fileEl.attributeValue("endTag");
				if(insertTag==null)
					insertTag="<!--insertbefore-->";
				if(StringUtil.isEmpty(startTag)) startTag="";
				if(StringUtil.isEmpty(endTag)) endTag="";
				if(sub!=null&&sub.toLowerCase().equals("true")){
					files.addFile(null, filename, dir,true,isOverride,true,insertTag,startTag,endTag);
				}else{
					files.addFile(null, filename, dir,false,isOverride,true,insertTag,startTag,endTag);
				}
			}else{
				if(sub!=null&&sub.toLowerCase().equals("true")){
					files.addFile(null, filename, dir,true,isOverride,false,"","","");
				}else{
					files.addFile(null, filename, dir,false,isOverride,false,"","","");
				}
			}
		}
		return cm;
	}
	
	/**
	 * 根据配置删除相应的生成的代码
	 * @param configModel
	 * @throws Exception
	 */
	public  void deleteFileByConfig(ConfigModel configModel)throws Exception{
		List<Table> tableList=configModel.getTables();
		Files files=configModel.getFiles();
		String baseDir=files.getBaseDir();
		String charset=configModel.getCharset();
		for(Table table:tableList){
			Map<String,String> variables=table.getVariable();
			List<ConfigModel.Files.File> list=files.getFiles();
			for(ConfigModel.Files.File file: list){
				String filename=file.getFilename();
				String dir=file.getDir();
				filename=StringUtil.replaceVariable(filename, variables);
				dir=StringUtil.replaceVariable(dir, variables);
				
				String fileDir=baseDir+"\\"+dir;
				String startTag=StringUtil.replaceVariable(file.getStartTag(), variables);
				String endTag=StringUtil.replaceVariable(file.getEndTag(), variables);
				boolean isAppend=file.getAppend();
				if(isAppend){
					deleteAppendFile(fileDir,filename,charset,startTag,endTag);
				}else{
					deleteFile(fileDir,filename);
				}
			}
			List<SubTable> subtables=table.getSubtable();
			if(subtables!=null&& subtables.size()!=0){
				for(SubTable subtable:subtables){
					Map<String,String> vars=subtable.getVars();
					for(ConfigModel.Files.File file: list){
						String filename=file.getFilename();
						String dir=file.getDir();
						if(filename.indexOf("{")!=-1){
							String var=filename.substring(filename.indexOf('{')+1,filename.indexOf('}'));
							filename=vars.get(var)+filename.substring(filename.indexOf('}')+1);
						}
						if(dir.indexOf("{")!=-1){
							String var=dir.substring(dir.indexOf('{')+1,dir.indexOf('}'));
							dir=dir.substring(0,dir.indexOf('{'))+vars.get(var);
						}
						String fileDir=baseDir+"\\"+dir;
						boolean isSub=file.isSub();
						boolean isAppend=file.getAppend();
						String startTag=StringUtil.replaceVariable(file.getStartTag(), vars);
						String endTag=StringUtil.replaceVariable(file.getStartTag(), vars);
						if(isSub){
							if(isAppend){
								deleteAppendFile(fileDir,filename,charset,file.getStartTag(),file.getEndTag());
							}else{
								deleteFile(fileDir,filename);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 根据目录和文件名 删除生成的代码文件
	 * 如果该目录为空，则删除该目录
	 * @param fileDir
	 * @param filename
	 */
	private void deleteFile(String fileDir,String filename) {
		String filepath=StringUtil.combilePath(fileDir, filename);
		File file=new File(filepath);
		if(file.exists()){
			boolean isDelete = file.delete();
			logger.info("删除了文件"+isDelete+filename);
		}else{
			logger.info(filename+"该文件不存在");
		}
		if(!FileHelper.isExistFile(fileDir)){
			file=new File(fileDir);
			boolean isDelete = file.delete();
			logger.info("文件删除"+isDelete);
		}
	}
	
	/**
	 * 删除生成的代码
	 * @param fileDir
	 * @param filename
	 * @param charset
	 * @param startTag
	 * @param endTag
	 * @throws Exception
	 */
	private void deleteAppendFile(String fileDir,String filename,String charset,String startTag,String endTag) throws Exception{
		String filepath=StringUtil.combilePath(fileDir, filename);
		File file=new File(filepath);
		if(file.exists()){
			boolean exists=false;
			String content=FileHelper.readFile(filepath,charset);
			if(content.indexOf(startTag)!=-1){
				content=content.substring(0,content.indexOf(startTag)).trim()+"\r\n"
							+content.substring(content.indexOf(endTag)+endTag.length()).trim();
				boolean isDelete = file.delete();
				logger.info("文件删除结果"+isDelete);
				file=new File(filepath);
				FileHelper.writeFile(file, charset, content.trim());
				logger.info("删除了内容"+startTag+"-----"+endTag);
			}
		}
	}

	public void execute() throws Exception {
		String dir=new File("").getAbsolutePath();
        if(xmlPath==null||xmlPath==""){
        	setXmlPath(dir+"\\"+"codegenconfig.xml");
        }
		System.out.println("execute:" +xmlPath);
		if (xmlPath == null) {
			throw new CodegenException("未指定XML路径!");
		}
		ConfigModel cm = loadXml(xmlPath);
		deleteFileByConfig(cm);
	}
	
//   public static void main(String[] args) throws Exception{
//		CodeDelete cd = new CodeDelete();
//		cd.setXmlPath("D:\\workspace\\codegen\\src\\mscodegenconfig.xml");
//		cd.execute();
//	}

}
