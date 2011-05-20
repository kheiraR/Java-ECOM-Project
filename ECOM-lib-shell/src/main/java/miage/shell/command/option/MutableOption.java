package miage.shell.command.option;

import miage.shell.Option;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class MutableOption extends BaseOption implements Option {

	public MutableOption(Option option) {
		super(option.getShortName(), option.getName(), option.isSwitch(), option.isRequired());
	}

	public MutableOption(String name, boolean isSwitch) {
		super(name, isSwitch);
	}

	public void setIsRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public void setIsSwitch(boolean isSwitch) {
		this.isSwitch = isSwitch;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setShortName(char shortName) {
		this.shortName = shortName;
	}

}
