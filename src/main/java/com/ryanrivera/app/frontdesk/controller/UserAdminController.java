package com.ryanrivera.app.frontdesk.controller;

import com.ryanrivera.app.frontdesk.model.CreateUserRequest;
import com.ryanrivera.app.frontdesk.model.CreateUserResponse;
import com.ryanrivera.app.frontdesk.model.domain.AppUser;
import com.ryanrivera.app.frontdesk.service.ManagerAppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAdminController {

    private final ManagerAppUserService appUserService;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest userRequest){
        final AppUser userResponse = appUserService.createAppUser(userRequest);
        if(userResponse.getAppUserId() == null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        CreateUserResponse response = CreateUserResponse.builder().user(userResponse).build();
        return ResponseEntity.ok(response);
    }
}
