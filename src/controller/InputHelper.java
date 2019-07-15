package controller;

import java.util.Scanner;

public class InputHelper {
//	public static void main(String[] args) {
//		int num = checkInput(2,9);
//	}

	public static String userString() {
	    Scanner scan = new Scanner( System.in );
		String str =  scan.nextLine();
		return str;
	}
	public static int checkInput(int min, int max) {
	    Scanner scan = new Scanner( System.in );
		String str =  scan.nextLine();
//		if(type.equalsIgnoreCase("String")) {
//			return str;
//		}
		int num = -1;
		while(min < num ||  max > num) {
			while(!str.matches("-?\\d+(\\.\\d+)?")) {
				System.out.println("That is not a number! Try again");
				str =  scan.nextLine();
			}
			num = Integer.parseInt(str);
			if(num >= min && num <= max) {
				return num;
			}
			else {
				System.out.println("That is not in range!! Try again");
				str =  scan.nextLine();

			}
		}
		return 0;
	}
}
