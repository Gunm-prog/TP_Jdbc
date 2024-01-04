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
            System.out.println();
            System.out.println("------------------------");
            System.out.println("1. Add Supplier");
            System.out.println("2. Display All Suppliers");
            System.out.println("3. Display specific Supplier");
            System.out.println("4. Update Supplier");
            System.out.println("5. Delete Supplier");
            System.out.println("0. Exit");
            System.out.println("------------------------");
            System.out.println();

            System.out.print("Selection : ");
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
                    System.out.println("Invalid selection: try again!");
            }
        }
      }

    private boolean isValidEmail(String email) {
        // Expression régulière pour valider l'e-mail
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private void addSupplier(Scanner scanner) {
        System.out.println("Add Supplier :");
        System.out.println("------------------------");

        // Demander à l'utilisateur de remplir les données jusqu'à ce qu'elles soient toutes valides
        while (true) {
            // Saisie du numéro du fournisseur
            System.out.print("Supplier Number: ");
            String supplierNumberInput = scanner.nextLine();

            if (supplierNumberInput.isEmpty()) {
                System.out.println("Please enter Supplier Number.");
                continue;
            }

            // Vérification si le numéro du fournisseur existe déjà
            int supplierNumber;
            try {
                supplierNumber = Integer.parseInt(supplierNumberInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Supplier Number. Please enter a valid number.");
                continue;
            }

            if (supplierService.isSupplierNumberExists(supplierNumber)) {
                System.out.println("Supplier Number already exists. Please choose a different number.");
                return; // Sortez de la méthode si le numéro existe déjà
            }

            // Saisie du nom du fournisseur
            String name;
            do {
                System.out.print("Supplier Name : ");
                name = scanner.nextLine();

                if (name.isEmpty()) {
                    System.out.println("Please enter Supplier Name.");
                } else if (!name.matches("[a-zA-z]+")) {
                    System.out.println("Retry, with only letters please.");
                    name="";
                }
            } while (name.isEmpty());


            // Saisie de l'e-mail du fournisseur
            String email;
            do {
                System.out.print("Supplier Email : ");
                email = scanner.nextLine();

                if (email.isEmpty()) {
                    System.out.println("Please enter Supplier Email.");
                }

                if (!isValidEmail(email)) {
                    System.out.println("Email address invalid. Please retry.");
                }
            } while (!isValidEmail(email));

            // Saisie de l'adresse du fournisseur

            String address;
            do {
                System.out.print("Supplier Address : ");
                 address = scanner.nextLine();

                if (address.isEmpty()){
            }
                System.out.println("Please enter Supplier Address. ");
            }while (address.isEmpty());

            // Créer l'objet Fournisseur
            Supplier newSupplier = new Supplier();
            newSupplier.setSupplierNb(supplierNumber);
            newSupplier.setName(name);
            newSupplier.setEmail(email);
            newSupplier.setAdress(address);

            // Appeler la méthode addSupplier pour ajouter un fournisseur
            supplierService.addSupplier(newSupplier);

            System.out.println("Supplier successfully added");
            break; // Sortir de la boucle une fois que toutes les données sont valides
        }
    }



    private void getAllSuppliers() {
        System.out.println("Suppliers List :");

        // Appeler la méthode getAllSuppliers
        List<Supplier> suppliers = supplierService.getAllSuppliers();

        //si aucun fournisseur trouvé affichage (Aucun fournisseur trouvé)
        if (suppliers.isEmpty()) {
            System.out.println("No supplier found");
        } else {
            for (Supplier supplier : suppliers) {
                System.out.println("ID : " + supplier.getId());
                System.out.println("Number : " + supplier.getSupplierNb());
                System.out.println("Name : " + supplier.getName());
                System.out.println("Email : " + supplier.getEmail());
                System.out.println("Adress : " + supplier.getAdress());
                System.out.println("------------------------");
            }
        }
    }


    private void getSupplierById(Scanner scanner) {

        System.out.print("Enter Supplier ID : ");
        //recupération de lid du fournisseur
        long supplierId = scanner.nextLong();
        scanner.nextLine();

        // Appeler la méthode getSupplierById pour recupérer le fournisseur par son id
        Supplier supplier = supplierService.getSupplierById(supplierId);

       //vérification de l'id
        if (supplier != null) {
            System.out.println("Supplier details :");
            System.out.println("ID : " + supplier.getId());
            System.out.println("Number : " + supplier.getSupplierNb());
            System.out.println("Name : " + supplier.getName());
            System.out.println("Email : " + supplier.getEmail());
            System.out.println("Adress : " + supplier.getAdress());
        } else {
            System.out.println("No supplier found with ID " + supplierId);
        }
    }


    private void updateSupplier(Scanner scanner) {
        System.out.println("Update Supplier :");

        System.out.print("Enter supplier ID : ");
        //recupération de lid du fournisseur
        long supplierId = -1;

        // Demander à l'utilisateur de remplir l'ID jusqu'à ce qu'il soit valide
        while (true) {
            String supplierIdInput = scanner.nextLine();

            if (supplierIdInput.isEmpty()) {
                System.out.println("Please enter Supplier ID.");
                continue;
            }

            try {
                supplierId = Long.parseLong(supplierIdInput);
                break; // Sortir de la boucle si l'ID est valide
            } catch (NumberFormatException e) {
                System.out.println("Invalid Supplier ID. Please enter a valid number.");
            }
        }

        // Vérifier si le fournisseur existe
        Supplier existingSupplier = supplierService.getSupplierById(supplierId);

        if (existingSupplier != null) {
            System.out.println("Enter Supplier's new data :");

            // Récupérer le numéro du fournisseur existant
            int existingSupplierNumber = existingSupplier.getSupplierNb();

            // Saisir le nouveau numéro du fournisseur
            int newSupplierNumber = -1; // Initialiser avec une valeur par défaut
            do {
                System.out.print("New Supplier Number: ");
                String newSupplierNumberInput = scanner.nextLine();

                if (newSupplierNumberInput.isEmpty()) {
                    System.out.println("Please enter New Supplier Number.");
                    continue;
                }

                newSupplierNumber = Integer.parseInt(newSupplierNumberInput);

                // Vérifier si le nouveau numéro est déjà utilisé
                if (supplierService.isSupplierNumberExists(newSupplierNumber) && newSupplierNumber != existingSupplierNumber) {
                    System.out.println("Supplier Number already exists. Please choose a different number.");
                }
            } while (supplierService.isSupplierNumberExists(newSupplierNumber) && newSupplierNumber != existingSupplierNumber);




            String newName;
            do {
                System.out.print("New Supplier Name : ");
                newName = scanner.nextLine();

                if (newName.isEmpty()){
                    System.out.println("Please enter new name");
                }

            }while (newName.isEmpty());

            // Validation de l'e-mail
            String newEmail;
            do {
                System.out.print("New Supplier Email : ");
                newEmail = scanner.nextLine();

                if (newEmail.isEmpty()) {
                    System.out.println("Please enter New Supplier Email.");
                    continue;
                }

                if (!isValidEmail(newEmail)) {
                    System.out.println("Email address invalid. Please retry.");
                }
            } while (!isValidEmail(newEmail));


            String newAddress;
            do {
                System.out.print("New Supplier Address : ");
                newAddress = scanner.nextLine();

                if (newAddress.isEmpty()){
                }
                System.out.println("Please enter New Supplier Address. ");
            }while (newAddress.isEmpty());


            // Créer un objet Supplier avec les nouvelles données entrées par l'utilisateur
            Supplier updatedSupplier = new Supplier();
            updatedSupplier.setId(supplierId);
            updatedSupplier.setSupplierNb(newSupplierNumber);
            updatedSupplier.setName(newName);
            updatedSupplier.setEmail(newEmail);
            updatedSupplier.setAdress(newAddress);

            // Appeler la méthode updateSupplier pour ajouter les modifications du fournisseur
            supplierService.updateSupplier(updatedSupplier);

            System.out.println("Supplier successfully updated");
        } else {
            System.out.println("No supplier found with ID " + supplierId);
        }
    }




    private void deleteSupplier(Scanner scanner) {
        System.out.println("Delete Supplier :");

        System.out.print("Enter Supplier ID : ");
        long supplierId = -1;

        // Demander à l'utilisateur de remplir l'ID jusqu'à ce qu'il soit valide
        while (true) {
            String supplierIdInput = scanner.nextLine();

            if (supplierIdInput.isEmpty()) {
                System.out.println("Please enter Supplier ID.");
                continue;
            }

            try {
                supplierId = Long.parseLong(supplierIdInput);
                break; // Sortir de la boucle si l'ID est valide
            } catch (NumberFormatException e) {
                System.out.println("Invalid Supplier ID. Please enter a valid number.");
            }
        }

        // Appeler la méthode getSupplierById pour vérifier si le fournisseur existe
        Supplier existingSupplier = supplierService.getSupplierById(supplierId);

        if (existingSupplier != null) {
            // Afficher les détails du fournisseur
            System.out.println("Supplier details:");
            System.out.println("ID: " + existingSupplier.getId());
            System.out.println("Number: " + existingSupplier.getSupplierNb());
            System.out.println("Name: " + existingSupplier.getName());
            System.out.println("Email: " + existingSupplier.getEmail());
            System.out.println("Address: " + existingSupplier.getAdress());

            // Demander confirmation à l'utilisateur
            System.out.print("Are you sure you want to delete this supplier? (yes/no): ");
            String confirmation = scanner.nextLine().toLowerCase();

            if (confirmation.equals("yes")) {
                // Appeler la méthode deleteSupplier pour supprimer
                supplierService.deleteSupplier(supplierId);
                System.out.println("Supplier successfully deleted");
            } else {
                System.out.println("Deletion canceled. Supplier not deleted.");
            }
        } else {
            System.out.println("No supplier found with ID " + supplierId);
        }
    }

}
