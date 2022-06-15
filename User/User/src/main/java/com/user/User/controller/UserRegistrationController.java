package com.user.User.controller;

import com.user.User.dto.ResponseDTO;
import com.user.User.dto.UserDTO;
import com.user.User.dto.UserLoginDTO;
import com.user.User.model.UserContact;
import com.user.User.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 *   @RestController :-
 *           @RestController is used for making restful web services with the help of the @RestController annotation.
 *           This annotation is used at the class level and allows the class to handle the requests made by the client
 *  @RequestMapping :-
 *           @RequestMapping used to map web requests onto specific handler classes and/or handler methods.
 *           RequestMapping can be applied to the controller class as well as methods
 *
 * - Created controller so that we can perform rest api calls
 */
@RestController
@RequestMapping("/userregistration")
@Slf4j
public class UserRegistrationController
{
    /**
     *  @AutoMapping :-
     *          @Autowiring feature of spring framework enables you to inject the object dependency implicitly.
     *          It internally uses setter or constructor injection.
     *
     * - Autowired  IUserService interface, so we can inject its dependency here
     */
    @Autowired
    private IUserService Iuserregistrationservice;

    /**
     * - Ability to save book details to repository
     * @apiNote- accepts the book data in JSON format and stores it in DB
     * @param userDTO - book data
     * @return :- responseDTO
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addUser(@Valid @RequestBody UserDTO userDTO){
        String newUser= Iuserregistrationservice.addUser(userDTO);
        ResponseDTO responseDTO=new ResponseDTO("User Registered Successfully",newUser);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }


    /**
     * - Ability to get all book' data by findAll() method
     * @return :- showing all data
     */
    @GetMapping( "/getAll")
    public ResponseEntity<String> getAllUser() {
        List<UserContact> listOfUsers = Iuserregistrationservice.getAllUsers();
        ResponseDTO dto = new ResponseDTO("User retrieved successfully (:",listOfUsers);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    /**
     * ability to user login
     * @param userLoginDTO - email and password
     * @return - login successfully msg show
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseEntity<ResponseDTO>(Iuserregistrationservice.loginUser(userLoginDTO),HttpStatus.OK);
    }

    /**
     *Ability to call api to forgotPassword
     * @param email
     * @param password
     * @return
     */

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestParam String email, @RequestParam String password) {
        String resp = Iuserregistrationservice.forgotPassword(email,password);
        return new ResponseEntity(resp, HttpStatus.OK);
    }

    /**
     * get data for particular emailId
     * Ability to get a record by emailId
     */
    @GetMapping("/getByEmailId/{emailId}")
    public ResponseEntity<ResponseDTO> getUserByEmailId(@PathVariable("emailId") String email) {
        return new ResponseEntity<ResponseDTO>( new
                ResponseDTO("Get User Data by Email",
                Iuserregistrationservice.getUserByEmailId(email)), HttpStatus.OK);
    }

    /**
     * update  record data by id
     * @apiNote accepts the user data in JSON format and updates the user data having same Id from database
     * @param contactId - represents user id
     * @param userDTO - represents object of UserDto class
     * @return	updated user information in JSON format
     */
    @PutMapping("/update/{contactId}")
    public ResponseEntity<String> updateRecordById(@PathVariable int  contactId,@Valid @RequestBody UserDTO userDTO){
        UserContact entity = Iuserregistrationservice.updateRecordById(contactId,userDTO);
        ResponseDTO dto = new ResponseDTO("User Record updated successfully",entity);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }

    /**
     * Ability to call api to verify data by token
     */

    @GetMapping("/verify/{token}")
    public ResponseEntity<ResponseDTO> verifyUser(@PathVariable String token) {
        return Iuserregistrationservice.verify(token);
    }

    //--------------------API Calls for RestTemplate----------------------------//

    /**
     * Ability to call api to findById by Contact id
     * @param contactId
     * @return
     */
    @GetMapping("/findById/{contactId}")
    public UserContact getByIdAPI(@PathVariable int contactId){
        UserContact user =  Iuserregistrationservice.getByIdAPI(contactId);
        return user;
    }

}
