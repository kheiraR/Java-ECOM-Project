package miage.shell;

import java.io.PrintStream;
import java.util.List;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public interface Command {
    public String getName();
    public String getUsage();
    public String getShortDescription();
	
	public void execute(List<String> arguments, PrintStream out, PrintStream err) throws Exception;

}
