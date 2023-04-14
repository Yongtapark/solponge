package com.example.demo.repository.payment;

import com.example.demo.domain.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface paymentRepository extends JpaRepository<Payment,Long> {

}
