package com.oldBookSell.service;

import java.util.List;
import java.util.Optional;

import com.oldBookSell.dto.BookDTO;
import com.oldBookSell.model.Book;

public interface BookService {

	public void saveBook(BookDTO bookDTOObj);

	public List<Book> findBooks(int min, int max);
	
	public List<Book> findBookByCategory(String category);
	
	public List<Book> findBookByNameAuthorAndIsbn(String searchType);
	
	public Iterable<Object> findAllCatogory();
	
	public List<Book> findBookByAuthor(String author);
	
	public List<Book> findBookByPublisher(String publisher);
	
	public Optional<Book> findBookById(int id);
	
}
