//Лабораторна робота №5
//Завдання 1.1

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

abstract class Detail implements Comparable<Detail> {
    protected String name;
    protected double weight;
    protected double cost;

    public Detail(String name, double weight, double cost) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getCost() {
        return cost;
    }

    public abstract void show();

    @Override
    public int compareTo(Detail other) {
        return this.name.compareTo(other.name);
    }
}

class Mechanism extends Detail {
    private int numParts;

    public Mechanism(String name, double weight, double cost, int numParts) {
        super(name, weight, cost);
        this.numParts = numParts;
    }

    public int getNumParts() {
        return numParts;
    }

    @Override
    public void show() {
        System.out.println("Назва: " + name);
        System.out.println("Вага: " + weight);
        System.out.println("Вартість: " + cost);
        System.out.println("Кількість деталей: " + numParts);
    }
}

class Product extends Detail {
    private String manufacturer;

    public Product(String name, double weight, double cost, String manufacturer) {
        super(name, weight, cost);
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void show() {
        System.out.println("Назва: " + name);
        System.out.println("Вага: " + weight);
        System.out.println("Вартість: " + cost);
        System.out.println("Виробник: " + manufacturer);
    }
}

class Node extends Detail {
    private String location;

    public Node(String name, double weight, double cost, String location) {
        super(name, weight, cost);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public void show() {
        System.out.println("Назва: " + name);
        System.out.println("Вага: " + weight);
        System.out.println("Вартість: " + cost);
        System.out.println("Локація: " + location);
    }
}

public class Lab5_1 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Detail> details = new ArrayList<>();
        File file;
        Scanner scanner;
        String name;
        double weight;
        double cost;
        int numParts;
        String manufacturer;
        String location;

        // Read details from files and add them to the collection
        file = new File("mechanisms.txt");
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            name = scanner.nextLine();
            weight = Double.parseDouble(scanner.nextLine());
            cost = Double.parseDouble(scanner.nextLine());
            numParts = Integer.parseInt(scanner.nextLine());
            details.add(new Mechanism(name, weight, cost, numParts));
        }
        scanner.close();

        file = new File("products.txt");
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            name = scanner.nextLine();
            weight = Double.parseDouble(scanner.nextLine());
            cost = Double.parseDouble(scanner.nextLine());
            manufacturer = scanner.nextLine();
            details.add(new Product(name, weight, cost, manufacturer));
        }
        scanner.close();

        file = new File("nodes.txt");
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            name = scanner.nextLine();
            weight = Double.parseDouble(scanner.nextLine());
            cost = Double.parseDouble(scanner.nextLine());
            location = scanner.nextLine();
            details.add(new Node(name, weight, cost, location));
        }
        scanner.close();

        // Print the details
        System.out.println("Деталі до сортування:");
        for (Detail detail : details) {
            detail.show();
            System.out.println("------------------");
        }

        // Sort the details and print them
        Collections.sort(details);
        System.out.println("Деталі після сортування за назвою:");
        for (Detail detail : details) {
            detail.show();
            System.out.println("------------------");
        }

        // Add a new detail to each collection from the keyboard
        Scanner input = new Scanner(System.in);
        System.out.print("Введіть назву нової механізму: ");
        name = input.nextLine();
        System.out.print("Введіть вагу нової механізму: ");
        weight = Double.parseDouble(input.nextLine());
        System.out.print("Введіть вартість нової механізму: ");
        cost = Double.parseDouble(input.nextLine());
        System.out.print("Введіть кількість деталей нової механізму: ");
        numParts = Integer.parseInt(input.nextLine());
        details.add(new Mechanism(name, weight, cost, numParts));

        System.out.print("Введіть назву нового продукту: ");
        name = input.nextLine();
        System.out.print("Введіть вагу нового продукту: ");
        weight = Double.parseDouble(input.nextLine());
        System.out.print("Введіть вартість нового продукту: ");
        cost = Double.parseDouble(input.nextLine());
        System.out.print("Введіть виробника нового продукту: ");
        manufacturer = input.nextLine();
        details.add(new Product(name, weight, cost, manufacturer));

        System.out.print("Введіть назву нового вузла: ");
        name = input.nextLine();
        System.out.print("Введіть вагу нового вузла: ");
        weight = Double.parseDouble(input.nextLine());
        System.out.print("Введіть вартість нового вузла: ");
        cost = Double.parseDouble(input.nextLine());
        System.out.print("Введіть локацію нового вузла: ");
        location = input.nextLine();
        details.add(new Node(name, weight, cost, location));

        input.close();

        // Sort the details and print them again
        Collections.sort(details, new DetailComparator());
        System.out.println("Деталі після сортування за вагою:");
        for (Detail detail : details) {
            detail.show();
            System.out.println("------------------");
        }

        // Write the sorted details to a file
        PrintWriter writer = new PrintWriter("sorted_details.txt");
        for (Detail detail : details) {
            writer.println(detail.getClass().getSimpleName());
            writer.println(detail.getName());
            writer.println(detail.getWeight());
            writer.println(detail.getCost());
            if (detail instanceof Mechanism) {
                writer.println(((Mechanism) detail).getNumParts());
            } else if (detail instanceof Product) {
                writer.println(((Product) detail).getManufacturer());
            } else if (detail instanceof Node) {
                writer.println(((Node) detail).getLocation());
            }
        }
        writer.close();
    }

    static class DetailComparator implements Comparator<Detail> {
        @Override
        public int compare(Detail detail1, Detail detail2) {
            if (detail1.getWeight() < detail2.getWeight()) {
                return -1;
            } else if (detail1.getWeight() > detail2.getWeight()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}