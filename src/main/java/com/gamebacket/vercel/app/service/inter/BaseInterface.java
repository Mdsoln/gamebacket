package com.gamebacket.vercel.app.service.inter;

import com.gamebacket.vercel.app.dto.User;

public interface BaseInterface {
    void createNewAccount(User user);

    void deleteUserById(Long userId);

}
