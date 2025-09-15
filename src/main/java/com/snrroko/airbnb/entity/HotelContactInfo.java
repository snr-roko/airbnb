package com.snrroko.airbnb.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class HotelContactInfo {

    private String address;

    private String phone;

    private String location;

    private String email;
}
