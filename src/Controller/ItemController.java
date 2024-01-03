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

    private boolean isValidEmail(String email) {
        // Expression régulière pour valider l'e-mail
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private void addItem (Scanner scanner) {
        System.out.println("Add item: ");
        System.out.println("------------------------------");

        int number = makeControlledIntInput(scanner, "Item Number: ");
        /*System.out.println("Item Number: ");
        int number = scanner.nextInt();*/

        String status = makeControlledStringInput(scanner, "Item Status: ", 255);
        /*System.out.println("Item Status: ");
        String status = getStatusChoice( scanner );*/

        String name = makeControlledStringInput(scanner, "Item Name: ", 255);
        /*System.out.println("Item's name: ");
        String name = scanner.nextLine();*/

        String description = makeControlledStringInput(scanner, "Item Description: ", 255);
        /*System.out.println("Item's description: ");
        String description = scanner.nextLine();*/


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


    private void findAll() {
        System.out.println("Item List: ");

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


    private void getById(Scanner scanner) {

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

    private void updateItem (Scanner scanner) {
        System.out.println("Update item: ");


        Long id = (long) makeControlledIntInput(scanner, "Enter item's id to delete: ");
        /*System.out.print("Enter user's id to delete : ");
        Long id = scanner.nextLong();
        scanner.nextLine()*/;

        //Check if item exists
        Item existingItem = itemService.getById(id);

        if (existingItem != null) {
            System.out.println("Enter item's new datas: ");

            int newNumber = makeControlledIntInput(scanner, "Item's number: ");
            /*System.out.println("Item's number: ");
            int newNumber = scanner.nextInt();
            */
            String newStatus = getStatusChoice( scanner );

            String newName = makeControlledStringInput(scanner, "Item's name: ", 255);
            /*System.out.println("Item's name: ");
            String newName = scanner.nextLine();*/

            String newDescription = makeControlledStringInput(scanner, "Item's Description: ", 255);
            /*System.out.println("Item's Description: ");
            String newDescription = scanner.nextLine();
*/

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

    private void deleteUser(Scanner scanner) {
        System.out.println("Delete item :");

        Long id = (long) makeControlledIntInput(scanner, "Enter item's id to delete: ");
        /*System.out.print("Enter user's id to delete : ");
        Long id = scanner.nextLong();
        scanner.nextLine()*/;

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

    private String getStatusChoice(Scanner scanner){
        int indexStatus = makeControlledIntInput(scanner, "Item status: [0] sold or [1] for sell");
        /*System.out.println("Item status: [0] sold or [1] for sell ");
        int indexStatus = scanner.nextInt();
        scanner.nextLine();*/

        String newStatus;
        if (indexStatus == 0) {
            newStatus = String.valueOf(ItemStatus.sold);
        } else {
            newStatus = String.valueOf(ItemStatus.for_sell);
        }
        return newStatus;
    }

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
     * Method controlling that the value entered is a numeric one
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
