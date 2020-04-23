package com.oldBookSell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oldBookSell.model.SellOrderRequest;

@Repository
public interface SellOrderRequestRepository extends JpaRepository<SellOrderRequest, Integer>{

	@Query(value="select * from sell_order_request where book_name=?1 and authors=?2",nativeQuery = true)
	SellOrderRequest findByBookNameAndAuthor(String BookName,String authors);
	
	@Query(value = "select * from sell_order_request limit ?1,?2",nativeQuery=true)
	List<SellOrderRequest> findBooks(int min,int max);

	@Query(value="select * from sell_order_request where book_name like %?1% or authors like %?1% or isbn_no1 like %?1% or isbn_no2 like %?1%",nativeQuery = true)
	List<SellOrderRequest> findBookByNameAuthorAndIsbn(String searchType);
}
