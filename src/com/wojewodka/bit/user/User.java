package com.wojewodka.bit.user;

import java.util.Date;

public class User {

	private Date created = new Date();

	private int age;

	private String name;

	public Date getCreated() {
		return created;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

}
