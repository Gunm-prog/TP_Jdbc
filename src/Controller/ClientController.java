package Controller;

import Entity.Client;
import Service.contract.IClientService;
import java.util.List;
import java.util.Scanner;

public class ClientController {
    private final IClientService clientService;

    //Constructor giving the service in parameter to the attribute
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }
    
    //Select menu
    public void userChoice(){
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            try{
                System.out.println();
                System.out.println("------------------------");
                System.out.println("1. Add Client");
                System.out.println("2. Display all Clients");
                System.out.println("3. Display specific Client");
                System.out.println("4. Update Client");
                System.out.println("5. Delete Client");
                System.out.println("0. Exit");
                System.out.println("------------------------");
                System.out.println();

                System.out.print("Selection : ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addClient(scanner);
                    case 2 -> getAllClients();
                    case 3 -> getClientById(scanner);
                    case 4 -> updateClient(scanner);
                    case 5 -> deleteClient(scanner);
                    case 0 -> {
                        System.out.println("Return to main menu.");
                        run = false;
                    }
                    default -> System.out.println("Wrong choice. Please retry.");
                }
            }catch(Exception e){
                System.out.println(e);
                System.out.println("Wrong choice. Please retry.");
                scanner.nextLine(); 
            } 
        }
    }
    
    private boolean isValidEmail(String email) {
        // Regex pour valider l'e-mail
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    
    public static boolean isInteger(String strInt) {
        if (strInt == null) {
            return false;
        }
        try {
            Integer.parseInt(strInt);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    //Demande et récupère les informations du client que l'on veut insérer en base de données
    private void addClient(Scanner scanner) {
        try{
            System.out.println("Add Client :");
            System.out.println("------------------------");

            //Verify if the input given is an Integer
            String NumberString;
            do {
                System.out.print("Client Number : ");
                NumberString = scanner.nextLine();

                if (!this.isInteger(NumberString)) {
                    System.out.println("Please enter an Integer");
                }
            } while (!this.isInteger(NumberString));
            int clientNumber = Integer.parseInt(NumberString);

            System.out.print("Client Firstname : ");
            String firstname = scanner.nextLine();

            System.out.print("Client Lastname : ");
            String lastname = scanner.nextLine();

            // Validation de l'e-mail
            String email;
            do {
                System.out.print("Client Email : ");
                email = scanner.nextLine();

                if (!isValidEmail(email)) {
                    System.out.println("Email adress invalid. Please retry.");
                }
            } while (!isValidEmail(email));


            System.out.print("Client Adress : ");
            String address = scanner.nextLine();

            //Création de l'objet client avec les informations donnéées
            Client client = new Client();
            client.setClientNumber(clientNumber);
            client.setFirstname(firstname);
            client.setLastname(lastname);
            client.setEmail(email);
            client.setAdress(address);

            //Appel au service pour gérer l'insertion en base
            clientService.create(client);

            System.out.println("Client successfully created.");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //Affiche la liste des clients
    private void getAllClients() {
        System.out.println("Clients List :");

        //Récupération de la liste des client en base
        List<Client> clients = clientService.readAll();

        //Vérifie la présence de client en base
        if (clients.isEmpty()) {
            System.out.println("No Client found");
        } else {
            for (Client client : clients) {
                System.out.println("ID : " + client.getId());
                System.out.println("Client Number : " + client.getClientNumber());
                System.out.println("Firstname : " + client.getFirstname());
                System.out.println("Lastname : " + client.getLastname());
                System.out.println("Email : " + client.getEmail());
                System.out.println("Adress : " + client.getAdress());
                System.out.println("------------------------");
            }
        }
    }

    //Affiche la le client dont l'ID est donné
    private void getClientById(Scanner scanner) {

        //Verify if the input given is an Integer
        String NumberString;
        do {
            System.out.print("Enter Client ID : ");
            NumberString = scanner.nextLine();

            if (!this.isInteger(NumberString)) {
                System.out.println("Please enter an Integer");
            }
        } while (!this.isInteger(NumberString));
        long clientId = Integer.parseInt(NumberString);

        Client client = clientService.read(clientId);

        System.out.println("ID : " + client.getId());
        System.out.println("Client Number : " + client.getClientNumber());
        System.out.println("Firstname : " + client.getFirstname());
        System.out.println("Lastname : " + client.getLastname());
        System.out.println("Email : " + client.getEmail());
        System.out.println("Adress : " + client.getAdress());
        System.out.println("------------------------");
    }

    //Demande les informations pour la mise à jour d'un client
    private void updateClient(Scanner scanner) {
        System.out.println("Client Update :");
        
        //Verify if the input given is an Integer
        String NumberString;
        do {
            System.out.print("Enter Client ID : ");
            NumberString = scanner.nextLine();

            if (!this.isInteger(NumberString)) {
                System.out.println("Please enter an Integer");
            }
        } while (!this.isInteger(NumberString));
        long clientId = Integer.parseInt(NumberString);
        

        //On vérifie si le client existe
        Client client = clientService.read(clientId);

        if (client != null) {
            System.out.println("Enter Client new data :");

            //Verify if the input given is an Integer
            do {
                System.out.print("New Client Number : ");
                NumberString = scanner.nextLine();

                if (!this.isInteger(NumberString)) {
                    System.out.println("Please enter an Integer");
                }
            } while (!this.isInteger(NumberString));
            int newNumber = Integer.parseInt(NumberString);

                        
            System.out.print("New Client Firstname : ");
            String newFirstname = scanner.nextLine();
            
            System.out.print("New Client Lastname : ");
            String newLastname = scanner.nextLine();

            // Validation de l'e-mail
            String newEmail;
            do {
                System.out.print("Client Email : ");
                newEmail = scanner.nextLine();

                if (!isValidEmail(newEmail)) {
                    System.out.println("Email adress invalid. Please retry.");
                }
            } while (!isValidEmail(newEmail));

            System.out.print("New Client Adress : ");
            String newAddress = scanner.nextLine();

            Client newClient = new Client();
            newClient.setId(clientId);
            newClient.setClientNumber(newNumber);
            newClient.setFirstname(newFirstname);
            newClient.setLastname(newLastname);
            newClient.setEmail(newEmail);
            newClient.setAdress(newAddress);

            //Appel au service pour gï¿½rer la mise ï¿½ jour en base
            clientService.update(newClient);

            System.out.println("Client successfully updated.");
        } else {
            System.out.println("No client with ID " + clientId);
        }
    }

    //Supprime le client dont l'ID est donné
    private void deleteClient(Scanner scanner) {
        System.out.println("Delete Client :");

        //Verify if the input given is an Integer
        String NumberString;
        do {
            System.out.print("Enter Client ID : ");
            NumberString = scanner.nextLine();

            if (!ClientController.isInteger(NumberString)) {
                System.out.println("Please enter an Integer");
            }
        } while (!this.isInteger(NumberString));
        long clientId = Integer.parseInt(NumberString);

        clientService.delete(clientId);
    }

}
