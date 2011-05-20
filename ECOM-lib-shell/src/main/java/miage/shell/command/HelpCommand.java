package miage.shell.command;

import java.io.PrintStream;
import java.util.Map;
import miage.shell.Command;
import miage.shell.Context;
import miage.shell.context.BaseContext;
import miage.shell.context.VolatileContext;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public final class HelpCommand extends AbstractCommand implements ContextualCommand {

	private Context context;

	public HelpCommand() {
		this.context = VolatileContext.CONTEXT;
	}

	public HelpCommand(Context context) {
		this.context = context;
	}
	

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getUsage() {
		return "help";
	}

	@Override
	public String getShortDescription() {
		return "Help about this shell";
	}

	@Override
	protected void execute(PrintStream out, PrintStream err) {

		Map<String, Command> commands = context.getCommands();
		int nbCommand = commands.size();

		String[] usage = new String[nbCommand];
		String[] desc = new String[nbCommand];

		int maxUsage = 0;
		int i = 0;
		for (Command command : commands.values()) {
			usage[i] = command.getUsage();
			desc[i] = command.getShortDescription();

			// Just in case the command has gone away.
			if ((usage[i] != null) && (desc[i] != null)) {
				maxUsage = Math.max(maxUsage, usage[i].length());
			}

			i++;
		}

		StringBuffer sb = new StringBuffer();
		i = 0;
		for (Command command : commands.values()) {
			// Just in case the command has gone away.
			if ((usage[i] != null) && (desc[i] != null))
			{
				sb.delete(0, sb.length());
				for (int j = 0; j < (maxUsage - usage[i].length()); j++)
				{
					sb.append(' ');
				}
				out.println(usage[i] + sb + " - " + desc[i]);
			}

			i++;
		}

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
