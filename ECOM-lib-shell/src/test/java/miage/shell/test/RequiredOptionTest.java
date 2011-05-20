package miage.shell.test;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import miage.shell.command.AbstractCommand;
import miage.shell.command.EchoCommand;
import miage.shell.command.option.BaseOption;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class RequiredOptionTest {

	private ByteArrayOutputStream out;
	private ByteArrayOutputStream err;

	@Before
	public void createShell() {

		out = new ByteArrayOutputStream();
		err = new ByteArrayOutputStream();
		
	}
	

	@Test
	public void testRequiredPass() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("required --test --test2 ok", out, err)
			.addCommand(new RequiredOptionCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(out.toString().contains("Okay nice"));
	}

	@Test
	public void testRequiredFail() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("required --test", out, err)
			.addCommand(new RequiredOptionCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());


		assertTrue(err.toString().contains("L'option 'test2' est requise'"));
	}

	@Test
	public void testRequiredFail2() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("required --test2 ok", out, err)
			.addCommand(new RequiredOptionCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());


		assertTrue(err.toString().contains("L'option 'test' est requise'"));
	}


	@Test
	public void testRequiredFail3() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("required --test3", out, err)
			.addCommand(new RequiredOptionCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());


		assertTrue(err.toString().contains("L'option 'test' est requise'"));
	}

	@Test
	public void testRequiredFail4() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("required", out, err)
			.addCommand(new RequiredOptionCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());


		assertTrue(err.toString().contains("L'option 'test' est requise'"));
	}


	public class RequiredOptionCommand extends AbstractCommand {

		@Override
		public String getName() {
			return "required";
		}

		@Override
		public String getUsage() {
			return "required";
		}

		@Override
		public String getShortDescription() {
			return "required command test";
		}

		public RequiredOptionCommand() {
			
			this.addOption(new BaseOption("test", true, true));
			this.addOption(new BaseOption("test2", false, true));
			this.addOption(new BaseOption("test3", true));

		}

		@Override
		protected void execute(PrintStream out, PrintStream err) {
			out.println("Okay nice");
		}



	}

}
