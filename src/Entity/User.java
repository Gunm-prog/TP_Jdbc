package Entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private int employeeNb;
    private String lastname;
    private String firstname;
    private String email;
    private String login;
    private String password;
    //private boolean isConnected;
}
