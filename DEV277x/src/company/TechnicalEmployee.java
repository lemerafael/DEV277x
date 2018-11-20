package company;

public class TechnicalEmployee extends Employee {
	
	private int checkInCount;
	private Employee manager;
	
	public TechnicalEmployee(String name) {
		super(name, 75000);
		checkInCount=0;
	}
	
	public TechnicalEmployee(String name, double baseSalary) {
		super(name, baseSalary);
	}
	
	@Override
	public String employeeStatus()	{
		return super.toString()+" has "+checkInCount+" successful check ins";
	}

	@Override
	public Employee getManager() {
		return manager;
	}
	
	public void setManager(TechnicalLead e) {
		this.manager = e;
	}

}
