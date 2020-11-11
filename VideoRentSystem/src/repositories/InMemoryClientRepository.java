package repositories;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import entities.Client;
import entities.ClientRepository;
import entities.Video;

public class InMemoryClientRepository implements ClientRepository {


   final Scanner scan;
   final List<Client> list;
   public InMemoryClientRepository() {
      list = new ArrayList<>();
      scan = new Scanner(System.in);
      list.add(new Client("홍길동"));
      LocalDate date1 = LocalDate.now().minus(Period.ofDays(1));
      list.get(0).getVideoList().add(new Video("1234",date1));
      list.add(new Client("아무개"));
      LocalDate date2 = LocalDate.now().minus(Period.ofDays(2));
      list.get(1).getVideoList().add(new Video("5678",date2));
      list.add(new Client("동글이"));
      LocalDate date3 = LocalDate.now().minus(Period.ofDays(3));
      list.get(2).getVideoList().add(new Video("6452",date3));
   }

   @Override
   public List<Client> getClientListByName() {
      return list;
   }

   public void setClientByName(Client c)    {
      this.list.add(c);
   }

   @Override
   public Set<Video> getVideoListByName(Client client) {
      return client.getVideoList();
   }
   public void setVideoData(Client client) {
      System.out.println("비디오 코드를 입력하세요.");
      String videocode = scan.next();
      client.getVideoList().add(new Video(videocode,LocalDate.now()));
   }
}