package com.gamebacket.vercel.app.controller;

import com.gamebacket.vercel.app.dto.AuthenticationRequest;
import com.gamebacket.vercel.app.dto.AuthenticationResponse;
import com.gamebacket.vercel.app.dto.User;
import com.gamebacket.vercel.app.service.impl.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/base")
@RequiredArgsConstructor
public class BaseController {

    private final BaseService baseService;


    @PostMapping("/createAccount")
    public ResponseEntity<AuthenticationResponse> userGameBacketAccount(
            @RequestBody User user
    ){
       return ResponseEntity.ok(baseService.createNewAccount(user));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(baseService.authenticate(request));
    }


    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable("userId") Long userId){
        baseService.deleteUserById(userId);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }
}
// TODO: 29/02/2024 sign up/sign with google or other accounts like GitHub