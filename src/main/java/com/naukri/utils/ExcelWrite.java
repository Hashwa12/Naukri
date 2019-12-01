package com.naukri.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite {

	public static File file;
	public static OutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	String tech;
	public static Row row;
	int rownum = 1;

	public ExcelWrite(String tech) throws Exception {
		// excelwrite
		file = new File(
				"D:\\Hashwanth\\Programs\\1.Java\\Others\\Naukri\\NaukriJobDetails.xlsx");
		if (file.exists()) {
			workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
		} else {
			workbook = new XSSFWorkbook();
		}
		sheet = workbook.createSheet(tech);
		// excel header
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("JobTitle");
		row.createCell(1).setCellValue("CompanyName");
		row.createCell(2).setCellValue("Exeperience");
		row.createCell(3).setCellValue("location");
		row.createCell(4).setCellValue("PostedDate");
		row.createCell(5).setCellValue("ContactDetails");
	}

	public void excelwriteutil(String jobtitle, String companyname, String requiredexperience, String location,
			String posteddate, String contactinfo) throws Exception {

		row = sheet.createRow(rownum++);
		row.createCell(0).setCellValue(jobtitle);
		row.createCell(1).setCellValue(companyname);
		row.createCell(2).setCellValue(requiredexperience);
		row.createCell(3).setCellValue(location);
		row.createCell(4).setCellValue(posteddate);
		row.createCell(5).setCellValue(contactinfo);
	}

	public void write() throws Exception {
		fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.flush();
		workbook.close();
	}

}
