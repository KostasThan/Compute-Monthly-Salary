package datamodel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Personel {
	Map<String, Staff> staffMap = new HashMap<>();

	// TODO: LOAD MORE THAN ONE XLSX FILE!
	// TODO: IF THE STAFF IS ALREADY HIRED,ADD TO HIS WORKING PROGRAM
	// TODO: WHICH MEANS STAFF MUST RETURN HIS WORKING PROGRAM AS MAP
	public boolean hireStaff(Staff staff) {

		if (staff.getSalaryPerHour() > 0) {
			staffMap.put(staff.getName(), staff);
			return true;
		}
		return false;
	}

	public void hireStaff(Staff staff, double salary) {
		if (!staffMap.containsKey(staff.getName())) {
			staff.setSalaryPerHour(salary);
			hireStaff(staff);
		} else {
			staffMap.get(staff.getName()).setSalaryPerHour(salary);
		}

	}

//	public void hireStaff(ArrayList<Staff> staffList) {
//		for (Staff staff : staffList) {
//			hireStaff(staff);
//		}
//	}

	public boolean isEmployed(Staff staff) {
		return staffMap.containsValue(staff);
	}

	public int getpaidStaffSize() {
		return staffMap.size();
	}

	public List<Staff> getPersonel() {
		List<Staff> staffList = new LinkedList<>();
		Iterator<Entry<String, Staff>> it = staffMap.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Staff staff = (Staff) pair.getValue();
			staffList.add(staff);
		}
		return staffList;
	}

	public Staff getStaff(String name) {
		return staffMap.get(name);
	}

}
