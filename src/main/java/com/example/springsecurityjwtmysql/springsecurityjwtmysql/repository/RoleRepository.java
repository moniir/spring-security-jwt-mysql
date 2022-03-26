package com.example.springsecurityjwtmysql.springsecurityjwtmysql.repository;

import com.example.springsecurityjwtmysql.springsecurityjwtmysql.domain.Role;
import com.example.springsecurityjwtmysql.springsecurityjwtmysql.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}

