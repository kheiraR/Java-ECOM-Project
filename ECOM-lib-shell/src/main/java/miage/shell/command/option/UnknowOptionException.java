package miage.shell.command.option;

import miage.shell.Option;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class UnknowOptionException extends OptionException {

	public UnknowOptionException(Option option) {
		super(option, "L'option '" + option.getShortName() + "' est invalide");
	}

}
