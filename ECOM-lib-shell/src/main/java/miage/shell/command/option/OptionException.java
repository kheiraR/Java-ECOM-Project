package miage.shell.command.option;

import miage.shell.Option;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class OptionException extends Exception {
	protected Option option;

	public OptionException(Option option, String string) {
		super(string);

		this.option = option;
	}


}
