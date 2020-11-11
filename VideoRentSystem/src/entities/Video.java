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
		System.out.println("������� ������ ���� ����Դϴ�.");
		return "[�����ڵ�=" + videocode + ", ���� ��¥=" + rentedday + ", ���� �ϼ�=" + rentdays
				+ ", ��ü��=" + rentfee + "]";
	}
}   
