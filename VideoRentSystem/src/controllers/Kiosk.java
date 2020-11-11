package controllers;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import entities.Client;
import entities.Video;
import repositories.InMemoryClientRepository;

public class Kiosk {
	Scanner scan = new Scanner(System.in);
	InMemoryClientRepository ClientList;
	final List<Client> clients;

	public Kiosk() {
		ClientList = new InMemoryClientRepository();
		clients = ClientList.getClientListByName();
	}

	public void run() {
		//0.접속 안내 창을 띄워줍니다.
		renderWelcomMessage();
		// 메뉴
		while(true) {
			switch(menu()) {
			case 1:
				Client rentclient = inputClientImformation();
				final long rentfee = checkrentfee(rentclient);
				payRentFee(rentclient, rentfee);
				inputVideoImformation(rentclient);
				continue;
			case 2:
				selectClientImformation();
				continue;
			case 3:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("다시 입력하세요.");
				continue;
			}
		}
	}

	private void selectClientImformation() {
		for(Client client:clients) {
			System.out.println(client);
		}
		System.out.println("회원의 비디오 목록을 보려면 1번, 이전 메뉴로 돌아가시려면 2번을 눌러주세요.");
		int selectnum = scan.nextInt();
		while(true) {
			switch(selectnum) {
			case 1:
				while(true) {
					System.out.println("회원의 이름을 입력해주세요.");
					String selectname = scan.next();
					for(Client client:clients) {
						if(client.getName().equals(selectname)) {
							client.getVideoListImformation();
							int rentfees = client.rentFee();
							System.out.println("지금 납부하시겠습니까? 네/아니오");
							String answer = scan.next();
							if(answer.equals("네")) {
								payRentFee(client, rentfees);
								return;
							}
							else if(answer.equals("아니오")) {
								return;
							}
							else {
								System.out.println("잘못 입력했습니다. 다시 입력하세요.");
								continue;
							}
						}
					}
				}				
			case 2: 
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
				continue;
			}
		}
	}

	private void getClientImformation(Client rentclient) {
		System.out.println(rentclient);
		rentclient.getVideoListImformation();
	}

	private void renderWelcomMessage() {
		System.out.println("비디오 대여 시스템에 접속하셨습니다.");
	}

	public Client inputClientImformation() {
		System.out.println("이름을 입력하세요.");
		String name = scan.next();

		for(Client checkclient : clients) {
			if(checkclient.getName().equals(name)) {
				System.out.println("기존 멤버입니다.");
				return checkclient;
			}
		}
		System.out.println("신규 멤버입니다.");
		clients.add(new Client(name));
		return new Client(name);
	}

	public long checkrentfee (Client rentclient) {
		return rentclient.rentFee();
	}

	public void payRentFee(Client rentclient, long rentfee) {
		if(rentfee != 0) {
			while(true) {
				System.out.printf("[연체금: %d원]", rentfee);
				System.out.println("납부가 완료되었으면 \"납부완료\"라고 적어주세요.");
				String checkpay = scan.next(); 
				if(!checkpay.equals("납부완료")) {
					System.out.println("잘못 입력되었습니다.");
					continue;
				}
				System.out.println("납부가 완료되었습니다.");
				rentclient.resetRentFee(); 
				return;
			}
		}
	}
	public void inputVideoImformation(Client rentclient) {
		ClientList.setVideoData(rentclient);
		System.out.println("입력되었습니다.");
	}

	public int menu() {
		System.out.println("메뉴 \n1. 입력 \n2. 회원 정보 조회 \n3. 종료");
		int selectmenu = scan.nextInt();
		return selectmenu;
	}
}


