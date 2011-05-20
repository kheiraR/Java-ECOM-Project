package miage.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import miage.shell.util.StringTokenizer;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public abstract class AbstractShell implements Shell {
	protected Map<String,Command> commands;
	protected Context context;
	
	private boolean stop;
	private InputStream in;
	private PrintStream out;
	private PrintStream err;

	public AbstractShell(Context context) {
		this(context, System.in, System.out, System.err);
	}

	public AbstractShell(Context context, InputStream is, PrintStream out, PrintStream err) {
		this.commands = new HashMap<String, Command>();

		this.context = context;
		this.in = is;
		this.out = out;
		this.err = err;
	
		this.context.setCommands(commands);
	}

	@Override
	public Set<Constants> getConstants() {
		return EnumSet.allOf(Constants.class);
	}

	@Override
	public Context getContext() {
		return this.context;
	}

	@Override
	public void run() {
		String banner;
		String line = null;
		BufferedReader br = null;

		if (!stop) {
			banner = (String) context.getVar(Constants.BANNER.name());
			if (banner != null) {
				out.println(banner);
			}
			br = new BufferedReader(new InputStreamReader(this.in));
		}

		while (!stop) {
			String prompt;
			prompt = (String) context.getVar(Constants.PROMPT.name());
			if (prompt == null) {
				prompt = "> ";
			}
			out.print(prompt);

			try {
				line = br.readLine();
				if (line == null) {
					stop = true;
					continue;
				}
			} catch (IOException ex) {
				err.println("Could not read input, please try again.");
				continue;
			}

			line = line.trim();

			if (line.length() == 0) {
				continue;
			}

			try {
				executeCommand(line);
			} catch (Exception ex) {
				ex.printStackTrace(err);
			}
		}
	}

	protected void executeCommand(String commandLine) throws Exception {
		commandLine = commandLine.trim();
		List<String> cmdTokens = StringTokenizer.tokenize(commandLine);
		
		Command command = null;
		
		if (cmdTokens.size() > 0) {
			String commandName = cmdTokens.get(0);
			command = commands.get(commandName);
		} else {
			err.println("Command not found.");
		}
		
		if (command != null) {
			command.execute(cmdTokens.subList(1, cmdTokens.size()), out, err);
		} else {
			err.println("Command not found.");
		}
	}

}
