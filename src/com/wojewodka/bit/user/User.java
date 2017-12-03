package com.wojewodka.bit.user;

import java.util.Date;

public class User {

	private Date created = new Date();

	private int age;

	private String name;

	private int stamina;

	private int energy;

	private int charisma;

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

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
