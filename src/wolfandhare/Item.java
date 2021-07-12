package wolfandhare;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.*;

public class Item {
	protected Point Coordinates;	// координаты объекта
	
	public Item() { 
		Coordinates = new Point(0, 0);
	}
	
	public Item(int x, int y) {
		Coordinates = new Point(x, y);
	}
	
	public Item(Item src) {
		Coordinates = new Point(src.Coordinates.x, src.Coordinates.y);
	}
	
	// установить координаты объекта
	public void setCoordinates(int x, int y) {
		Coordinates.x = x;
		Coordinates.y = y;
	}
	
	// нарисовать объект
	public void draw(Canvas canvas) { }
	
	// выполнить действие над объектом
	public void action(Canvas canvas) { }

	// получить максимальное рассто€ние, на котором объект видит другие объекты
	public int getVision() { return 0;}
	
	// установить максимальное рассто€ние, на котором объект видит другие объекты
	public static void setVision(int vision) { }
	
	// получить возраст объекта
	public int getAge() { return -1;}
	
	// получить значение голода объекта
	public int getHunger() { return -1; }
	
	// получить скорость объекта
	public Point getSpeed() { return null; }
	
	// получить размер объекта
	public int getSize() { return -1;}
	
	// жив или нет
	public boolean getAlive() { return false;}
	
	// за€ц мертв
	public void deleteHare() { }
}
