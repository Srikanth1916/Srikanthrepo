package com.skyhive.veneer.common.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LocationTime {
    private Location location;
    private String extraInstructions;
    private boolean remote;
    private String type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int startHour;
    private String duration;
    private int quantity;
}
