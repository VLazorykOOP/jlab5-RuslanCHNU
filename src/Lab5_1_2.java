//Лабораторна робота №5
//Завдання 1.2

import java.io.*;
import java.util.*;

abstract class Equation {
    protected double[] roots;

    public abstract void computeRoots();

    public void printRoots() {
        if (roots != null) {
            for (double root : roots) {
                System.out.println(root);
            }
        }
    }
}

class LinearEquation extends Equation implements Comparable<LinearEquation> {
    private double a;
    private double b;

    public LinearEquation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void computeRoots() {
        if (a != 0) {
            roots = new double[1];
            roots[0] = -b / a;
        }
    }

    @Override
    public String toString() {
        return "Лінійне рівняння: " + a + "x + " + b + " = 0";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LinearEquation) {
            LinearEquation other = (LinearEquation) obj;
            return a == other.a && b == other.b;
        }
        return false;
    }

    @Override
    public int compareTo(LinearEquation other) {
        if (this.a == other.a) {
            return Double.compare(this.b, other.b);
        }
        return Double.compare(this.a, other.a);
    }
}

class SquareEquation extends Equation implements Comparable<SquareEquation> {
    private double a;
    private double b;
    private double c;

    public SquareEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void computeRoots() {
        double d = b * b - 4 * a * c;
        if (d > 0) {
            roots = new double[2];
            roots[0] = (-b + Math.sqrt(d)) / (2 * a);
            roots[1] = (-b - Math.sqrt(d)) / (2 * a);
        } else if (d == 0) {
            roots = new double[1];
            roots[0] = -b / (2 * a);
        }
    }

    @Override
    public String toString() {
        return "Квадратне рівняння: " + a + "x^2 + " + b + "x + " + c + " = 0";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SquareEquation) {
            SquareEquation other = (SquareEquation) obj;
            return a == other.a && b == other.b && c == other.c;
        }
        return false;
    }

    @Override
    public int compareTo(SquareEquation other) {
        double thisMax = Math.max(this.a, Math.max(this.b, this.c));
        double otherMax = Math.max(other.a, Math.max(other.b, other.c));
        if (thisMax == otherMax) {
            double thisMin = Math.min(this.a, Math.min(this.b, this.c));
            double otherMin = Math.min(other.a, Math.min(other.b, other.c));
            return Double.compare(thisMin, otherMin);
        }
        return Double.compare(thisMax, otherMax);
    }
}

public class Lab5_1_2 {
    public static void main(String[] args) {
        ArrayList<LinearEquation> linearEquations = new ArrayList<>();
        ArrayList<SquareEquation> squareEquations = new ArrayList<>();

        // Читання даних з файлів та занесення їх у колекції
        try (Scanner linearScanner = new Scanner(new File("linear.txt"))) {
            while (linearScanner.hasNextDouble()) {
                double a = linearScanner.nextDouble();
                double b = linearScanner.nextDouble();
                linearEquations.add(new LinearEquation(a, b));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл linear.txt не знайдено");
        }

        try (Scanner squareScanner = new Scanner(new File("square.txt"))) {
            while (squareScanner.hasNextDouble()) {
                double a = squareScanner.nextDouble();
                double b = squareScanner.nextDouble();
                double c = squareScanner.nextDouble();
                squareEquations.add(new SquareEquation(a, b, c));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл square.txt не знайдено");
        }

    // Виведення даних на екран
    System.out.println("Лінійні рівняння:");
    Collections.sort(linearEquations);
    for (LinearEquation eq : linearEquations) {
        eq.computeRoots();
        System.out.println(eq);
        eq.printRoots();
    }

    System.out.println("Квадратні рівняння:");
    Collections.sort(squareEquations);
    for (SquareEquation eq : squareEquations) {
        eq.computeRoots();
        System.out.println(eq);
        eq.printRoots();
    }
}
}