package miage.shell.command;

import java.io.PrintStream;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class QuitCommand extends AbstractCommand {

	public QuitCommand() {
	}

	@Override
	public String getName() {
		return "quit";
	}

	@Override
	public String getUsage() {
		return "quit";
	}

	@Override
	public String getShortDescription() {
		return "quit this shell";
	}

	@Override
	protected void execute(PrintStream out, PrintStream err) {
		System.exit(0);
	}

	
}
