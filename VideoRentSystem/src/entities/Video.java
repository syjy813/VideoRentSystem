package entities;

import java.time.LocalDate;
import java.time.Period;

public class Video {

	final String videocode;
	final LocalDate rentedday;
	final LocalDate today;
	long rentfee;
	final long rentdays;

	public Video (String code, LocalDate date) {
		this.videocode = code;
		this.rentedday = date;
		this.today = LocalDate.now();
		rentdays = Period.between(date, today).getDays();
		rentfee = 0;
		if(rentdays > 2) {
			rentfee = (rentdays-2) * 500;
		}
	}

	public String getVideocode() {
		return videocode;
	}

	public long getRentdays() {
		return rentdays;
	}

	public long getrentfee() {
		if(rentdays > 2) {
			return rentfee;
		}
		return 0;
	}

	@Override
	public String toString() {
		System.out.println("현재까지 빌리신 비디오 목록입니다.");
		return "[비디오코드=" + videocode + ", 빌린 날짜=" + rentedday + ", 빌린 일수=" + rentdays
				+ ", 연체금=" + rentfee + "]";
	}
}   
