package com.gamebacket.vercel.app.controller;

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
    public ResponseEntity<String> userGameBacketAccount(
            @RequestBody User user
    ){
       baseService.createNewAccount(user);
       return ResponseEntity.ok("created successfully");
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable("userId") Long userId){
        baseService.deleteUserById(userId);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }
}
