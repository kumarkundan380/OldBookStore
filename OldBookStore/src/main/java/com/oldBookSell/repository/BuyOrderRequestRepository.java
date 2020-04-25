package com.oldBookSell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oldBookSell.model.BuyOrderRequest;

@Repository
public interface BuyOrderRequestRepository extends JpaRepository<BuyOrderRequest,Integer>{

}
