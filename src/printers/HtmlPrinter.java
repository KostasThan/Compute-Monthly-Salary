package printers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import datamodel.Staff;

public class HtmlPrinter {
	private HtmlPrinter() {}
	
	public static boolean exportHtml(List<Staff> staffList,String filename) {
		
		String printThis = htmlFormat(staffList);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename+".html"))) {
			writer.write(printThis);
			
		} catch (Exception e) { // TODO //i do not care about the reason. whatever the exception return -1
			e.printStackTrace();
			return false;
		}

		return true;

		
	}
	
	private static  String htmlFormat(List<Staff> staffList) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<html>");
		sb.append(printHtmlTitle(staffList));
		sb.append(printHtmlStaff(staffList));
		sb.append("</html>");
		return sb.toString();
	}
	
	private static String printHtmlTitle(List<Staff> staffList) {
		Iterator<Staff> staffIterator = staffList.iterator();
		double totalPayment = 0;
		while(staffIterator.hasNext()) {
			totalPayment += staffIterator.next().getPayment();
		}
		return ("<h2> <i> Total payement: " + totalPayment + "&#8364</i></h2>\n\n<p></p>");
		
		
	}

	private static String printHtmlStaff(List<Staff> staffList) {
		StringBuilder sb = new StringBuilder();

		for (Staff staff : staffList) {
			sb.append(editStaffFormat(staff));
		}
		
		return sb.toString();
	}

	private static String editStaffFormat(Staff staff) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<p><h2>" + staff.getName() +"</h2></p>"); 
		sb.append("<p><b>Payment:</b> " + staff.getPayment()+"&#8364</p>");
		sb.append("<p><b>Salary per hour:</b> " + staff.getSalaryPerHour()+"&#8364</p>");
		sb.append("<p><b>Hours worked:</b> " + staff.getHoursWorked()+"h</p>");
		sb.append("<p><b>Days worked: </b> " + staff.getWorkingDays() + "</p>");
		sb.append("<p><b><i>Complete program: </b> " + staff.getWorkingProgram() + "</i></p>");
		return sb.toString();
	
	}
}
