package Service.impl;

import Dao.SupplierDAO;
import Entity.Supplier;
import Service.contract.ISupplierService;

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

    @Override
    public boolean isSupplierNumberExists(int supplierNumber) {
        // Implémentation pour vérifier si le numéro du fournisseur existe déjà
        return supplierDAO.isSupplierNumberExists(supplierNumber);
    }

}
