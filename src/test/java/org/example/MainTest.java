package org.example;

import org.example.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/** Класс для тестирования основных функций {@link Main} */
class MainTest {

    @TempDir Path tempDir;

    private Path testFile;

    private final List<Integer> normalData = List.of(1, 2, 3, 4);
    private final List<Integer> singleElementData = List.of(4);
    private final List<Integer> emptyData = new ArrayList<>();

    private final Random random = new Random();

    @BeforeEach
    void setUp() throws IOException {
        testFile = tempDir.resolve("testFile.txt");
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(testFile))) {
            out.println("1 4 2 3");
        }
    }

    /**
     * Функция для тестирования считывания данных их файла, когда там есть данные {@link
     * Main#readNumbersFromFile(String filename)}
     */
    @Test
    void testReadNumbersFromFile() throws IOException {
        List<Integer> numbers = Main.readNumbersFromFile(testFile.toString());
        assertEquals(List.of(1, 4, 2, 3), numbers);
    }

    /**
     * Функция для тестирования считывания данных их файла, когда файл пустой {@link
     * Main#readNumbersFromFile(String filename)}
     */
    @Test
    void testReadNumbersFromFileEmpty() throws IOException {
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(testFile))) {
            out.println("");
        }
        List<Integer> numbers = Main.readNumbersFromFile(testFile.toString());
        assertTrue(numbers.isEmpty());
    }

    /**
     * Функция для тестирования считывания данных их файла, когда средли чисел есть буква {@link
     * Main#readNumbersFromFile(String filename)}
     */
    @Test
    void testReadNumbersFromFileWithLetter() throws IOException {
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(testFile))) {
            out.println("1 2 3 a");
        }
        assertThrows(NumberFormatException.class, () -> Main.readNumbersFromFile(testFile.toString()));
    }

    /**
     * Функция для тестирования считывания данных их файла, когда файла не существует {@link
     * Main#readNumbersFromFile(String filename)}
     */
    @Test
    void testReadNumbersFromFileNotExists() {
        assertThrows(IOException.class, () -> Main.readNumbersFromFile("notExists.txt"));
    }

    /**
     * Функция для тестирования поиска минимума, когда более одного числа в списке {@link
     * Main#getMin(List)}
     */
    @Test
    void minWithNormalData() {
        assertEquals(1, Main.getMin(normalData));
    }

    /**
     * Функция для тестирования поиска максимума, когда более одного числа в списке {@link
     * Main#getMax(List)}
     */
    @Test
    void maxWithNormalData() {
        assertEquals(4, Main.getMax(normalData));
    }

    /**
     * Функция для тестирования поиска суммы, когда более одного числа в списке {@link
     * Main#getSum(List)}
     */
    @Test
    void sumWithNormalData() {
        assertEquals(10, Main.getSum(normalData));
    }

    /**
     * Функция для тестирования поиска произведения, когда более одного числа в списке {@link
     * Main#getMult(List)}
     */
    @Test
    void multWithNormalData() {
        assertEquals(24, Main.getMult(normalData));
    }

    /** Функция для тестирования поиска минимума, когда одно числа в списке {@link Main#getMin(List)} */
    @Test
    void minSingleElementData() {
        assertEquals(4, Main.getMin(singleElementData));
    }

    /**
     * Функция для тестирования поиска максимума, когда одно числа в списке {@link Main#getMax(List)}
     */
    @Test
    void maxSingleElementData() {
        assertEquals(4, Main.getMax(singleElementData));
    }

    /** Функция для тестирования поиска суммы, когда одно числа в списке {@link Main#getSum(List)} */
    @Test
    void sumSingleElementData() {
        assertEquals(4, Main.getSum(singleElementData));
    }

    /**
     * Функция для тестирования поиска произведения, когда одно числа в списке {@link
     * Main#getMult(List)}
     */
    @Test
    void multSingleElementData() {
        assertEquals(4, Main.getMult(singleElementData));
    }

    /** Функция для тестирования поиска минимума, когда нет чисел в списке {@link Main#getMin(List)} */
    @Test
    void minEmptyData() {
        assertThrows(NoSuchElementException.class, () -> Main.getMin(emptyData));
    }

    /** Функция для тестирования поиска максимума, когда нет чисел в списке {@link Main#getMax(List)} */
    @Test
    void maxEmptyData() {
        assertThrows(NoSuchElementException.class, () -> Main.getMax(emptyData));
    }

    /** Функция для тестирования поиска суммы, когда нет чисел в списке {@link Main#getSum(List)} */
    @Test
    void sumEmptyData() {
        assertThrows(NoSuchElementException.class, () -> Main.getSum(emptyData));
    }

    /**
     * Функция для тестирования поиска произведения, когда нет чисел в списке {@link Main#getMult(List)}
     */
    @Test
    void multEmptyData() {
        assertThrows(NoSuchElementException.class, () -> Main.getMult(emptyData));
    }

    /** Функция для тестирования производительности поиска минимума {@link Main#getMin(List)} */
    @Test
    void performanceTestForMin() {
        for (int size = 1000; size <= 100000000; size *= 10) {
            List<Integer> numbers = generateRandomList(size);
            long startTime = System.nanoTime();
            Main.getMin(numbers);
            long endTime = System.nanoTime();
            System.out.println(
                    "Время выполнения min для " + size + " элементов: " + (endTime - startTime) + " нс");
        }
    }

    /** Функция для тестирования производительности поиска максимума {@link Main#getMax(List)} */
    @Test
    void performanceTestForMax() {
        for (int size = 1000; size <= 100000000; size *= 10) {
            List<Integer> numbers = generateRandomList(size);
            long startTime = System.nanoTime();
            Main.getMax(numbers);
            long endTime = System.nanoTime();
            System.out.println(
                    "Время выполнения max для " + size + " элементов: " + (endTime - startTime) + " нс");
        }
    }

    /** Функция для тестирования производительности поиска суммы {@link Main#getSum(List)} */
    @Test
    void performanceTestForSum() {
        for (int size = 1000; size <= 100000000; size *= 10) {
            List<Integer> numbers = generateRandomList(size);
            long startTime = System.nanoTime();
            Main.getSum(numbers);
            long endTime = System.nanoTime();
            System.out.println(
                    "Время выполнения sum для " + size + " элементов: " + (endTime - startTime) + " нс");
        }
    }

    /** Функция для тестирования производительности поиска произведения {@link Main#getMult(List)} */
    @Test
    void performanceTestForMult() {
        for (int size = 1000; size <= 100000000; size *= 10) {
            List<Integer> numbers = generateRandomList(size);
            long startTime = System.nanoTime();
            Main.getMult(numbers);
            long endTime = System.nanoTime();
            System.out.println(
                    "Время выполнения mult для " + size + " элементов: " + (endTime - startTime) + " нс");
        }
    }

    /** Функция для генерации случайного списка */
    private List<Integer> generateRandomList(int size) {
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(100) + 1); // Генерация случайных чисел от 1 до 100
        }
        return list;
    }
}
