package miage.shell.command;

import miage.shell.command.option.OptionArgumentRequiredException;
import java.io.PrintStream;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import miage.shell.Command;
import miage.shell.Option;
import miage.shell.command.option.BaseOption;
import miage.shell.command.option.OptionException;
import miage.shell.command.option.UnknowOptionException;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public abstract class AbstractCommand implements Command {


	protected Map<String, Option> options = new HashMap<String, Option>();
	protected Map<String, Option> optionsByName = new HashMap<String, Option>();

	protected Map<Option, String> values;

	protected Deque<String> dataArguments;


	@Override
	public final void execute(List<String> args, PrintStream out, PrintStream err) throws Exception {

		dataArguments = new LinkedList<String>();
		values = new LinkedHashMap<Option, String>();

		try {
			
			this.parse(args.toArray(new String[args.size()]));
			
			this.execute(out, err);

		} catch (OptionException ex) {
			err.println(ex.getMessage());
		}

	}

	/**
	 * 
	 * @param options
	 * @param out
	 * @param err
	 */
	protected abstract void execute(PrintStream out, PrintStream err) throws Exception;

	/**
	 * 
	 * @param option
	 */
	protected void addOption(Option option) {
		options.put("-" + option.getShortName(), option);
		options.put("--" + option.getName(), option);
		optionsByName.put(option.getName(), option);
	}


	/**
     * Extract the options and non-option arguments from the given
     * list of command-line arguments. The specified locale is used for
     * parsing options whose values might be locale-specific.
     */
    protected final void parse(String[] argv) throws OptionException {
		int position = 0;
		
        while (position < argv.length) {

            String curArg = argv[position];

			if (curArg.startsWith("-")) {
				
                if (curArg.equals("--")) {
                    position += 1;
                    break;
                }
				
                String valueArg = null;

				if (curArg.startsWith("--")) {
					
                    int equalsPos = curArg.indexOf("=");

                    if (equalsPos != -1) {
                        valueArg = curArg.substring(equalsPos+1);
                        curArg = curArg.substring(0,equalsPos);
                    }
					
                } else if(curArg.length() > 2) {
					
                    for(int i=1; i<curArg.length(); i++) {
                        Option opt = this.options.get("-" + curArg.charAt(i));
						
                        if(opt == null) {
							Option unknowOption = new BaseOption(curArg, false);
							throw new UnknowOptionException(unknowOption);
						}
						
                        if(!opt.isSwitch()) {
							throw new OptionArgumentRequiredException(opt);
						}

						values.put(opt, null);

                    }
					
                    position++;
					
                    continue;
                }

                Option opt = this.options.get(curArg);
                if (opt == null) {
                    Option unknowOption = new BaseOption(curArg, false);
					throw new UnknowOptionException(unknowOption);
                }
				
                String value = null;
                if (!opt.isSwitch()) {
                    if (valueArg == null) {
                        position += 1;
                        if (position < argv.length) {
                            valueArg = argv[position];
                        }
                    }
                    value = valueArg;

					if (value == null) {
						throw new OptionArgumentRequiredException(opt);
					}
                }

                values.put(opt, value);

                position += 1;
            }
            else {
                dataArguments.add(curArg);
                position += 1;
            }
        }
		
        for ( ; position < argv.length; ++position ) {
            dataArguments.add(argv[position]);
        }

		for(Option option : optionsByName.values()) {
			if (option.isRequired() && !values.containsKey(option)) {
				throw new RequiredOptionException(option);
			}
		}
    }

	
	protected boolean hasOption(String optionName) {
		return this.optionsByName.containsKey(optionName) &&
			   values.containsKey(this.optionsByName.get(optionName));
	}

	protected boolean hasOption(Option option) {
		return this.optionsByName.containsValue(option) &&
			   values.containsKey(option);
	}

	protected boolean getBooleanOption(String optionName) {
		return hasOption(optionName) && this.optionsByName.get(optionName).isSwitch();
	}

	
	protected int getIntegerOption(String optionName, int defaultValue) {

		int returnValue = defaultValue;

		if (hasOption(optionName)) {
			try {
				returnValue = Integer.parseInt(values.get(optionsByName.get(optionName)));
			} catch(NumberFormatException e) {}
		}

		return returnValue;
	}

	protected String getStringOption(String optionName, String defaultValue) {

		String returnValue = defaultValue;

		if (hasOption(optionName)) {
			returnValue = values.get(optionsByName.get(optionName));
		}

		return returnValue;
	}
}
