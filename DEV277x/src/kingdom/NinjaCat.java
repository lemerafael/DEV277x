package kingdom;

import java.awt.Color;
import java.util.Random;

public class NinjaCat extends Critter {
	private int move;
	private String currentString;
	private Color currentColor;
	
	public NinjaCat() {
		super();
		move = 0;
		currentString = "cat";
		currentColor = getRandomColor();
	}
	
	public Action getMove(CritterInfo info) {
    	move++;
        if(info.frontThreat()) {
        	return Action.INFECT;
        } else if (info.getFront().equals(Neighbor.WALL) || info.getRight().equals(Neighbor.WALL)) {
        	return Action.LEFT;
        } else if (info.getLeft().equals(Neighbor.WALL) || info.getFront().equals(Neighbor.SAME)) {
        	return Action.RIGHT;
        } else {
        	return Action.HOP;
        }
    }
    
    private Color getRandomColor() {
    	Random random = new Random();
    	Color color = null;
    	int seed = random.nextInt(3);
    	switch (seed) {
		case 0:
			color = Color.BLACK;
			break;
			
		case 1:
			color = Color.WHITE;
			break;
			
		case 2:
		default:
			color = Color.GRAY;
			break;
		}
    	return color;
    }

    public Color getColor() {
    	if (move%3 == 0) {
    		currentColor = getRandomColor();
    	}
		return currentColor;
    }
    
    private String getNextString(String current) {
    	String next = null;
    	switch (current) {
		case "nin":
			next = "ja";
			break;
			
		case "ja":
			next = "cat";
			break;
			
		case "cat":
			next = "nin";
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