package com.soft.operationImp;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.soft.model.Address;
import com.soft.model.Players;
import com.soft.operation.Team;
import com.soft.utility.HibarnetUtil;

public class TeamImp implements Team{

	Scanner sc=new Scanner(System.in);
	Session sf=HibarnetUtil.getSessionFactory().openSession();

	@Override
	public void SetPlayersDetails() {
	Address ad=new Address();
	Players pl=new Players();
	Transaction tx=sf.beginTransaction();
	
	System.out.println("Enter player Details : ");
	
	System.out.println();
	System.out.println("Enter Jersy No : ");
	pl.setJerseyNo(sc.nextInt());
	sc.nextLine();
	
	String i;
	while(true)
	{
		System.out.println("Enter Name : ");
		i=sc.nextLine();
		
		if(i.matches("[A-Za-z]{2,50}"))
		{
			
			break;
		}
		else
		{
			System.out.println("Invalid Name .please enter Characters only.");
			
		}
		
	}
	
	pl.setName(i);
	
	System.out.println("Enter Age : ");
	pl.setAge(sc.nextInt());
	System.out.println("Enter Height : ");
	pl.setHeight(sc.nextFloat());
	System.out.println("Enter Weight : ");
	pl.setWeight(sc.nextFloat());
	
	
	String input;
	while(true)
	{
		System.out.println("Enter Mobno : ");
		 input =sc.next();
	
		if(input.matches("\\d{10}"))
		{
			
			break;
		}
		else
		{
			System.out.println("Invalid mobile Number .please enter exactly 10 digit without any special characters.");
				
		}
		
	}pl.setMobno(Long.parseLong(input));
	
	
	
	String bloodGroup;
	while (true) {
	    System.out.print("Enter Blood Group :  ");
	    bloodGroup = sc.next().toUpperCase(); 
	    
	    if (bloodGroup.matches("^(A|B|AB|O)[+-]$")) 
	    {
	        break;
	    } 
	    else
	    {
	        System.out.println("Invalid Blood Group. Please enter a valid type like A+, B-, O+, etc.");
	    }
	}
	pl.setBloodgroup(bloodGroup);
	
	System.out.println("Enter city : ");
	ad.setCity(sc.next());
	
	String in;
	while(true)
	{
		System.out.println("Enter Pincode : ");
		 in =sc.next();
	
		if(in.matches("\\d{6}"))
		{
			
			break;
		}
		else
		{
			System.out.println("Invalid Pincode .please enter exactly 6 digit without any special characters.");
			
		}
		
	}
	ad.setPincode(Integer.parseInt(in));
	
	
	
	System.out.println("Enter State : ");
	ad.setState(sc.next());
	pl.setAdr(ad);
	
	sf.save(pl);
	tx.commit();
	
	}
	
	

	@Override
	public void GetPlayersDetails() {
		
		Transaction tx=sf.beginTransaction();
		String hql="from Players ";
		Query<Players> li=sf.createQuery(hql);

		List<Players> pl=li.getResultList();
		for(Players details:pl) {
			System.out.println();
		System.out.println("Player JerseyNo : "+details.getJerseyNo());
		System.out.println("Player Name : "+details.getName());
		System.out.println("Player Age : "+details.getAge());
		System.out.println("Player BloodGroup : "+details.getBloodgroup());
		System.out.println("Player Height : "+details.getHeight());
		System.out.println("Player Mobile Number : "+details.getMobno());
		System.out.println("Player Weight : "+details.getWeight());
		System.out.println("Player City : "+details.getAdr().getCity());
		System.out.println("Player Pincode : "+details.getAdr().getPincode());
		System.out.println("Player State : "+details.getAdr().getState());
		System.out.println("-------------*-----------------");
		

	}
		tx.commit();
	}

	@Override
	public void EligibilityCriteria() {
		Transaction tx=sf.beginTransaction();
		
		String hql="from Players where JerseyNo= :JerseyNo";
		Query<Players> jno=sf.createQuery(hql);
		System.out.print("Enter JerseyNo : " );
		jno.setParameter("JerseyNo", sc.nextInt());
		Players result=jno.getSingleResult();
		
		if(result.getAge()>=18 && result.getAge()<=30) {
			if(result.getHeight()>5.0) {
				if(result.getWeight()>50) {
					System.out.println();
					System.out.println("you are selected in team");
					System.out.println();
					System.out.println("Player JerseyNo : "+result.getJerseyNo());
					System.out.println("Player Name : "+result.getName());
					System.out.println("Player Age : "+result.getAge());
					System.out.println("Player BloodGroup : "+result.getBloodgroup());
					System.out.println("Player Height : "+result.getHeight());
					System.out.println("Player Mobile Number : "+result.getMobno());
					System.out.println("Player Weight : "+result.getWeight());
					System.out.println("Player City : "+result.getAdr().getCity());
					System.out.println("Player Pincode : "+result.getAdr().getPincode());
					System.out.println("Player State : "+result.getAdr().getState());
				}
				else {
					System.out.println();
					System.out.println("You are not selected because Weight " +result.getWeight()+" is not as per requirment");
				}
				
			}
			else {
				System.out.println();
				System.out.println("You are not selected because Height " +result.getHeight()+" is not as per requirment");
			}
		}
		else {
			System.out.println();
			System.out.println("You are not selected because Age " +result.getAge()+" is not as per requirment");
		}
		tx.commit();
	}

	@Override
	public void UpdatePlayersDetails() {
		Transaction tx=sf.beginTransaction();
		
		System.out.println("******Update player details****** \n");
		System.out.println("1 update player Age  ");
		System.out.println("2 update player Name  ");
		System.out.println("3 update player Height ");
		System.out.println("4 update player Weight ");
		System.out.println("5 update player MobileNumber \n");
		
		
		System.out.print("Enter Field no.that you want to update : ");
		int num=sc.nextInt();
		int count=0;
		
		switch(num) {
		case 1: 
		    String hql = "update Players set age = :age where jerseyNo = :jerseyNo";
		    Query<Players> updt = sf.createQuery(hql);

		    System.out.print("Enter JerseyNo : ");
		    int jerseyNo = sc.nextInt();  
		    System.out.print("Enter New-Age : ");
		    int newAge = sc.nextInt();

		    updt.setParameter("jerseyNo", jerseyNo);
		    updt.setParameter("age", newAge);

		    count = updt.executeUpdate();
		    tx.commit();

		    if (count > 0) {
		        System.out.println("\nAge is successfully updated ");
		    } else {
		        System.out.println("No player found with JerseyNo " + jerseyNo);
		    }
		    break;
		

		case 2: 
		    String hql2 = "update Players set Name = :newName where JerseyNo = :JerseyNo";
		    Query<Players> updt2 = sf.createQuery(hql2);

		    System.out.print("Enter JerseyNo : ");
		    int jerseyNo2 = sc.nextInt(); 
		    sc.nextLine(); 

		    System.out.print("Enter New-Name : ");
		    String newName = sc.nextLine();

		    updt2.setParameter("JerseyNo", jerseyNo2);
		    updt2.setParameter("newName", newName);

		    count = updt2.executeUpdate();
		    tx.commit();

		    if (count > 0) {
		        System.out.println("\nName is successfully updated ");
		    } else {
		        System.out.println("No player found with JerseyNo " + jerseyNo2);
		    }
		    break;
		    
		case 3: 
		    String hql3 = "update Players set Height = :newHeight where JerseyNo = :JerseyNo";
		    Query<Players> updt3 = sf.createQuery(hql3);

		    System.out.print("Enter JerseyNo : ");
		    int jerseyNo3 = sc.nextInt(); 
		    System.out.print("Enter New-Height : ");
		    float newHeight = sc.nextFloat(); 

		    updt3.setParameter("JerseyNo", jerseyNo3);
		    updt3.setParameter("newHeight", newHeight);

		    count = updt3.executeUpdate();
		    tx.commit();

		    if (count > 0) {
		        System.out.println("\nHeight is successfully updated ");
		    } else {
		        System.out.println("No player found with JerseyNo " + jerseyNo3);
		    }
		    break;

			
		case 4: 
		    String hql4 = "update Players set Weight = :newWeight where JerseyNo = :JerseyNo";
		    Query<Players> updt4 = sf.createQuery(hql4);

		    System.out.print("Enter JerseyNo : ");
		    int jerseyNo4 = sc.nextInt();
		    System.out.print("Enter New-Weight : ");
		    float newWeight = sc.nextFloat();

		    updt4.setParameter("JerseyNo", jerseyNo4);
		    updt4.setParameter("newWeight", newWeight);

		    count = updt4.executeUpdate();
		    tx.commit();

		    if (count > 0) {
		        System.out.println("\nWeight is successfully updated ");
		    } else {
		        System.out.println("No player found with JerseyNo " + jerseyNo4);
		    }
		    break;
		

		case 5: 
		    String hql5 = "update Players set mobno = :newmobno where JerseyNo = :JerseyNo";
		    Query<Players> updt5 = sf.createQuery(hql5);

		    System.out.print("Enter JerseyNo : ");
		    int jerseyNo5 = sc.nextInt();
		    System.out.print("Enter New-Mobile-Number : ");
		    long newMobNo = sc.nextLong();

		    updt5.setParameter("JerseyNo", jerseyNo5);
		    updt5.setParameter("newmobno", newMobNo);

		    count = updt5.executeUpdate();
		    tx.commit();

		    if (count > 0) {
		        System.out.println("\nMobile Number is successfully updated ");
		    } else {
		        System.out.println("No player found with JerseyNo " + jerseyNo5);
		    }
		    break;
			
			default:
				System.out.println("Enter correct choice :");
		}

	}

	@Override
	public void DeletePlayers() {
		Transaction tx=sf.beginTransaction();
		String hql="delete from Players where jerseyNo = :jerseyNo";
		Query<Players> dlt=sf.createQuery(hql);
		System.out.print("Enter JerseyNo : ");
		dlt.setParameter("jerseyNo", sc.nextInt());
		dlt.executeUpdate();
		tx.commit();
		System.out.println("Player information is Deleted successfully ");
	
	}

}
