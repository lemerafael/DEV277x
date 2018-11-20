package company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TechnicalLead extends TechnicalEmployee implements Manager {

	private int headCount;
	private List<SoftwareEngineer> team;
	private BusinessLead supporting;
	
	public TechnicalLead(String name)	{
		super(name);
		setBaseSalary(1.3*super.getBaseSalary());
		headCount = 4;
		team = new ArrayList<SoftwareEngineer>();
	}
	
	@Override
	public boolean hasHeadCount() {
		if (team.size()<headCount) {
			return true;
		}
		return false;
	}
	
	public boolean addReport(SoftwareEngineer e) {
		if (hasHeadCount()) {
			team.add(e);
			e.setManager(this);
			return true;
		}
		return false;
	}
	
	public boolean approveCheckIn(SoftwareEngineer e) {
		if (team.contains(e) && e.checkInCode()) {
			return true;
		}
		return false;
	}
	
	public boolean requestBonus(Employee e, double bonus) {
		if(supporting.approveBonus(e, bonus)) {
			return true;
		}
		return false;
	}
	
	public void setBusinessSupport(BusinessLead e) {
		this.supporting = e;
	}
	
	public String getTeamStatus() {
		String teamStatus = super.employeeStatus();
		if (team.isEmpty()) {
			teamStatus+=" and no direct reports yet ";
		} else {
			teamStatus+=" and is managing: \n";
			for (Iterator<SoftwareEngineer> iterator = team.iterator(); iterator.hasNext();) {
				SoftwareEngineer softwareEngineer = (SoftwareEngineer) iterator.next();
				teamStatus+=softwareEngineer.employeeStatus()+"\n";
			}
		}
		return teamStatus;
	}
	
	public List<SoftwareEngineer> getTeam() {
		return team;
	}
			
}
