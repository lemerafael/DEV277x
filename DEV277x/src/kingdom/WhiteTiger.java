package kingdom;

import java.awt.Color;

public class WhiteTiger extends Tiger {
	private boolean hasInfected;
	
	public WhiteTiger() {
		super();
		hasInfected = false;
	}
	
    public Action getMove(CritterInfo info) {
    	Action action = super.getMove(info);
        if (action.equals(Action.INFECT)) {
        	hasInfected = true;
        }
        return action;
    }

    public Color getColor() {
		return Color.WHITE;
    }

    public String toString() {
    	if (hasInfected) {
    		return "TGR";
    	} else {
    		return "tgr";
    	}
    }
}