package com.order.model;

import com.order.dto.BookDTO;
import lombok.Data;

import javax.persistence.*;
import java.awt.print.Book;

@Entity
@Table(name = "book_contact")
@Data

public class BookContact
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;

    @Column(name = "BookName")
    private String bookName;

    @Column(name = "AutherName")
    private String autherName;

    @Column(name = "BookDescription")
    private String bookDescription;

    @Column(name = "BookImg")
    private  String bookImg;

    @Column(name = "Book_Price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    public BookContact(BookContact book) {
        super();
        this.bookId = book.getBookId();
        this.autherName = book.getAutherName();
        this.bookName = book.getBookName();
        this.bookImg = book.getBookImg();
        this.price = book.getPrice();
        this.quantity = book.getQuantity();
    }



    public BookContact() {

    }

}
