package com.user.User.service;

import com.user.User.dto.ResponseDTO;
import com.user.User.dto.UserDTO;
import com.user.User.dto.UserLoginDTO;
import com.user.User.model.UserContact;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService
{
    String addUser(UserDTO userDTO);

    List<UserContact> getAllUsers();

    ResponseDTO loginUser(UserLoginDTO userLoginDTO);

    String forgotPassword(String email, String password);

    Object getUserByEmailId(String email);

    List<UserContact> getAllUserDataByToken(String token);

    UserContact updateRecordById(int contactId, UserDTO userDTO);
    ResponseEntity<ResponseDTO> verify(String token);
    UserContact getByIdAPI(int contactId);
}
