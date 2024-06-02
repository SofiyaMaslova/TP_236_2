package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    /**
     * Основная функция программы
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            List<Integer> numbers = readNumbersFromFile("test.txt");
            System.out.println("Минимальное: " + getMin(numbers));
            System.out.println("Максимальное: " + getMax(numbers));
            System.out.println("Сумма: " + getSum(numbers));
            System.out.println("Произведение: " + getMult(numbers));
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    /**
     * Функция для считывания чисел из файла
     *
     * @param filename
     * @return List of Integers
     * @throws IOException
     */
    public static List<Integer> readNumbersFromFile(String filename) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return Arrays.stream(content.split("\\s+")).map(Integer::parseInt).toList();
    }

    /**
     * Функция для поиска минимума в списке
     *
     * @param numbers
     * @return
     */
    public static int getMin(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new NoSuchElementException("Пустой список!");
        }
        return numbers.stream().min(Integer::compare).orElseThrow();
    }

    /**
     * Функция для поиска максимума в списке
     *
     * @param numbers
     * @return
     */
    public static int getMax(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new NoSuchElementException("Пустой список!");
        }
        return numbers.stream().max(Integer::compare).orElseThrow();
    }

    /**
     * Функция для поиска суммы в списке
     *
     * @param numbers
     * @return
     */
    public static int getSum(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new NoSuchElementException("Пустой список!");
        }
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Функция для поиска произведения в списке
     *
     * @param numbers
     * @return
     */
    public static long getMult(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new NoSuchElementException("Пустой список!");
        }
        return numbers.stream().mapToLong(Integer::longValue).reduce(1, (a, b) -> a * b);
    }
}
