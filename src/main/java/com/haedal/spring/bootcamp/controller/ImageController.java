package com.haedal.spring.bootcamp.controller;

import com.haedal.spring.bootcamp.domain.User;
import com.haedal.spring.bootcamp.service.AuthService;
import com.haedal.spring.bootcamp.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {
    private final AuthService authService;
    private final ImageService imageService;

    @Autowired
    public ImageController(AuthService authService, ImageService imageService) {
        this.authService = authService;
        this.imageService = imageService;
    }

    @PutMapping("/users/image")
    public ResponseEntity<String> updateUserImage(@RequestParam("image") MultipartFile image, HttpServletRequest request) throws IOException {
        User currentUser = authService.getCurrentUser(request);

        String savedImageName = imageService.updateUserImage(currentUser, image);
        return ResponseEntity.ok(savedImageName);
    }
}
