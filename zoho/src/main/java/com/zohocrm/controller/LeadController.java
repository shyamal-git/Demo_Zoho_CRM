package com.zohocrm.controller;

import com.zohocrm.entity.Lead;
import com.zohocrm.payload.EmailDto;
import com.zohocrm.payload.LeadDto;
import com.zohocrm.repository.LeadRepostiory;
import com.zohocrm.service.LeadService;
import com.zohocrm.service.impl.ExcelHelper;
import com.zohocrm.service.impl.PdfHelper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/leads")
public class LeadController {
    private LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    //http://localhost:8080/api/leads
    @PostMapping
    public ResponseEntity<LeadDto> createLead(@RequestBody LeadDto leadDto){
        LeadDto dto = leadService.createLead(leadDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/leads/lid
    @DeleteMapping("/{lid}")
    public ResponseEntity<String> deleteLeadbyId(@PathVariable String lid){
        leadService.deleteLeadById(lid);
        return new ResponseEntity<>("Lead is Deleted", HttpStatus.OK);

    }

    //http://localhost:8080/api/leads/list
    @GetMapping("/list")
    public ResponseEntity<List<LeadDto>> getAllLeads(){
        List<LeadDto> leads = leadService.getAllLeads();
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
    public RequestEntity<?> EmailSend(@RequestBody EmailDto emailDto){
        return null;
    }


    //http://localhost:8080/api/leads/excelReports
    @GetMapping("/excelReports")
    public ResponseEntity<Resource> getLeadsExcelReports(){
        List<Lead> leads = leadService.getLeadsExcelReports();
        ByteArrayInputStream leadReports = ExcelHelper.leadsToExcel(leads);
        String filename = "Lead_Reports.xlsx";
        InputStreamResource file = new InputStreamResource(leadReports);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    //http://localhost:8080/api/leads/leadsReportPdf
    @GetMapping(value = "/leadsReportPdf", produces =
            MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> leadsPDFReport()
            throws IOException {
        List<Lead> leads = leadService.getLeadsExcelReports();

        ByteArrayInputStream pdfReport = PdfHelper
                .leadsPDFReport(leads);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=employees.pdf");

        return ResponseEntity.ok().headers(headers).contentType
                        (MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfReport));
    }

    }
