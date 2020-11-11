package driver;

import java.time.LocalDate;
import java.time.Period;

import controllers.Kiosk;

public class Application {

	public static void main(String[] args) {
		Kiosk system = new Kiosk();
		system.run();
	}

}
