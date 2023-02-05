import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class DateDemo {
	public static void main(String [] args) {

		// difference Between 2 dates.
		LocalDateTime d1 = LocalDateTime.of(2020, 12, 1, 0, 0, 0);
		LocalDateTime d2 = LocalDateTime.of(2021, 1, 2, 0, 0, 0);

		Duration duration = Duration.between(d1, d2);
		double sec = duration.getSeconds();
		double day = sec/86400;

		// get Current year, month, date, hour, minutes, seconds.
			DateTimeFormatter y = DateTimeFormatter.ofPattern("yyyy");
			DateTimeFormatter mm = DateTimeFormatter.ofPattern("MM");	
			DateTimeFormatter d = DateTimeFormatter.ofPattern("dd");	
			DateTimeFormatter h = DateTimeFormatter.ofPattern("hh");	
			DateTimeFormatter m = DateTimeFormatter.ofPattern("mm");	
		

		LocalDateTime now = LocalDateTime.now();	
		
		int year = Integer.parseInt(y.format(now));
		int month = Integer.parseInt(mm.format(now));
		int days = Integer.parseInt(d.format(now));
		int hour = Integer.parseInt(h.format(now));
		int min = Integer.parseInt(m.format(now));

		System.out.println(year + " " + month + " " + days + " " + hour + " " + min);
	}
}

// 86400 seconds == 1 Day
// 3600 seconds == 1 hours
// LocalDateTime d1 = LocalDateTime.of(2020, 12, 1, 11, 20, 20);
