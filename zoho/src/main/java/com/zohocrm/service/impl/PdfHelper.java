package com.zohocrm.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import com.zohocrm.entity.Lead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfHelper {

    private static Logger logger = LoggerFactory.
            getLogger(PdfHelper.class);

    public static ByteArrayInputStream leadsPDFReport
            (List<Lead> leads) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Add Text to PDF file ->
            Font font = FontFactory
                    .getFont(FontFactory.COURIER, 14,BaseColor.BLACK);
            Paragraph para = new Paragraph("Leads Table", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(10);
            // Add PDF Table Header ->
            Stream.of("Lead Id", "First Name", "Last Name", "Email","Mobile", "Lead Type",
                            "Address", "Designation","Company","Note")
                    .forEach(headerTitle ->
                    {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.
                                getFont(FontFactory.HELVETICA_BOLD, 10);
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_LEFT);
                        header.setBorderWidth(1);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);

                    });

            for (Lead lead : leads) {
                PdfPCell idCell = new PdfPCell(new Phrase(lead.getLid().
                        toString()));
                idCell.setPaddingLeft(1);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase
                        (lead.getFirstname()));
                firstNameCell.setPaddingLeft(1);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getLastname())));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                lastNameCell.setPaddingLeft(1);
                table.addCell(lastNameCell);

                PdfPCell emailCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getEmail())));
                emailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                emailCell.setPaddingLeft(1);
                table.addCell(emailCell);

                PdfPCell mobileCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getMobile())));
                mobileCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                mobileCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                mobileCell.setPaddingLeft(1);
                table.addCell(mobileCell);

                PdfPCell leadTypeCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getLeadType())));
                leadTypeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                leadTypeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                leadTypeCell.setPaddingLeft(1);
                table.addCell(leadTypeCell);

                PdfPCell addressCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getAddress())));
                addressCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                addressCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                addressCell.setPaddingLeft(1);
                table.addCell(addressCell);

                PdfPCell designationCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getDesignation())));
                designationCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                designationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                designationCell.setPaddingLeft(1);
                table.addCell(designationCell);

                PdfPCell companyCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getCompany())));
                companyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                companyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                companyCell.setPaddingLeft(1);
                table.addCell(companyCell);

                PdfPCell noteCell = new PdfPCell(new Phrase
                        (String.valueOf(lead.getNote())));
                noteCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                noteCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                noteCell.setPaddingRight(1);
                table.addCell(noteCell);
            }
            document.add(table);

            document.close();
        } catch (DocumentException e) {
            logger.error(e.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}