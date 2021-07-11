package wolfandhare;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.*;

public class Item {
	protected Point Coordinates;
	
	public Item() { 
		Coordinates = new Point(0, 0);
	}
	
	public Item(int x, int y) {
		Coordinates = new Point(x, y);
	}
	
	public Item(Item src) {
		Coordinates = new Point(src.Coordinates.x, src.Coordinates.y);
	}
	
	public void setCoordinates(int x, int y) {
		Coordinates.x = x;
		Coordinates.y = y;
	}
	
	public void draw(Canvas canvas) { }
	
	public void action(Canvas canvas) { }

	public int getVision() { return 0;}
	
	public static void setVision(int vision) { }
	
	public int getAge() { return -1;}
	
	public int getHunger() { return -1; }
	
	public Point getSpeed() { return null; }
}
