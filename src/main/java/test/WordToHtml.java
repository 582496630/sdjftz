package test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.hwpf.usermodel.Picture;
//import org.junit.Assert;  
//import org.junit.Test;  

public class WordToHtml {

	// @Test
	public static void canExtractImage() throws IOException {
		File f = new File("F:/2017年12月跳闸统计报表.doc");
		if (!f.exists()) {
			System.out.println("Sorry File does not Exists!");
		} else {
			if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {

				// 1) 将DOCX加载到XWPFDocument中
				InputStream in = new FileInputStream(f);
				XWPFDocument document = new XWPFDocument(in);

				// 2）准备XHTML选项（这里我们设置IURIResolver为
				// 从“word / media”文件夹加载图片）
				File imageFolderFile = new File("F:/img");
				XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
				options.setExtractor(new FileImageExtractor(imageFolderFile));
				// options.setIgnoreStylesIfUnused(false);
				// options.setFragment(true);

				// 3）将XWPFDocument转换为XHTML
				OutputStream out = new FileOutputStream(new File("F:/2017-12.htm"));
				XHTMLConverter.getInstance().convert(document, out, options);
			} else {
				System.out.println("Enter only MS Office 2007+ files");
			}
		}
	}

	public static void docToHtml() throws Exception {
		String sourceFileName = "F:/201712.doc";
		String targetFileName = "F:/2017-12.html";
		if (!new File(sourceFileName).exists()) {
			System.out.println("Sorry File does not Exists!");
			return;
		}
		// String imagePathStr = "C:\\html\\image\\";
		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(new File(sourceFileName)));
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
		// 保存图片，并返回图片的相对路径
		// wordToHtmlConverter.setPicturesManager((content, pictureType, name,
		// width, height) -> {
		// try(FileOutputStream out = new FileOutputStream(imagePathStr +
		// name)){
		// out.write(content);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// return "image/" + name;
		// });
		wordToHtmlConverter.processDocument(wordDocument);
		Document htmlDocument = wordToHtmlConverter.getDocument();
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(new File(targetFileName));

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
	}

	public static void PoiWord03ToHtml() throws IOException, ParserConfigurationException, TransformerException{  
    	 final String path = "F:/";  
    	  final String file = "F:/201712.doc";  
    	  InputStream input = new FileInputStream(file);  
    	  HWPFDocument wordDocument = new HWPFDocument(input);  
    	  WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(  
    	    DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());  
    	  wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			@Override
			 public String savePicture(byte[] content, PictureType pictureType,  
		    	     String suggestedName, float widthInches, float heightInches) {     //图片在html页面加载路径  
		    	    return "F:/img/"+suggestedName;  
		    	   } 
		}); 
    	  wordToHtmlConverter.processDocument(wordDocument);  
    	  //获取文档中所有图片  
    	  List pics = wordDocument.getPicturesTable().getAllPictures();  
    	  if (pics != null) {  
    	   for (int i = 0; i < pics.size(); i++) {  
    	    Picture pic = (Picture) pics.get(i);  
    	    try {//图片保存在文件夹的路径  
    	     pic.writeImageContent(new FileOutputStream(path  
    	       + pic.suggestFullFileName()));  
    	    } catch (FileNotFoundException e) {  
    	     e.printStackTrace();  
    	    }  
    	   }  
    	  }  
    	  //创建html页面并将文档中内容写入页面  
    	  Document htmlDocument = wordToHtmlConverter.getDocument();  
    	  ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
    	  DOMSource domSource = new DOMSource(htmlDocument);  
    	  StreamResult streamResult = new StreamResult(outStream);  
    	  TransformerFactory tf = TransformerFactory.newInstance();  
    	  Transformer serializer = tf.newTransformer();  
    	  serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");  
    	  serializer.setOutputProperty(OutputKeys.INDENT, "yes");  
    	  serializer.setOutputProperty(OutputKeys.METHOD, "html");  
    	  serializer.transform(domSource, streamResult);  
    	  outStream.close();  
    	  String content = new String(outStream.toString("UTF-8"));  
    	  FileUtils.writeStringToFile(new File(path, "2017-12.html"), content, "utf-8");  
    	  
    	}

	public static void main(String args[]) {
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}