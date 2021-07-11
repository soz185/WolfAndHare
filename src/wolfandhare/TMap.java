package wolfandhare;

import java.util.*;
import org.eclipse.swt.widgets.Canvas;

public class TMap {
	private static TMap Map;
	private static ArrayList<Item> Items;
	private static ArrayList<Item> PreviousItems;
	
	public TMap() {	}
	
	public static TMap getMap(Canvas canvas, int countHare, int countWolf) {
		if (Map == null)
			Map = new TMap();
		return Map;
	}
	
	public void setMap(Canvas canvas, int countHare, int countWolf) {
		if (Items == null && PreviousItems == null) {
			Items = new ArrayList<Item>();
			PreviousItems = new ArrayList<Item>();
		}
		else {
			Items.clear();
			PreviousItems.clear();
		}
		Wolf.clearCount();
		Hare.clearCount();
		Random random = new Random();
		for(int i = 0; i < countWolf; i++) {
			Item item = new Wolf();
			item.setCoordinates(random.nextInt(canvas.getSize().x - 6) + 3, random.nextInt(canvas.getSize().y - 6) + 3);
			Items.add(item);
		}
		for(int i = 0; i < countHare; i++) {
			Item item = new Hare();
			item.setCoordinates(random.nextInt(canvas.getSize().x - 6) + 3, random.nextInt(canvas.getSize().y - 6) + 3);
			Items.add(item);
		}
	}
	
	public static void copy(ArrayList<Item> dest, ArrayList<Item> src) {
		dest.clear();
		for (Item item : src) {
			Item copyItem;
			if (item instanceof Wolf) {
				copyItem = new Wolf(item);
				dest.add(copyItem);
				continue;
			}
			if (item instanceof Hare) {
				copyItem = new Hare(item);
				dest.add(copyItem);
				continue;
			}
		}
	}
	
	public static void newWolf(int x, int y) {
		Random random = new Random();
		Item item = new Wolf();
		item.setCoordinates(x, y);
		Items.add(item);
	}
	
	public static void newHare(int x, int y) {
		Random random = new Random();
		Item item = new Hare();
		item.setCoordinates(x, y);
		Items.add(item);
	}
	
	public static void deleteItem(Item item) {
		Items.remove(item);
	}
	
	public void  draw(Canvas canvas) {
		copy(PreviousItems, Items);
		for(Item item : PreviousItems) {
			item.draw(canvas);
			/*if (Items.indexOf(item) != -1)*/ if (Items.contains(item))
				Items.get(Items.indexOf(item)).action(canvas);
		}
	}
	
	public static  Map<Item, Double> getVisibleItems(Item item) {
		Map<Item, Double> visibleItems = new HashMap<Item, Double>();
		for (Item visible : PreviousItems) {
			if (!visible.equals(item)) {
				double distance = Math.pow((visible.Coordinates.x - item.Coordinates.x) * (visible.Coordinates.x - item.Coordinates.x) + (visible.Coordinates.y - item.Coordinates.y) * (visible.Coordinates.y - item.Coordinates.y), 1.0 / 2);
				if (item.getVision() >= distance)
					visibleItems.put(visible, distance);
			}
		}
			
		return visibleItems;
	}
}
