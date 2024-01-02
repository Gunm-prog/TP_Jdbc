package Service_Impl;

import TpJDBC.src.Dao.ClientDao;
import TpJDBC.src.Entity.Client;
import TpJDBC.src.Service.IClientService;
import java.sql.Connection;
import java.util.List;
        
public class ClientServiceImpl implements IClientService{
    private Connection databaseConnection;
    
    private final ClientDao clientDao = new ClientDao(databaseConnection);

    @Override
    public void create(Client client) {
        this.clientDao.create(client);
    }

    @Override
    public void update(Client client) {
        this.clientDao.update(client);
    }

    @Override
    public Client read(Long id) {
        return this.clientDao.read(id);
    }

    @Override
    public List<Client> readAll() {
        return this.clientDao.readAll();
    }

    @Override
    public void delete(Long id) {
        this.clientDao.delete(id);
    }
    
}
