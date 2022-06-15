package com.book.Book.service;

import com.book.Book.Util.TokenUtility;
import com.book.Book.dto.BookDTO;
import com.book.Book.exception.BookStoreException;
import com.book.Book.model.BookContact;
import com.book.Book.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService implements IBookService
{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    TokenUtility util;

    /**
     * create a method name as createBook
     * Ability to save book details to repository
     * @param bookDTO - all book data
     * @return - save all data
     */
    @Override
    public String createBook(BookDTO bookDTO) {
        BookContact bookData = new BookContact(bookDTO);
        bookRepository.save(bookData);
        String token = util.createToken(bookData.getBookId());
        return token;
    }

    /**
     * create a method name as getBookDataById
     * - Ability to get book data by id
     * @param token - book id
     * @return - book data by id
     */
    @Override
    public BookContact getBookDataById(String token) {
        int id = util.decodeToken(token);
        Optional<BookContact> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new BookStoreException(HttpStatus.NOT_FOUND,"Exception with id" + id + "does not exist!!");
        }
    }

    /**
     * create a method name as getAllData
     * - Ability to get all book' data by findAll() method
     * @return - all data
     */
    @Override
    public List<BookContact> getAllBookData(String token) {
        int id = util.decodeToken(token);
        Optional<BookContact> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            List<BookContact> listOfBooks = bookRepository.findAll();
            return listOfBooks;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }
    }

    /* create a method name as updataBooksByQuantity by token and quantity */
    @Override
    public BookContact updataBooksByQuantity(String token, int quantity) {
        int id = util.decodeToken(token);
        Optional<BookContact> book = bookRepository.findById(id);
        if (book.isPresent()) {
            BookContact bookData1 = new BookContact();
            bookData1.setQuantity(quantity);
            bookRepository.save(bookData1);
            return bookData1;
        } else {
            throw new BookStoreException(HttpStatus.NOT_FOUND,"Bookdata record does not found");
        }
    }

    /**
     * create a method name as updateRecordById
     * Ability to update book data for particular id
     * @param token - token
     * @param bookDTO - book data
     * @return - updated book information in JSON format
     */
    @Override
    public BookContact updateRecordById(String token, BookDTO bookDTO) {
        int id = util.decodeToken(token);
        Optional<BookContact> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            BookContact updateData = new BookContact(id, bookDTO);
            bookRepository.save(updateData);
            return updateData;
        } else {
            throw new BookStoreException(HttpStatus.NOT_FOUND,"Bookdata record does not found");
        }
    }

    /**
     * create a method name as deleteRecordById
     * ability to delete data by particular book id
     * @param token - book id
     * @return - bookId and Acknowledgment message
     */
    @Override
    public String deleteRecordById(String token) {
        int id = util.decodeToken(token);
        Optional<BookContact> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new BookStoreException(HttpStatus.NOT_FOUND,"Book record does not found");
        }
        return token;
    }

    /* create a method name as getBookByName by bookName */
    @Override
    public List<BookContact> getBookByName(String bookName) {
        List<BookContact> findBook = bookRepository.findByBookName(bookName);
        if (findBook.isEmpty()) {
            throw new BookStoreException(HttpStatus.NOT_FOUND," Details for provided Book is not found");
        }
        return findBook;
    }

    /* create a method name as sortedListOfBooksInAscendingOrder */
    @Override
    public List<BookContact> sortedListOfBooksInAscendingOrder() {
        List<BookContact> getSortedList = bookRepository.getSortedListOfBooksInAsc();
        return getSortedList;
    }

    /* create a method name as sortedListOfBooksInDescendingOrder  */
    @Override
    public List<BookContact> sortedListOfBooksInDescendingOrder() {
        List<BookContact> getSortedListInDesc = bookRepository.getSortedListOfBooksInDesc();
        return getSortedListInDesc;
    }

    /* create a method name as getBookByAuthorName by  authorName*/
    @Override
    public List<BookContact> getBookByAuthorName(String authorName) {
        List<BookContact> findBook = bookRepository.findByBookAuthorName(authorName);
        if (findBook.isEmpty()) {
            throw new BookStoreException(HttpStatus.NOT_FOUND," Details for provided Book is not found");
        }
        return findBook;
    }
    //----------------------------service for rest template---------------//

    /**
     * create a method name as getbookByIdAPI by bookId
     * @param bookId
     * @return
     */
    @Override
    public BookContact getbookByIdAPI(int bookId) {
        Optional<BookContact> book = bookRepository.findById(bookId);
        if(book.isEmpty()) {
            throw new BookStoreException(HttpStatus.NOT_FOUND,"There are no Books with given id");
        }
        return book.get();
    }
}
