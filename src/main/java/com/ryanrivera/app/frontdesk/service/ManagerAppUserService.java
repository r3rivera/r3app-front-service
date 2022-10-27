package com.ryanrivera.app.frontdesk.service;

import com.ryanrivera.app.frontdesk.model.CreateUserRequest;
import com.ryanrivera.app.frontdesk.model.domain.AppUser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ManagerAppUserService {

    private static Map<UUID, CreateUserRequest> mapUser = new HashMap<>();


    public AppUser createAppUser(CreateUserRequest request){
        final UUID id = UUID.randomUUID();

        final AppUser userResponse = new AppUser();
        userResponse.setAppUserId(id);
        userResponse.setFirstName(request.getUserInfo().getFirstName());
        userResponse.setLastName(request.getUserInfo().getLastName());
        return userResponse;
    }

    public boolean deleteAppUser(UUID appUserId){
        if(mapUser.containsKey(appUserId)){
            CreateUserRequest user = mapUser.remove(appUserId);
            if(user == null){
                return false;
            }
        }
        return true;
    }
}
