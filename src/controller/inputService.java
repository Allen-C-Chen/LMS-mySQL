package controller;

import java.util.Scanner;

public class inputService {
	private static Scanner scan;
	public inputService(){
	    scan = new Scanner( System.in );

	}
	public static String checkString() {
		return scan.nextLine();
	}
	public static int StringToInt() {
		String numStr = scan.nextLine();
		return Integer.parseInt(numStr);
	}
}
