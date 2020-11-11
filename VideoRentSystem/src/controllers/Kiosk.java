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
            System.out.println(rentclient);
            final long rentfee = checkrentfee(rentclient);
            payRentFee(rentclient, rentfee);
            inputVideoImformation(rentclient);
            continue;
         case 2:
            continue;
         case 3:
            continue;
         case 4:
            System.out.println("���α׷��� �����մϴ�.");
            return;
         default:
            System.out.println("�ٽ� �Է��ϼ���.");
            continue;
         }
      }
   }

   private void renderWelcomMessage() {
      System.out.println("���� �뿩 �ý��ۿ� �����ϼ̽��ϴ�.");
   }

   public Client inputClientImformation() {
      System.out.println("�̸��� �Է��ϼ���.");
      String name = scan.next();

      for(Client checkclient : ClientList.getClientListByName()) {
         if(checkclient.getName().equals(name)) {
            System.out.println("���� ����Դϴ�.");
            return checkclient;
         }
      }
      System.out.println("�ű� ����Դϴ�.");
      ClientList.setClientByName(new Client(name));
      return new Client(name);
   }

   public long checkrentfee (Client rentclient) {
      long rentfee = 0;
      for(Client client : clients) {
         if(client.getName().equals(rentclient.getName())) {
            rentfee = client.rentFee() * 500;
            return rentfee;
         }
      }
      return rentfee;
   }

   public void payRentFee(Client rentclient, long rentfee) {
      if(rentfee != 0) {
         while(true) {
            System.out.printf("��ü���� %d�� �Դϴ�.", rentfee);
            System.out.println("���ΰ� �Ϸ�Ǿ����� \"���οϷ�\"��� �����ּ���.");
            String checkpay = scan.next(); 
            if(!checkpay.equals("���οϷ�")) {
               System.out.println("�߸� �ԷµǾ����ϴ�.");
               continue;
            }
            System.out.println("���ΰ� �Ϸ�Ǿ����ϴ�.");
            rentclient.getVideoList().removeAll(null);
         }
      }
   }
   public void inputVideoImformation(Client rentclient) {
      ClientList.setVideoData(rentclient);
      System.out.println("�ԷµǾ����ϴ�.");
   }

   public int menu() {
      System.out.println("�޴� \n1. �Է� \n2. ��� \n3. �˻� \n4. ����");
      int selectmenu = scan.nextInt();
      return selectmenu;
   }
}


