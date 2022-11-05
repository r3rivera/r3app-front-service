package com.ryanrivera.app.frontdesk.service;

import com.ryanrivera.app.frontdesk.config.AppConfig;
import com.ryanrivera.app.frontdesk.exception.UserCreationException;
import com.ryanrivera.app.frontdesk.model.CreateUserRequest;
import com.ryanrivera.app.frontdesk.model.domain.AppUser;
import com.ryanrivera.app.frontdesk.model.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerAppUserService {
    private static Map<UUID, CreateUserRequest> mapUser = new HashMap<>();
    private final AppConfig appConfig;


    public AppUser createAppUser(CreateUserRequest request){

        if(request.getUserInfo() == null){
            throw new UserCreationException(appConfig.getAppCode(), "ERRRRRRR");
        }
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
