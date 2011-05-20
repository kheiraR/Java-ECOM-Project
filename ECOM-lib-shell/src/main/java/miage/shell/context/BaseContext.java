package miage.shell.context;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import miage.shell.Command;
import miage.shell.Context;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class BaseContext implements Context {
	private Map<String, Object> environnementVariables;
	private Map<String, Command> commands;

	public BaseContext(){
		environnementVariables = new TreeMap<String,Object>();
		commands = Collections.emptyMap();
	}

	@Override
	public Object getVar(String key){
		return environnementVariables.get(key);
	}
	
	@Override
	public Set<String> getVarNames(){
		return environnementVariables.keySet();
	}
	
	@Override
	public void setVar(String key, Object value){
		environnementVariables.put(key,value);
	}
	
	@Override
	public Object unsetVar(String key){
		return environnementVariables.remove(key);
	}

	@Override
	public Map<String, Command> getCommands() {
		return Collections.unmodifiableMap(this.commands);
	}

	@Override
	public void setCommands(Map<String, Command> commands) {
		this.commands = commands;
	}

	
}
