/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miage.shell.command;

import miage.shell.Option;
import miage.shell.command.option.OptionException;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
class RequiredOptionException extends OptionException {

	private static final long serialVersionUID = -7948444865452603840L;

	public RequiredOptionException(Option option) {
		super(option, "L'option '" + option.getName() + "' est requise'");
	}

}
