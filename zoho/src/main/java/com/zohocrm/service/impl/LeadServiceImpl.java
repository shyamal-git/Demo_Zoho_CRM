package com.zohocrm.service.impl;

import com.zohocrm.entity.Lead;
import com.zohocrm.exception.LeadExist;
import com.zohocrm.payload.LeadDto;
import com.zohocrm.repository.LeadRepostiory;
import com.zohocrm.service.LeadService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LeadServiceImpl implements LeadService {

    private LeadRepostiory leadRepo;
    private ModelMapper modelMapper;

    public LeadServiceImpl(LeadRepostiory leadRepo, ModelMapper modelMapper) {
        this.leadRepo = leadRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public LeadDto createLead(LeadDto leadDto) {
        boolean EmailExists = leadRepo.existsByEmail(leadDto.getEmail());
        boolean MobileExists = leadRepo.existsByMobile(leadDto.getMobile());
        if(EmailExists){
            throw new LeadExist("Email exist- "+leadDto.getEmail());
        }if(MobileExists){
            throw new LeadExist("Mobile exist- "+leadDto.getMobile());
        }
        Lead lead = mapToEntity(leadDto);
        String lid = UUID.randomUUID().toString();
        lead.setLid(lid);
        Lead savedLead = leadRepo.save(lead);
        LeadDto dto = mapToDto(savedLead);
        return dto;
    }
    @Override
    public void deleteLeadById(String lid) {
        leadRepo.findById(lid).orElseThrow(
                ()->new LeadExist("Lead with this id not present - " + lid)
        );
        leadRepo.deleteById(lid);
    }

    @Override
    public List<LeadDto> getAllLeads() {
        List<Lead> leads = leadRepo.findAll();
        return leads.stream().map(lead -> mapToDto(lead)).collect(Collectors.toList());
    }

    @Override
    public List<Lead> getLeadsExcelReports() {
        return leadRepo.findAll();
    }

    Lead mapToEntity(LeadDto leadDto){
        Lead lead = modelMapper.map(leadDto, Lead.class);
        return lead;

    }
    LeadDto mapToDto(Lead lead){
        LeadDto leadDto = modelMapper.map(lead, LeadDto.class);
        return leadDto;

    }

}
