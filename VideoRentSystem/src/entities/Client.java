package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Client {

   final String name;
   final Set<Video> videolist;
   final LocalDate today;

   public Client(String name) {
      this.name = name;
      videolist = new HashSet<>();
      today = LocalDate.now();
   }

   public String getName() {
      return name;
   }

   public int rentFee() {
      int sum = 0;
      int rentfees = 0;
      if(!videolist.isEmpty()) {
         for(Video video:videolist) {
            rentfees += video.getrentfee(); 
            }
         }
      return rentfees;
   }
   
   public Set<Video> getVideoList() {
      return videolist;
   }
   @Override
   public String toString() {
      return "[이름=" + name + ", 오늘 날짜=" + today + ", 빌린 비디오 개수: "+videolist.size()+"]";
   }

public void getVideoListImformation() {
	if(videolist.isEmpty() == true) {
		System.out.println("현재 빌린 비디오가 없습니다.");
		return;
	}
	for(Video video : videolist) {
		System.out.println(video);
		System.out.println("총 연체금은 "+rentFee()+"원입니다.");
	}
}

public void resetRentFee() {
	for(Video video : videolist) {
		if(video.getRentdays() > 2) {
			videolist.remove(video);
		}
	}
	
	
}

}