package com.ryanrivera.app.frontdesk.service;

import com.ryanrivera.app.frontdesk.model.CreateUserRequest;
import com.ryanrivera.app.frontdesk.model.domain.AppUser;
import com.ryanrivera.app.frontdesk.model.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ManagerAppUserService {

    private static Map<UUID, CreateUserRequest> mapUser = new HashMap<>();


    public AppUser createAppUser(CreateUserRequest request){
        final UUID id = UUID.randomUUID();
        mapUser.put(id, request);
        return transformUser(id, request.getUserInfo());
    }

    private AppUser transformUser(UUID id, User user){
        final AppUser userResponse = new AppUser();
        userResponse.setAppUserId(id);
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        return userResponse;
    }

    public AppUser getAppUser(UUID appUserId){
        if(mapUser.containsKey(appUserId)){
            final CreateUserRequest user = mapUser.get(appUserId);
            return transformUser(appUserId,user.getUserInfo());
        }
        return null;
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
