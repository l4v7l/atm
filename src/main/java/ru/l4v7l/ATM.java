package ru.l4v7l;

import ru.l4v7l.exception.AmountIsIncorrectException;
import ru.l4v7l.exception.NotEnoughBanknotesException;
import ru.l4v7l.exception.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ATM {

    private Sdk sdk ;
    private final Map<Integer, Integer> banknotesInATM = new HashMap<>();
    private int totalMoneyInATM;

    private final List<Integer> nominals = new ArrayList<>(List.of(5000, 1000, 500, 100, 50));

    //при запуске банкомата узнаем сколько в нем денег всех номиналов
    public ATM(Sdk sdk) {

        this.sdk = sdk;

        System.out.println("Изначально в банкомате: ");
        for (Integer nominal: nominals) {
            int banknoteCount = sdk.countBanknotes("RUB", nominal);
            banknotesInATM.put(nominal, banknoteCount);
            totalMoneyInATM += nominal * banknoteCount;
            System.out.printf("%s : %s%n", nominal, banknoteCount);
        }
        System.out.printf("Всего денег: %s%n", totalMoneyInATM);
    }



    public Response giveMeTheMoney(Integer amount) {

        try {
            Map<Integer, Integer> result = calculateBanknotesFromSum(amount);
            result.forEach((nominal, count) -> sdk.moveBanknoteToDispenser("RUB", nominal, count));
            banknotesInATM.forEach((nominal, count) -> banknotesInATM.put(nominal, count - result.getOrDefault(nominal, 0)));
            sdk.openDispenser();
            totalMoneyInATM -= amount;

        } catch (Exception e) {
            return new Response(e.getMessage());
        }

        return new Response("Успешно выдано: " + amount);
    }

    private Map<Integer, Integer> calculateBanknotesFromSum(Integer amount) throws NotEnoughBanknotesException, AmountIsIncorrectException, NotEnoughMoneyException {

        if (amount % 50 != 0) {
            throw new AmountIsIncorrectException("Сумма не кратна 50");
        }

        if (amount > totalMoneyInATM) {
            throw new NotEnoughMoneyException("В банкомате недостаточно средств");
        }

        if (amount <= 0) {
            throw new AmountIsIncorrectException("Сумма должна быть больше 0");
        }

        Map<Integer, Integer> banknotesToOutput = new HashMap<>();
        for (Integer nominal: nominals) {
            int countForNominal = Math.min(amount / nominal, banknotesInATM.getOrDefault(nominal, 0));

            if (countForNominal > 0) {
                banknotesToOutput.put(nominal, countForNominal);
                amount -= countForNominal * nominal;
            }
        }

        if (amount != 0) {
            throw new NotEnoughBanknotesException("В банкомате недостаточно купюр чтобы выдать запрошенную сумму");
        }

        return banknotesToOutput;

    }


}
