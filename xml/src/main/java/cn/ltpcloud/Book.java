package cn.ltpcloud;

import java.util.Arrays;

/**
 * @author dpc
 * @create YEAR-{YEAR}-YEAR-{MONTH}-28 13:14
 */
public class Book {
    private String sn;
    private String name;
    private double price;
    private String[] author;

    public Book(String sn, String name, double price, String[] author) {
        this.sn = sn;
        this.name = name;
        this.price = price;
        this.author = author;
    }

    public String getSn() {
        return sn;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author=" + Arrays.toString(author) +
                '}';
    }
}
