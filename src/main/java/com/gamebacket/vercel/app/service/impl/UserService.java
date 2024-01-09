package com.gamebacket.vercel.app.service.impl;

import com.gamebacket.vercel.app.repo.UserRepo;
import com.gamebacket.vercel.app.service.inter.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {
    private final UserRepo userRepo;
}
