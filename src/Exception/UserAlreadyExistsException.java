package Exception;


import java.sql.SQLIntegrityConstraintViolationException;

public class UserAlreadyExistsException extends SQLIntegrityConstraintViolationException {
    private static final long serialVersionUID=1L;

    //constructeur de mon exception qui hérite de l'extends qui a son propre constructeur
    public UserAlreadyExistsException(String message) {
        // fait appel au constructeur parent
        super( "User's duplication forbidden! \n" + message );
    }

}
