package com.zohocrm.service;

import com.zohocrm.entity.Lead;
import com.zohocrm.payload.LeadDto;

import java.util.List;

public interface LeadService {
    LeadDto createLead(LeadDto leadDto);

    void deleteLeadById(String lid);

    List<LeadDto> getAllLeads();

    List<Lead> getLeadsExcelReports();
}
