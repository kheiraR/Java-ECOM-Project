package miage.shell.test;

import java.io.UnsupportedEncodingException;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import miage.shell.command.AbstractActionCommand;
import miage.shell.command.option.BaseOption;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class ActionCommandTest {

	private ByteArrayOutputStream out;
	private ByteArrayOutputStream err;

	@Before
	public void createShell() {

		out = new ByteArrayOutputStream();
		err = new ByteArrayOutputStream();
		
	}

	
	@Test
	public void testActionNotProvided() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("example", out, err)
			.addCommand(new ExampleActionCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(out.toString().contains("No Action provided."));
	}

	@Test
	public void testActionMethodNotExists() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("example --test2", out, err)
			.addCommand(new ExampleActionCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(err.toString().contains("No Action named : 'test2Action'"));
	}

	@Test
	public void testActionExec() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("example --test", out, err)
			.addCommand(new ExampleActionCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(out.toString().contains("ok nice!"));
	}

	@Test
	public void testActionExecArgs() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("example --test3 ok --test", out, err)
			.addCommand(new ExampleActionCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(out.toString().contains("ok"));
	}

	@Test
	public void testActionExecNoArgs() throws UnsupportedEncodingException {

		Util.createShellBuilderWithInput("example --test3", out, err)
			.addCommand(new ExampleActionCommand())
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(err.toString().contains("L'option 'test3' prend un argument obligatoire"));
	}


	public class ExampleActionCommand extends AbstractActionCommand {

		@Override
		public String getName() {
			return "example";
		}

		@Override
		public String getUsage() {
			return "example usage";
		}

		@Override
		public String getShortDescription() {
			return "example description";
		}

		public ExampleActionCommand() {
			super();

			this.addOption(new BaseOption("test", true));
			this.addOption(new BaseOption("test2", true));

			this.addOption(new BaseOption("test3", false));
		}

		public void testAction() {

			this.getOut().println("ok nice!");

		}

		public void test3Action() {
			this.getOut().println(dataArguments.getFirst());
		}

                @Override
                public void defaultAction() {
                    this.getOut().println("No Action provided.");
                }
	}

}
