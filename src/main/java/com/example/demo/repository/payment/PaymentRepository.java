package com.example.demo.repository.payment;

import com.example.demo.domain.payment.Payment;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {


}
