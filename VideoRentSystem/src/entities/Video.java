package entities;

import java.time.LocalDate;

public class Video {
   
   final String videocode;
   final LocalDate rentday;
   
   public Video (String code, LocalDate date) {
      this.videocode = code;
      this.rentday = date;
   }

   public String getVideocode() {
      return videocode;
   }

   public LocalDate getRentday() {
      return rentday;
   }

   @Override
   public String toString() {
      return "Video [videocode=" + videocode + ", rentday=" + rentday + "]";
   }
}