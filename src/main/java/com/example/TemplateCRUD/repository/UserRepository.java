package com.example.TemplateCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.TemplateCRUD.models.*;
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
