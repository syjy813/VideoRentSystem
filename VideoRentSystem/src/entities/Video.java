package entities;

import java.time.LocalDate;
import java.time.Period;

public class Video {
   
   final String videocode;
   final LocalDate rentedday;
   final LocalDate today;
   final long rentfee;
   final long rentdays;
   
   public Video (String code, LocalDate date) {
      this.videocode = code;
      this.rentedday = date;
      this.today = LocalDate.now();
      rentdays = Period.between(date, today).getDays();
      rentfee = rentdays * 500;
   }

   public String getVideocode() {
      return videocode;
   }

   public LocalDate getRentday() {
      return rentedday;
   }
   
   public long getrentfee() {
	   if(rentdays > 2) {
		   return rentfee;
	   }
	   return 0;
   }

@Override
public String toString() {
	return "[비디오코드=" + videocode + ", 빌린 날짜=" + rentedday + ", rentdays=" + rentdays
			+ ", rentfee=" + rentfee + "]";
}
}   
