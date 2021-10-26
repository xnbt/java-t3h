package com.example.jpa.repository;

import com.example.jpa.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeevkumarsingh on 20/11/17.
 */
@Repository
@Scope("prototype")
public interface UserRepository extends JpaRepository<User, Long> {

}
