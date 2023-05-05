//Лабораторна робота №5
//Завдання 2

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lab5_2 {
    public static void main(String[] args) {
        // Створюємо список для зберігання назв віршів
        List<String> poems = new ArrayList<>();
        
        // Додаємо назви віршів деякого автора до списку
        poems.add("Тече вода в ярі");
        poems.add("Червона калина");
        poems.add("Моя мрія");
        poems.add("І знову вітер, і знову дощ");
        poems.add("Ой у лузі червона калина");
        poems.add("Все летить і все минає");
        
        // Сортуємо список за зростанням довжини рядків
        Collections.sort(poems, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        
        // Виводимо вірші у відсортованому порядку
        for (String poem : poems) {
            System.out.println(poem);
        }
    }
}