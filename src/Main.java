import Controller.MainController;


//Si on veut déclencher le chargement des données, il faut lancer l'app avec l'argument with_dataset
//Si on ne quitte pas l'app via le menu quitter, la bdd n'est pas supprimee

public class Main {
    public static void main(String[] args) {
        MainController.init( args );
        MainController.select( args );
    }
}