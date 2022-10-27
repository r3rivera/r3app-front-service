package com.ryanrivera.app.frontdesk.model;

import com.ryanrivera.app.frontdesk.model.domain.AppUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private AppUser user;
}
