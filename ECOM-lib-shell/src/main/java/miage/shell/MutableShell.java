package miage.shell;

import java.io.InputStream;
import java.io.PrintStream;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class MutableShell extends AbstractShell {

	public MutableShell(Context context, InputStream is, PrintStream out, PrintStream err) {
		super(context, is, out, err);
	}

	public MutableShell(Context context) {
		super(context);
	}

	public void addCommand(Command command) {
		this.commands.put(command.getName(), command);
	}
	
}
