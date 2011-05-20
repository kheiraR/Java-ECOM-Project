package miage.shell.context;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import miage.shell.Command;
import miage.shell.Context;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class VolatileContext implements Context {

	public static final VolatileContext CONTEXT = new VolatileContext();

	@Override
	public Object getVar(String key) {
		return null;
	}

	@Override
	public Object unsetVar(String key) {
		return null;
	}

	@Override
	public Set<String> getVarNames() {
		return Collections.emptySet();
	}

	@Override
	public void setVar(String key, Object value) {
		return;
	}

	@Override
	public Map<String, Command> getCommands() {
		return Collections.emptyMap();
	}

	@Override
	public void setCommands(Map<String, Command> commands) {
		return;
	}

}
