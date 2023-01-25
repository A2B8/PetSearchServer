package com.base.PetSearchServer.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AdRequest {
    private final Boolean isLost;
    private final String animal;
    private final String gender;
    private final String date;
    private final String address;
    private final float geoLat;
    private final float geoLon;
    private final String description;
}
