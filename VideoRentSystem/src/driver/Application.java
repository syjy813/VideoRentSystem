package driver;

import java.time.LocalDate;
import java.time.Period;

import controllers.Kiosk;

public class Application {

	public static void main(String[] args) {
		/*final LocalDate today = LocalDate.now();
		final LocalDate rentday = LocalDate.now().minus(Period.ofDays(3));
		System.out.println(today);
		System.out.println(rentday);
		
		final int rentfee = Period.between(rentday, today).getDays();
		System.out.println(rentfee);*/
		Kiosk system = new Kiosk();
		system.run();
	}

}
