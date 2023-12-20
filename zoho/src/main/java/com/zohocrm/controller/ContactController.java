package com.zohocrm.controller;

import com.zohocrm.payload.ContactDto;
import com.zohocrm.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @PostMapping("{leadId}")
    public ResponseEntity<ContactDto> createContact(@PathVariable String leadId){
        ContactDto dto = contactService.createContact(leadId);
        return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
