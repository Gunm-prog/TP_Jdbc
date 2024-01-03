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

        System.out.print("Supplier Number: ");
        int supplierNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Supplier Name : ");
        String name = scanner.nextLine();

        // Validation de l'e-mail
        String email;
        do {
            System.out.print("Supplier Email : ");
            email = scanner.nextLine();

            if (!isValidEmail(email)) {
                System.out.println("Email adress invalid. Please retry.");
            }
        } while (!isValidEmail(email));

        System.out.print("Supplier Adress : ");
        String address = scanner.nextLine();

        // Créer l'objet Fournisseur
        Supplier newSupplier = new Supplier();
        newSupplier.setSupplierNb(supplierNumber);
        newSupplier.setName(name);
        newSupplier.setEmail(email);
        newSupplier.setAdress(address);

        // Appeler la méthode addSupplier pour ajouter un fournisseur
        supplierService.addSupplier(newSupplier);

        System.out.println("Supplier successfully added");
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
        long supplierId = scanner.nextLong();
        scanner.nextLine();

        // Vérifier si le fournisseur existe
        Supplier existingSupplier = supplierService.getSupplierById(supplierId);

        if (existingSupplier != null) {
            System.out.println("Enter Supplier's new data :");

            System.out.print("New Supplier Number: ");
            int newSupplierNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("New Supplier Name : ");
            String newName = scanner.nextLine();

            System.out.print("New Supplier Email : ");
            String newEmail = scanner.nextLine();

            System.out.print("New Supplier Adress : ");
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

            System.out.println("Supplier successfully updated");
        } else {
            System.out.println("No supplier found with ID " + supplierId);
        }
    }


    private void deleteSupplier(Scanner scanner) {
        System.out.println("Delete Supplier :");

        System.out.print("Enter Supplier ID : ");
        long supplierId = scanner.nextLong();
        scanner.nextLine();

        // Appeler la méthode getSupplierById pour vérifier si le fournisseur existe
        Supplier existingSupplier = supplierService.getSupplierById(supplierId);

        if (existingSupplier != null) {
            // Appeler la méthode deleteSupplier pour supprimer
            supplierService.deleteSupplier(supplierId);

            System.out.println("Supplier successfully deleted");
        } else {
            System.out.println("No supplier found with ID " + supplierId);
        }
    }

}
