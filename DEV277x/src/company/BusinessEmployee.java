package company;

public abstract class BusinessEmployee extends Employee {
	
	private Employee manager;
	abstract double getBonusBudget();
	
	public BusinessEmployee(String name) {
		super(name, 50000);
	}
	
	@Override
	public String employeeStatus()	{
		double budget=0;
		return super.toString()+" with a budget of "+budget;
	}

	@Override
	public Employee getManager() {
		return manager;
	}
	
	public void setManager(BusinessLead e) {
		this.manager = e;
	}
}
