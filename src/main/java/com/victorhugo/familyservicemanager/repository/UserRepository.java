package com.victorhugo.familyservicemanager.repository;

import com.victorhugo.familyservicemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
