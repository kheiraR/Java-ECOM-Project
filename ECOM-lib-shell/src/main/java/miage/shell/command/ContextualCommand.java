package miage.shell.command;

import miage.shell.Command;
import miage.shell.Context;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public interface ContextualCommand extends Command {

	public Context getContext();
	public void setContext(Context context);
}
