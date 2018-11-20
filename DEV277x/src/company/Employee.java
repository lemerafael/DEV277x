package company;

public abstract class Employee {
	
	private static int id = 0;
	private String name;
	private double baseSalary;
	private int userId;
	
	public Employee(String name, double baseSalary)	{
		this.name = name;
		this.baseSalary = baseSalary;
		id++;
		this.userId = id;
	}
	
	public double getBaseSalary() {
		return baseSalary;
	}
	
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public String getName() {
		return name;
	}
	
	public int getEmployeeID()	{
		return userId;
	}
	
	public boolean equals(Employee other)	{
		return (this.userId == other.getEmployeeID());
	}
	
	public String toString()	{
		return (this.getEmployeeID()+" "+this.getName());
	}

	abstract String employeeStatus();
	abstract Employee getManager();
	
}
