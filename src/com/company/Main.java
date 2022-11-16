package com.company;

import java.util.*;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getAnonymousLogger();
        // Задача 1
        PhoneBook pb = new PhoneBook();
        pb.addRow("Иван", "+78998755");
        pb.addRow("Иван", "+789985");
        pb.addRow("Игорь", "+78998325");
        logger.info(pb.toString());

        // Задача 2
        ArrayList<String> workerList = new ArrayList<>(List.of(
                "Светлана Петрова", "Кристина Белова", "Анна Мусина", "Анна Крутова", "Иван Юрин",
                "Петр Лыков", "Павел Чернов", "Петр Чернышов", "Мария Федорова", "Марина Светлова",
                "Мария Савина", "Мария Рыкова", "Марина Лугова", "Анна Владимирова", "Иван Мечников",
                "Петр Петин", "Иван Ежов"));
        logger.info(sortNameDescending(getMapNames(workerList)));

        // Задача 3
        int[] arr = {12, 141, 2522, 535, 657, 234, 1};
        logger.info(heapSort(arr));

        // Задача 4
        ChessBoard field = new ChessBoard();
        logger.info( "\n"+ field.searchSolution());

    }

    // Задача 2
    public static Map<String, Integer> getMapNames(List<String> workerList) {
        Map<String, Integer> countMap = new HashMap<>();

        for (String worker : workerList) {
            String name = worker.split(" ")[0];
            if (!countMap.containsKey(name))
                countMap.put(name, 1);
            else
                countMap.put(name, countMap.get(name) + 1);
        }
        return countMap;
    }

    public static String sortNameDescending(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Map<String, Integer> result = new LinkedHashMap<>();
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);
        return  list.toString();

    }

    // Задача 3
    private static String heapSort(int[] array) {
        int length = array.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(array, i, length);
        }

        for (int i = length - 1; i >= 0; i--) {
            array[0] = (array[0] + array[i]) - (array[i] = array[0]);
            heapify(array, 0, i);
        }
        return Arrays.toString(array);
    }

    private static void heapify(int[] array, int indexPerent, int length) {
        int rightChildInex = 2 * indexPerent + 2;
        int leftChildIndex = 2 * indexPerent + 1;
        int indexMax = indexPerent;

        if (leftChildIndex < length && array[leftChildIndex] > array[indexMax]) {
            indexMax = leftChildIndex;
        }
        if (rightChildInex < length && array[rightChildInex] > array[indexMax]) {
            indexMax = rightChildInex;
        }
        if (indexPerent != indexMax) {
            array[indexPerent] = (array[indexPerent] + array[indexMax]) -
                    (array[indexMax] = array[indexPerent]);
            heapify(array,indexMax,length);
        }
    }

}
