package miage.shell;

import java.io.InputStream;
import java.io.PrintStream;
import miage.shell.command.ContextualCommand;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class ShellBuilder {
	private MutableShell shell;

	public ShellBuilder() {
	}

	public void createNewShell(Context context, InputStream is, PrintStream out, PrintStream err) {
		this.shell = new MutableShell(context, is, out, err);
	}

	public void createNewShell(Context context) {
		this.shell = new MutableShell(context);
	}

	public ShellBuilder addCommand(Command command) {
		shell.addCommand(command);

		return this;
	}

	public ShellBuilder addContextualCommand(ContextualCommand command) {
		command.setContext(shell.getContext());
		shell.addCommand(command);

		return this;
	}

	public Shell getShell() {
		return shell;
	}

}
