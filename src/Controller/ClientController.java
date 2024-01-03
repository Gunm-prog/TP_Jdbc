package TpJDBC.src.Controller;

import TpJDBC.src.Entity.Client;
import TpJDBC.src.Service.contract.IClientService;
import java.util.List;
import java.util.Scanner;

public class ClientController {
    private final IClientService clientService;

    //Affecte le service donn� en param�tre
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }
    
    //Menu de s�l�ction des fonctionnalit�es
    public void userChoice(){
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println();
            System.out.println("------------------------");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Afficher la liste des clients");
            System.out.println("3. Afficher un client spécifique");
            System.out.println("4. Mettre � jour un client");
            System.out.println("5. Supprimer un client");
            System.out.println("0. Quitter");
            System.out.println("------------------------");
            System.out.println();

            System.out.print("Choix : ");
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
                    System.out.println("Retour au menu principal.");
                    run = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez r�essayer.");
            }
        }
    }
    
    //Demande et r�cup�re les informations du client que l'on veut ins�rer en base de donn�es
    private void addClient(Scanner scanner) {
        System.out.println("Ajouter un client :");
        System.out.println("------------------------");

        System.out.print("Num�ro Client : ");
        int clientNumber = Integer.valueOf(scanner.nextLine());

        System.out.print("Nom du client : ");
        String lastname = scanner.nextLine();
        
        System.out.print("Pr�nom du client : ");
        String firstname = scanner.nextLine();

        System.out.print("Email du client : ");
        String email = scanner.nextLine();

        System.out.print("Adresse du client : ");
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

        System.out.println("Client enregistr� avec succ�s.");
    }

    //Affiche la liste des clients
    private void getAllClients() {
        System.out.println("Liste des clients :");

        //R�cup�ration de la liste des client en base
        List<Client> clients = clientService.readAll();

        //V�rifie la pr�sence de client en base
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouv�.");
        } else {
            for (Client client : clients) {
                System.out.println("ID : " + client.getId());
                System.out.println("Num�ro Client : " + client.getClientNumber());
                System.out.println("Pr�nom : " + client.getFirstname());
                System.out.println("Nom : " + client.getLastname());
                System.out.println("Email : " + client.getEmail());
                System.out.println("Adresse : " + client.getAdress());
                System.out.println("------------------------");
            }
        }
    }

    //Affiche la le client dont l'ID est donn�
    private void getClientById(Scanner scanner) {

        System.out.print("Entrez l'ID du client : ");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        Client client = clientService.read(clientId);

        System.out.println("ID : " + client.getId());
        System.out.println("Num�ro : " + client.getClientNumber());
        System.out.println("Pr�nom : " + client.getFirstname());
        System.out.println("Nom : " + client.getLastname());
        System.out.println("Email : " + client.getEmail());
        System.out.println("Adresse : " + client.getAdress());
        System.out.println("------------------------");
    }

    //Demande les informations pour la mise � jour d'un client
    private void updateClient(Scanner scanner) {
        System.out.println("Mettre � jour un client :");

        System.out.print("Entrez l'ID du client � mettre � jour : ");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        //On v�rifie si le client existe
        Client client = clientService.read(clientId);

        if (client != null) {
            System.out.println("Saisissez les nouvelles donn�es du client :");

            System.out.print("Nouveau num�ro du client : ");
            int newNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nouveau nom du client : ");
            String newLastname = scanner.nextLine();
            
            System.out.print("Nouveau pr�nom du client : ");
            String newFirstname = scanner.nextLine();

            System.out.print("Nouvel email du client : ");
            String newEmail = scanner.nextLine();

            System.out.print("Nouvelle adresse du client : ");
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

            System.out.println("Client mis � jour avec succ�s.");
        } else {
            System.out.println("Aucun client trouv� avec l'ID " + clientId);
        }
    }

    //Supprime le client dont l'ID est donn�
    private void deleteClient(Scanner scanner) {
        System.out.println("Supprimer un client :");

        System.out.print("Entrez l'ID du client � supprimer : ");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        clientService.delete(clientId);
    }

}
