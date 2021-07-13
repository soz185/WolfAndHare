package wolfandhare;

import java.util.*;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.*;

public class Obstacle extends Item{
	
	private int Size;	// ������
	
	public Obstacle() {
		super();
		Random random = new Random();
		Coordinates.x = 0;
		Coordinates.y = 0;
		Size = random.nextInt(10) + 10;
	}
	
	public Obstacle(Item src) {
		super(src);
		Size = src.getSize();
	}
	
	// ��������� ��������
	@Override
    public boolean equals(Object object)
    {
        boolean result = false;
        if (object != null && object instanceof Obstacle)
        {
        	if ((Coordinates.x == ((Obstacle)object).Coordinates.x) && (Coordinates.y == ((Obstacle)object).Coordinates.y)
            		&& (Size == ((Obstacle) object).Size)) {
        		result = true;
        	}
        }
        return result;
    }
	
	// ��������� �����������
	public void draw(Canvas canvas) { 
		GC graphic = new GC(canvas);
		graphic.setBackground(new Color(canvas.getDisplay(), 0, 170, 30));	// ���� - �������
		graphic.fillRectangle(Coordinates.x - Size / 2, Coordinates.y - Size / 2, Size / 2, Size / 2);
		graphic.dispose();
	}
	
	// �������� ������
	public int getSize() {
		return Size;
	}
}
