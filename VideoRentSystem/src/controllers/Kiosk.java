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
            System.out.println("프로그램을 종료합니다.");
            return;
         default:
            System.out.println("다시 입력하세요.");
            continue;
         }
      }
   }

   private void renderWelcomMessage() {
      System.out.println("비디오 대여 시스템에 접속하셨습니다.");
   }

   public Client inputClientImformation() {
      System.out.println("이름을 입력하세요.");
      String name = scan.next();

      for(Client checkclient : ClientList.getClientListByName()) {
         if(checkclient.getName().equals(name)) {
            System.out.println("기존 멤버입니다.");
            return checkclient;
         }
      }
      System.out.println("신규 멤버입니다.");
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
            System.out.printf("연체금은 %d원 입니다.", rentfee);
            System.out.println("납부가 완료되었으면 \"납부완료\"라고 적어주세요.");
            String checkpay = scan.next(); 
            if(!checkpay.equals("납부완료")) {
               System.out.println("잘못 입력되었습니다.");
               continue;
            }
            System.out.println("납부가 완료되었습니다.");
            rentclient.getVideoList().removeAll(null);
         }
      }
   }
   public void inputVideoImformation(Client rentclient) {
      ClientList.setVideoData(rentclient);
      System.out.println("입력되었습니다.");
   }

   public int menu() {
      System.out.println("메뉴 \n1. 입력 \n2. 출력 \n3. 검색 \n4. 종료");
      int selectmenu = scan.nextInt();
      return selectmenu;
   }
}


