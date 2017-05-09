package Test;
import Commands.Commands;

public class TestCommands {
	//Questions pour Marcus :
	//Pas de méthodes pour ajouter des initiateurs ?
	//Pourquoi "CREATERUN ADDRESS" ? l'addresse de l'initiateur devrait être directement le mec qui a lancé la commande ?
	//Status doit être forcément lancé après "USER IDENTIFIER", et sinon retourne le status pour l'utilisateur ?
	//Quand un e-mail contient une erreur (exemple suppression d'un client non existant), faut-il bien exécuter les commandes sans erreurs précédents et suivant cette erreur ?
	//Ou au contraire ne rien faire de l'e-mail ?

	public static void main(String[] args) {
		Commands command= new Commands();
		
		//The initiator create and configure a run
		try {
			command.initUser("john.doe@etu.univ-nantes.fr");
			
			command.createRun("useless ?");

			command.tokenCount(10);
			command.description("This is a description");
			command.addClient("mailClient1");
			command.addClient("mailClient2");
			command.addClient("mailClient3");
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
