package TpJDBC.src.Service.impl;

import TpJDBC.src.Dao.ClientDao;
import TpJDBC.src.Service.contract.IClientService;
import TpJDBC.src.Entity.Client;
import java.sql.Connection;
import java.util.List;

public class ClientServiceImpl implements IClientService{
    private Connection databaseConnection;

    private final ClientDao clientDao;
    
    //Le constructeur initialise le clientDao avec la connexion à la base de données qui est en paramètres
    public ClientServiceImpl(Connection databaseConnection){
        this.clientDao = new ClientDao(databaseConnection);
    }

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
