package com.book.Book.repository;

import com.book.Book.model.BookContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookContact, Integer> {
    @Query(value = "SELECT * FROM book_contact where bookName=:bookName", nativeQuery = true)
    List<BookContact> findByBookName(String bookName);


    @Query(value = "select * from book_contact order by price", nativeQuery = true)
    List<BookContact> getSortedListOfBooksInAsc();

    @Query(value = "select * from book_contact order by price desc", nativeQuery = true)
    List<BookContact> getSortedListOfBooksInDesc();

    @Query(value = "select * from book_contact where author_name like :authorName%", nativeQuery = true)
    List<BookContact> findByBookAuthorName(String authorName);
}
