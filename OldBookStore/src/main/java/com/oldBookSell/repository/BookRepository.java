package com.oldBookSell.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oldBookSell.model.Book;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query(value="select * from book where amount!=0 and book_name=?1 or authors=?2",nativeQuery = true)
	Book findByBookNameAndAuthor(String bookName, String authors);
	
	@Query(value = "select * from book where book_status='sell' limit ?1,?2",nativeQuery=true)
	List<Book> findBooks(int min, int max);
	
	@Query(value="select * from sell_order_request where book_name like %?1% or authors like %?1% or isbn_no1 like %?1% or isbn_no2 like %?1%",nativeQuery = true)
	List<Book> findBookByNameAuthorAndIsbn(String searchType);
	
	@Query(value="select * from sell_order_request where categories like %?1% or book_name like %?1%",nativeQuery=true)
	List<Book> findBookByCategory(String category);
	
	@Query(value="select * from sell_order_request where authors like %?1%",nativeQuery=true)
	List<Book> findBookByAuthor(String author);
	
	@Query(value="select * from sell_order_request where publisher like %?1%",nativeQuery=true)
	List<Book> findBookByPublisher(String publisher);
	
	@Query(value="select distinct categories from sell_order_request",nativeQuery=true)
	Iterable<Object> findAllCatogory();

}
