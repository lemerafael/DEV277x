package company;

public class SoftwareEngineer extends TechnicalEmployee {

	private boolean hasAccess;
	private int successfulCheckIns;
	
	public SoftwareEngineer(String name) {
		super(name);
		hasAccess = false;
		successfulCheckIns = 0;
	}
	
	public boolean getCodeAccess() {
		return hasAccess;
	}
	
	public void setCodeAccess(boolean access) {
		this.hasAccess = access;
	}
	
	public int getSuccessfulCheckIns() {
		return successfulCheckIns;
	}
	
	public boolean checkInCode() {
		TechnicalLead manager = (TechnicalLead)super.getManager();
		if (manager.approveCheckIn(this)) {
			successfulCheckIns++;
			return true;
		} else {
			setCodeAccess(false);
			return false;
		}
	}
	
}
