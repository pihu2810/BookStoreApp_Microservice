package com.user.User.repository;

import com.user.User.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<UserContact, Integer>
{
    @Query(value = "select * from book_app.userregistration_DB where email = :email", nativeQuery = true)
    Optional<UserContact> findByEmailId(String email);
}
