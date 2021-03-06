package com.oldBookSell.model;

/**
 * This is Address Class that is use to map the required
 * column in address table
 * @author  Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
@SequenceGenerator(name="address_sequence", initialValue=1001, allocationSize=1)
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="address_sequence")
	private int id;
	
	@Column(name="address")	
	private String address;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="district")
	private String district;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="location")
	private String location;
	
	@Column(name="state")
	private String 	state;

}
