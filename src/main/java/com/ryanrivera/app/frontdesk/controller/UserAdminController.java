package com.ryanrivera.app.frontdesk.controller;

import com.ryanrivera.app.frontdesk.model.CreateUserRequest;
import com.ryanrivera.app.frontdesk.model.UserResponse;
import com.ryanrivera.app.frontdesk.model.domain.AppUser;
import com.ryanrivera.app.frontdesk.service.ManagerAppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAdminController {

    private final ManagerAppUserService appUserService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest userRequest){
        final AppUser appUser = appUserService.createAppUser(userRequest);
        if(appUser.getAppUserId() == null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        UserResponse response = UserResponse.builder().user(appUser).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{appUserId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("appUserId") UUID appUserId){
        final AppUser appUser = appUserService.getAppUser(appUserId);
        if(appUser == null || appUser.getAppUserId() == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        UserResponse response = UserResponse.builder().user(appUser).build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{appUserId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("appUserId") UUID appUserId){
        final boolean isDeleted = appUserService.deleteAppUser(appUserId);
        return ResponseEntity.ok(isDeleted);
    }

}
