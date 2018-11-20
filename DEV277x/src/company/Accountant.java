package company;

import java.util.Iterator;

public class Accountant extends BusinessEmployee {

	private TechnicalLead supporting;
	private double bonusBudget;
	
	public Accountant(String name)	{
		super(name);
		bonusBudget=0.0;
		this.supporting = null;
	}
	
	public TechnicalLead getTeamSupported()	{
		return this.supporting;
	}
	
	public void supportTeam(TechnicalLead lead)	{
		this.supporting = lead;
		for (Iterator<SoftwareEngineer> iterator = lead.getTeam().iterator(); iterator.hasNext();) {
			SoftwareEngineer softwareEngineer = (SoftwareEngineer) iterator.next();
			this.bonusBudget += 1.1*softwareEngineer.getBaseSalary();
		}
	}
	
	public boolean approveBonus(double bonus) {
		if (bonus>this.getBonusBudget()) {
			return false;
		} else {
			return true;
		}
	}
	
	public String employeeStatus()	{
		if (this.supporting != null) {
			return super.employeeStatus()+" is supporting "+this.supporting.getName();
		} else {
			return super.employeeStatus()+" is not supporting any teams. ";
		}
	}

	@Override
	double getBonusBudget() {
		return bonusBudget;
	}
	
}
