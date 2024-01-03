package com.gamebacket.vercel.app.controller;

import com.gamebacket.vercel.app.dto.User;
import com.gamebacket.vercel.app.service.impl.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/base")
@RequiredArgsConstructor
public class BaseController {

    private final BaseService baseService;

    @PostMapping("/createAccount")
    public ResponseEntity<String> userGameBacketAccount(
            @RequestBody User user
    ){
       baseService.createNewAccount(user);
       return ResponseEntity.ok("Saved successfully");
    }

}
