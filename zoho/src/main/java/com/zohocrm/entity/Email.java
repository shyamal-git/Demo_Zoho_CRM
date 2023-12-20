package com.zohocrm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "emails")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Email {
    @Id
    @Column(name = "eid")
    private String eid;
    @Column(name = "recipient")
    private String to;
    @Column(name = "subject")
    private String subject;
    @Column(name = "message")
    private String message;
}
