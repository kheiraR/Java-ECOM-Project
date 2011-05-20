package miage.shell.command;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Map.Entry;
import miage.shell.Option;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public abstract class AbstractActionCommand extends AbstractCommand {

	private PrintStream out;
	private PrintStream err;

	@Override
	protected final void execute(PrintStream out, PrintStream err) {

		this.out = out;
		this.err = err;

		if (this.values.size() > 0) {
			Entry<Option, String> firstOptionEntry = this.values.entrySet().iterator().next();
			String firstOptionValue = firstOptionEntry.getValue();
			Option firstOption = firstOptionEntry.getKey();
			String optionName = firstOption.getName();
			
			String actionName = optionName + "Action";
			dataArguments.addFirst(firstOptionValue);
			
			Method actionMethod = null;
			
			try {
				actionMethod = this.getClass().getMethod(actionName);
			} catch (Exception ex) {
				err.println("No Action named : '"+actionName+"'");
			}

			if (actionMethod != null) {
				try {
					actionMethod.invoke(this);
				} catch (Exception ex) {
					err.println("Cannot execute action named : '" +actionName+ "'");
				}
			}

		} else {
			defaultAction();
		}
	}

	protected final PrintStream getErr() {
		return err;
	}

	protected final PrintStream getOut() {
		return out;
	}

        protected abstract void defaultAction();
}
