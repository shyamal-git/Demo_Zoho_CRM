package com.zohocrm.service.impl;

import com.zohocrm.entity.Contact;
import com.zohocrm.entity.Lead;
import com.zohocrm.exception.LeadExist;
import com.zohocrm.payload.ContactDto;
import com.zohocrm.payload.LeadDto;
import com.zohocrm.repository.ContactRepostiory;
import com.zohocrm.repository.LeadRepostiory;
import com.zohocrm.service.ContactService;
import com.zohocrm.service.LeadService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {
     private LeadRepostiory leadRepo;
     private ContactRepostiory contactRepo;
     private ModelMapper modelMapper;

    public ContactServiceImpl(LeadRepostiory leadRepo, ContactRepostiory contactRepo, ModelMapper modelMapper) {
        this.leadRepo = leadRepo;
        this.contactRepo = contactRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ContactDto createContact(String leadId) {
        Lead lead = leadRepo.findById(leadId).orElseThrow(
                () -> new LeadExist("Lead with this id does not exist: " + leadId)
        );
        Contact contact = ConvertLeadToContact(lead);
        String cid = UUID.randomUUID().toString();
        contact.setCid(cid);
        Contact saveContact = contactRepo.save(contact);
        if (saveContact.getCid()!=null){
            leadRepo.deleteById(lead.getLid());
        }
        return mapToDto(saveContact);
    }
        ContactDto mapToDto(Contact contact){
            ContactDto contactDto = modelMapper.map(contact, ContactDto.class);
            return contactDto;
        }
        Contact ConvertLeadToContact(Lead lead){
            Contact contact = modelMapper.map(lead, Contact.class);
            return contact;
        }

}
