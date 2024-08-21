package agilebit.homedraw.utils;

import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator {
	
	private AgeCalculator() {
	}
	
	public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		}
		return 0;
	}
	
	public static Boolean isOver18(LocalDate birthDate) {
		LocalDate currentDate = LocalDate.now();
		return calculateAge(birthDate, currentDate) >= 18;
	}
}
