package kingdom;

import java.awt.Color;
import java.util.Random;

public class Tiger extends Critter {
	private int move;
	private Color currentColor;
	
	public Tiger() {
		super();
		move = 0;
		currentColor = getRandomColor();
	}
	
    public Action getMove(CritterInfo info) {
    	move++;
        if(info.frontThreat()) {
        	return Action.INFECT;
        } else if (info.getFront().equals(Neighbor.WALL) || info.getRight().equals(Neighbor.WALL)) {
        	return Action.LEFT;
        } else if (info.getFront().equals(Neighbor.SAME)) {
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
			color = Color.RED;
			break;
			
		case 1:
			color = Color.GREEN;
			break;
			
		case 2:
		default:
			color = Color.BLUE;
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

    public String toString() {
    	return "TGR";
    }
}