package cn.ltpcloud.jsoup;

import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class JsoupDemo01 {
    @Test
    //快速入门
    public void demo1() throws IOException {
        //获取xml的路径
        String path = JsoupDemo01.class.getClassLoader().getResource("student.xml").getPath();
        //解析xml,获取dom树
        Document document = Jsoup.parse(new File(path), "UTF-8");
        //通过标签名获取标签，返回的Elements实际上为 ArrayList<Element>
        Elements names = document.getElementsByTag("name");
        //获取第一个元素
        Element element = names.get(0);
        //获取元素内的文本
        String text = element.text();
        System.out.println(text);
    }

    //获取dom对象
    @Test
    public void demo2() throws IOException {
        String path = JsoupDemo01.class.getClassLoader().getResource("student.xml").getPath();
        //方式1
        Document document = Jsoup.parse(new File(path), "utf-8");

        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "\n" +
                "<students>\n" +
                "\t<student number=\"heima_0001\">\n" +
                "\t\t<name>tom</name>\n" +
                "\t\t<age>18</age>\n" +
                "\t\t<sex>male</sex>\n" +
                "\t</student>\n" +
                "\t<student number=\"heima_0002\">\n" +
                "\t\t<name>jack</name>\n" +
                "\t\t<age>18</age>\n" +
                "\t\t<sex>female</sex>\n" +
                "\t</student>\n" +
                "\n" +
                "</students>";
        //方式2
       document = Jsoup.parse(str);

        URL url = new URL("https://baike.baidu.com/item/jsoup/9012509?fr=aladdin");//代表网络中的一个资源路径
        //方式3
        document = Jsoup.parse(url, 10000);
        System.out.println(document);
    }

    //常用功能
    @Test
    public void demo3() throws IOException {
        String path = Jsoup.class.getClassLoader().getResource("student.xml").getPath();
        Document document = Jsoup.parse(new File(path), "utf-8");
        //通过标签名获取元素
        Elements stus = document.getElementsByTag("student");
        //通过属性获取元素
        Elements nums = document.getElementsByAttribute("number");

        //通过属性值获取元素
        Elements ele1 = document.getElementsByAttributeValue("number", "0001");
        //通过id获取元素
        Element ele2 = document.getElementById("1001");

        //获取第一个student元素
        Element firstStu = stus.get(0);
        Elements names = firstStu.getElementsByTag("name");
        System.out.println(names.size());//1
        //获取元素上指定的属性值
        String id = names.attr("id");
        System.out.println(id);
        System.out.println(names.get(0).attr("id"));

        //获取元素内文本/html
        String text = names.text();
        String html = names.html();
        System.out.println(text);
        System.out.println(html);
    }

    //selector查询
    @Test
    public void demo4() throws IOException {
        String path = JsoupDemo01.class.getClassLoader().getResource("student.xml").getPath();
        Document document = Jsoup.parse(new File(path), "utf-8");
        //3.查询name标签
        Elements names = document.select("name");
        //4.查询id值为itcast的元素
        Elements ele = document.select("#itcast");
        //5.1.获取student标签并且number属性值为0001
        Elements stu = document.select("student[number=\"0001\"]");
        System.out.println(stu);

        //5.2获取student标签并且number属性值为0001的age子标签
        Elements age = document.select("student[number=\"0001\"] > age");
        System.out.println(age);
    }

    //xpath查询
    @Test
    public void demo5() throws Exception {
        String path = JsoupDemo01.class.getClassLoader().getResource("student.xml").getPath();
        Document document = Jsoup.parse(new File(path), "utf-8");
        //根据document对象，创建JXDocument对象
        JXDocument jxDocument = new JXDocument(document);

        //查询所有student标签
        List<JXNode> jxNodes = jxDocument.selN("//student");
        for (JXNode jxNode : jxNodes) {
            System.out.println(jxNode);
        }

        System.out.println("--------------------");

        //查询所有student标签下的name标签
        List<JXNode> jxNodes2 = jxDocument.selN("//student/name");
        for (JXNode jxNode : jxNodes2) {
            System.out.println(jxNode);
        }

        System.out.println("--------------------");

        //查询student标签下带有id属性的name标签
        List<JXNode> jxNodes3 = jxDocument.selN("//student/name[@id]");
        for (JXNode jxNode : jxNodes3) {
            System.out.println(jxNode);
        }
        System.out.println("--------------------");
        //查询student标签下带有id属性的name标签 并且id属性值为1001

        List<JXNode> jxNodes4 = jxDocument.selN("//student/name[@id='1001']");
        for (JXNode jxNode : jxNodes4) {
            System.out.println(jxNode);
        }
    }
}
