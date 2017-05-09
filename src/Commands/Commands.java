package Commands;
import java.util.ArrayList;
import java.util.List;

import Exceptions.ChoiceNotFoundException;
import Exceptions.CommandNotAvailableException;
import Exceptions.RunNotFoundException;
import Exceptions.UserNotFoundException;

/**
 * A class with all the commands usable in an e-mail.
 * @author Maraval Nathan
 */
public class Commands {
	private List<Run> runs;
	private String emailCurrentUser;
	private boolean isInitiator;
	private Run currentRun;
	
	public Commands() {
		super();
		this.runs = new ArrayList<Run>();
		this.emailCurrentUser = "";
		this.isInitiator = false;
		this.currentRun = null;
	}
	
	/**
	 * Method to call at the opening of an email
	 * @param email the email address of the user who sent the email
	 */
	public void initUser(String email) {
		this.emailCurrentUser = email;
	}

	/**
	 * Return the run associate to the id given
	 * @param id identifier of the run
	 * @return the run with the id given
	 * @throws RunNotFoundException
	 */
	private Run getRun(int id) throws RunNotFoundException{
		for (Run run : runs) {
			if(run.getId() == id) return(run); 
		}
		
		throw new RunNotFoundException();
	}
	
	//General commands
	
	/**
	 * Selects a specific run with the given identifier. 
	 * Shall always precede any other command except 'createRun' and 'help'.
	 * @param id the identifier of the run
	 * @throws RunNotFoundException
	 */
	public void run(int id) throws RunNotFoundException {
		currentRun = getRun(id);
	}
	
	//TODO :
	//public void user(int id) {	
	//}
	
	//TODO : Not sure this command is useful here
	public void helpMessage(String email){
		//send help message to client.getEmail()	
	}
	
	//TODO :
	public String status(int idRun) throws RunNotFoundException{
		Run run = getRun(idRun);
		if(this.isInitiator){
			return run.initiatorStatus(this.emailCurrentUser);
		} else {
			return run.clientStatus(this.emailCurrentUser);
		}
	}
	
	
	//Initiator commands
	
	//TODO : complete address in javadoc
	/**
	 * Creates and selects a new run. 
	 * All further commands are configuring the created run. 
	 * The system answers with the initiators version of STATUS.
	 * @param address I don't know
	 * @return the initiators version of STATUS.
	 */
	public String createRun(String address) {
		//TODO : What is address ?
		
		this.isInitiator = true;
		
		//TODO : create unique id
		Run run = new Run(0, emailCurrentUser);
		runs.add(run);
		currentRun = run;
		
		//TODO : The system answers with the initiators version of STATUS.
		return "";
	}
	
	/**
	 * Set the number of tokens that a client holds in order to express his preference in the run.
	 * @param numberTokens the new number of Tokens in the run
	 * @throws CommandNotAvailableException the user need to be an Initiator
	 */
	public void tokenCount(int numberTokens) throws CommandNotAvailableException{
		if(!this.isInitiator) throw new CommandNotAvailableException();
		
		currentRun.tokenCount(numberTokens);
		
	}
	
	/**
	 * Set the description of the run as presented to the clients.
	 * @param text the description of the run
	 * @throws CommandNotAvailableException the user need to be an Initiator
	 */
	public void description(String text) throws CommandNotAvailableException{
		if(!this.isInitiator) throw new CommandNotAvailableException();
		
		currentRun.description(text);
	}
	
	/**
	 * Adds a client to the current run.
	 * @param clientEmail the e-mail address of the new client
	 * @throws CommandNotAvailableException the user need to be an Initiator
	 */
	public void addClient(String clientEmail) throws CommandNotAvailableException{
		if(!this.isInitiator) throw new CommandNotAvailableException();
		
		currentRun.addClient(clientEmail);
	}
	
	/**
	 * Deletes a client from the current run.
	 * @param id the identifier of the client
	 * @throws UserNotFoundException
	 * @throws CommandNotAvailableException the user need to be an Initiator
	 */
	public void delClient(int id) throws UserNotFoundException, CommandNotAvailableException{
		if(!this.isInitiator) throw new CommandNotAvailableException();
		
		currentRun.delClient(id);
	}
	
	/**
	 * Adds the provide text of the choice to the list of choices.
	 * @param text the description of the new choice
	 * @throws ChoiceNotFoundException
	 * @throws CommandNotAvailableException the user need to be an Initiator
	 */
	public void addChoice(String text) throws CommandNotAvailableException{
		if(!this.isInitiator) throw new CommandNotAvailableException();
		
		currentRun.addChoice(text);
	}
	
	/**
	 * Deletes the choice corresponding to the identifier from the list of choices.
	 * @param idChoice the identifier of the choice
	 * @throws ChoiceNotFoundException
	 * @throws CommandNotAvailableException the user need to be an Initiator
	 */
	public void delChoice(int idChoice) throws ChoiceNotFoundException, CommandNotAvailableException{
		if(!this.isInitiator) throw new CommandNotAvailableException();
		
		currentRun.delChoice(idChoice);
	}

}
