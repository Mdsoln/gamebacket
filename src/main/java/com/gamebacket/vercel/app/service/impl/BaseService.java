package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.constants.UserRole;
import com.gamebacket.vercel.app.dto.User;
import com.gamebacket.vercel.app.entity.Contact;
import com.gamebacket.vercel.app.entity.Customer;
import com.gamebacket.vercel.app.exc.EmptyOrNullValueException;
import com.gamebacket.vercel.app.repo.ContactRepo;
import com.gamebacket.vercel.app.repo.UserRepo;
import com.gamebacket.vercel.app.service.inter.BaseInterface;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseService implements BaseInterface {
    private final UserRepo userRepo;
    private final ContactRepo contactRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createNewAccount(User user) {
        if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getFirst_name()) || StringUtils.isEmpty(user.getLast_name())
                || StringUtils.isEmpty(user.getPassword())
        ){
           throw new EmptyOrNullValueException("All fields are required");
        }

        if (user.getPhones() == null || user.getPhones().isEmpty()){
           throw new EmptyOrNullValueException("At least one phone number is required");
        }

        Customer checkEmail = userRepo.findByUserEmail(user.getEmail());
        if (checkEmail != null){
            throw new EmptyOrNullValueException("Already exists a user with email: "+user.getEmail());
        }
        Customer customer = new Customer();
        customer.setFull_name(user.getFirst_name()+" "+user.getLast_name());
        customer.setUserEmail(user.getEmail());
        customer.setPasswords(passwordEncoder.encode(user.getPassword()));
        if (user.getEmail().endsWith("@gamebacket.com") || user.getFirst_name().contains("Admin")){
         customer.setRoles(UserRole.ADMIN);
        }
        else{
         customer.setRoles(UserRole.USER);
        }

        userRepo.save(customer);

        List<Contact> contacts = new ArrayList<>();
        for (String phone: user.getPhones()){
            Contact contact = Contact
                    .builder()
                    .phone_numbers(phone)
                    .customer(customer)
                    .build();
            contacts.add(contact);
        }
        contactRepo.saveAll(contacts);

        customer.setContact(contacts);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepo.deleteById(userId);
    }
}
