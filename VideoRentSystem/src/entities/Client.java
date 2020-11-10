package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Client {

   final String name;
   final List<Video> videolist;
   final LocalDate today;

   public Client(String name) {
      this.name = name;
      videolist = new ArrayList<>();
      today = LocalDate.now();
   }

   public String getName() {
      return name;
   }

   public int rentFee() {
      int sum = 0;
      int rentdays;
      if(!videolist.isEmpty()) {
         for(Video video:videolist) {
        	 rentdays = Period.between(video.getRentday(), today).getDays();
        	 if(rentdays > 2) {
        		 sum += (rentdays-2); 
        	 }
         }
      }
      return sum;
   }
   
   public List<Video> getVideoList() {
	   return videolist;
   }
   @Override
   public String toString() {
      return "Client [name=" + name + ", today=" + today + "]";
   }

}