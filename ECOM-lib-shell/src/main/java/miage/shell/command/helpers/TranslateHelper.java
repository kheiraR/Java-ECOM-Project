package miage.shell.command.helpers;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import miage.shell.Constants;
import miage.shell.command.ContextualCommand;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class TranslateHelper {
	private final String resourceBundleBaseName;
	private final ContextualCommand command;

	public TranslateHelper(ContextualCommand command, String resourceBundleBaseName) {
		this.command = command;
		this.resourceBundleBaseName = resourceBundleBaseName;
	}

	public TranslateHelper(ContextualCommand command) {
		this(command, command.getClass().getPackage().getName() + ".i18n." + command.getClass().getSimpleName());
	}

	
	public String translate(String key, Object ... messageArgs) {
		ResourceBundle bundle = ResourceBundle.getBundle(resourceBundleBaseName, getLocale(), this.getClass().getClassLoader());

		return MessageFormat.format(bundle.getString(key), messageArgs);
	}


	public Locale getLocale() {
		Locale contextLocale = (Locale) command.getContext().getVar(Constants.LANGUAGE.name());

		return (contextLocale != null) ? contextLocale : Locale.getDefault();
	}

	public void setLocale(Locale locale) {
		command.getContext().setVar(Constants.LANGUAGE.name(), locale);
	}
}
