package miage.shell.test;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Queue;
import miage.shell.Command;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import miage.shell.command.AbstractCommand;
import miage.shell.command.ContextualCommand;
import miage.shell.command.EchoCommand;
import miage.shell.command.MacroCommand;
import miage.shell.command.option.BaseOption;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class MacroCommandTest {

	private ByteArrayOutputStream out;
	private ByteArrayOutputStream err;

	@Before
	public void createShell() {

		out = new ByteArrayOutputStream();
		err = new ByteArrayOutputStream();
		
	}
	

	@Test
	public void testMacroSimple() throws UnsupportedEncodingException {

		DummyMacroCommand dummyCommand = new DummyMacroCommand();
		List<String> args = new ArrayList<String>();
		args.add("echo");
		args.add("ok nice!");
		dummyCommand.commands.add(args);

		List<String> args2 = new ArrayList<String>();
		args2.add("echo");
		args2.add("hello world 2");
		
		dummyCommand.commands.add(args2);

		Util.createShellBuilderWithInput("dummy", out, err)
			.addCommand(new EchoCommand())
			.addContextualCommand(dummyCommand)
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(out.toString().contains("> dummy \"ok nice!\""));
		assertTrue(out.toString().contains("> dummy \"hello world 2\""));
		assertTrue(out.toString().contains("ok nice!"));
		assertTrue(out.toString().contains("hello world 2"));
	}


	@Test
	public void testMacroCommandNotFound() throws UnsupportedEncodingException {

		DummyMacroCommand dummyCommand = new DummyMacroCommand();
		List<String> args = new ArrayList<String>();
		args.add("unknown");
		args.add("ok nice!");
		dummyCommand.commands.add(args);


		Util.createShellBuilderWithInput("dummy", out, err)
			.addCommand(new EchoCommand())
			.addContextualCommand(dummyCommand)
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());

		assertTrue(err.toString().contains("Command not found : unknown"));
	}


	public class DummyMacroCommand extends MacroCommand {
		public Queue<List<String>> commands = new LinkedList<List<String>>();

		@Override
		public String getName() {
			return "dummy";
		}

		@Override
		public String getUsage() {
			return "dummy";
		}

		@Override
		public String getShortDescription() {
			return "dummy macro command";
		}

		@Override
		protected Queue<List<String>> getCommands() {
			return commands;
		}
		
	}
}
