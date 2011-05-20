/*
 *  The MIT License
 * 
 *  Copyright 2011 MRABARIS.
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

package miage.ecom.admin;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import javax.ejb.EJB;
import miage.ecom.admin.command.AccountAdminShellCommand;
import miage.ecom.appclient.EcomAdminRemote;
import miage.ecom.admin.command.AuthAdminCommand;
import miage.ecom.admin.command.BatchCommand;
import miage.ecom.admin.command.ProductAdminShellCommand;
import miage.ecom.appclient.command.CartShellCommand;
import miage.ecom.appclient.command.OutputCommand;
import miage.ecom.appclient.command.StoreShellCommand;
import miage.ecom.appclient.command.TransactionCommand;
import miage.shell.Constants;
import miage.shell.Context;
import miage.shell.Shell;
import miage.shell.ShellBuilder;
import miage.shell.command.EchoCommand;
import miage.shell.command.HelpCommand;
import miage.shell.command.LanguageCommand;
import miage.shell.command.QuitCommand;
import miage.shell.context.BaseContext;

/**
 *
 * @author MRABARIS
 */
public class AdminShell {
        private final Context context;
	private final Shell shell;
	private final Thread shellThread;

        @EJB
	private static EcomAdminRemote adminRemoteBean;

        public AdminShell() throws UnsupportedEncodingException {

		context = new BaseContext();

		setupContext(context);

		ShellBuilder builder = new ShellBuilder();
		builder.createNewShell(context, System.in, new PrintStream(System.out, true, "UTF-8"), new PrintStream(System.out, true, "UTF-8"));

		addShellCommands(builder);
        
		shell = builder.getShell();
		shellThread = new Thread(shell);

	}


        public void start() {
		shellThread.start();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		AdminShell adminShell = new AdminShell();
		adminShell.start();
	}

        private void addShellCommands(ShellBuilder builder) {
            builder.addCommand(new EchoCommand());
            builder.addCommand(new LanguageCommand(context));
            builder.addCommand(new QuitCommand());
            builder.addCommand(new AuthAdminCommand(context, adminRemoteBean));
            builder.addCommand(new HelpCommand(context));
            builder.addCommand(new TransactionCommand(adminRemoteBean));
            builder.addCommand(new OutputCommand(adminRemoteBean));
            builder.addCommand(new ProductAdminShellCommand(adminRemoteBean));
            builder.addCommand(new CartShellCommand(adminRemoteBean));
            builder.addCommand(new StoreShellCommand(adminRemoteBean));
            builder.addCommand(new AccountAdminShellCommand(adminRemoteBean));
            builder.addContextualCommand(new BatchCommand());
	}

        private void setupContext(Context context) {

		String banner = "************************************************" + "\n" +
						"*                                              *" + "\n" +
						"*     //   / /  //   ) )  //   ) ) /|    //| | *" + "\n" +
					    "*    //____    //        //   / / //|   // | | *" + "\n" +
					    "*   / ____    //        //   / / // |  //  | | *" + "\n" +
					    "*  //        //        //   / / //  | //   | | *" + "\n" +
					    "* //____/ / ((____/ / ((___/ / //   |//    | | *" + "\n" +
						"*                                              *" + "\n" +
						"************************************************" + "\n" +
						"*               ECOM Admin Shell            *" + "\n" +
						"************************************************" + "\n";

		context.setVar(Constants.BANNER.toString(), banner);
	}
}
