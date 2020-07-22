package datamodel;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class StandardStaff implements Staff {
	

	private final String name;
	private Map<LocalDateTime, Double> workingProgram = new HashMap<>();
	private double salaryPerHour = 0;
	

	public StandardStaff(String name) {
		this.name = name;
	}

	public void setSalaryPerHour(double salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}

	public void addToWorkingProgram(LocalDateTime ld, double hours) {
		if (workingProgram.containsKey(ld)) {
			hours = workingProgram.get(ld) + hours; // adds the known worked hours to the new hours
		}
		workingProgram.put(ld, hours);
	}

	public String getName() {
		return name;
	}

	public double getSalaryPerHour() {
		return salaryPerHour;
	}

	public double getHoursWorked() {
		Iterator<Double> iterateHours = workingProgram.values().iterator();
		double hoursWorked = 0;
		while (iterateHours.hasNext()) {
			hoursWorked += iterateHours.next();
		}
		return hoursWorked;
	}

	public int getWorkingDays() {
		return workingProgram.size();
	}

	public double getPayment() {
		return getHoursWorked() * salaryPerHour;
	}

	// TODO return workingProgramm deep copy instead of string
	public String getWorkingProgram() {
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<LocalDateTime, Double>> iterator = workingProgram.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<LocalDateTime, Double> pair = iterator.next();
			sb.append("{" + formatLocalDateTime(pair.getKey()) + " (" + pair.getValue() + ")" + "},");
		}

		return sb.substring(0, sb.length() - 1);
	}

	private String formatLocalDateTime(LocalDateTime ld) {
		StringBuilder sb = new StringBuilder();
		sb.append(ld.getDayOfMonth() + "/" + ld.getMonthValue() + "/" + ld.getYear());
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Staff [name=" + name + ", salaryPerHour=" + salaryPerHour + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardStaff other = (StandardStaff) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	



	
	
	
//	@Override
//	public void addToWorkingProgram(HashMap<LocalDateTime, Double> wp) {
//		this.workingProgram = (Map<LocalDateTime, Double>) wp.clone();
//	}

}
