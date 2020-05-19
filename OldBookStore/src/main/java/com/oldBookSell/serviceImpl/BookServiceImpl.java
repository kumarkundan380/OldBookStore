package com.oldBookSell.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oldBookSell.dto.BookDTO;
import com.oldBookSell.model.Book;
import com.oldBookSell.repository.BookRepository;
import com.oldBookSell.service.BookService;

/**
 * This is BookServiceImpl implements an application that
 * simply calls BookService interface methods
 * @author Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
 */

@Service
public class BookServiceImpl implements BookService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Override
	public void saveBook(BookDTO bookDTOObj) {
		LOGGER.info("BookService saveBook method is calling....");
		Book bookObj=new Book();
		
		bookObj.setAmount(bookDTOObj.getAmount());
		bookObj.setAuthors(bookDTOObj.getAuthors());
		bookObj.setBookName(bookDTOObj.getBookName());
		bookObj.setBookStatus(bookDTOObj.getBookStatus());
		bookObj.setCategories(bookDTOObj.getCategories());
		bookObj.setCurrencyCode(bookDTOObj.getCurrencyCode());
		bookObj.setDescription(bookDTOObj.getDescription());
		bookObj.setIsbnNo1(bookDTOObj.getIsbnNo1());
		bookObj.setIsbnNo2(bookDTOObj.getIsbnNo2());
		bookObj.setIsbnType10(bookDTOObj.getIsbnType10());
		bookObj.setIsbnType13(bookDTOObj.getIsbnType13());
		bookObj.setPublishedDate(bookDTOObj.getPublishedDate());
		bookObj.setPublisher(bookDTOObj.getPublisher());
		bookObj.setQuantity(bookDTOObj.getQuantity());
		bookObj.setSmallThumbnail(bookDTOObj.getSmallThumbnail());
		bookObj.setThumbnail(bookDTOObj.getThumbnail());
		
		//This code for useful for book Tabel find the the unique book and update quantity
		Book uniqueBook=bookRepository.findByBookNameOrAuthor(bookDTOObj.getBookName(),bookDTOObj.getAuthors());
		try {	
			if(uniqueBook == null) {
				bookRepository.save(bookObj);
			}else {
				bookObj.setQuantity(uniqueBook.getQuantity()+1);
				bookObj.setAmount(uniqueBook.getAmount());
				bookObj.setBookId(uniqueBook.getBookId());
				bookRepository.save(bookObj);
				LOGGER.info("Book information save in book table....");
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Override
	public List<Book> findBooks(int min, int max) {
		LOGGER.info("BookService findBooks method is calling....");
		LOGGER.info("In BookService findBooks Min="+min);
		LOGGER.info("In BookService findBooks Max="+max);
		return bookRepository.findBooks(min, max);
	}
	
	@Override
	public Book updateBookPrice(int bookId, int price) {
		LOGGER.info("BookService updateBookPrice method is calling....");
		Optional<Book> bookObj=bookRepository.findById(bookId);
		LOGGER.info("In BookService findBooks method amount is "+bookObj.get().getAmount());
		bookObj.get().setAmount(price);
		return bookRepository.save(bookObj.get());
	}
	
	@Override
	public List<Book> findBookByAuthor(String author){
		LOGGER.info("BookService findBookByAuthor method is calling....");
		LOGGER.info("In BookService findBookByAuthor Author Name="+author);
		return bookRepository.findBookByAuthor(author);
	}
	
	@Override
	public List<Book> findBookByNameAuthorAndIsbn(String bookName) {
		LOGGER.info("BookRequestService findBooksByNameAuthorAndIsbn method is calling....");
		return bookRepository.findBookByNameAuthorAndIsbn(bookName);
	}
	
	@Override
	public List<Book> findBookByCategory(String category){
		LOGGER.info("BookRequestService findBookByCategory method is calling....");
		return bookRepository.findBookByCategory(category);
	}
	
	@Override
	public List<Book> findBookByPublisher(String publisher){
		LOGGER.info("BookService findBookByPublisher method is calling....");
		return bookRepository.findBookByPublisher(publisher);
	}
	
	@Override
	public Iterable<Object> findAllCatogory(){
		LOGGER.info("BookService findAllCatogory method is calling....");
		return bookRepository.findAllCatogory();
	}
	
	@Override
	public Optional<Book> findBookById(int bookId) {
		LOGGER.info("BookService findBookById method is calling.....");
		return bookRepository.findById(bookId);
	}
	
	@Override
	public List<Book> getAllBook() {
		LOGGER.info("BookService getAllBook method is calling.....");
		return bookRepository.getAllBook();
	}
	
	@Override
	public List<Book> getAllBookForUpdate() {
		LOGGER.info("BookService getAllBookForUpdate method is calling.....");
		return bookRepository.getAllBookForUpdate();
	}
	
	@Override
	public int getQuantity(int bookId) {
		LOGGER.info("BookService getQuantity method is calling.....");
		return bookRepository.getQuantity(bookId);
	}
	
	@Override
	public void minusQuantity(int bookId, int quantity) {
		LOGGER.info("BookService minusQuantity method is calling.....");
		Optional<Book> bookRequest =bookRepository.findByBookId(bookId);
		Book bookObj=new Book();
		bookObj=bookRequest.get();
		bookObj.setQuantity(bookObj.getQuantity()-quantity);
		LOGGER.info(" In BookService getAllBookForUpdate method book quantity "+bookObj.getQuantity());
		bookRepository.save(bookObj);
	}

	@Override
	public List<Book> findAllBookForSell() {
		LOGGER.info("BookService findAllBookForSell method is calling.....");
		return bookRepository.findAllBookForSell();
	}
}
