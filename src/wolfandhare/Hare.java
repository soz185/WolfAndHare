package wolfandhare;

import java.util.*;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.*;

public class Hare extends Item{

	private static int Vision;	// максимальное рассто€ние, на котором объект видит другие объекты
	private int Age;	// возраст
	private Point Speed;	// скорость
	private boolean isAlive;
	
	public Hare() {
		super();
		Random random = new Random();
		Coordinates.x = 0;
		Coordinates.y = 0;
		Age = random.nextInt(30);
		Speed = new Point(random.nextInt(4) - 2, random.nextInt(4) - 2);
		isAlive = true;
	}
	
	public Hare(Item src) {
		super(src);
		Age = src.getAge();
		Speed = new Point(src.getSpeed().x, src.getSpeed().y);
		isAlive = true;
	}
	
	// сравнение объектов
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
	
	// отрисовка зайца
	public void draw(Canvas canvas) { 
		GC graphic = new GC(canvas);
		if (Age > 100)
			graphic.setBackground(new Color(canvas.getDisplay(), 90, 90, 90)); // взрослый за€ц - темно-серый
		else
			graphic.setBackground(new Color(canvas.getDisplay(), 180, 180, 180));	// молодой за€ц - серый
		graphic.fillOval(Coordinates.x - 3, Coordinates.y - 3, 6, 6);
		graphic.dispose();
	}
	
	// передвижение зайца
	private void move(Canvas canvas) {
		if (Coordinates.x + Speed.x < 3 || Coordinates.x + Speed.x > canvas.getSize().x - 3)
			Speed.x = -Speed.x;
		if (Coordinates.y + Speed.y < 3 || Coordinates.y + Speed.y > canvas.getSize().y - 3)
			Speed.y = -Speed.y; 
		Coordinates.x += Speed.x;
		Coordinates.y += Speed.y;
	}
	
	// действие зайца
	public void action(Canvas canvas) {
		// удаление старого зайца
		if (Age > 1000)
			{
				isAlive = false;
				TMap.deleteItem(this);
				return;
			}
		Random random = new Random();
		double minDistance = Double.MAX_VALUE;
		// обход списка всех видимых объектов
		for (Map.Entry<Item, Double> item : TMap.getVisibleItems(this).entrySet()) {
			// если объект за€ц
			if (item.getKey() instanceof Hare)
				if (item.getValue() < 1.0 && item.getKey().getAge() > 100 && this.Age > 100) {
					if (item.getKey().getSpeed().x != 0 && item.getKey().getSpeed().y != 0
							&& Speed.x != 0 && Speed.y != 0) {
						// создание нового зайца
						TMap.newHare(this.Coordinates.x, this.Coordinates.y);
						TMap.newHare(this.Coordinates.x, this.Coordinates.y);
						Speed.x = -Speed.x;
						Speed.y = -Speed.y;
						break;
					}
				}
			// если объект волк
			if (item.getKey() instanceof Wolf) {
				if (minDistance > item.getValue()) {
					if (this.Coordinates.x < item.getKey().Coordinates.x) {
						if (Speed.x >= 0)
							Speed.x = -Speed.x;
					}	
					else
						if (this.Coordinates.x > item.getKey().Coordinates.x) {
							if (Speed.x <= 0)
								Speed.x = -Speed.x;
						}
						else
							Speed.x = 0;				
					// изменение координаты y
					if (this.Coordinates.y < item.getKey().Coordinates.y) {
						if (Speed.y >= 0)
							Speed.y = -Speed.y;
					}
					else
						if (this.Coordinates.y > item.getKey().Coordinates.y) {
							if (Speed.y <= 0)
								Speed.y = -Speed.y;
						}
						else
							Speed.y = 0;
				}
				minDistance = item.getValue();
			}
		}
		Age++;
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
	
	// за€ц мертв
	public void deleteHare() {
		isAlive = false;;
	}
	
	// получить максимальное рассто€ние, на котором объект видит другие объекты
	public int getVision() {
		return Vision;
	}
	
	// получить возраст
	public int getAge() {
		return Age;
	}
	
	// получить скорость
	public Point getSpeed() {
		return Speed;
	}
	
	// ∆ив за€ц или нет
	public boolean getAlive() {
		return isAlive;
	}
}
