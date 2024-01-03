package TpJDBC.src.Service.impl;


import TpJDBC.src.Dao.ItemDao;
import TpJDBC.src.Entity.Item;
import TpJDBC.src.Service.contract.IItemService;

import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl implements IItemService {

    private final ItemDao itemDao;

    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public List<Item> findAll() {
        return itemDao.findAll();
    }

    @Override
    public Item getById(Long id) {
        return itemDao.getById(id);
    }

    @Override
    public void addItem(Item item) throws SQLException {
        itemDao.addItem(item);
    }

    @Override
    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    @Override
    public void deleteById(Long id) {
        itemDao.deleteItem(id);
    }
}
