package TpJDBC.src.Service.contract;

import TpJDBC.src.Entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface IItemService {

    List<Item> findAll();

    Item getById(Long id);

    void addItem(Item item) throws SQLException;

    void updateItem(Item item);

    void deleteById(Long id);
    
}
