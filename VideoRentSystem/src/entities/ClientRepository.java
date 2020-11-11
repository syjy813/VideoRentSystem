package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface ClientRepository {
   public List<Client> getClientListByName();
   public Set<Video> getVideoListByName(Client client);
}