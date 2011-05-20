package miage.shell.test;

import java.io.UnsupportedEncodingException;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import miage.shell.Shell;
import miage.shell.command.EchoCommand;
import miage.shell.command.HelpCommand;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class HelpCommandTest {

	private ByteArrayOutputStream out;
	private ByteArrayOutputStream err;

	@Before
	public void createShell() {

		out = new ByteArrayOutputStream();
		err = new ByteArrayOutputStream();
		
	}
	

	@Test
	public void testHelp() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("help", out, err)
			.addCommand(new EchoCommand())
			.addContextualCommand(new HelpCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(out.toString().contains("help           - Help about this shell"));
		assertTrue(out.toString().contains("echo <message> - Echo a string"));
	}


}
