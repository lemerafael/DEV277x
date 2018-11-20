package kingdom;

import java.awt.Color;

public class Bear extends Critter {
	private boolean polar;
	private boolean slash;
	
	public Bear(boolean polar) {
		super();
		this.polar = polar;
		this.slash = true;
	}
	
    public Action getMove(CritterInfo info) {
    	slash = !slash;
        if (info.frontThreat()) {
        	return Action.INFECT;
        } else if (info.getFront().equals(Neighbor.EMPTY)) {
        	return Action.HOP;
        } else {
        	return Action.LEFT;
        }
    }

    public Color getColor() {
    	if (polar) {
    		return Color.WHITE;
    	} else {
    		return Color.BLACK;
    	}
    }

    public String toString() {
    	if (slash) {
    		return "/";
    	} else {
    		return "\\";
    	}
    }
}