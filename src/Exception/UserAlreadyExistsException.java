package TpJDBC.src.Exception;


import java.sql.SQLIntegrityConstraintViolationException;

public class UserAlreadyExistsException extends SQLIntegrityConstraintViolationException {
    private static final long serialVersionUID=1L;


    public UserAlreadyExistsException(String message) {

        super( "User's duplication forbidden! \n" + message );
    }

}
