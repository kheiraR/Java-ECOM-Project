package miage.shell.command.option;

import miage.shell.Option;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class OptionArgumentRequiredException extends OptionException {

	private static final long serialVersionUID = -1792456390614405665L;

	public OptionArgumentRequiredException(Option opt) {
		super(opt, "L'option '"  + opt.getName() + "' prend un argument obligatoire");
	}

}
