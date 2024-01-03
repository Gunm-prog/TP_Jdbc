package Controller;

import Entity.Supplier;
import Service.contract.ISupplierService;

import java.util.List;
import java.util.Scanner;

public class SupplierController {

    private final ISupplierService supplierService;

    public SupplierController(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public void userChose(){
        Scanner scanner = new Scanner(System.in);

        boolean isContinue = true;

        while (isContinue) {

            System.out.println("1. Ajouter un fournisseur");
            System.out.println("2. Afficher la liste des fournisseurs");
            System.out.println("3. Afficher un fournisseur");
            System.out.println("4. Mettre à jour un fournisseur");
            System.out.println("5. Supprimer un fournisseur");
            System.out.println("0. Quitter");

            System.out.print("Choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            //recupération du choix de lutilisateur avec un switch case
            switch (choice) {
                case 1:
                    addSupplier(scanner);
                    break;
                case 2:
                    getAllSuppliers();
                    break;
                case 3:
                    getSupplierById(scanner);
                    break;
                case 4:
                    updateSupplier(scanner);
                    break;
                case 5:
                    deleteSupplier(scanner);
                    break;
                case 0:
                    System.out.println("Exit Menu.");
                    isContinue = false;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
      }

    private boolean isValidEmail(String email) {
        // Expression régulière pour valider l'e-mail
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private void addSupplier(Scanner scanner) {
        System.out.println("Ajouter un fournisseur :");
        System.out.println("------------------------");

        System.out.print("Numéro du fournisseur : ");
        int supplierNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nom du fournisseur : ");
        String name = scanner.nextLine();

        // Validation de l'e-mail
        String email;
        do {
            System.out.print("Email du fournisseur : ");
            email = scanner.nextLine();

            if (!isValidEmail(email)) {
                System.out.println("L'adresse e-mail n'est pas valide. Veuillez réessayer.");
            }
        } while (!isValidEmail(email));


        System.out.print("Adresse du fournisseur : ");
        String address = scanner.nextLine();

        // Créer l'objet Fournisseur
        Supplier newSupplier = new Supplier();
        newSupplier.setSupplierNb(supplierNumber);
        newSupplier.setName(name);
        newSupplier.setEmail(email);
        newSupplier.setAdress(address);

        // Appeler la méthode addSupplier pour ajouter un fournisseur
        supplierService.addSupplier(newSupplier);

        System.out.println("Fournisseur ajouté avec succès.");
    }


    private void getAllSuppliers() {
        System.out.println("Liste des fournisseurs :");

        // Appeler la méthode getAllSuppliers
        List<Supplier> suppliers = supplierService.getAllSuppliers();

        //si aucun fournisseur trouvé affichage (Aucun fournisseur trouvé)
        if (suppliers.isEmpty()) {
            System.out.println("Aucun fournisseur trouvé.");
        } else {
            for (Supplier supplier : suppliers) {
                System.out.println("ID : " + supplier.getId());
                System.out.println("Numéro : " + supplier.getSupplierNb());
                System.out.println("Nom : " + supplier.getName());
                System.out.println("Email : " + supplier.getEmail());
                System.out.println("Adresse : " + supplier.getAdress());
                System.out.println("------------------------");
            }
        }
    }


    private void getSupplierById(Scanner scanner) {

        System.out.print("Entrez l'ID du fournisseur : ");
        //recupération de lid du fournisseur
        long supplierId = scanner.nextLong();
        scanner.nextLine();

        // Appeler la méthode getSupplierById pour recupérer le fournisseur par son id
        Supplier supplier = supplierService.getSupplierById(supplierId);

       //vérification de l'id
        if (supplier != null) {
            System.out.println("Détails du fournisseur :");
            System.out.println("ID : " + supplier.getId());
            System.out.println("Numéro : " + supplier.getSupplierNb());
            System.out.println("Nom : " + supplier.getName());
            System.out.println("Email : " + supplier.getEmail());
            System.out.println("Adresse : " + supplier.getAdress());
        } else {
            System.out.println("Aucun fournisseur trouvé avec l'ID " + supplierId);
        }
    }


    private void updateSupplier(Scanner scanner) {
        System.out.println("Mettre à jour un fournisseur :");

        System.out.print("Entrez l'ID du fournisseur à mettre à jour : ");
        //recupération de lid du fournisseur
        long supplierId = scanner.nextLong();
        scanner.nextLine();

        // Vérifier si le fournisseur existe
        Supplier existingSupplier = supplierService.getSupplierById(supplierId);

        if (existingSupplier != null) {
            System.out.println("Saisissez les nouvelles données du fournisseur :");

            System.out.print("Nouveau numéro du fournisseur : ");
            int newSupplierNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nouveau nom du fournisseur : ");
            String newName = scanner.nextLine();

            System.out.print("Nouvel email du fournisseur : ");
            String newEmail = scanner.nextLine();

            System.out.print("Nouvelle adresse du fournisseur : ");
            String newAddress = scanner.nextLine();

            // Créer un objet Supplier avec les nouvelles données entré par l'utilisateur
            Supplier updatedSupplier = new Supplier();
            updatedSupplier.setId(supplierId);
            updatedSupplier.setSupplierNb(newSupplierNumber);
            updatedSupplier.setName(newName);
            updatedSupplier.setEmail(newEmail);
            updatedSupplier.setAdress(newAddress);

            // Appeler la méthode updateSupplier pour ajouter les modification du fournisseur
            supplierService.updateSupplier(updatedSupplier);

            System.out.println("Fournisseur mis à jour avec succès.");
        } else {
            System.out.println("Aucun fournisseur trouvé avec l'ID " + supplierId);
        }
    }


    private void deleteSupplier(Scanner scanner) {
        System.out.println("Supprimer un fournisseur :");

        System.out.print("Entrez l'ID du fournisseur à supprimer : ");
        long supplierId = scanner.nextLong();
        scanner.nextLine();

        // Appeler la méthode getSupplierById pour vérifier si le fournisseur existe
        Supplier existingSupplier = supplierService.getSupplierById(supplierId);

        if (existingSupplier != null) {
            // Appeler la méthode deleteSupplier pour supprimer
            supplierService.deleteSupplier(supplierId);

            System.out.println("Fournisseur supprimé avec succès.");
        } else {
            System.out.println("Aucun fournisseur trouvé avec l'ID " + supplierId);
        }
    }

}
