package ru.l4v7l;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Банкомат
 * Взаимодействует с сдк
 * Реализовать запрос на выдачу определенной суммы в рублях
 * Если невозможно, отвечать отказом
 * Номиналы: 50, 100, 500, 1000, 5000
 * Дополнительно: расширить для разных валют
 */

public class Main {




    public static void main(String[] args) {
        SdkStub sdkStub = new SdkStub();

        ATM atm = new ATM(sdkStub);

        System.out.println(atm.giveMeTheMoney(0).getMessage());
        System.out.println(atm.giveMeTheMoney(-5).getMessage());
        System.out.println(atm.giveMeTheMoney(1000000).getMessage());
        System.out.println(atm.giveMeTheMoney(2023).getMessage());
        System.out.println(atm.giveMeTheMoney(50).getMessage());
        System.out.println(atm.giveMeTheMoney(150).getMessage());
        System.out.println(atm.giveMeTheMoney(2150).getMessage());
        System.out.println(atm.giveMeTheMoney(7750).getMessage());
        System.out.println(atm.giveMeTheMoney(22500).getMessage());
        System.out.println(atm.giveMeTheMoney(13450).getMessage());

    }


}