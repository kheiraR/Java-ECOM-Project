package miage.shell;

import java.util.Set;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public interface Shell extends Runnable {

	public Set<Constants> getConstants();
	public Context getContext();
}
