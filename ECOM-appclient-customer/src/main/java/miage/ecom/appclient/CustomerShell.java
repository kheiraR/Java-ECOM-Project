package miage.ecom.appclient;

import miage.ecom.appclient.command.AuthCommand;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import javax.ejb.EJB;
import miage.ecom.appclient.command.AccountShellCommand;
import miage.ecom.appclient.command.CartShellCommand;
import miage.ecom.appclient.command.OutputCommand;
import miage.ecom.appclient.command.ProductShellCommand;
import miage.ecom.appclient.command.QuitShellCommand;
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
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class CustomerShell {

	private final Context context;
	private final Shell shell;
	private final Thread shellThread;
	@EJB
	private static EcomCustomerRemote customerRemoteBean;

	public CustomerShell() throws UnsupportedEncodingException {

		context = new BaseContext();

		setupContext(context);

		ShellBuilder builder = new ShellBuilder();
		builder.createNewShell(context, System.in,
							   new PrintStream(System.out, true, "UTF-8"), new PrintStream(
				System.out, true, "UTF-8"));

		addShellCommands(builder);

		shell = builder.getShell();
		shellThread = new Thread(shell);

	}

	public void start() {
		shellThread.start();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		CustomerShell customerShell = new CustomerShell();
		customerShell.start();
	}

	private void addShellCommands(ShellBuilder builder) {
		builder.addCommand(new EchoCommand());
		builder.addCommand(new LanguageCommand(context));
		builder.addCommand(new HelpCommand(context));
		
		builder.addCommand(new QuitCommand());
		builder.addCommand(new AuthCommand(context, customerRemoteBean));
		builder.addCommand(new CartShellCommand(customerRemoteBean));
		builder.addCommand(new ProductShellCommand(customerRemoteBean));
		builder.addCommand(new StoreShellCommand(customerRemoteBean));
		builder.addCommand(new AccountShellCommand(customerRemoteBean));
		builder.addCommand(new OutputCommand(customerRemoteBean));
                builder.addCommand(new TransactionCommand(customerRemoteBean));
	}

	private void setupContext(Context context) {

		String banner = "************************************************" + "\n"
				+ "*                                              *" + "\n"
				+ "*     //   / /  //   ) )  //   ) ) /|    //| | *" + "\n"
				+ "*    //____    //        //   / / //|   // | | *" + "\n"
				+ "*   / ____    //        //   / / // |  //  | | *" + "\n"
				+ "*  //        //        //   / / //  | //   | | *" + "\n"
				+ "* //____/ / ((____/ / ((___/ / //   |//    | | *" + "\n"
				+ "*                                              *" + "\n"
				+ "************************************************" + "\n"
				+ "*               ECOM Customer Shell            *" + "\n"
				+ "************************************************" + "\n";

		context.setVar(Constants.BANNER.toString(), banner);
	}
}
