package Controller;

import TpJDBC.src.Entity.Client;
import TpJDBC.src.Service.IClientService;
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
                case 1:
                    addClient(scanner);
                    break;
                case 2:
                    getAllClients();
                    break;
                case 3:
                    getClientById(scanner);
                    break;
                case 4:
                    updateClient(scanner);
                    break;
                case 5:
                    deleteClient(scanner);
                    break;
                case 0:
                    System.out.println("Return to main menu.");
                    run = false;
                    break;
                default:
                    System.out.println("Wrong choice. Please retry.");
            }
        }
    }
    
    //Demande et r�cup�re les informations du client que l'on veut ins�rer en base de donn�es
    private void addClient(Scanner scanner) {
        System.out.println("Add Client :");
        System.out.println("------------------------");

        System.out.print("Client Number : ");
        int clientNumber = Integer.valueOf(scanner.nextLine());
        
        System.out.print("Client Firstname : ");
        String firstname = scanner.nextLine();

        System.out.print("Client Lastname : ");
        String lastname = scanner.nextLine();
        
        System.out.print("Client Email : ");
        String email = scanner.nextLine();

        System.out.print("Client Adress : ");
        String address = scanner.nextLine();

        //Cr�ation de l'objet client avec les informations donn�es
        Client client = new Client();
        client.setClientNumber(clientNumber);
        client.setFirstname(firstname);
        client.setLastname(lastname);
        client.setEmail(email);
        client.setAdress(address);

        //Appel au service pour g�rer l'insertion en base
        clientService.create(client);

        System.out.println("Client successfully created.");
    }

    //Affiche la liste des clients
    private void getAllClients() {
        System.out.println("Clients List :");

        //R�cup�ration de la liste des client en base
        List<Client> clients = clientService.readAll();

        //V�rifie la pr�sence de client en base
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

    //Affiche la le client dont l'ID est donn�
    private void getClientById(Scanner scanner) {

        System.out.print("Enter Client ID : ");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        Client client = clientService.read(clientId);

        System.out.println("ID : " + client.getId());
        System.out.println("Client Number : " + client.getClientNumber());
        System.out.println("Firstname : " + client.getFirstname());
        System.out.println("Lastname : " + client.getLastname());
        System.out.println("Email : " + client.getEmail());
        System.out.println("Adress : " + client.getAdress());
        System.out.println("------------------------");
    }

    //Demande les informations pour la mise � jour d'un client
    private void updateClient(Scanner scanner) {
        System.out.println("Client Update :");

        System.out.print("Enter Client ID : ");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        //On v�rifie si le client existe
        Client client = clientService.read(clientId);

        if (client != null) {
            System.out.println("Enter Client new data :");

            System.out.print("New Client Number : ");
            int newNumber = scanner.nextInt();
            scanner.nextLine();

                        
            System.out.print("New Client Firstname : ");
            String newFirstname = scanner.nextLine();
            
            System.out.print("New Client Lastname : ");
            String newLastname = scanner.nextLine();

            System.out.print("New Client Email : ");
            String newEmail = scanner.nextLine();

            System.out.print("New Client Adress : ");
            String newAddress = scanner.nextLine();

            Client newClient = new Client();
            newClient.setId(clientId);
            newClient.setClientNumber(newNumber);
            newClient.setFirstname(newFirstname);
            newClient.setLastname(newLastname);
            newClient.setEmail(newEmail);
            newClient.setAdress(newAddress);

            //Appel au service pour g�rer la mise � jour en base
            clientService.update(newClient);

            System.out.println("Client successfully updated.");
        } else {
            System.out.println("No client with ID " + clientId);
        }
    }

    //Supprime le client dont l'ID est donn�
    private void deleteClient(Scanner scanner) {
        System.out.println("Delete Client :");

        System.out.print("Enter Client ID : ");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        clientService.delete(clientId);
    }

}
