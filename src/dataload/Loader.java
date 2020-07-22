package dataload;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Constants.ExcelRules;
import datamodel.Personel;
import datamodel.Staff;
import datamodel.StandardStaff;

public class Loader {

	public static int load(String filename, HashMap<String, Staff> staffMap) {
		
		try {

			// open input stream f
			File myFile = new File(filename);
			FileInputStream fis = new FileInputStream(myFile);

			// Finds the workbook instance for XLSX file
			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

			// Return first sheet from the XLSX workbook
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);

			// for each column
			for (int i = 0; i < ExcelRules.MAX_COLUMN; i++) {

				// original date is null
				LocalDateTime ldt = null;

				// for each row
				for (int j = 0; j < mySheet.getLastRowNum(); j++) {

					// if it is not a null row
					if (mySheet.getRow(j) != null) {
						Cell cell = mySheet.getRow(j).getCell(i);

						// if it's not a null cell
						if (cell != null) {

							// if it is a date change the date
							if (isDate(cell)) {
								ldt = cell.getLocalDateTimeCellValue();
								continue; // no need to check if it is a staff member as it was a date
							}

							// if for the current column we have a date
							if (ldt != null) {

								if (cell.getCellType() == CellType.STRING) {
									double hours = getHours(cell.getRow(), cell.getColumnIndex());

									// if we find hours > 0
									if (hours > 0) {
										
										// get employee name
										String employeeName = cell.getStringCellValue().trim();
										
										
										// if he is already in the set 
										//add the current date to his working program
										
										if (staffMap.containsKey(employeeName)) {
											staffMap.get(employeeName).addToWorkingProgram(ldt, hours);

											// if he is not
											// 1. make new object
											// 2. set his workingProgram
										} else {
											Staff staff = new StandardStaff(employeeName);
											staff.addToWorkingProgram(ldt, hours);
											staffMap.put(staff.getName(), staff);
										}
									}
								}
							}

						}

					}

				}

			}
			myWorkBook.close();
			return staffMap.size();
		} catch (IOException e) {
			return 0;
		}

	}

	private static double getHours(Row row, int i) {
		// try catch was used instead of checking the cell type because the new
		// cell can throw an exception
		try {
			Cell cell = row.getCell(i + 1);
			return cell.getNumericCellValue();
		} catch (Exception e) {
			return 0;
		}

	}

	private static boolean isDate(Cell cell) {

		if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
			return true;
		}

		return false;
	}

}

// 							----------Apotuxhmena-----

//	private static boolean hasDateFormat(String date) {
//		Pattern p = Pattern.compile("\\d{1,2}/\\d{1,2}/\\d{4}");
//		return (p.matcher(date).matches());
//
//	}

// ------LocalDateTime to LocalDate

//	public static boolean hasDate2(Cell cell) {
//		DateTimeFormatter f = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z uuuu").withLocale(Locale.US);
//
//		try {
//			ZonedDateTime zdt = ZonedDateTime.parse(cell.getLocalDateTimeCellValue() + "", f);
//			LocalDate ld = zdt.toLocalDate();
//			return true;
//
//		} catch (Exception e) {
//			return false;
//		}
//
//	}

//--------iterator

// Get iterator to all the rows in current sheet
//Iterator<Row> rowIterator = mySheet.iterator();

// Traversing over each row of XLSX file
/*
 * while (rowIterator.hasNext()) { Row row = rowIterator.next();
 * ArrayList<String> rowList = new ArrayList<>(); // For each row, iterate
 * through each columns Iterator<Cell> cellIterator = row.cellIterator(); while
 * (cellIterator.hasNext()) {
 * 
 * Cell cell = (Cell) cellIterator.next();
 * 
 * switch (cell.getCellType()) {
 * 
 * case STRING: rowList.add(cell.getStringCellValue()); break; case NUMERIC: if
 * (cell.getNumericCellValue() < 24) { rowList.add(cell.getNumericCellValue() +
 * ""); } else { rowList.add(cell.getDateCellValue() + ""); } break; case
 * BOOLEAN: rowList.add(cell.getBooleanCellValue() + ""); break;
 * 
 * default: rowList.add(cell.getColumnIndex() + ""); } } dataList.add(rowList);
 * 
 * }
 */
