package miage.shell.test;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import miage.shell.context.BaseContext;
import miage.shell.ShellBuilder;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class Util {


	public static ShellBuilder createShellBuilderWithInput(String input, OutputStream output, OutputStream error) throws UnsupportedEncodingException {

		input = input + "\n";

		ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes("UTF-8"));

		BaseContext c = new BaseContext();
		ShellBuilder sb = new ShellBuilder();
		sb.createNewShell(c, inputStream, new PrintStream(output), new PrintStream(error));
		
		return sb;

	}
}
