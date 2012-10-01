package com.brill.hotel;

public class Constructormenu {
	String name;
	 String price;
	 String category;
	
	 public Constructormenu( String name, String price,String category) {
	  
	  this.name = name;
	  this.price = price;
	  this.category=category;
	  
	  

	 }
	
	 public String Name() {
	  return name;
	 }

	 public String Price() {
	  return price;
	 }

	 public String Category() {
		  return category;
		 }
}
