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
		//0.���� �ȳ� â�� ����ݴϴ�.
		renderWelcomMessage();
		// �޴�
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
				System.out.println("���α׷��� �����մϴ�.");
				return;
			default:
				System.out.println("�ٽ� �Է��ϼ���.");
				continue;
			}
		}
	}

	private void selectClientImformation() {
		for(Client client:clients) {
			System.out.println(client);
		}
		System.out.println("ȸ���� ���� ����� ������ 1��, ���� �޴��� ���ư��÷��� 2���� �����ּ���.");
		int selectnum = scan.nextInt();
		while(true) {
			switch(selectnum) {
			case 1:
				while(true) {
					System.out.println("ȸ���� �̸��� �Է����ּ���.");
					String selectname = scan.next();
					for(Client client:clients) {
						if(client.getName().equals(selectname)) {
							client.getVideoListImformation();
							int rentfees = client.rentFee();
							System.out.println("���� �����Ͻðڽ��ϱ�? ��/�ƴϿ�");
							String answer = scan.next();
							if(answer.equals("��")) {
								payRentFee(client, rentfees);
								return;
							}
							else if(answer.equals("�ƴϿ�")) {
								return;
							}
							else {
								System.out.println("�߸� �Է��߽��ϴ�. �ٽ� �Է��ϼ���.");
								continue;
							}
						}
					}
				}				
			case 2: 
				System.out.println("���� �޴��� ���ư��ϴ�.");
				return;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ���.");
				continue;
			}
		}
	}

	private void getClientImformation(Client rentclient) {
		System.out.println(rentclient);
		rentclient.getVideoListImformation();
	}

	private void renderWelcomMessage() {
		System.out.println("���� �뿩 �ý��ۿ� �����ϼ̽��ϴ�.");
	}

	public Client inputClientImformation() {
		System.out.println("�̸��� �Է��ϼ���.");
		String name = scan.next();

		for(Client checkclient : clients) {
			if(checkclient.getName().equals(name)) {
				System.out.println("���� ����Դϴ�.");
				return checkclient;
			}
		}
		System.out.println("�ű� ����Դϴ�.");
		clients.add(new Client(name));
		return new Client(name);
	}

	public long checkrentfee (Client rentclient) {
		return rentclient.rentFee();
	}

	public void payRentFee(Client rentclient, long rentfee) {
		if(rentfee != 0) {
			while(true) {
				System.out.printf("[��ü��: %d��]", rentfee);
				System.out.println("���ΰ� �Ϸ�Ǿ����� \"���οϷ�\"��� �����ּ���.");
				String checkpay = scan.next(); 
				if(!checkpay.equals("���οϷ�")) {
					System.out.println("�߸� �ԷµǾ����ϴ�.");
					continue;
				}
				System.out.println("���ΰ� �Ϸ�Ǿ����ϴ�.");
				rentclient.resetRentFee(); 
				return;
			}
		}
	}
	public void inputVideoImformation(Client rentclient) {
		ClientList.setVideoData(rentclient);
		System.out.println("�ԷµǾ����ϴ�.");
	}

	public int menu() {
		System.out.println("�޴� \n1. �Է� \n2. ȸ�� ���� ��ȸ \n3. ����");
		int selectmenu = scan.nextInt();
		return selectmenu;
	}
}


