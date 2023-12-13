package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfService {
	public ByteArrayInputStream generatePdf(List<User> users) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(document, out);
			document.open();
			
			Font headerFont = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD);
			Paragraph heading = new Paragraph(" This is Users Report",headerFont);
			heading.setAlignment(Element.ALIGN_CENTER);
			heading.setAlignment(Element.ALIGN_TOP);
			heading.setSpacingAfter(20f);
			float leftMargin = 46f;
			heading.setIndentationLeft(leftMargin);
			document.add(heading);
			PdfPTable table = new PdfPTable(4);
			// Add table headers
			table.addCell("ID");
			table.addCell("Name");
			table.addCell("Email");
			table.addCell("Company");		
			// Add table data
			for (User user : users) {
				table.addCell(String.valueOf(user.getId()));
				table.addCell(user.getUsername());
				table.addCell(user.getEmail());
				table.addCell(user.getCompany());
			}
			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			document.close();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}
