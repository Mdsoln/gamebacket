package com.gamebacket.vercel.app.service.security;

import com.gamebacket.vercel.app.entity.Customer;
import com.gamebacket.vercel.app.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer availableUser = userRepo.findByUserEmail(username);
        if (availableUser != null){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+availableUser.getRoles()));
            return  new org.springframework.security.core.userdetails.User(
                    availableUser.getUserEmail(),
                    availableUser.getPasswords(),
                    authorities
            );
        }

        throw new UsernameNotFoundException("Incorrect username or password");
    }
}
