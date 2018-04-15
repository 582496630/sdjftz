package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class WordTest {
	private Configuration configuration = null;

	public WordTest() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
	}

	public static void main(String[] args) {
		WordTest test = new WordTest();
		test.createWord();
	}

	public void createWord() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		getData(dataMap);
		// configuration.setClassForTemplateLoading(this.getClass(),
		// "F:/项目文件/hssjdoc/ftl");//模板文件所在路径
		try {
			configuration.setDirectoryForTemplateLoading(new File("F:/项目文件/hssjdoc/ftl"));
		} catch (IOException e2) {
			e2.printStackTrace();
		} // 模板文件所在路径
		Template t = null;
		try {
			t = configuration.getTemplate("test1.ftl"); // 获取模板文件
		} catch (IOException e) {
			e.printStackTrace();
		}
		File outFile = new File("F:/outFile123.html"); // 导出文件
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			t.process(dataMap, out); // 将填充数据填入模板文件并输出到目标文件
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getData(Map<String, Object> dataMap) {
		dataMap.put("title", "标题");
		dataMap.put("nian", "2018");
		dataMap.put("yue", "1");
		dataMap.put("ri", "17");
		dataMap.put("xuehao", "123456");
		dataMap.put("neirong", "sss");
	}


}
