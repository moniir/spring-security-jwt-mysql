package com.example.springsecurityjwtmysql.springsecurityjwtmysql.service;


import com.example.springsecurityjwtmysql.springsecurityjwtmysql.domain.Role;
import com.example.springsecurityjwtmysql.springsecurityjwtmysql.domain.User;
import com.example.springsecurityjwtmysql.springsecurityjwtmysql.repository.RoleRepository;
import com.example.springsecurityjwtmysql.springsecurityjwtmysql.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        if(user != null && role != null)
                user.getRoles().add(role);

        //transactional, so no need to call save method
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
