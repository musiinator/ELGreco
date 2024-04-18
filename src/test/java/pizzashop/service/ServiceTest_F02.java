package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceTest_F02 {
    private static List<Payment> payments;
    private static PizzaService pizzaService;

    @BeforeAll
    @Timeout(1000)
    static void setUp() {
        MenuRepository repoMenu = new MenuRepository();
        PaymentRepository payRepo = new PaymentRepository();
        pizzaService = new PizzaService(repoMenu, payRepo);
//        pizzaService.getPayments().clear();

        payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.Card, 15.25));
        payments.add(new Payment(1, null, 15.25));
    }

    @AfterEach
    void clear() {
        pizzaService.getPayments().clear();
    }

    @Test
    void TC1() {
        Payment p = payments.get(0);
        pizzaService.addPayment(p.getTableNumber(), p.getType(), p.getAmount());

        assertEquals(pizzaService.getTotalAmount(PaymentType.Card), 15.25);
    }

    @Test
    void TC2() {
        Payment p = payments.get(1);
        pizzaService.addPayment(p.getTableNumber(), p.getType(), p.getAmount());

        try {
            assertEquals(pizzaService.getTotalAmount(PaymentType.Card), 0);
            assert (false);
        } catch (NullPointerException | IllegalArgumentException e) {
            assert (true);
        }
        try {
            assertEquals(pizzaService.getTotalAmount(PaymentType.Cash), 0);
            assert (false);
        } catch (NullPointerException | IllegalArgumentException e) {
            assert (true);
        }
    }

    @Test
    void TC3() {
        assertEquals(pizzaService.getTotalAmount(PaymentType.Cash), 0);
    }

    @Test
    void TC4() {
        Payment p = payments.get(0);
        pizzaService.addPayment(p.getTableNumber(), p.getType(), p.getAmount());
        pizzaService.getPayments().clear();
        assertEquals(pizzaService.getTotalAmount(PaymentType.Cash), 0);
    }
}