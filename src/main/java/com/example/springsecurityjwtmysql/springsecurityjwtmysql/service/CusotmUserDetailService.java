package com.example.springsecurityjwtmysql.springsecurityjwtmysql.service;

import com.example.springsecurityjwtmysql.springsecurityjwtmysql.domain.User;
import com.example.springsecurityjwtmysql.springsecurityjwtmysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CusotmUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw  new UsernameNotFoundException("User not found in the database");
        else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role-> authorities.add(new SimpleGrantedAuthority(role.getName())));
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authorities);
        }
    }
}
