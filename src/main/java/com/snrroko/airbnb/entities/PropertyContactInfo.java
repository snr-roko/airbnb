package com.snrroko.airbnb.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PropertyContactInfo {

    private String address;

    private String phone;

    private String email;
}
