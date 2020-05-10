package com.oldBookSell.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oldBookSell.model.Payment;

@Repository
@Transactional
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
