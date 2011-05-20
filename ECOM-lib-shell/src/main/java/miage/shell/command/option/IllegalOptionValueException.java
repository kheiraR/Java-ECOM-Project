/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miage.shell.command.option;

import miage.shell.Option;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class IllegalOptionValueException extends OptionException {

	public IllegalOptionValueException(Option option, String string) {
		super(option, string);
	}

}
