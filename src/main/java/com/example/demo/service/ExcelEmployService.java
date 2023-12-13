package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Repository.ExcelEmployeeRepository;
import com.example.demo.model.Excelemploy;

@Service
@CrossOrigin
public class ExcelEmployService {
	@Autowired
	private ExcelEmployeeRepository excelemployrepo;
	@Autowired
	private EmailService emailService;
	private String storedRecipientEmail;
	public void processRecipientEmail(String recipientEmail) {
        System.out.println("Received recipientEmail: " + recipientEmail);
        this.storedRecipientEmail = recipientEmail;
	}
	public String getStoredRecipientEmail() {
        return storedRecipientEmail;
    }

	private List<String> warnings = new ArrayList<>();
	

	// This method is used to store all the warnings
	public List<String> getWarnings() {
		// Create a copy of the warnings
		List<String> currentWarnings = new ArrayList<>(warnings);
		// Clear the warnings after retrieving them
		warnings.clear();
		return currentWarnings;
	}

	// To save the Excel file with specific validations
	public void saveDataFromExcelFile(MultipartFile file) throws Exception {
		Logger logger = LoggerFactory.getLogger(ExcelEmployService.class);
		try (InputStream inputStream = file.getInputStream()) {
			logger.warn("A warning message");
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.rowIterator();
			if (!rowIterator.hasNext()) {
				logger.error("The Excel sheet is empty.");
//				throw new Exception("The Excel sheet is empty.");
				warnings.add("The Excel sheet is empty");
			}

			// Check the header row for order and format
			Row headerRow = rowIterator.next();
			Cell headerCell0 = headerRow.getCell(0);
			Cell headerCell1 = headerRow.getCell(1);
			Cell headerCell2 = headerRow.getCell(2);
			Cell headerCell3 = headerRow.getCell(3);
			Cell headerCell4 = headerRow.getCell(4);

			if (headerCell0 == null || headerCell1 == null || headerCell2 == null || headerCell3 == null
					|| headerCell4 == null) {
				warnings.add("Header row does not contain all required columns.");
			}

			if (!"FirstName".equals(headerCell0.getStringCellValue())
					|| !"LastName".equals(headerCell1.getStringCellValue())
					|| !"CeoCid".equals(headerCell2.getStringCellValue())
					|| !"Date-of-birth".equals(headerCell3.getStringCellValue())
					|| !"Mail".equals(headerCell4.getStringCellValue())) {
				warnings.add(
						"Header row is not in the correct format it have to be in this fromat (FirstName,LastName,CeoCid,Date-of-birth,Mail).");
			}

			while (rowIterator.hasNext()) {
				if (rowIterator.hasNext()) {
				} else {
					logger.warn("No data rows found in the Excel sheet.");
					warnings.add("No data rows found in the Excel sheet.");
					return; // No data rows to process
				}
				Row row = rowIterator.next();
				// Check for null values in each cell
				Cell firstNameCell = row.getCell(0);
				Cell lastNameCell = row.getCell(1);
				Cell ceoCidCell = row.getCell(2);
				Cell dateOfBirthCell = row.getCell(3);
				Cell mailCell = row.getCell(4);

				if (firstNameCell == null || lastNameCell == null || ceoCidCell == null || dateOfBirthCell == null
						|| mailCell == null) {
					logger.warn("One or more fields are null in row " + (row.getRowNum() + 1));
					warnings.add("One or more fields are null in row " + (row.getRowNum() + 1));
					// Generate Excel file for the warning
					Workbook warningWorkbook = createExcelFileForWarning(row);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					warningWorkbook.write(bos);
					byte[] excelData = bos.toByteArray();

					// Send email with the Excel file as an attachment
					String mail = mailCell.getStringCellValue();
					emailService.sendEmailWithAttachment(mail, "Null fields present",
							"Null fields in the row ", excelData, "warning_data.xlsx");
					continue;

				}

				String firstName = firstNameCell.getStringCellValue();
				int maxFirstnameLength = 30; // Specify the maximum allowed length

				if (firstName.length() > maxFirstnameLength) {
					logger.error("FirstName in row " + (row.getRowNum() + 1) + " exceeds the maximum allowed length.");
					warnings.add("FirstName in row " + (row.getRowNum() + 1) + " exceeds the maximum allowed length.");
					String mail = mailCell.getStringCellValue();
					emailService.sendEmail(mail, "First Name is invalid",
							"The length of the FirstName is more than 30 charecters in row ");

				}
				try {
					firstName = firstNameCell.getStringCellValue();
				} catch (Exception e) {
					logger.error("Error in FirstName cell in row " + (row.getRowNum()) + ": " + e.getMessage());
					continue; // Skip this row and move to the next one
				}

				String lastName = lastNameCell.getStringCellValue();
				int maxLastnameLength = 30; // Specify the maximum allowed length

				if (lastName.length() > maxLastnameLength) {
					logger.error("Lastname in row " + (row.getRowNum()) + " exceeds the maximum allowed length.");
					warnings.add("LastName in row " + (row.getRowNum() + 1) + " exceeds the maximum allowed length.");
					emailService.sendEmail("mahendraleo28@gmail.com", "Last Name is invalid",
							"The length of the LastName is more than 30 charecters in row   ");
				}
				try {
					lastName = lastNameCell.getStringCellValue();
				} catch (Exception e) {
					logger.error("Error in LastName cell in row " + (row.getRowNum()) + ": " + e.getMessage());
					continue; // Skip this row and move to the next one
				}

				String ceoCid;
				try {
					ceoCid = ceoCidCell.getStringCellValue();
				} catch (Exception e) {
					logger.error("Error in CeoCid cell in row " + (row.getRowNum()) + ": " + e.getMessage());
					continue; // Skip this row and move to the next one
				}

				Date dateOfBirth;
				try {
					dateOfBirth = dateOfBirthCell.getDateCellValue();
				} catch (Exception e) {
					logger.error("Error in Date-of-birth cell in row " + (row.getRowNum()) + ": " + e.getMessage());
					continue; // Skip this row and move to the next one
				}

				String mail = mailCell.getStringCellValue();
				int maxMailLength = 50; // Specify the maximum allowed length

				if (mail.length() > maxMailLength) {
					logger.error(
							"Mail address in row " + (row.getRowNum() + 1) + " exceeds the maximum allowed length.");
					warnings.add(
							"Mail address in row " + (row.getRowNum() + 1) + " exceeds the maximum allowed length.");
					emailService.sendEmail("mahendraleo28@gmail.com", "Invalid Email",
							"The length of the email is more than 50 charecters  ");
				}
				// Regular expression pattern for mail addresses
				String emailPattern = "^\\w+@gmail\\.com$";

				if (!Pattern.matches(emailPattern, mail)) {
					logger.error("Mail address in row " + (row.getRowNum() + 1) + " is not a valid Gmail address.");
					warnings.add("Mail address in row " + (row.getRowNum() + 1) + " is not a valid Gmail address.");
					emailService.sendEmail("mahendraleo28@gmail.com", "Invalid Email",
							"The format of the mail address is not in @gmail.com ");
					// throw new Exception("Mail address in row " + (row.getRowNum()) + " is not a
					// valid Gmail address.");
				}
				try {
					mail = mailCell.getStringCellValue();
				} catch (Exception e) {
					logger.error("Error in Mail cell in row " + (row.getRowNum()) + ": " + e.getMessage());
					continue; // Skip this row and move to the next one
				}
				if (excelemployrepo.existsByMail(mail)) {
					logger.warn("Mail address in row " + (row.getRowNum() + 1) + " already exists in the database.");
					warnings.add("Mail address in row " + (row.getRowNum() + 1) + " already exists in the database.");
					emailService.sendEmail(storedRecipientEmail, "Invalid Email",
							"This email is already exists in our database. Row is " + (row.getRowNum() + 1));
					continue;

				}

				int age = calculateAgeFromDateOfBirth(dateOfBirth);

				int minimumAge = 18;

				if (age < minimumAge) {
					logger.error("Age in row " + (row.getRowNum() + 1) + " is not greater than 18 years.");
					warnings.add("Age in row " + (row.getRowNum() + 1) + " is not greater than 18 years.");
					emailService.sendEmail("mahendraleo28@gmail.com", "Date of birth is invalid",
							"The Date of birth is Invali. It is under 18" + (row.getRowNum() + 1));
					continue; // Skip this row and move to the next one
				}

				String eligibilityStatus = "No";
				if (age < 25 && "Java".equalsIgnoreCase(ceoCid)) {
					eligibilityStatus = "Yes";
				}

				Excelemploy employee = new Excelemploy(firstName, lastName, ceoCid, dateOfBirth, age, mail,
						eligibilityStatus);
				try {
					excelemployrepo.save(employee);
				} catch (Exception e) {
					logger.error("Error executing the query for row " + (row.getRowNum()) + ": " + e.getMessage());
					continue; // Skip this row and move to the next one
				}

				// SMTP Server Configuration for Gmail
				logger.info("logger in smtp is working");
				Properties properties = new Properties();
				properties.put("mail.smtp.host", "smtp.gmail.com"); // Gmail SMTP server hostname
				properties.put("mail.smtp.port", "587"); // Gmail SMTP port (TLS)
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Trust the Gmail SMTP server
				properties.put("mail.smtp.timeout", "10000"); // 10 seconds (adjust as needed)
				properties.put("mail.smtp.connectiontimeout", "10000"); // 10 seconds (adjust as needed)

				// Your Gmail email credentials
				String username = "test730630@gmail.com";
				String password = "opxb wxwn ynff gbxk";

				Session session = Session.getInstance(properties, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
					message.setSubject("Data Processing Error");
					message.setText("There was an error processing your data. Please review the data and try again.");

					Transport.send(message);

					logger.info("Error notification email sent to: " + mail);
				} catch (MessagingException e) {
					logger.error("Error sending email: " + e.getMessage());
				}

			}
		}
	}

	private Workbook createExcelFileForWarning(Row row) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Warning Data");
		
		// Create a header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Firstname");
        headerRow.createCell(1).setCellValue("Lastname");
        headerRow.createCell(2).setCellValue("coecid");
        headerRow.createCell(3).setCellValue("dateofbirth");
        headerRow.createCell(4).setCellValue("email");
       
		// Create a new row in the Excel sheet and copy data from the original row
		Row newRow = sheet.createRow(1);
		for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
			Cell originalCell = row.getCell(cellNum);
			Cell newCell = newRow.createCell(cellNum);

			if (originalCell != null) {
				switch (originalCell.getCellType()) {
				case STRING:
					newCell.setCellValue(originalCell.getStringCellValue());
					break;
				case NUMERIC:
					newCell.setCellValue(originalCell.getNumericCellValue());
					break;
				// Add cases for other cell types if needed
				case BLANK:
					break;
				case BOOLEAN:
					break;
				case ERROR:
					break;
				case FORMULA:
					break;
				case _NONE:
					break;
				default:
					break;
				}
			}
		}

		return workbook;
	}

	// This method calculates the age column from the date of birth column
	private int calculateAgeFromDateOfBirth(Date dateOfBirth) {
		LocalDate birthDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(birthDate, currentDate);
		return period.getYears();
	}

	// This method is used to get all the employees
	public List<Excelemploy> getAllEmployees() {
		return excelemployrepo.findAll();
	}

	// This method is used to filter the employees
	public List<Excelemploy> getEmployeesWithCeoCidAndAgeLessThan25() {
		return excelemployrepo.findByCeoCidAndAgeLessThan("Java", 25);
	}
}
