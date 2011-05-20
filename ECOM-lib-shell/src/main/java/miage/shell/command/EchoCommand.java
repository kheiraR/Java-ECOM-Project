package miage.shell.command;

import java.io.PrintStream;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class EchoCommand extends AbstractCommand {

	public EchoCommand() {
	}

	@Override
	public String getName() {
		return "echo";
	}

	@Override
	public String getUsage() {
		return "echo <message>";
	}

	@Override
	public String getShortDescription() {
		return "Echo a string";
	}

	@Override
	protected void execute(PrintStream out, PrintStream err) {
		if (dataArguments.isEmpty()) {
			err.println("Nothing to echo");
		} else {
			out.println(dataArguments.getFirst());
		}
	}

	
}
