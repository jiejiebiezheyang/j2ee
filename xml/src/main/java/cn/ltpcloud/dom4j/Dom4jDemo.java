package cn.ltpcloud.dom4j;

import cn.ltpcloud.Book;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;


public class Dom4jDemo {
    @Test
    public void test1() throws DocumentException {
        SAXReader reader = new SAXReader();//获取SAX解析器
        String path = Dom4jDemo.class.getClassLoader().getResource("books.xml").getPath();//获取xml文件路径
        Document document = reader.read(path);//获取Document对象

        //Document document = reader.read("XML/src/books.xml");//获取Document对象

        Element rootElement = document.getRootElement();//获取根目录元素
        List<Element> bookEles = rootElement.elements("book");//获取元素下指定的子元素
        for(Element bookEle:bookEles){
            String sn = bookEle.attributeValue("sn");//获取元素的指定属性值
            Element nameEle = bookEle.element("name");//获取元素下指定的子元素
            String name = nameEle.getText();//获取元素下的文本
            double price = Double.parseDouble(bookEle.elementText("price"));//获取元素内的文本
            String[] authors = {bookEle.elementText("author")};
            System.out.println(new Book(sn,name,price,authors));
        }
    }

}
