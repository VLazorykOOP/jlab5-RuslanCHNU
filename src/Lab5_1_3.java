//Лабораторна робота №5
//Завдання 1.3

import java.util.*;
import java.io.*;

abstract class Record implements Comparable<Record> {
    protected String name;
    protected int year;

    public Record(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public abstract void print();

    @Override
    public int compareTo(Record other) {
        int result = this.name.compareTo(other.name);
        if (result == 0) {
            result = Integer.compare(this.year, other.year);
        }
        return result;
    }
}

class Book extends Record {
    private String author;

    public Book(String name, int year, String author) {
        super(name, year);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public void print() {
        System.out.println("Книга: " + name + ", автор: " + author + ", рік видання: " + year);
    }
}

class Movie extends Record {
    private String director;

    public Movie(String name, int year, String director) {
        super(name, year);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public void print() {
        System.out.println("Фільм: " + name + ", режисер: " + director + ", рік випуску: " + year);
    }
}

class RecordComparator implements Comparator<Record> {
    @Override
    public int compare(Record r1, Record r2) {
        int result = Integer.compare(r1.getYear(), r2.getYear());
        if (result == 0) {
            result = r1.getName().compareTo(r2.getName());
        }
        return result;
    }
}

public class Lab5_1_3 {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList<>();

        // Читання даних з файлів та занесення їх у колекції
        try (Scanner bookScanner = new Scanner(new File("books.txt"))) {
            while (bookScanner.hasNextLine()) {
                String name = bookScanner.nextLine();
                int year = bookScanner.nextInt();
                bookScanner.nextLine();  // Consume the remaining newline character
                String author = bookScanner.nextLine();
                books.add(new Book(name, year, author));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл books.txt не знайдено");
        }

        try (Scanner movieScanner = new Scanner(new File("movies.txt"))) {
            while (movieScanner.hasNextLine()) {
                String name = movieScanner.nextLine();
                int year = movieScanner.nextInt();
                movieScanner.nextLine();  // Consume the remaining newline character
                String director = movieScanner.nextLine();
                movies.add(new Movie(name, year, director));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл movies.txt не знайдено");
        }

        // Виведення даних на екран
        System.out.println("Книги:");
        for (Book book : books) {
            book.print();
        }

        System.out.println("\nФільми:");
        for (Movie movie : movies) {
            movie.print();
        }

        // Сортування та виведення даних на екран
        Collections.sort(books);
        Collections.sort(movies);

        System.out.println("\nКниги (відсортовані):");
        for (Book book : books) {
            book.print();
        }

        System.out.println("\nФільми (відсортовані):");
        for (Movie movie : movies) {
            movie.print();
        }

        // Додавання нових записів
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("\nВведіть назву книги: ");
        String bookName = inputScanner.nextLine();
        System.out.print("Введіть автора книги: ");
        String bookAuthor = inputScanner.nextLine();
        System.out.print("Введіть рік видання книги: ");
        int bookYear = inputScanner.nextInt();
        inputScanner.nextLine();  // Consume the remaining newline character
        books.add(new Book(bookName, bookYear, bookAuthor));

        System.out.print("\nВведіть назву фільму: ");
        String movieName = inputScanner.nextLine();
        System.out.print("Введіть режисера фільму: ");
        String movieDirector = inputScanner.nextLine();
        System.out.print("Введіть рік випуску фільму: ");
        int movieYear = inputScanner.nextInt();
        inputScanner.nextLine();  // Consume the remaining newline character
        movies.add(new Movie(movieName, movieYear, movieDirector));

        // Сортування та виведення даних на екран
        Collections.sort(books, new RecordComparator());
        Collections.sort(movies, new RecordComparator());

        System.out.println("\nКниги (відсортовані):");
        for (Book book : books) {
            book.print();
        }

        System.out.println("\nФільми (відсортовані):");
        for (Movie movie : movies) {
            movie.print();
        }

        // Запис відсортованих даних у файл
        try (PrintWriter writer = new PrintWriter(new File("sorted_records.txt"))) {
            writer.println("Книги:");
            for (Book book : books) {
                writer.println(book.getName() + " (" + book.getYear() + "), автор " + book.getAuthor());
            }

            writer.println("\nФільми:");
            for (Movie movie : movies) {
                writer.println(movie.getName() + " (" + movie.getYear() + "), режисер " + movie.getDirector());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не вдалося записати в файл sorted_records.txt");
        }
    }
}