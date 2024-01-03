package TpJDBC.src.Service.impl;

import TpJDBC.src.Dao.SupplierDAO;
import TpJDBC.src.Entity.Supplier;
import TpJDBC.src.Service.contract.ISupplierService;

import java.sql.Connection;
import java.util.List;

public class SupplierServiceImpl implements ISupplierService {

    private final SupplierDAO supplierDAO;

    public SupplierServiceImpl(Connection databaseConnection) {
        this.supplierDAO = new SupplierDAO(databaseConnection);
    }

    @Override
    public void addSupplier(Supplier supplier) {
        supplierDAO.addSupplier(supplier);
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierDAO.getSupplierById(id);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierDAO.getAllSuppliers();
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        supplierDAO.updateSupplier(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierDAO.deleteSupplier(id);
    }

}
