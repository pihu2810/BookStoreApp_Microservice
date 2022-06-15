package com.user.User.service;

import com.user.User.Util.EmailSenderService;
import com.user.User.Util.TokenUtility;
import com.user.User.dto.ResponseDTO;
import com.user.User.dto.UserDTO;
import com.user.User.dto.UserLoginDTO;
import com.user.User.exception.BookStoreException;
import com.user.User.model.UserContact;
import com.user.User.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService
{
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    EmailSenderService mailService;

    @Autowired
    TokenUtility util;

    /**
     * create a method name as addUser
     * Ability to save user details to repository
     * */
    @Override
    public String addUser(UserDTO userDTO) {
        UserContact newUser= new UserContact(userDTO);
        userRegistrationRepository.save(newUser);
        String token = util.createToken(newUser.getContactId());
        mailService.sendEmail(newUser.getEmail(), "Test Email", "Registered SuccessFully, hii: "
                +newUser.getFirstName()+"Please Click here to get data-> "
                +"http://localhost:9005/user/getBy/"+token);
        return token;
    }

    /**
     * create a method name as getAllUser
     * */
    @Override
    public List<UserContact> getAllUsers() {
        List<UserContact> getUsers= userRegistrationRepository.findAll();
        return getUsers;
    }

    /**
     * create a method name as loginUser
     * */
    @Override
    public ResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        ResponseDTO dto = new ResponseDTO();
        Optional<UserContact> login = userRegistrationRepository.findByEmailId(userLoginDTO.getEmail());
        if(login.isPresent()){
            //String pass = login.get().getPassword();
            if(login.get().getPassword().equals(userLoginDTO.getPassword())){
                dto.setMessage("login successful ");
                dto.setData(login.get());
                return dto;
            }

            else {
                dto.setMessage("Sorry! login is unsuccessful");
                dto.setData("Wrong password");
                return dto;
            }
        }
        return new ResponseDTO("User not found!","Wrong email");
    }

    /**
     * create a method name as forgotPassword by email and password
     * Ability to save user details to repository
     * */
    @Override
    public String forgotPassword(String email, String password) {
        Optional<UserContact> isUserPresent = userRegistrationRepository.findByEmailId(email);

        if(!isUserPresent.isPresent()) {
            throw new BookStoreException("Book record does not found");
        }
        else {
            UserContact user = isUserPresent.get();
            user.setPassword(password);
            userRegistrationRepository.save(user);
            return "Password updated successfully";
        }

    }

    /**
     * create a method name as getUserByEmailId by email
     * */
    @Override
    public Object getUserByEmailId(String email) {

        return userRegistrationRepository.findByEmailId(email);
    }

    /**
     * create a method name as getAllUserDataByToken by token
     * */
    @Override
    public List<UserContact> getAllUserDataByToken(String token) {
        int id=util.decodeToken(token);
        Optional<UserContact> isContactPresent=userRegistrationRepository.findById(id);
        if(isContactPresent.isPresent()) {
            List<UserContact> listOfUsers=userRegistrationRepository.findAll();
            mailService.sendEmail("vishakakadam19@gmail.com", "Test Email", "Get your data with this token, hii: "
                    +isContactPresent.get().getEmail()+"Please Click here to get data-> "
                    +"http://localhost:9005/user/getAll/"+token);
            return listOfUsers;
        }else {
            System.out.println("Exception ...Token not found!");
            return null;	}
    }

    /**
     * create a method name as updateRecordById by id
     * */
    @Override
    public UserContact updateRecordById(int  contactId, UserDTO userDTO) {

        Optional<UserContact> addressBook = userRegistrationRepository.findById(contactId);
        if(addressBook.isPresent()) {
            UserContact newBook = new UserContact(userDTO);
            userRegistrationRepository.save(newBook);
            return newBook;

        }
        throw new BookStoreException(HttpStatus.NOT_FOUND,"User Details for id not found");
    }

    /**
     * create a method name as verify by token
     * Ability to save user details to repository
     * */
    @Override
    public ResponseEntity<ResponseDTO> verify(String token) {
        Optional<UserContact> user=userRegistrationRepository.findById(Math.toIntExact(util.decodeToken(token)));
        if (user.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO("ERROR: Invalid token", null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
        }
        user.get().setVerified(true);
        userRegistrationRepository.save(user.get());
        ResponseDTO responseDTO = new ResponseDTO(" The user has been verified ", user);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //-------------------------------services for Rest Template API-----------------------------------//

    /**
     * create a method name as getByIdAPI  by contactId
     * @param contactId
     * @return
     */
    @Override
    public UserContact getByIdAPI(int contactId) {
        Optional<UserContact> user = userRegistrationRepository.findById(contactId);
        if(user.isEmpty()) {
            throw new BookStoreException(HttpStatus.NOT_FOUND,"There are no users with given id");
        }
        return user.get();
    }
}
