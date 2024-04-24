package pizzashop.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceRepositoryTest {

    PaymentRepository payRepo;

    PizzaService service;

    @BeforeEach
    void setUp() {
        payRepo=new PaymentRepository();
        service=new PizzaService(null,payRepo);
    }

    @AfterEach
    void tearDown() {
        payRepo.getAll().clear();
        payRepo.writeAll();
    }

    @Test
    void getPayments() {
        List<Payment> result=service.getPayments();
        assertEquals(0,result.size());
    }

    @DisplayName("Integration test 1")
    @Test
    void addPayment() {
        Payment payment=mock(Payment.class);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());

        List<Payment> result=service.getPayments();
        assertEquals(1,result.size());
    }
}