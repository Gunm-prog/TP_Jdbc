package Entity;

import java.io.Serializable;

public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private int supplierNb;
    private String name;
    private String email;
    private String adress;

    //getter et setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSupplierNb() {
        return supplierNb;
    }

    public void setSupplierNb(int supplierNb) {
        this.supplierNb = supplierNb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
