package miage.shell.command;

import java.io.PrintStream;
import java.util.Locale;
import miage.shell.Context;
import miage.shell.command.helpers.TranslateHelper;
import miage.shell.context.VolatileContext;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class LanguageCommand extends AbstractCommand implements ContextualCommand, LocalizedCommand {
	private final TranslateHelper translateHelper;
	private Context context;

	
	public LanguageCommand() {
		this(VolatileContext.CONTEXT);
	}

	public LanguageCommand(Context context) {
		this.context = context;
		this.translateHelper = new TranslateHelper(this);
	}

	@Override
	public String getName() {
		return "language";
	}

	@Override
	public String getUsage() {
		return translateHelper.translate("usage");
	}

	@Override
	public String getShortDescription() {
		return translateHelper.translate("desc");
	}


	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	protected void execute(PrintStream out, PrintStream err) {
		if (dataArguments.isEmpty()) {
			out.println(translateHelper.getLocale().getDisplayLanguage());
		} else {
			Locale newLocale = new Locale(dataArguments.getFirst());
			this.translateHelper.setLocale(newLocale);
		}
	}
}
