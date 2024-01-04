package Service.contract;

import Entity.Client;

import java.util.List;

public interface IClientService {

    void create(Client client);
    
    void update(Client client);
    
    Client read(Long id);
    
    List<Client> readAll();
    
    void delete(Long id);

}
