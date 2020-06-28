package com.oldBookSell.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is Book Class that is use to map the required
 * column in book table
 * @author  Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
@SequenceGenerator(name = "book_sequence", initialValue = 50001, allocationSize = 1)
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
	private int bookId;

	@Column(name = "book_name")
	private String bookName;// title

	@Column(name = "authors")
	private String authors;

	@Column(name = "description", length = 256)
	private String description;

	@Column(name = "publisher")
	private String publisher;

	@Column(name = "publishedDate")
	private String publishedDate;

	@Column(name = "categories")
	private String categories;

	@Column(name = "isbn_type_10")
	private String isbnType10;

	@Column(name = "isbnNo1")
	private String isbnNo1;

	@Column(name = "isbn_type_13")
	private String isbnType13;

	@Column(name = "isbnNo2")
	private String isbnNo2;

	@Column(name = "smallThumbnail")
	private String smallThumbnail;// small image size

	@Column(name = "thumbnail")
	private String thumbnail;// large image size

	@Column(name = "amount")
	private float amount;

	@Column(name = "currencyCode")
	private String currencyCode;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "book_status")
	private String bookStatus="sell";

}
