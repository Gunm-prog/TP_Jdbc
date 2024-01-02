package Controller;

import Entity.User;
import Service.contract.IUserService;
import Exception.UserAlreadyExistsException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    public void select() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("1. Add user");
            System.out.println("2. Display user's list");
            System.out.println("3. Display user");
            System.out.println("4. Update user");
            System.out.println("5. Delete user");
            System.out.println("0. Quit");

            System.out.println("Selection: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            //get user's choice using switch case method
            switch (choice) {
                case 1:
                    addUser(scanner);
                    break;
                case 2:
                    findAll();
                    break;
                case 3:
                    getById(scanner);
                    break;
                case 4:
                    updateUser(scanner);
                    break;
                case 5:
                    deleteUser(scanner);
                    break;
                case 0:
                    System.out.println("Exit Menu");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection: try again!");
            }
        }
    }

    private void addUser (Scanner scanner) {
        System.out.println("Add user: ");
        System.out.println("------------------------------");

        System.out.println("User Number: ");
        int employeeNb = scanner.nextInt();
        scanner.nextLine();

        System.out.println("User's lastname: ");
        String lastname = scanner.nextLine();

        System.out.println("User's firstname: ");
        String firstname = scanner.nextLine();

        System.out.println("User's email: ");
        String email = scanner.nextLine();

        System.out.println("User's pseudo: ");
        String login = scanner.nextLine();

        System.out.println("User's password: ");
        String password = scanner.nextLine();

        //Creation of Object User
        User newUser = new User();
        newUser.setEmployeeNb(employeeNb);
        newUser.setLastname(lastname);
        newUser.setFirstname(firstname);
        newUser.setEmail(email);
        newUser.setLogin(login);
        newUser.setPassword(password);

        try{
            //Calling addUser method in order to add user
            userService.addUser(newUser);
            System.out.println("User successfully added!");
        } catch( UserAlreadyExistsException e ){
            System.out.println("User addition failed!");
            System.out.println(e.getMessage());
        } catch (SQLException e ){
            System.out.println("User addition failed!");
            e.printStackTrace();
        }
    }


    private void findAll() {
        System.out.println("Users' List: ");

        //Calling findAll method
        List<User> users = userService.findAll();

        //Display "User not found" in case of it
        if (users.isEmpty()) {
            System.out.println("User not found");
        } else {
            for (User user: users) {
                System.out.println("Id: " + user.getId());
                System.out.println("Employee number: " + user.getEmployeeNb());
                System.out.println("Lastname: " + user.getLastname());
                System.out.println("Firstname: " + user.getFirstname());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Pseudo: " + user.getLogin());
                System.out.println("Password: " + user.getPassword());
                System.out.println("-------------------------------");
            }
        }
    }


    private void getById(Scanner scanner) {

        System.out.println("Enter user's id: ");
        //get user's id
        Long id = scanner.nextLong();
        scanner.nextLine();

        //Calling getById method to get a specific user thanks to its id
        User user = userService.getById(id);

        //Check id
        if (user != null) {
            System.out.println("User's details: ");
            System.out.println("Id: " + user.getId());
            System.out.println("Employee number: " + user.getEmployeeNb());
            System.out.println("Lastname: " + user.getLastname());
            System.out.println("Firstname: " + user.getFirstname());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Pseudo: " + user.getLogin());
            System.out.println("Password: " + user.getPassword());
        } else {
            System.out.println("User with id " + id + " not found.");
        }
    }

    private void updateUser (Scanner scanner) {
        System.out.println("Update user: ");

        System.out.println("Enter user's id to update: ");
        //Get user's id
        Long id = scanner.nextLong();
        scanner.nextLine();

        //Check if user exists
        User existingUser = userService.getById(id);

        if (existingUser != null) {
            System.out.println("Enter user's new datas: ");

            System.out.println("User Number: ");
            int newEmployeeNb = scanner.nextInt();
            scanner.nextLine();

            System.out.println("User's lastname: ");
            String newLastname = scanner.nextLine();

            System.out.println("User's firstname: ");
            String newFirstname = scanner.nextLine();

            System.out.println("User's email: ");
            String newEmail = scanner.nextLine();

            System.out.println("User's pseudo: ");
            String newLogin = scanner.nextLine();

            System.out.println("User's password: ");
            String newPassword = scanner.nextLine();

            //Updating Object user with new datas entered by user
            User updatedUser = new User();
            updatedUser.setEmployeeNb(newEmployeeNb);
            updatedUser.setLastname(newLastname);
            updatedUser.setFirstname(newFirstname);
            updatedUser.setEmail(newEmail);
            updatedUser.setLogin(newLogin);
            updatedUser.setPassword(newPassword);
            updatedUser.setId(id);

            //Calling updateUser method to save modifications
            userService.updateUser(updatedUser);

            System.out.println(" User successfully updated!");
        } else {
            System.out.println("User with id: " + id + " not found");
        }
    }

    private void deleteUser(Scanner scanner) {
        System.out.println("Delete user :");

        System.out.print("Enter user's id to delete : ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        // Calling getById method to check if user exists
        User existingUser = userService.getById(id);

        if (existingUser != null) {
            // Deleting by calling deleteUser method
            userService.deleteById(id);

            System.out.println("User successfully deleted!");
        } else {
            System.out.println("User with id: " + id + " not found");
        }
    }



}
