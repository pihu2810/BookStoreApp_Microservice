package com.user.User.model;


import com.user.User.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "userregistration_DB")
@Data

public class UserContact
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "User_Id")
    private int contactId;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    private String email;
    private String address;
    private String password;
    public boolean verified;




    public UserContact(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.address = userDTO.getAddress();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
    }

    public UserContact(int contactId, UserDTO userDTO) {
        this.contactId = contactId;
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.address = userDTO.getAddress();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
    }

    public UserContact(String email, UserDTO userDTO) {
        this.email = email;
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.address = userDTO.getAddress();
        this.password = userDTO.getPassword();
    }

    public UserContact() {

    }

    public void updateContact(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.password = userDTO.getPassword();
    }

}
