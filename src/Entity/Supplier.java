package Entity;

import java.io.Serializable;

public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private int nb;

    private String name;

    private String email;

    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
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
    }
}
