package com.main;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.config.HibarnetUtil;
import com.model.Account;
import com.services.AccountDoesNotFoundException;
import com.services.Sbi;

public class Test {
	public static void main(String[] args)  {

	Sbi s=new Sbi();
	Account a=new Account();
	Scanner sc=new Scanner(System.in);
	while(true){
		System.out.println("***************************************************************************************");
    	System.out.println();
    	System.out.println();
        System.out.println("......Welcome to SBI Banking System......");
        System.out.println("       press 1 Create Account");
        System.out.println("       press 2 Show Details");
        System.out.println("       press 3 Deposit money");
        System.out.println("       press 4 Withdraw money");
        System.out.println("       press 5 Check Balance");
        System.out.println("       press 6 for update Details");
        System.out.println("       press 7 for delete data for your Account");
      
        System.out.println("       press 8 for print the Passbook");
        System.out.println("       press 9 for exit");
        System.out.println();
        System.out.println();
        System.out.println("***************************************************************************************");
        System.out.println();
        System.out.println("Enter the number you want to execute");
        int num = sc.nextInt();
        System.out.println();
        
		switch(num) {
		case 1:
            s.createAccount();
            break;
		case 2:
			s.getData();
			break;
		case 3:
			s.depositMoney();
			break;
		case 4:
			s.withdrawMoney();
			break;
		case 5:
			s.balanceCheck();
			break;
			
		case 6:
			s.updateDetails();
			break;
		case 7:
			s.deleteData();
			break;
//		case 8:
			
//			try {
//			    System.out.println("Enter Account Number:");
//			    long accountNo = sc.nextLong();  // Read account number once
//
//			    // Optionally verify if account exists here
//
//			    // Write a custom message to passbook
//			    s.writePassbook("Checked account details", accountNo);  // Pass correct values
//
//			} catch (Exception e) {
//			    System.out.println("❌ Error: " + e.getMessage());
//			}
//			break;

		case 8:
			s.printPassbook();
			break;
			
		case 9:
			System.exit(0);
			
		default:
			System.out.println("Enter the correct choice");
			
		}	
		
		
	}
	}  
}
