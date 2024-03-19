package pizzashop.repository;

import pizzashop.model.Payment;

import java.util.List;

public interface IPaymentRepository {
    void add(Payment payment);
    void writeAll();
    List<Payment> getAll();
}
