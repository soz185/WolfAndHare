package wolfandhare;

import java.util.*;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.*;

public class Wolf extends Item{
	//private String Image;
	private int Hunger;
	private static int Vision;
	private int Age;
	private Point Speed;
	private static int CountWolf = 0;
	
	public Wolf() {
		super();
		Random random = new Random();
		Coordinates.x = 0;
		Coordinates.y = 0;
		Hunger = 500;
		Age = random.nextInt(100);
		CountWolf++;
		Speed = new Point(random.nextInt(4) - 2, random.nextInt(4) - 2);
	}
	
	public Wolf(Item src) {
		super(src);
		Hunger = src.getHunger();
		Age = src.getAge();
		Speed = new Point(src.getSpeed().x, src.getSpeed().y);
	}
	
	@Override
    public boolean equals(Object object)
    {
        boolean result = false;
        if (object != null && object instanceof Wolf)
        {
            if ((Coordinates.x == ((Wolf)object).Coordinates.x) && (Coordinates.y == ((Wolf)object).Coordinates.y)
            		&& (Hunger == ((Wolf) object).Hunger) && (Age == ((Wolf) object).Age)
            		&& (Speed.x == ((Wolf)object).Speed.x) && (Speed.y == ((Wolf)object).Speed.y)) {
            	result = true;
            }
        }
        return result;
    }
	
	/*public static void copy(Object dest, Object src) {
		if (dest != null && dest instanceof Wolf && src != null && src instanceof Wolf) {
			((Wolf)dest).Coordinates.x = ((Wolf)src).Coordinates.y;
			((Wolf)dest).Coordinates.y = ((Wolf)src).Coordinates.y;
			((Wolf)dest).Hunger = ((Wolf)src).getHunger();
			((Wolf)dest).Age = ((Wolf)src).getAge();
			((Wolf)dest).Speed.x = ((Wolf)src).Speed.x; 
			((Wolf)dest).Speed.y = ((Wolf)src).Speed.y;
		}
	}*/
	
	public void draw(Canvas canvas) { 
		GC graphic = new GC(canvas);
		if (Age > 70)
			graphic.setBackground(new Color(canvas.getDisplay(), 210, 0, 0));
		else
			graphic.setBackground(new Color(canvas.getDisplay(), 255, 138, 138));
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
		if (Hunger == 0 || Age > 700)
			{
				TMap.deleteItem(this);
				CountWolf--;
				return;
			}
		Random random = new Random();
		double minDistance = canvas.getSize().x;
		for (Map.Entry<Item, Double> item : TMap.getVisibleItems(this).entrySet()) {
			if (item.getKey() instanceof Wolf) {
				if (item.getValue() < 1.0 && item.getKey().getAge() > 100 && this.Age > 100) {
					TMap.newWolf(this.Coordinates.x, this.Coordinates.y);
					Speed.x = -Speed.x;
					Speed.y = -Speed.y;
				}
				break;
			}
			if (item.getKey() instanceof Hare) {
				if (item.getValue() < 1.5) {// && Hunger > 490) {
					TMap.deleteItem(item.getKey());
					Hare.reduceCount();
					Hunger += 30;
					Speed.x = random.nextInt(4) - 2;
					Speed.y = random.nextInt(4) - 2;
					break;
				}
				//
				// преследование
				// 
				else {
					// изменение координаты x
					if (minDistance > item.getValue()) {
						if (this.Coordinates.x > item.getKey().Coordinates.x) {
							if (Speed.x > 0)
								Speed.x = -Speed.x;
						}	
						else
							if (this.Coordinates.x < item.getKey().Coordinates.x) {
								if (Speed.x < 0)
									Speed.x = -Speed.x;
							}
							else
								Speed.x = 0;				
						// изменение координаты y
						if (this.Coordinates.y > item.getKey().Coordinates.y) {
							if (Speed.y > 0)
								Speed.y = -Speed.y;
						}
						else
							if (this.Coordinates.y < item.getKey().Coordinates.y) {
								if (Speed.y < 0)
									Speed.y = -Speed.y;
							}
							else
								Speed.y = 0;
					}
					minDistance = item.getValue();
				}
			}
		}
		Age++;
		Hunger--;
		move(canvas);
		if (Speed.x == 0)
			Speed.x = random.nextInt(4) - 2;
		if (Speed.y == 0)
			Speed.y = random.nextInt(4) - 2;
	}
	
	public static void setVision(int vision) {
		Vision = vision;
	}
	
	public static void clearCount() {
		CountWolf = 0;
	}
	
	public int getVision() {
		return Vision;
	}
	
	public int getAge() {
		return Age;
	}
	
	public int getHunger() {
		return Hunger;
	}
	
	public Point getSpeed() {
		return Speed;
	}
	
	public static int getCountWolf() {
		return CountWolf;
	}
}
