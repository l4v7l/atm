package ru.l4v7l;

public interface Sdk {

    /**
     * вызывать как можно реже
     * @param currency валюта
     * @param banknote номинал купюры
     * @return количество купюр в банкомате
     */
    int countBanknotes(String currency, int banknote);

    /**
     * Переместить купюры в лоток выдачи
     * @param currency валюта
     * @param banknote номинал купюры
     * @param count количество купюр
     */
    void moveBanknoteToDispenser(String currency, int banknote, int count);

    /**
     * открыть лоток выдачи
     */
    void openDispenser();
}
