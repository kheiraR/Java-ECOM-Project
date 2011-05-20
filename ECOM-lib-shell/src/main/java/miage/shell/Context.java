package miage.shell;

import java.util.Map;
import java.util.Set;

/**
 * @author Fabienne Boyer - july 2000
 * @author Didier Donsez - november 2002 
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public interface Context {
	public Object getVar(String key);
	public Object unsetVar(String key);
	public Set<String> getVarNames();
	public void setVar(String key, Object value);

	public Map<String, Command> getCommands();
	public void setCommands(Map<String, Command> commands);
}
