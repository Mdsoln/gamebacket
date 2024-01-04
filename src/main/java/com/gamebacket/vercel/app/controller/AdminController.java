package com.gamebacket.vercel.app.controller;

import com.gamebacket.vercel.app.dto.AccessoryRequest;
import com.gamebacket.vercel.app.dto.GamePublisher;
import com.gamebacket.vercel.app.service.impl.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/publishGame")
    public ResponseEntity<String> publishGameToServer(
            @RequestBody GamePublisher gamePublisher
    ){
        adminService.publishNewGame(gamePublisher);
        return ResponseEntity.ok("published successfully");
    }


    @PostMapping("/publishAccessory")
    public ResponseEntity<String> publishProduct(@RequestBody AccessoryRequest accessoryRequest){
        adminService.publishAccessories(accessoryRequest);
        return ResponseEntity.ok("published successfully");
    }
}
