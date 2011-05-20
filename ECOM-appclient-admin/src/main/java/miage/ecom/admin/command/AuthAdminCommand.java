/*
 *  The MIT License
 * 
 *  Copyright 2011 Michaël Schwartz <m.schwartz@epokmedia.fr>.
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package miage.ecom.admin.command;

import java.io.PrintStream;
import miage.ecom.appclient.EcomAdminRemote;
import miage.shell.Context;
import miage.shell.command.AbstractCommand;
import miage.shell.command.ContextualCommand;
import miage.shell.command.option.MutableOption;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class AuthAdminCommand extends AbstractCommand implements ContextualCommand {

	private Context context;
	private final EcomAdminRemote ecomAdminRemote;


	public AuthAdminCommand(Context context, EcomAdminRemote ecomAdminRemote) {
		
		
		this.context = context;
		this.ecomAdminRemote = ecomAdminRemote;

		MutableOption loginOption = new MutableOption("user", false);
		loginOption.setIsRequired(true);

		MutableOption passwordOption = new MutableOption("password", false);
		passwordOption.setIsRequired(true);

		addOption(loginOption);
		addOption(passwordOption);

	}

	@Override
	protected void execute(PrintStream out, PrintStream err) {

		String login = this.getStringOption("user", null);
		String password = this.getStringOption("password", null);

		this.ecomAdminRemote.authenticate(login, password);

		String adminName = this.ecomAdminRemote.getAdminName();

		if (adminName != null && adminName.trim().length() > 0) {
			out.println("Vous êtes désormais identifié en tant que " + adminName);
		} else {
			out.println("Authentification FAILED");
		}

	}

	@Override
	public String getName() {
		return "login";
	}

	@Override
	public String getUsage() {
		return "login --user <login> --password <password>";
	}

	@Override
	public String getShortDescription() {
		return "Identification d'un administrateur sur le système ECOM";
	}

	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	

}
