package miage.ecom.appclient.helpers;

import miage.ecom.entity.Account;
import miage.shell.command.ContextualCommand;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class AuthHelper {
	private final ContextualCommand command;

	public static final String ENV_AUTH = "AUTH";

	public AuthHelper(ContextualCommand command) {
		this.command = command;
		
	}

	/**
	 * Retourne true si l'utilisateur est actuellement identifié
	 *
	 * @return vrai si l'utilisateur est identifié
	 */
	public boolean hasIdentity() {
		return command.getContext().getVar(ENV_AUTH) != null;
	}


	/**
	 *
	 * @return l'entite du compte de l'utilisateur identifié ou null si l'utilisateur
	 * n'est pas identifié
	 */
	public Account getIdentity() {
		return (Account) command.getContext().getVar(ENV_AUTH);
	}

	
	
}
