package com.book.Book.controller;

import com.book.Book.dto.BookDTO;
import com.book.Book.dto.ResponseDTO;
import com.book.Book.model.BookContact;
import com.book.Book.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController
{

    @Autowired
    private IBookService iBookServiceservice;

    /**
     * @PostMapping :-
     *           @PostMapping annotation maps HTTP POST requests onto specific handler methods.
     *           It is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. POST)
     *
     *  @RequestBody :-
     *            @RequestBody annotation is applicable to handler methods of Spring controllers.
     *            This annotation indicates that Spring should deserialize a request body into an object.
     *            This object is passed as a handler method parameter
     *
     * - Ability to save book details to repository
     * @apiNote- accepts the book data in JSON format and stores it in DB
     * @param bookDTO - book data
     * @return :- responseDTO
     */
    @PostMapping("/insert")
    public ResponseEntity<String> addBookToRepository(@Valid @RequestBody BookDTO bookDTO) {
        String newBook = iBookServiceservice.createBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("New Book Added in Book Store", newBook);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    /**
     * - Ability to get all book' data by findAll() method
     * @return :- showing all data
     */
    @GetMapping(value = "/getAll/{token}")
    public ResponseEntity<String> getAllBookData(@PathVariable String token) {
        List<BookContact> listOfBooks = iBookServiceservice.getAllBookData(token);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:", listOfBooks);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     *  @PathVariable :-
     *           @PathVariable is a Spring annotation which indicates that a method parameter should be bound to a URI template variable. It has the following optional elements: name - name of the path variable to bind to.
     *           required - tells whether the path variable is required.
     * - Ability to get book data by id
     * @param token -  token
     * @return -book information with same bookId in JSON format
     */
    @GetMapping(value = "/getById/{token}")
    public ResponseEntity<String> getBookDataById(@PathVariable String token) {
        BookContact Book = iBookServiceservice.getBookDataById(token);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully by id (:", Book);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     * - Ability to delete book data for particular id
     * @apiNote - accepts the bookId and deletes the data of that book from DB
     * @param token - represents token
     * @return -  bookId and Acknowledgment message
     */
    @DeleteMapping("/delete/{token}")
    public ResponseEntity<String> deleteRecordById(@PathVariable String token) {
        ResponseDTO dto = new ResponseDTO("Book Record deleted successfully", iBookServiceservice.deleteRecordById(token));
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     *  @PutMapping :-
     *            @PutMapping Annotation for mapping HTTP PUT requests onto specific handler methods.
     *            Specifically, @PutMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.PUT).
     *
     * Ability to update book data for particular id
     * @apiNote - accepts the book data in JSON format and updates the book having same bookId from database
     * @param token - token
     * @param bookDTO -  represents object of bookDTO class
     * @return - updated book information in JSON format
     */
    @PutMapping("/updateBookById/{token}")
    public ResponseEntity<String> updateRecordById(@PathVariable String token, @Valid @RequestBody BookDTO bookDTO) {
        BookContact updateRecord = iBookServiceservice.updateRecordById(token,bookDTO);
        ResponseDTO dto = new ResponseDTO(" Book Record updated successfully by Id", updateRecord);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    // Ability to call api to Update Book record by quantity
    @PutMapping("/update/{token}/{quantity}")
    public ResponseEntity<ResponseDTO> updateBooksByQuantity(@PathVariable String token,@PathVariable int quantity){
        BookContact bookData=iBookServiceservice.updataBooksByQuantity(token,quantity);
        ResponseDTO responseDTO=new ResponseDTO("updated book data succesfully",bookData);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * create a method name as getBookByName
     * Ability to get book by book name
     * @param name - book name
     * @return - book data
     */
    @GetMapping("searchByBookName/{name}")
    public ResponseEntity<ResponseDTO> getBookByName(@PathVariable("name") String name) {
        List<BookContact> listOfBooks = iBookServiceservice.getBookByName(name);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:", listOfBooks);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     * ability to get book data in ascending order
     * @return - data in ascending order
     */
    @GetMapping("/sortAsc")
    public ResponseEntity<ResponseDTO> getBooksInAscendingOrder() {
        List<BookContact> listOfBooks = iBookServiceservice.sortedListOfBooksInAscendingOrder();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:", listOfBooks);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     * ability to get book data in descending order
     * @return - data in descending order
     */
    @GetMapping("/sortDesc")
    public ResponseEntity<ResponseDTO> getBooksInDescendingOrder() {
        List<BookContact> listOfBooks = iBookServiceservice.sortedListOfBooksInDescendingOrder();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:", listOfBooks);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     * Ability to get book data by author name.
     * @param authorName - put author-name in url
     * @return - book data by author name
     */
    @GetMapping("searchByAuthorName/{authorName}")
    public ResponseEntity<ResponseDTO> getBookByAuthorName(@PathVariable("authorName") String authorName) {
        List<BookContact> listOfBooks = iBookServiceservice.getBookByAuthorName(authorName);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:", listOfBooks);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    //---------------------------API for rest template---------------------------//

    /**
     * Ability to get book data by bookId
     * @param bookId
     * @return
     */
    @GetMapping("/getBook/{bookId}")
    public BookContact getbookByIdAPI(@PathVariable int bookId){
        System.out.println("Test");
        BookContact book = iBookServiceservice.getbookByIdAPI(bookId);
        return book;
    }
}
