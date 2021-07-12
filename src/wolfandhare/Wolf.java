package wolfandhare;

import java.util.*;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.*;

public class Wolf extends Item{

	private int Hunger;	// �����
	private static int Vision;	// ������������ ����������, �� ������� ������ ����� ������ �������
	private int Age;	// �������
	private Point Speed;	// ��������
	
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
	
	// ��������� ��������
	// true - �����, false - �� �����
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
	
	// ��������� �����
	public void draw(Canvas canvas) { 
		GC graphic = new GC(canvas);
		if (Age > 70)
			graphic.setBackground(new Color(canvas.getDisplay(), 210, 0, 0));	// �������� ���� - �������
		else
			graphic.setBackground(new Color(canvas.getDisplay(), 255, 138, 138));	// ������� ���� - �������
		graphic.fillOval(Coordinates.x - 3, Coordinates.y - 3, 6, 6);
		graphic.dispose();
	}
	
	// ������������ �����
	private void move(Canvas canvas) {
		if (Coordinates.x + Speed.x < 3 || Coordinates.x + Speed.x > canvas.getSize().x - 3)
			Speed.x = -Speed.x;
		if (Coordinates.y + Speed.y < 3 || Coordinates.y + Speed.y > canvas.getSize().y - 3)
			Speed.y = -Speed.y; 
		Coordinates.x += Speed.x;
		Coordinates.y += Speed.y;
	}
	
	// �������� �����
	public void action(Canvas canvas) {
		// �������� �������� �� �������� ��� ������ �����
		if (Hunger == 0 || Age > 5000)
			{
				TMap.deleteItem(this);
				return;
			}
		Random random = new Random();
		double minDistance = Double.MAX_VALUE;
		// ����� ���� ������� ����� ��������
		for (Map.Entry<Item, Double> item : TMap.getVisibleItems(this).entrySet()) {
			// ���� ������ ����
			if (item.getKey() instanceof Wolf) {
				if (item.getValue() <= 1.0 && item.getKey().getAge() > 300 && this.Age > 300) {
					// �������� ������ �����
					TMap.newWolf(this.Coordinates.x, this.Coordinates.y);
					Speed.x = -Speed.x;
					Speed.y = -Speed.y;
				}
				break;
			}
			// ���� ������ ����
			if (item.getKey() instanceof Hare) {
				if (item.getValue() <= 1.5 && Hunger < 490 && item.getKey().getAlive()) {
					// �������� �����
					item.getKey().deleteHare();
					TMap.deleteItem(item.getKey());
					Hunger = 500;
					Speed.x = random.nextInt(4) - 2;
					Speed.y = random.nextInt(4) - 2;
					break;
				}
				//
				// �������������
				// 
				else {
					// ��������� ���������� x
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
						// ��������� ���������� y
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
	
	// ���������� ������������ ����������, �� ������� ������ ����� ������ �������
	public static void setVision(int vision) {
		Vision = vision;
	}
	
	// �������� ������������ ����������, �� ������� ������ ����� ������ �������
	public int getVision() {
		return Vision;
	}
	
	// �������� �������
	public int getAge() {
		return Age;
	}
	
	// �������� �������� ������
	public int getHunger() {
		return Hunger;
	}
	
	// �������� ��������
	public Point getSpeed() {
		return Speed;
	}
}
