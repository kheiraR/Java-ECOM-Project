package miage.shell.command.option;

import miage.shell.Option;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class BaseOption implements Option {


	protected boolean isRequired;
	protected boolean isSwitch;
	protected String name;
	protected char shortName;

	public BaseOption(char shortName, String name, boolean isSwitch, boolean isRequired) {
		this.isSwitch = isSwitch;
		this.name = name;
		this.shortName = shortName;
		this.isRequired = isRequired;
	}

	public BaseOption(String name, boolean isSwitch, boolean isRequired) {
		this(name.charAt(0), name, isSwitch, isRequired);
	}

	public BaseOption(String name, boolean isSwitch) {
		this(name.charAt(0), name, isSwitch, false);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public char getShortName() {
		return this.shortName;
	}

	@Override
	public boolean isRequired() {
		return this.isRequired;
	}

	@Override
	public boolean isSwitch() {
		return this.isSwitch;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final BaseOption other = (BaseOption) obj;
		if (this.isRequired != other.isRequired) {
			return false;
		}
		if (this.isSwitch != other.isSwitch) {
			return false;
		}
		if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
			return false;
		}
		if (this.shortName != other.shortName) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
		return hash;
	}

	
}
