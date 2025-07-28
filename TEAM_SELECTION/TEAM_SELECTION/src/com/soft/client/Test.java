package com.soft.client;

import java.util.Scanner;

import com.soft.operationImp.TeamImp;

public class Test {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		TeamImp tm=new TeamImp();
		while(true) {
			System.out.println();
			System.out.println("**--Team Selection--**");
			System.out.println();
			System.out.println("1 Enter player Details : ");
			System.out.println("2 View player Details : ");
			System.out.println("3 Check players Eligibility : ");
			System.out.println("4 Update player Details : ");
			System.out.println("5 Delete player Details : ");
			System.out.println("6 Exit : ");
			System.out.println();
			
			System.out.println("Enter your choice :");
			
			int choice=sc.nextInt();
			switch(choice) {
			
			case 1:
				tm.SetPlayersDetails();
				break;
			case 2:
				tm.GetPlayersDetails();
				break;
			case 3:
				tm.EligibilityCriteria();
				break;
			case 4:
				tm.UpdatePlayersDetails();
				break;
			case 5:
				tm.DeletePlayers();
				break;
			case 6:
				System.out.println("Thank you ...");
				System.exit(0);
			default:
				System.out.println("Enter correct choice :");
			
			}
		}
	}
}
