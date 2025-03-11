
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import ru.l4v7l.ATM;
import ru.l4v7l.Sdk;
import ru.l4v7l.SdkStub;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

public class ATMTest {

    Map<Integer, Integer> vault = new HashMap<>(Map.of(5000, 15, 1000, 25, 500, 35, 100, 47, 50, 66));


    @InjectMocks
    ATM atm;

    @Mock
    Sdk sdkStub = new SdkStub();


    @Test
    public void shouldGetBadResponseWhenAmountIsNotCorrect() {
        doReturn(vault.get(5000)).when(sdkStub).countBanknotes("RUB", 5000);
        doReturn(vault.get(1000)).when(sdkStub).countBanknotes("RUB", 1000);
        doReturn(vault.get(500)).when(sdkStub).countBanknotes("RUB", 500);
        doReturn(vault.get(100)).when(sdkStub).countBanknotes("RUB", 100);
        doReturn(vault.get(50)).when(sdkStub).countBanknotes("RUB", 50);
        assertEquals("Сумма должна быть больше 0", atm.giveMeTheMoney(0).getMessage());
    }

    @Test
    public void shouldGetBadResponseWhenCanNotCountBanknotes() {
        doReturn(vault.get(5000)).when(sdkStub).countBanknotes("RUB", 5000);
        doReturn(vault.get(1000)).when(sdkStub).countBanknotes("RUB", 1000);
        doReturn(vault.get(500)).when(sdkStub).countBanknotes("RUB", 500);
        doReturn(vault.get(100)).when(sdkStub).countBanknotes("RUB", 100);
        doReturn(vault.get(50)).when(sdkStub).countBanknotes("RUB", 50);
        assertEquals("Сумма должна быть больше 0", atm.giveMeTheMoney(0).getMessage());
    }

    @Test
    public void shouldGetSuccessResponseWhenAmountIsCorrect() {
        doReturn(10).when(sdkStub).countBanknotes("RUB", 5000);
        doReturn(vault.get(1000)).when(sdkStub).countBanknotes("RUB", 1000);
        doReturn(vault.get(500)).when(sdkStub).countBanknotes("RUB", 500);
        doReturn(vault.get(100)).when(sdkStub).countBanknotes("RUB", 100);
        doReturn(vault.get(50)).when(sdkStub).countBanknotes("RUB", 50);
        assertEquals("Успешно выдано: 12500", atm.giveMeTheMoney(12500).getMessage());
    }
}
