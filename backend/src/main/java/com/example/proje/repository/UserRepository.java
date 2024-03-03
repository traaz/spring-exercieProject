package com.example.proje.repository;

import com.example.proje.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name); //userDetails istiyor bu metodu
}
