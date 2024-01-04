package Controller;

import Entity.Item;
import Entity.ItemStatus;
import Service.contract.IItemService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ItemController {

    private final IItemService itemService;

    public ItemController(IItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Displays the menu for the item table, and calls the methods for each action corresponding to the user’s choice
     */
    public void select() {
        Scanner scanner = new Scanner(System.in);

        boolean isContinue = true;

        while(isContinue) {
            System.out.println();
            System.out.println("------------------------");
            System.out.println("1. Add item");
            System.out.println("2. Display item list");
            System.out.println("3. Display item");
            System.out.println("4. Update item");
            System.out.println("5. Delete item");
            System.out.println("0. Quit");
            System.out.println("------------------------");
            System.out.println();

            System.out.println("Selection: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            //get user's choice using switch case method
            switch (choice) {
                case 1 -> addItem(scanner);
                case 2 -> findAll();
                case 3 -> getById(scanner);
                case 4 -> updateItem(scanner);
                case 5 -> deleteUser(scanner);
                case 0 -> {
                    System.out.println("Exit Menu");
                    isContinue = false;
                }
                default -> System.out.println("Invalid selection: try again!");
            }
        }
    }

    /**
     * check with reggex the mail format
     * @param email
     * @return true if ok, else false
     */
    private boolean isValidEmail(String email) {
        // Expression régulière pour valider l'e-mail
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    /**
     * Asks questions to create a new item and create it in the database
     * @param scanner with system.in
     */
    private void addItem (Scanner scanner) {
        System.out.println("Add item: ");
        System.out.println("------------------------------");

        int number = makeControlledIntInput(scanner, "Item Number: ");

        System.out.println("Item Status: ");
        String status = getStatusChoice( scanner );

        String name = makeControlledStringInput(scanner, "Item Name: ", 255);

        String description = makeControlledStringInput(scanner, "Item Description: ", 255);


        //Creation of Object Item
        Item newItem = new Item();
        newItem.setNumber(number);
        newItem.setStatus(ItemStatus.valueOf(status));
        newItem.setName(name);
        newItem.setDescription(description);

        try{
            //Calling addUser method in order to add item
            itemService.addItem(newItem);
            System.out.println("Item successfully added!");
        } catch (SQLException e ){
            System.out.println("Item addition failed!");
            e.printStackTrace();
        }
    }

    /**
     * Displays all items that can be found in the database
     */
    private void findAll() {
        System.out.println("Item List: ");
        System.out.println("------------------------------");

        //Calling findAll method
        List<Item> items = itemService.findAll();

        //Display "Item not found" in case of it
        if (items.isEmpty()) {
            System.out.println("Item not found");
        } else {
            for (Item item: items) {
                System.out.println("Id: " + item.getId());
                System.out.println("Number: " + item.getNumber());
                System.out.println("Status: " + item.getStatus());
                System.out.println("Name: " + item.getName());
                System.out.println("Description: " + item.getDescription());
                System.out.println("-------------------------------");
            }
        }
    }


    /**
     * Ask for an id, try to find the article with its id for display
     * @param scanner with system.in
     */
    private void getById(Scanner scanner) {
        System.out.println("Item by id: ");
        System.out.println("------------------------------");

        Long id = (long) makeControlledIntInput(scanner, "Enter item's id to delete: ");
        /*System.out.print("Enter user's id to delete : ");
        Long id = scanner.nextLong();
        scanner.nextLine()*/;

        //Calling getById method to get a specific item thanks to its id
        Item item = itemService.getById(id);

        //Check id
        if (item != null) {
            System.out.println("Item details: ");
            System.out.println("Id: " + item.getId());
            System.out.println("Number: " + item.getNumber());
            System.out.println("Status: " + item.getStatus());
            System.out.println("Name: " + item.getName());
            System.out.println("Description: " + item.getDescription());
        } else {
            System.out.println("Item with id " + id + " not found.");
        }
    }

    /**
     * Ask for an identifier, try to find the item with its identifier and then ask for each attribute a new value avent to update the item in the database
     * @param scanner with system.in
     */
    private void updateItem (Scanner scanner) {
        System.out.println("Update item: ");
        System.out.println("------------------------------");


        Long id = (long) makeControlledIntInput(scanner, "Enter item's id to delete: ");

        //Check if item exists
        Item existingItem = itemService.getById(id);

        if (existingItem != null) {
            System.out.println("Enter item's new datas: ");

            int newNumber = makeControlledIntInput(scanner, "Item's number: ");

            String newStatus = getStatusChoice( scanner );

            String newName = makeControlledStringInput(scanner, "Item's name: ", 255);

            String newDescription = makeControlledStringInput(scanner, "Item's Description: ", 255);

            //Updating Object item with new datas
            Item updatedItem = new Item();
            updatedItem.setNumber(newNumber);
            updatedItem.setStatus(ItemStatus.valueOf(newStatus));
            updatedItem.setName(newName);
            updatedItem.setDescription(newDescription);
            updatedItem.setId(id);

            //Calling updateItem method to save modifications
            itemService.updateItem(updatedItem);

            System.out.println(" Item successfully updated!");
        } else {
            System.out.println("Item with id: " + id + " not found");
        }
    }

    /**
     * Ask for an id, try to find item with its id and delete the item in database
     * @param scanner with system.in
     */
    private void deleteUser(Scanner scanner) {
        System.out.println("Delete item :");
        System.out.println("------------------------------");

        Long id = (long) makeControlledIntInput(scanner, "Enter item's id to delete: ");

        // Calling getById method to check if item exists
        Item existingItem = itemService.getById(id);

        if (existingItem != null) {
            // Deleting by calling deleteItem method
            itemService.deleteById(id);

            System.out.println("Item successfully deleted!");
        } else {
            System.out.println("Item with id: " + id + " not found");
        }
    }

    /**
     * Displays the choice between two status, and asks the user his choice
     * @param scanner
     * @return
     */
    private String getStatusChoice(Scanner scanner){
        boolean isOk;
        int indexStatus;
        do{
            isOk = false;
            indexStatus = makeControlledIntInput(scanner, "Item status: [0] sold or [1] for sell");
            if( indexStatus == 0 || indexStatus == 1 ){
                isOk = true;
            } else {
                System.out.println( "Invalid input. \n Choice only [0] or [1] options" );
            }
        } while( !isOk );

        String newStatus;
        if (indexStatus == 0) {
            newStatus = String.valueOf(ItemStatus.sold);
        } else {
            newStatus = String.valueOf(ItemStatus.for_sell);
        }
        return newStatus;
    }

    /**
     * method allowing to ask something via Scanner and controlling String validity (we ask for a String there)
     * String is valid if it is not null or empty or not superior to maxLength (due to the nb put in the VARCHAR)
     * While String is not valid, the loop goes on
     *
     * @param scanner Scanner Object with System.in parameter
     * @param question asked for a String
     * @param maxLength allowed for answer
     * @return the valid answer as a String
     */
    private String makeControlledStringInput( Scanner scanner, String question, int maxLength ) {
        boolean isOk;
        String userInput;
        do{
            isOk = false;
            System.out.print('\n' +  question );
            userInput = scanner.next();
            if( !userInput.isEmpty() && userInput.length() <= maxLength ){
                isOk = true;
            } else {
                System.out.println( "Invalid input. \n Length must be between 1 and " + maxLength);
            }
        } while( !isOk );
        return userInput;
    }

    /**
     * Method controlling that the value entered is a numerical one
     * @param scanner Scanner Object with System.in parameter
     * @param question asked for an int
     * @return the valid answer as an int
     */
    private int makeControlledIntInput( Scanner scanner, String question ) {
        boolean isOk;
        int  userInput = 0;
        do{
            isOk = false;
            System.out.print('\n' +  question );
            try {
                userInput = scanner.nextInt();
                isOk = true;
            } catch ( Exception e ) {
                System.out.println( "Entrée invalide. \n You must enter a numerical value" );
                scanner.next();
            }
        } while( !isOk );
        return userInput;
    }

}
