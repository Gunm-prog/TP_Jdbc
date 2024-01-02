package Service_Impl;

import java.util.List;

public interface IClientService {
    void create(TpJDBC.src.Entity.Client client);

    void update(TpJDBC.src.Entity.Client client);

    TpJDBC.src.Entity.Client read(Long id);

    List<TpJDBC.src.Entity.Client> readAll();

    void delete(Long id);
}
