/*SLIP 6: 
 * Write a Java Program to implement Iterator Pattern for Designing Menu like Breakfast, Lunch or Dinner Menu.
 */
import java.util.Iterator;
import java.util.ArrayList;

// MenuItem class representing an item in the menu
class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item: " + name + ", Price: $" + price;
    }
}

// Menu interface providing methods to create an iterator
interface Menu {
    Iterator<MenuItem> createIterator();
}

// BreakfastMenu class representing a breakfast menu
class BreakfastMenu implements Menu {
    private ArrayList<MenuItem> items;

    public BreakfastMenu() {
        items = new ArrayList<>();
        // Add breakfast items
        addItem("Eggs and Toast", 5.99);
        addItem("Pancakes", 6.49);
        addItem("Omelette", 7.99);
    }

    private void addItem(String name, double price) {
        MenuItem menuItem = new MenuItem(name, price);
        items.add(menuItem);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return items.iterator();
    }
}

// LunchMenu class representing a lunch menu
class LunchMenu implements Menu {
    private MenuItem[] items;
    private int numberOfItems = 0;

    public LunchMenu() {
        items = new MenuItem[3];
        // Add lunch items
        addItem("Chicken Sandwich", 8.99);
        addItem("Salad", 7.49);
        addItem("Burger", 9.99);
    }

    private void addItem(String name, double price) {
        MenuItem menuItem = new MenuItem(name, price);
        if (numberOfItems < items.length) {
            items[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return new LunchMenuIterator(items);
    }
}

// LunchMenuIterator class for iterating through lunch items
class LunchMenuIterator implements Iterator<MenuItem> {
    private MenuItem[] items;
    private int position = 0;

    public LunchMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.length && items[position] != null;
    }

    @Override
    public MenuItem next() {
        MenuItem menuItem = items[position];
        position++;
        return menuItem;
    }
}

// MenuClient class demonstrating the use of the iterator
public class MenuClient {
    public static void main(String[] args) {
        // Create menus
        Menu breakfastMenu = new BreakfastMenu();
        Menu lunchMenu = new LunchMenu();

        // Display items using iterators
        System.out.println("Breakfast Menu:");
        printMenu(breakfastMenu.createIterator());

        System.out.println("\nLunch Menu:");
        printMenu(lunchMenu.createIterator());
    }

    private static void printMenu(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            System.out.println(menuItem);
        }
    }
}
