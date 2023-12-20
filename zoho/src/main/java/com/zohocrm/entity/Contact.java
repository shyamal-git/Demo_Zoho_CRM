package com.zohocrm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contact {
    @Id
    private String cid;
    @Column(name = "first_name", nullable = false)
    private String firstname;
    @Column(name = "last_name", nullable = false)
    private String lastname;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "mobile", nullable = false, unique = true)
    private Long mobile;
    @Column(name = "lead_type", nullable = true)
    private String leadType;
    @Column(name = "address")
    private String address;
    @Column(name = "designation")
    private String designation;
    @Column(name = "company")
    private String company;
    @Column(name = "note")
    private String note;
}
