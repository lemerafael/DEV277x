package kingdom;

import java.awt.Color;

public class Giant extends Critter {
	private int move;
	private String currentString;
	
	public Giant() {
		super();
		move = 0;
		currentString = "fum";
	}
	
    public Action getMove(CritterInfo info) {
    	move++;
        if(info.frontThreat()) {
        	return Action.INFECT;
        } else if (info.getFront().equals(Neighbor.EMPTY)) {
        	return Action.HOP;
        } else {
        	return Action.RIGHT;
        }
    }
    
    public Color getColor() {
		return Color.GRAY;
    }
    
    private String getNextString(String current) {
    	String next = null;
    	switch (current) {
		case "fee":
			next = "fie";
			break;
			
		case "fie":
			next = "foe";
			break;
			
		case "foe":
			next = "fum";
			break;
			
		case "fum":
			next = "fee";
			break;
		}
    	return next;
    }

    public String toString() {
    	if (move%6 == 0) {
    		currentString = getNextString(currentString);
    	}
		return currentString;
    }
}