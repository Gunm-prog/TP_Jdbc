package Controller;


import Service.IClientService;

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
            System.out.println("1. Ajouter un Ccient");
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
                   // addSupplier(scanner);
                    break;
                case 2:
                    //getAllSuppliers();
                    break;
                case 3:
                    //getSupplierById(scanner);
                    break;
                case 4:
                    //updateSupplier(scanner);
                    break;
                case 5:
                    //deleteSupplier(scanner);
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    run = false;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
      }

    

}
