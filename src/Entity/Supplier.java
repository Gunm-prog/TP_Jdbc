package Entity;

import java.io.Serializable;

public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
<<<<<<< HEAD
    private int supplierNb;
    private String name;
    private String email;
    private String adress;
=======

    private int nb;

    private String name;

    private String email;

    private String address;
>>>>>>> 0716407853153ad27b744460a9cc4273bdeed75d

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    public int getSupplierNb() {
        return supplierNb;
    }

    public void setSupplierNb(int supplierNb) {
        this.supplierNb = supplierNb;
=======
    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
>>>>>>> 0716407853153ad27b744460a9cc4273bdeed75d
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

<<<<<<< HEAD
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
=======
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Supplier{" +
                "id=" + id +
                ", nb=" + nb +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
>>>>>>> 0716407853153ad27b744460a9cc4273bdeed75d
    }
}
