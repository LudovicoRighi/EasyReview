package com.lra.dao;

import com.lra.model.Person;

public class PersonDao {
	
	public Person getPerson(int info) {
		
		Person a = new Person();
		a.setId(101);
		a.setName("Ludovico");
		a.setInfo("Informatica");
		
		return a;
	}
	

}
