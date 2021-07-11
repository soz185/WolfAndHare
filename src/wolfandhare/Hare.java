package wolfandhare;

import java.util.*;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.*;

public class Hare extends Item{

	private static int CountHare = 0;
	private static int Vision;
	private int Age;
	private Point Speed;
	
	public Hare() {
		super();
		Random random = new Random();
		Coordinates.x = 0;
		Coordinates.y = 0;
		Age = random.nextInt(30);
		Speed = new Point(random.nextInt(4) - 2, random.nextInt(4) - 2);
		CountHare++;
	}
	
	public Hare(Item src) {
		super(src);
		Age = src.getAge();
		Speed = new Point(src.getSpeed().x, src.getSpeed().y);
	}
	
	@Override
    public boolean equals(Object object)
    {
        boolean result = false;
        if (object != null && object instanceof Hare)
        {
        	if ((Coordinates.x == ((Hare)object).Coordinates.x) && (Coordinates.y == ((Hare)object).Coordinates.y)
            		&& (Age == ((Hare) object).Age)
            		&& (Speed.x == ((Hare)object).Speed.x) && (Speed.y == ((Hare)object).Speed.y)) {
        		result = true;
        	}
        }
        return result;
    }
	
	/*public static void copy(Object dest, Object src) {
		if (dest != null && dest instanceof Hare && src != null && src instanceof Hare) {
			((Hare)dest).Coordinates.x = ((Hare)src).Coordinates.y;
			((Hare)dest).Coordinates.y = ((Hare)src).Coordinates.y;
			((Hare)dest).Age = ((Hare)src).getAge();
			((Hare)dest).Speed.x = ((Hare)src).Speed.x; 
			((Hare)dest).Speed.y = ((Hare)src).Speed.y;
		}
	}*/
	
	public void draw(Canvas canvas) { 
		GC graphic = new GC(canvas);
		if (Age > 30)
			graphic.setBackground(new Color(canvas.getDisplay(), 90, 90, 90));
		else
			graphic.setBackground(new Color(canvas.getDisplay(), 180, 180, 180));
		graphic.fillOval(Coordinates.x - 3, Coordinates.y - 3, 6, 6);
		graphic.dispose();
	}
	
	private void move(Canvas canvas) {
		if (Coordinates.x + Speed.x < 3 || Coordinates.x + Speed.x > canvas.getSize().x - 3)
			Speed.x = -Speed.x;
		if (Coordinates.y + Speed.y < 3 || Coordinates.y + Speed.y > canvas.getSize().y - 3)
			Speed.y = -Speed.y; 
		Coordinates.x += Speed.x;
		Coordinates.y += Speed.y;
	}
	
	public void action(Canvas canvas) {
		if (Age > 300)
			{
				TMap.deleteItem(this);
				CountHare--;
				return;
			}
		for (Map.Entry<Item, Double> item : TMap.getVisibleItems(this).entrySet()) {
			if (item.getKey() instanceof Hare)
				if (item.getValue() <= 1.0 && item.getKey().getAge() > 50 && this.Age > 50) {
					TMap.newHare(this.Coordinates.x, this.Coordinates.y);
					Speed.x = -Speed.x;
					Speed.y = -Speed.y;
				}
			//if (item instanceof Wolf)
				
		}
		Age++;
		move(canvas);
	}
	
	public static void clearCount() {
		CountHare = 0;
	}
	
	public static void reduceCount() {
		CountHare--;
	}
	
	public static void setVision(int vision) {
		Vision = vision;
	}
	
	public int getVision() {
		return Vision;
	}
	
	public int getAge() {
		return Age;
	}
	
	public Point getSpeed() {
		return Speed;
	}
	
	public static int getCountHare() {
		return CountHare;
	}
}
