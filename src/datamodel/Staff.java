package datamodel;

import java.time.LocalDateTime;

public interface Staff {

	public void setSalaryPerHour(double salaryPerHour);

	public void addToWorkingProgram(LocalDateTime ldt, double hours);

	public String getName();

	public double getSalaryPerHour();

	public double getHoursWorked();

	public int getWorkingDays();

	public double getPayment();

	public String getWorkingProgram();

	public String toString();

	public int hashCode();

	public boolean equals(Object obj);

}
