package com.example.basicaut.model.serveis;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ElMeuUserDetailService implements UserDetailsService {
    private ServeiUsuari serveiUsuariUserDetails;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return serveiUsuariUserDetails.consultarPerUsername(username);
    }
}
