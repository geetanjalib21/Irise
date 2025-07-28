package com.services;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.client.Rbi;
import com.config.HibarnetUtil;
import com.model.Account;
public class Sbi implements Rbi {
	
	Scanner sc=new Scanner(System.in); 
	  SessionFactory sf=HibarnetUtil.getSessionFactory();
	  Session session = sf.openSession();
	  
	  
	@Override
	public void createAccount() {
		Account a=new Account(); 
	Transaction tx=session.beginTransaction(); 
	int count=0;  
	for(int i=1; i<=3; i++)
	{
	System.out.println("Enter a Account no");
	long AccNo=sc.nextLong();
	
	try {
	if(String.valueOf(AccNo).length()==16) 
	{
		
		a.setAccountno(AccNo);
		break;
	}
	else {
		throw new InvalidAccountNoException("please provide valid account no.") ;
	}
	}
	catch(InvalidAccountNoException e){
		System.out.println(e.getMessage());
	}
	count=count+1;
	}
	if(count==3) 
	{
		System.out.println("please fill form again");
		createAccount();
	}
	sc.nextLine();
	System.out.println("Enter a Account Holder name"); 
	String  name=sc.nextLine();
	a.setName(name);
	
	int count1=0;
	for(int i=1; i<=3; i++) 
	{
	System.out.println("Enter Mobile no ");
	long mobno=sc.nextLong();
	try {
		if(String.valueOf(mobno).length()==10)
		{
			a.setMobno(mobno);//set the mobile no
			break;
		}
		else {
			throw new InvalidMobileNoException("Please provide valid mobile no.");//If mobile no is not 10 digit then it will print this msg
		}
	}
	catch(InvalidMobileNoException e) {
		System.out.println(e.getMessage());
	}
	count1=count1+1;// It decreased user chance by 1 
	
	}
	if(count1==3)//if users chances will be over then this statement will print and user have to create Account again from starting
	{
		System.out.println("please fill form again");
		createAccount(); // It will call create Account method
	}
	
	sc.nextLine();
	System.out.println("Enter the Address");
	String addr=sc.next();
	a.setAddr(addr);
	
	int count2=0;
	for(int i=1; i<=3; i++) // giving user 3 chances to enter correct Aadhar number
	{
	System.out.println("Enter the Aadhar no");
	long Aadharno=sc.nextLong();
	try {
		if(String.valueOf(Aadharno).length()==12) {
			a.setAadhar_no(Aadharno);
			break;
		}
		else {
			throw new InavalidAadharNoException("Please provide valid aadhar no");
		}
	}catch(InavalidAadharNoException e) {
		System.out.println(e.getMessage());
	}
	count2=count2+1;
	}
	if(count2==3) {
		System.out.println("please fill form again");
		createAccount();
	}
	
	int count3=0;
	for(int i=1; i<=3; i++)// giving user 3 chances to enter correct account number
	{
	System.out.println("Enter the Age");
	int age=sc.nextInt();
	try {
	if(age>=12) {//if age is greater than or equal to 12 then it will set the age
		a.setAge(age);
		break;
	}
	else {
		throw new InvalidAgeException("Your age is not complete to open bank Account"); // if age is less than 12 then this msg will shown
	}
	}
	catch(InvalidAgeException e) {
		System.out.println(e.getMessage());
	}
	count3=count3+1;// It will decrease user chance by 1
	}
	if(count3==3) //If users chances will over then this msg will show 
	{
		System.out.println("please fill form again");
		createAccount();// It will call create Account method
	}
	
	int count4=0;
	for(int i=1; i<=3; i++) 
	{
	System.out.println("Enter the gender");
	String gender=sc.next();
	try {
		if(gender.equals("FEMALE") || gender.equals("female") ){//.equal method is used for compare two object
			a.setGender(gender);
			break;
			
		}
		else if(gender.equals("MALE") || gender.equals("male") ) {
			a.setGender(gender);
			break;
		}
		else if(gender.equals("Male") || gender.equals("Female") ) {
			a.setGender(gender);
			break;
		}
		else if(gender.equals("M") || gender.equals("F") ) {
			a.setGender(gender);
			break;
		}
		else if(gender.equals("m") || gender.equals("f") ) {
			a.setGender(gender);
			break;
		}
		else {
			InvalidGenderException e3=new InvalidGenderException("Please provide valid details");
			throw e3;
		}
		}
		catch(InvalidGenderException e3) {
			System.out.println(e3.getMessage());
			
		}
		count4=count4+1;
		}
		if(count4==3) {
			   System.out.println("please fill form again");
				  createAccount(); 
		   }
		
		int count5=0;
		for(int i=1; i<=3; i++) {
	System.out.println("Enter the Amount you want to deposit to create your Account");
	long balance=sc.nextLong();
	try {
		if(balance>=1000) {
			a.setBalance(balance);
            break;
		}
		else {
			throw new MinimumDepositException("Minimum 1000₹ you have to deposit");
		}
	}catch(MinimumDepositException e) {
		System.out.println(e.getMessage());
	}
	count5=count5+1;
		}
		if(count5==3) {
		System.out.println("please fill form again");
		createAccount();
		}
	session.save(a);
	tx.commit();
	writePassbook("Account created with balance ₹" + a.getBalance(), a.getAccountno());

	   LocalDate date = LocalDate.now();
     LocalTime Time = LocalTime.now();
     
     System.out.println("Current Date: " + date);
     System.out.println("Current Time: " + Time);
     
	}
	
	@Override
	public void getData() {
	System.out.println("Enter a Account number");
	long useraccNo = sc.nextLong();
	String hql="from Account where Accountno =: Accno";
	Query<Account> q=session.createQuery(hql,Account.class);
	q.setParameter("Accno", useraccNo);
	try {
	Account a1=q.getSingleResult();
   
	if(useraccNo == a1.getAccountno()) {
		
	System.out.println("Account no            : "+a1.getAccountno());
	System.out.println("Account Holder name   : "+a1.getName());
	System.out.println("phone no              : "+a1.getMobno());
	System.out.println("Address               : "+a1.getAddr());
	System.out.println("Aadhar no             : "+a1.getAadhar_no());
	System.out.println("Gender                : "+a1.getGender());
	System.out.println("Age                   : "+a1.getAge());
	System.out.println("Balance               : "+a1.getBalance());
	
	}
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	}

	@Override
	public void depositMoney() {
		Transaction tx = session.beginTransaction();

	    System.out.println("Enter your Account Number:");
	    long accountNo = sc.nextLong();

	    // Fetch account from DB
	    String hql = "from Account where Accountno = :accNo";
	    Query<Account> query = session.createQuery(hql, Account.class);
	    query.setParameter("accNo", accountNo);
	    
	    Account account = null;

	    try {
	        account = query.getSingleResult();
	    } catch (Exception e) {
	        System.out.println("Account not found.");
	        return; // Exit method if account not found
	    }

	    System.out.println("Enter the amount you want to deposit:");
	    long amount = sc.nextLong();

	    // Update balance
	    long newBalance = account.getBalance() + amount;
	    account.setBalance(newBalance);

	    session.save(account);  // OR session.update(account);
	    tx.commit();

	    System.out.println("Deposit successful. Current Balance: ₹" + account.getBalance());
	    writePassbook("Deposited ₹" + amount + ", New balance: ₹" + account.getBalance(), account.getAccountno());
	    

	}

	@Override
	public void withdrawMoney() {
	    Transaction tx = session.beginTransaction();

	    System.out.println("Enter your Account Number:");
	    long accountNo = sc.nextLong();

	    // Fetch account from DB
	    String hql = "from Account where Accountno = :accNo";
	    Query<Account> query = session.createQuery(hql, Account.class);
	    query.setParameter("accNo", accountNo);

	    Account account = null;

	    try {
	        account = query.getSingleResult();
	    } catch (Exception e) {
	        System.out.println(" Account not found.");
	        return; // Stop if account not found
	    }

	    System.out.println("Enter the amount you want to withdraw:");
	    long amount = sc.nextLong();

	    try {
	        if (amount > account.getBalance()) {
	            throw new InsufficientBalanceException(" Insufficient Balance. Available: ₹" + account.getBalance());
	        }

	        // Update balance
	        long newBalance = account.getBalance() - amount;
	        account.setBalance(newBalance);

	        session.saveOrUpdate(account);
	        tx.commit();

	        System.out.println("Withdrawal successful. New Balance: ₹" + account.getBalance());

	        // Optional: log to passbook
	        writePassbook("Withdrawn ₹" + amount + ", New balance: ₹" + account.getBalance(), account.getAccountno());

	    } catch (InsufficientBalanceException e) {
	        System.out.println(e.getMessage());
	        tx.rollback(); // Rollback if exception occurs
	    }
	}


	@Override
	public void balanceCheck() {
	    Transaction tx = session.beginTransaction();  // Start transaction

	    System.out.println("Enter your Account Number:");
	    long accountNo = sc.nextLong();

	    // HQL to fetch the account
	    String hql = "from Account where Accountno = :accNo";
	    Query<Account> query = session.createQuery(hql, Account.class);
	    query.setParameter("accNo", accountNo);

	    Account account = null;

	    try {
	        account = query.getSingleResult();  // Fetch account from DB
	        System.out.println("Account Found.");
	        System.out.println("Current Balance: ₹" + account.getBalance());

	        tx.commit();
	        
	    } catch (Exception e) {
	        System.out.println("Account not found.");
	        tx.rollback();  // rollback in case of failure
	    }
	}

	
	@Override
	public void updateDetails() {
		Transaction tx=session.beginTransaction();
		
		System.out.println();
		System.out.println("Enter 1 for update name");
		System.out.println("Enter 2 for update mobile no");
		System.out.println("Enter 3 for update Address");
	    System.out.println("Enter 4 for Exit");
		System.out.println();
		System.out.println("Enter the number you want to execute");
		int no=sc.nextInt();
		switch(no) {
		case 1:

			String hql="update Account set Name =: Accname where Accountno=:Accno";
			Query<Account> q=session.createQuery(hql);
			System.out.println("Enter a Account no");
			q.setParameter("Accno", sc.nextLong());
			sc.nextLine();
			System.out.println("Enter Account Holder name");
			q.setParameter("Accname", sc.next());
			int rowsupdate=q.executeUpdate();
			break;
			
		case 2:
			String hql1="update Account set Mobno =: mobno where Accountno=:Accno";
			Query<Account> q1=session.createQuery(hql1);
			System.out.println("Enter a Account no");
			q1.setParameter("Accno", sc.nextLong());
			System.out.println("Enter Account Holder phone no");
			q1.setParameter("mobno", sc.nextLong());
			int rowsupdate1=q1.executeUpdate();
			
			break;
			
		case 3:
			String hql2="update Account set Addr =: Address where Accountno=:Accno";
			Query<Account> q2=session.createQuery(hql2);
			System.out.println("Enter a Account no");
			q2.setParameter("Accno", sc.nextLong());
			sc.nextLine();
			System.out.println("Enter a Address");
			q2.setParameter("Address", sc.next());
			int rowsupdate2=q2.executeUpdate();
            break;
            
		case 4:
			System.exit(0);
            
        default:
        	System.out.println("Enter the correct no");
			
		}
		tx.commit();
		
		}
	
	@Override
	public void deleteData() {
	Transaction tx=session.beginTransaction();
	String hql ="delete from Account where Accountno =: Accno";
 	Query<Account> A = session.createQuery(hql);
 	System.out.println("Enter a Account No");
 	long Accno=sc.nextLong();
	A.setParameter("Accno", Accno);
	
	int rowDeleted = A.executeUpdate();
try {
	if(rowDeleted==0) {
		throw new AccountDoesNotFoundException("Account no "+Accno +"not found");
	}
	else {
		System.out.println("Your Account deleted successfully");
	}
}catch(AccountDoesNotFoundException e) {
	System.out.println(e.getMessage());
}
	tx.commit();
		
	}
	
	public void writePassbook(String message, long accountNo) {
	    try {
	        String dirPath = "D:\\BankPassbooks";
	        File dir = new File(dirPath);
	        if (!dir.exists()) {
	            dir.mkdirs(); // create folder if not exists
	        }

	        FileWriter writer = new FileWriter(dirPath + "\\Passbook_" + accountNo + ".txt", true);

	        // Add current date and time to each entry
	        LocalDate date = LocalDate.now();
	        LocalTime time = LocalTime.now();

	        writer.write("[" + date + " " + time.getNano() + "] " + message + "\n");
	        writer.close();
	    } catch (IOException e) {
	        System.out.println("Error writing to passbook: " + e.getMessage());
	    }
	}

	public void printPassbook() {
		Transaction tx=session.beginTransaction();
	    System.out.println("Enter your Account Number:");
	    long accNo = sc.nextLong();
	    try {
	        File file = new File("D:\\BankPassbooks\\Passbook_" + accNo + ".txt");
	        Scanner reader = new Scanner(file);
	        System.out.println("----------------------- Passbook -----------------------");
	        while (reader.hasNextLine()) {
	            System.out.println(reader.nextLine());
	        }
	        reader.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("Passbook not found for Account No: " + accNo);
	    }
	    tx.commit();
	}



}
