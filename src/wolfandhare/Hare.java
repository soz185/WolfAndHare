package wolfandhare;

import java.util.*;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.*;

public class Hare extends Item{

	private static int CountHare = 0;	// количестов зайцев
	private static int Vision;	// максимальное рассто€ние, на котором объект видит другие объекты
	private int Age;	// возраст
	private Point Speed;	// скорость
	
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
		if (Age > 30)
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
		if (Age > 300)
			{
				TMap.deleteItem(this);
				CountHare--;
				return;
			}
		// обход списка всех видимых объектов
		for (Map.Entry<Item, Double> item : TMap.getVisibleItems(this).entrySet()) {
			// если объект за€ц
			if (item.getKey() instanceof Hare)
				if (item.getValue() <= 1.0 && item.getKey().getAge() > 50 && this.Age > 50) {
					// создание нового зайца
					TMap.newHare(this.Coordinates.x, this.Coordinates.y);
					Speed.x = -Speed.x;
					Speed.y = -Speed.y;
				}
			//if (item instanceof Wolf)
				
		}
		Age++;
		move(canvas);
	}
	
	// обнуление количества зайцев
	public static void clearCount() {
		CountHare = 0;
	}
	
	// уменьшение количества зайцев
	public static void reduceCount() {
		CountHare--;
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
	
	// получить скорость
	public Point getSpeed() {
		return Speed;
	}
	
	// получить количество зайцев
	public static int getCountHare() {
		return CountHare;
	}
}
