package testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TIIME {
	public static void main(String[] args) {
		
	    Scanner scan = new Scanner( System.in );

		String bob = Integer.toString(scan.nextInt());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String checkInDate = dtf.format(now);
		System.out.println(checkInDate); //2016/11/16 12:08:43
		
		String checkOutDate = dtf.format(now.plusWeeks(1));
		System.out.println(checkOutDate); //2016/11/16 12:08:43
		
		
		///////////////////////////////////////////////////

	}

}
