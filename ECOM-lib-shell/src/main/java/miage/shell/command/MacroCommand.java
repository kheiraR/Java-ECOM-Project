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

package miage.shell.command;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import miage.shell.Command;
import miage.shell.Context;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public abstract class MacroCommand extends AbstractCommand implements ContextualCommand {

	protected boolean stopOnError = true;
	protected boolean showCommandInput = true;

	private Context shellContext;

	public boolean isStopOnError() {
		return stopOnError;
	}

	public void setStopOnError(boolean stopOnError) {
		this.stopOnError = stopOnError;
	}

	public boolean isShowCommandInput() {
		return showCommandInput;
	}

	public void setShowCommandInput(boolean showCommandInput) {
		this.showCommandInput = showCommandInput;
	}

	@Override
	public Context getContext() {
		return shellContext;
	}

	@Override
	public void setContext(Context context) {
		shellContext = context;
	}


	protected abstract Queue<List<String>> getCommands();

	
	protected void beforeExecute(PrintStream out, PrintStream err) {

	}

	protected void afterExecute(PrintStream out, PrintStream err, boolean hasErrors) {
		
	}

	@Override
	protected final void execute(PrintStream out, PrintStream err) throws Exception {

                beforeExecute(out, err);

		Map<String, Command> availableCommands = getContext().getCommands();
		Queue< List<String>> commands = getCommands();

		

		Command command;
		List<String> commandLine;
		List<String> args;
		
		boolean hasErrors = false;

		while(!commands.isEmpty()) {
			commandLine = commands.poll();

			if (commandLine.isEmpty() ||
				!availableCommands.containsKey(commandLine.get(0))) {

				err.println("Command not found : " + ((!commandLine.isEmpty()) ? commandLine.get(0) : ""));
				hasErrors = true;
				if (isStopOnError()) {
					break;
				}

			} else {

				command = availableCommands.get(commandLine.get(0));
				args = commandLine.subList(1, commandLine.size());

				try {
					if (showCommandInput) {
						out.println(getCommandLine(command, args));
					}
					command.execute(args, out, err);
				} catch(Exception ex) {
					if (this.isStopOnError()) {
                                                out.println(ex.getMessage()); //TODO enlever
						throw ex;

					} else {
						err.println("An error has occured. Continue..");
						ex.printStackTrace(err);
						hasErrors = true;
					}
				}

			}

		}

		afterExecute(out, err, hasErrors);

	}


	protected String getCommandLine(Command command, List<String> args) {

		StringBuilder commandline = new StringBuilder();
		commandline.append("> ");
		commandline.append(getName());

		for(String arg : args) {
			commandline.append(" ");
			commandline.append("\"");
			commandline.append(arg);
			commandline.append("\"");
		}

		return commandline.toString();
	}


}
