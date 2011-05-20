package miage.shell.test;

import java.io.UnsupportedEncodingException;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import miage.shell.command.EchoCommand;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class EchoCommandTest {

	private ByteArrayOutputStream out;
	private ByteArrayOutputStream err;

	@Before
	public void createShell() {

		out = new ByteArrayOutputStream();
		err = new ByteArrayOutputStream();
		
	}
	

	@Test
	public void testNothingToEcho() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("echo", out, err)
			.addCommand(new EchoCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(err.toString().startsWith("Nothing to echo"));
	}

	@Test
	public void testEcho() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("echo hello", out, err)
			.addCommand(new EchoCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(out.toString().contains("hello"));
	}

}
