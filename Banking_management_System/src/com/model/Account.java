package com.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	private long Accountno;
	private String Name;
	private long Mobno;
	private String Addr;
	private long Aadhar_no;
	private String gender;
	private int Age;
	private long balance;
	
	public long getAccountno() {
		return Accountno;
	}
	public void setAccountno(long accountno) {
		Accountno = accountno;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public long getMobno() {
		return Mobno;
	}
	public void setMobno(long mobno) {
		Mobno = mobno;
	}
	public String getAddr() {
		return Addr;
	}
	public void setAddr(String addr) {
		Addr = addr;
	}
	public long getAadhar_no() {
		return Aadhar_no;
	}
	public void setAadhar_no(long aadhar_no) {
		Aadhar_no = aadhar_no;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		this.Age = age;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance=balance;
	}
}
