package entities;

import java.util.ArrayList;
import java.util.List;

public interface ClientRepository {
	public List<Client> getClientListByName();
	public List<Video> getVideoListByName(Client client);
}
