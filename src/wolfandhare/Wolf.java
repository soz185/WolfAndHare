package wolfandhare;

import java.util.*;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.*;

public class Wolf extends Item{

	private int Hunger;	// голод
	private static int Vision;	// максимальное рассто€ние, на котором объект видит другие объекты
	private int Age;	// возраст
	private Point Speed;	// скорость
	
	public Wolf() {
		super();
		Random random = new Random();
		Coordinates.x = 0;
		Coordinates.y = 0;
		Hunger = 500;
		Age = random.nextInt(100);
		Speed = new Point(random.nextInt(4) - 2, random.nextInt(4) - 2);
	}
	
	public Wolf(Item src) {
		super(src);
		Hunger = src.getHunger();
		Age = src.getAge();
		Speed = new Point(src.getSpeed().x, src.getSpeed().y);
	}
	
	// сравнение объектов
	// true - равны, false - не равны
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
	
	// отрисовка волка
	public void draw(Canvas canvas) { 
		GC graphic = new GC(canvas);
		if (Age > 70)
			graphic.setBackground(new Color(canvas.getDisplay(), 210, 0, 0));	// взрослый волк - красный
		else
			graphic.setBackground(new Color(canvas.getDisplay(), 255, 138, 138));	// молодой волк - розовый
		graphic.fillOval(Coordinates.x - 3, Coordinates.y - 3, 6, 6);
		graphic.dispose();
	}
	
	// передвижение волка
	private void move(Canvas canvas) {
		if (Coordinates.x + Speed.x < 3 || Coordinates.x + Speed.x > canvas.getSize().x - 3)
			Speed.x = -Speed.x;
		if (Coordinates.y + Speed.y < 3 || Coordinates.y + Speed.y > canvas.getSize().y - 3)
			Speed.y = -Speed.y; 
		Coordinates.x += Speed.x;
		Coordinates.y += Speed.y;
	}
	
	// действие волка
	public void action(Canvas canvas) {
		// удаление умершего от старости или голода волка
		if (Hunger == 0 || Age > 5000)
			{
				TMap.deleteItem(this);
				return;
			}
		Random random = new Random();
		double minDistance = Double.MAX_VALUE;
		// обход всех видимых волку объектов
		for (Map.Entry<Item, Double> item : TMap.getVisibleItems(this).entrySet()) {
			// если объект волк
			if (item.getKey() instanceof Wolf) {
				if (item.getValue() <= 1.0 && item.getKey().getAge() > 300 && this.Age > 300) {
					// рождение нового волка
					TMap.newWolf(this.Coordinates.x, this.Coordinates.y);
					Speed.x = -Speed.x;
					Speed.y = -Speed.y;
				}
				break;
			}
			// если объект за€ц
			if (item.getKey() instanceof Hare) {
				if (item.getValue() <= 1.5 && Hunger < 490 && item.getKey().getAlive()) {
					// поедание зайца
					item.getKey().deleteHare();
					TMap.deleteItem(item.getKey());
					Hunger = 500;
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
							if (Speed.x >= 0)
								Speed.x = -Speed.x;
						}	
						else
							if (this.Coordinates.x < item.getKey().Coordinates.x) {
								if (Speed.x <= 0)
									Speed.x = -Speed.x;
							}
							else
								Speed.x = 0;				
						// изменение координаты y
						if (this.Coordinates.y > item.getKey().Coordinates.y) {
							if (Speed.y >= 0)
								Speed.y = -Speed.y;
						}
						else
							if (this.Coordinates.y < item.getKey().Coordinates.y) {
								if (Speed.y <= 0)
									Speed.y = -Speed.y;
							}
							else
								Speed.y = 0;
					}
					minDistance = item.getValue();
				}
			}
			if (item.getKey() instanceof Obstacle) {
				if ((Coordinates.x + Speed.x >= item.getKey().Coordinates.x - item.getKey().getSize() / 2 - 5)
						&& (Coordinates.x + Speed.x <= item.getKey().Coordinates.x + item.getKey().getSize() / 2 + 5)
						&& (Coordinates.y + Speed.y >= item.getKey().Coordinates.y + item.getKey().getSize() / 2 - 5)
						&& (Coordinates.y + Speed.y <= item.getKey().Coordinates.y + item.getKey().getSize() / 2 + 5)) {
					if (Coordinates.x >= item.getKey().Coordinates.x + item.getKey().getSize() / 2 
							|| item.getKey().Coordinates.x >= Coordinates.x) {
						Speed.y = 0;
						break;
					}
					if (Coordinates.y > item.getKey().Coordinates.y + item.getKey().getSize() / 2
							|| item.getKey().Coordinates.y > Coordinates.y) {
						Speed.x = 0;
						break;
					}
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
	
	// установить максимальное рассто€ние, на котором объект видит другие объекты
	public static void setVision(int vision) {
		Vision = vision;
	}
	
	// получить максимальное рассто€ние, на котором объект видит другие объекты
	public int getVision() {
		return Vision;
	}
	
	// получить возраст
	public int getAge() {
		return Age;
	}
	
	// получить значение голода
	public int getHunger() {
		return Hunger;
	}
	
	// получить скорость
	public Point getSpeed() {
		return Speed;
	}
}
