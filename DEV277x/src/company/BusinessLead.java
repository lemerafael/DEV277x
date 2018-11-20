package company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BusinessLead extends BusinessEmployee implements Manager {

	private int headCount;
	private List<Accountant> team;
	private double bonusBudget;
	
	public BusinessLead(String name) {
		super(name);
		setBaseSalary(2*super.getBaseSalary());
		team = new ArrayList<Accountant>();
		headCount = 10;
	}
	
	@Override
	public boolean hasHeadCount() {
		if (team.size()<headCount) {
			return true;
		}
		return false;
	}
	
	public boolean addReport(Accountant e, TechnicalLead supportTeam) {
		if (hasHeadCount()) {
			team.add(e);
			e.setManager(this);
			bonusBudget+=1.1*e.getBaseSalary();
			return true;
		}
		return false;
	}
	
	public boolean requestBonus(Employee e, double bonus)	{
		if(bonus<getBonusBudget()) {
			bonusBudget-=bonus;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean approveBonus(Employee e, double bonus)	{
		for (Iterator<Accountant> iterator = team.iterator(); iterator.hasNext();) {
			Accountant accountant = (Accountant) iterator.next();
			if(accountant.getTeamSupported().equals(e.getManager())) {
				if(bonus<accountant.getBonusBudget()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	double getBonusBudget() {
		return bonusBudget;
	}

	@Override
	public String getTeamStatus() {
		String teamStatus = super.employeeStatus();
		if (team.isEmpty()) {
			teamStatus+=" and no direct reports yet ";
		} else {
			teamStatus+=" and is managing: \n";
			for (Iterator<Accountant> iterator = team.iterator(); iterator.hasNext();) {
				Accountant accountant = (Accountant) iterator.next();
				teamStatus+=accountant.employeeStatus()+"\n";
			}
		}
		return teamStatus;
	}

}
