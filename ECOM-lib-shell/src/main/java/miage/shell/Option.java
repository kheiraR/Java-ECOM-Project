package miage.shell;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public interface Option {

	public boolean isSwitch();
	public boolean isRequired();
	
	public String getName();
	public char getShortName();
	

}
