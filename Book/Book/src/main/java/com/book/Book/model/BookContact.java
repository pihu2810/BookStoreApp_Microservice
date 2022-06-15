package com.book.Book.model;

import com.book.Book.dto.BookDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book_contact")
@Data
public class BookContact
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
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


    public BookContact(BookDTO bookDTO) {
        this.autherName = bookDTO.getAutherName();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookImg = bookDTO.getBookImg();
        this.price = bookDTO.getPrice();
        this.quantity = bookDTO.getQuantity();
        this.bookName = bookDTO.getBookName();
    }

    public BookContact() {

    }

    public BookContact(int bookId, BookDTO bookDTO) {
        this.bookId = bookId;
        this.bookName = bookDTO.getBookName();
        this.autherName = bookDTO.getAutherName();
        this.bookImg = bookDTO.getBookImg();
        this.quantity = bookDTO.getQuantity();
        this.price = bookDTO.getPrice();
        this.bookDescription = bookDTO.getBookDescription();

    }

}
