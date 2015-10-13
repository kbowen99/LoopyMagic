package Loopy;

import java.util.Scanner;

public class Main {

	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ASCII PicDrawer = new ASCII();
		System.out.println(PicDrawer.greetUser());
		String userStatement = in.nextLine();
		
		while (!userStatement.equalsIgnoreCase("")) {
			PicDrawer.ProcessInput(userStatement);
			userStatement = in.nextLine();
		}
		//Pretend This is code
	}
}
