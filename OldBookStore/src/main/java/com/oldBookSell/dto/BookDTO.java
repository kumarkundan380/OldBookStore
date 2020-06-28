package com.oldBookSell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is BookDTO that is use to transfer data in
 * BookServiceImpl class.	
 * @author  Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	
	private int bookId;
	
	private String bookName;// title
	
	private String authors;
	
	private String description;
	
	private String publisher;
	
	private String publishedDate;
	
	private String categories;
	
	private String isbnType10;
	
	private String isbnNo1;
	
	private String isbnType13;
	
	private String isbnNo2;
	
	private String smallThumbnail;// small image size
	
	private String thumbnail;// large image size
	
	private float amount;
	
	private String currencyCode;
	
	private int quantity;
	
	private String bookStatus = "sell";
	
}
