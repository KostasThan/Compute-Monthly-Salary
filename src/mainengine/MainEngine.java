package mainengine;

import java.util.Collection;
import java.util.HashMap;

import dataload.Loader;
import datamodel.Personel;
import datamodel.Staff;
import printers.HtmlPrinter;

public class MainEngine{
	
	Personel personel = new Personel();
	HashMap<String, Staff> staffExtracted = new HashMap<>();


	public int load(String filename) {
		//load the data
		Loader.load(filename, staffExtracted);
//		Iterator<Entry<String, Staff>> it = staffExtracted.entrySet().iterator();
//		
//		//add the to personel if they are already found
//		while (it.hasNext()) {
//			Map.Entry pair = (Map.Entry) it.next();
//			Staff staff = (Staff) pair.getValue();
//			if(personel.hireStaff(staff)) {
//				it.remove();
//			}
//		}
		return staffExtracted.size();
		
	}
	
	
	public boolean hasExtractedStaff() {
		return (staffExtracted.size()>0)? true : false;
	}
		
	public void hire(Staff staff,double salary) {
		 personel.hireStaff(staff,salary);
	}

	
	public Collection<Staff> getExtractedStaff() {

		return staffExtracted.values();
	}
	
	public void clearExtractedStaff() {
		staffExtracted.clear();
	}
	
	public int getNumberOfEmployees() {
		return personel.getpaidStaffSize();
	}

	public boolean printToFile(String filename) {
		return HtmlPrinter.exportHtml(personel.getPersonel(), filename);
	}
	
	
}
