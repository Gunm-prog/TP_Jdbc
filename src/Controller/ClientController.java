package TpJDBC.src.Controller;

import TpJDBC.src.Entity.Client;
import TpJDBC.src.Service.IClientService;
import java.util.List;
import java.util.Scanner;

public class ClientController {
    
    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }
    public void userChose(){
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("1. Ajouter un client");
            System.out.println("2. Afficher la liste des clients");
            System.out.println("3. Afficher un client spécifique");
            System.out.println("4. Mettre à jour un client");
            System.out.println("5. Supprimer un client");
            System.out.println("0. Quitter");

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
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
    
    private void addClient(Scanner scanner) {
        System.out.println("Ajouter un client :");
        System.out.println("------------------------");

        System.out.print("Numéro Client : ");
        int clientNumber = Integer.valueOf(scanner.nextLine());

        System.out.print("Nom du client : ");
        String lastname = scanner.nextLine();
        
        System.out.print("Prénom du client : ");
        String firstname = scanner.nextLine();

        System.out.print("Email du client : ");
        String email = scanner.nextLine();

        System.out.print("Adresse du client : ");
        String address = scanner.nextLine();

        // Créer l'objet Fournisseur
        Client client = new Client();
        client.setClientNumber(clientNumber);
        client.setFirstname(firstname);
        client.setLastname(lastname);
        client.setEmail(email);
        client.setAdress(address);

        // Appeler la méthode de création du service
        clientService.create(client);

        System.out.println("Client enregistré avec succès.");
    }


    private void getAllClients() {
        System.out.println("Liste des clients :");

        // Appeler la méthode getAllSuppliers
        List<Client> clients = clientService.readAll();

        //si aucun fournisseur trouvé affichage (Aucun fournisseur trouvé)
        if (clients.isEmpty()) {
            System.out.println("Aucun fournisseur trouvé.");
        } else {
            for (Client client : clients) {
                System.out.println("ID : " + client.getId());
                System.out.println("Numéro : " + client.getClientNumber());
                System.out.println("Prénom : " + client.getFirstname());
                System.out.println("Nom : " + client.getLastname());
                System.out.println("Email : " + client.getEmail());
                System.out.println("Adresse : " + client.getAdress());
                System.out.println("------------------------");
            }
        }
    }

    private void getClientById(Scanner scanner) {

        System.out.print("Entrez l'ID du client : ");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        Client client = clientService.read(clientId);

        System.out.println("ID : " + client.getId());
        System.out.println("Numéro : " + client.getClientNumber());
        System.out.println("Prénom : " + client.getFirstname());
        System.out.println("Nom : " + client.getLastname());
        System.out.println("Email : " + client.getEmail());
        System.out.println("Adresse : " + client.getAdress());
        System.out.println("------------------------");
    }


    private void updateClient(Scanner scanner) {
        System.out.println("Mettre à jour un client :");

        System.out.print("Entrez l'ID du client à mettre à jour : ");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        // Vérifier si le fournisseur existe
        Client client = clientService.read(clientId);

        if (client != null) {
            System.out.println("Saisissez les nouvelles données du client :");

            System.out.print("Nouveau numéro du client : ");
            int newNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nouveau nom du client : ");
            String newLastname = scanner.nextLine();
            
            System.out.print("Nouveau prénom du client : ");
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

            // Appeler la méthode updateSupplier pour ajouter les modification du fournisseur
            clientService.update(newClient);

            System.out.println("Client mis à jour avec succès.");
        } else {
            System.out.println("Aucun fournisseur trouvé avec l'ID " + clientId);
        }
    }


    private void deleteClient(Scanner scanner) {
        System.out.println("Supprimer un client :");

        System.out.print("Entrez l'ID du client à supprimer : ");
        long clientId = scanner.nextLong();
        scanner.nextLine();

        clientService.delete(clientId);
    }

    

}
