package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.constants.UserRole;
import com.gamebacket.vercel.app.dto.User;
import com.gamebacket.vercel.app.entity.Contact;
import com.gamebacket.vercel.app.entity.Customer;
import com.gamebacket.vercel.app.exc.EmptyOrNullValueStorageException;
import com.gamebacket.vercel.app.repo.ContactRepo;
import com.gamebacket.vercel.app.repo.UserRepo;
import com.gamebacket.vercel.app.service.inter.BaseInterface;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class BaseService implements BaseInterface {
    private final UserRepo userRepo;
    private final ContactRepo contactRepo;


    @Override
    public void createNewAccount(User user) {
        if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getFirst_name()) || StringUtils.isEmpty(user.getLast_name())
                || StringUtils.isEmpty(user.getPassword())
        ){
           throw new EmptyOrNullValueStorageException("All fields are required");
        }

        if (user.getPhones() == null || user.getPhones().isEmpty()){
           throw new EmptyOrNullValueStorageException("At least one phone number is required");
        }

        Customer checkEmail = userRepo.findByUserEmail(user.getEmail());
        if (checkEmail != null){
            throw new EmptyOrNullValueStorageException("Already exists a user with email: "+user.getEmail());
        }
        Customer customer = new Customer();
        customer.setFull_name(user.getFirst_name()+" "+user.getLast_name());
        customer.setUserEmail(user.getEmail());
        customer.setPasswords(user.getPassword());
        if (user.getEmail().endsWith("@gamebacket.com") || user.getFirst_name().contains("Admin")){
         customer.setRoles(UserRole.ADMIN);
        }
        else{
         customer.setRoles(UserRole.USER);
        }

        userRepo.save(customer);

        /*List<Contact> contacts = new ArrayList<>();
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
        */

        /*
        saving phone number with either null alternative number or provided number
        * */
        Contact contact = Contact
                .builder()
                .phone_numbers(user.getPhones().get(0))
                .alternativeNo(user.getPhones().size() > 1 ? user.getPhones().get(1): null)
                .customer(customer)
                .build();
        contactRepo.save(contact);

        customer.setContact(Collections.singletonList(contact));
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepo.deleteById(userId);
    }
}
