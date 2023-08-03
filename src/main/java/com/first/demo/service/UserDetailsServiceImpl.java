//package com.first.demo.service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.first.demo.repo.UserRepository;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//  
//	@Autowired
//    private UserRepository userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        return User.builder()
//            .username(user.getUsername())
//            .password(user.getPassword())
//            .roles(user.getAuthorities())
//            .build();
//    }
//}
