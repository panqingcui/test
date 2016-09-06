package cjy.serialize;

import cjy.user.Book;

public class Test {
    public static void main(String[] args) {
        Book b = new Book();
        b.setName("1");
        Book b1 = new Book();
        b1.setName("1");
        System.out.println(b.equals(b1));
    }
}
