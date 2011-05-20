package miage.shell.test;

import java.io.UnsupportedEncodingException;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.util.Locale;
import miage.shell.command.EchoCommand;
import miage.shell.command.HelpCommand;
import miage.shell.command.LanguageCommand;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class LanguageCommandTest {

	private ByteArrayOutputStream out;
	private ByteArrayOutputStream err;

	@Before
	public void createShell() {

		out = new ByteArrayOutputStream();
		err = new ByteArrayOutputStream();
		
	}
	

	@Test
	public void testLanguageGet() throws UnsupportedEncodingException {

		Locale.setDefault(Locale.ENGLISH);

		Util.createShellBuilderWithInput("language", out, err)
			.addCommand(new EchoCommand())
			.addContextualCommand(new HelpCommand())
			.addContextualCommand(new LanguageCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());


		assertTrue(out.toString().contains("English"));
	}

	@Test
	public void testLanguageSet() throws UnsupportedEncodingException {

		Locale.setDefault(Locale.ENGLISH);

		Util.createShellBuilderWithInput("language fr" + "\n" +
										 "language", out, err)
			.addCommand(new EchoCommand())
			.addContextualCommand(new HelpCommand())
			.addContextualCommand(new LanguageCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(out.toString().contains("French"));
	}


	@Test
	public void testLocalize() throws UnsupportedEncodingException {

		Locale.setDefault(Locale.ENGLISH);

		Util.createShellBuilderWithInput("language fr" + "\n" + "help", out, err)
			.addCommand(new EchoCommand())
			.addContextualCommand(new HelpCommand())
			.addContextualCommand(new LanguageCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());


		assertTrue(out.toString().contains("Change la langue de ce shell"));
	}


	@Test
	public void testLocalizeDefault() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("help", out, err)
			.addCommand(new EchoCommand())
			.addContextualCommand(new HelpCommand())
			.addContextualCommand(new LanguageCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());


		assertTrue(out.toString().contains("Change this shell language"));
	}

	@Test
	public void testLocalizeChange() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("language fr\nhelp\nlanguage en\nhelp\n", out, err)
			.addCommand(new EchoCommand())
			.addContextualCommand(new HelpCommand())
			.addContextualCommand(new LanguageCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(out.toString().contains("Change la langue de ce shell"));
		assertTrue(out.toString().contains("Change this shell language"));
	}
}
