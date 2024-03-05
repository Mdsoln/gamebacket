package com.gamebacket.vercel.app.service.inter;

import com.gamebacket.vercel.app.dto.AuthenticationRequest;
import com.gamebacket.vercel.app.dto.AuthenticationResponse;
import com.gamebacket.vercel.app.dto.User;

public interface BaseInterface {
    AuthenticationResponse createNewAccount(User user);

    void deleteUserById(Long userId);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
