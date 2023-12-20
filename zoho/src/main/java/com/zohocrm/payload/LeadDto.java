package com.zohocrm.payload;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LeadDto {
    private String lid;
    private String firstname;
    private String lastname;
    private String email;
    private Long mobile;
    private String leadType;
    private String address;
    private String designation;
    private String company;
    private String note;
}
