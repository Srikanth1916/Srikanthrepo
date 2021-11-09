package com.skyhive.veneer.common.entities;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Compensation {
    private String type;
    private double payRate;
    private String currency;
    private String duration;
    private int quantity;
    private int hoursPerWeek;
    private boolean benefits;
    private boolean hidden;
    private String relocate;

}
