package com.zohocrm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactDto {
    private String cid;
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
