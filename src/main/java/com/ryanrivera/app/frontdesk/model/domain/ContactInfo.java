package com.ryanrivera.app.frontdesk.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "value")
public class ContactInfo {
    private CommunicationType type;
    private String value;
}
