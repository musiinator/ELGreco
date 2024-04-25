package pizzashop.repository;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositoryTest_F01 {
    private static List<Payment> payments;
    private static PaymentRepository paymentRepository;

    @BeforeAll
    @Timeout(1000)
    static void setUp() {
        payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.Card, 10));
        payments.add(new Payment(1, PaymentType.Card, 10));
        payments.add(new Payment(-1,PaymentType.Card, 10));
        payments.add(new Payment(1, PaymentType.Card, -10));
        payments.add(new Payment(2, PaymentType.Card, 10));
        payments.add(new Payment(7, PaymentType.Card, 10));
        payments.add(new Payment(0, PaymentType.Card, 10));
        payments.add(new Payment(9, PaymentType.Card, 0));

        paymentRepository = new PaymentRepository();
    }

    @AfterAll
    @Timeout(1000)
    public static void tearDown() {
        paymentRepository.writeAll();
    }

    @Order(1)
    @DisplayName("BVA_TEST_1")
    @Tag("ECP")
    @Test
    public void TC1() {
        Payment payment = payments.get(0);
        paymentRepository.add(payment);
        assertEquals(paymentRepository.getAll().get(paymentRepository.getAll().size()-1), payment);
        paymentRepository.getAll().remove(paymentRepository.getAll().size()-1);
    }

    @Order(2)
    @DisplayName("BVA_TEST_2")
    @Tag("ECP")
    @Test
    public void TC2() {
        Payment payment = payments.get(1);
        paymentRepository.add(payment);
        assertEquals(paymentRepository.getAll().get(paymentRepository.getAll().size()-1), payment);
        paymentRepository.getAll().remove(paymentRepository.getAll().size()-1);
    }

    @Order(3)
    @DisplayName("BVA_TEST_3")
    @Tag("ECP")
    @Test
    public void TC3() {
        Payment payment = payments.get(2);
        try{
            paymentRepository.add(payment);
            assert (false);
        } catch (IllegalArgumentException e) {
            assert(true);
        }
    }

    @Order(4)
    @DisplayName("BVA_TEST_4")
    @Tag("ECP")
    @Test
    public void TC4() {
        Payment payment = payments.get(3);
        try{
            paymentRepository.add(payment);
            assert (false);
        } catch (IllegalArgumentException e) {
            assert(true);
        }
    }

    @Order(5)
    @DisplayName("BVA_TEST_5")
    @Tag("BVA")
    @Test
    public void TC5() {
        Payment payment = payments.get(4);
        paymentRepository.add(payment);
        assertEquals(paymentRepository.getAll().get(paymentRepository.getAll().size()-1), payment);
        paymentRepository.getAll().remove(paymentRepository.getAll().size()-1);
    }

    @Order(6)
    @DisplayName("BVA_TEST_6")
    @Tag("BVA")
    @Test
    public void TC6() {
        Payment payment = payments.get(5);
        paymentRepository.add(payment);
        assertEquals(paymentRepository.getAll().get(paymentRepository.getAll().size()-1), payment);
        paymentRepository.getAll().remove(paymentRepository.getAll().size()-1);
    }

    @Order(7)
    @DisplayName("BVA_TEST_7")
    @Tag("BVA")
    @Test
    public void TC7() {
        Payment payment = payments.get(6);
        try{
            paymentRepository.add(payment);
            assert (false);
        } catch (IllegalArgumentException e) {
            assert(true);
        }
    }

    @Order(8)
    @DisplayName("BVA_TEST_8")
    @Tag("BVA")
    @Test
    public void TC8() {
        Payment payment = payments.get(7);
        try{
            paymentRepository.add(payment);
            assert (false);
        } catch (IllegalArgumentException e) {
            assert(true);
        }
    }

    @Disabled
    @Test
    public void TC9() {
        assert(false);
    }
}