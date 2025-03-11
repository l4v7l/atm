package ru.l4v7l;

import java.util.HashMap;
import java.util.Map;

public class SdkStub implements Sdk {

    Map<Integer, Integer> vault = new HashMap<>(Map.of(5000, 15, 1000, 25, 500, 35, 100, 47, 50, 66));

    @Override
    public int countBanknotes(String currency, int banknote) {
        return vault.get(banknote);
    }

    @Override
    public void moveBanknoteToDispenser(String currency, int banknote, int count) {
        System.out.printf("Перемещаю купюру %s в лоток выдачи, %s штук%n", banknote, count);
    }

    @Override
    public void openDispenser() {
        System.out.printf("Лоток выдачи открыт%n");
    }
}
