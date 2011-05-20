/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miage.shell.command;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.io.UnsupportedEncodingException;
import miage.shell.test.Util;
import java.io.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maria Rabarison
 */
public class CartCommandTest {

    private ByteArrayOutputStream out;
	private ByteArrayOutputStream err;

	@Before
	public void createShell() {

		out = new ByteArrayOutputStream();
		err = new ByteArrayOutputStream();

	}



       

    /**
     * Test of defaultAction method, of class CartCommand.
     */
    @Test
    public void testALLActions() throws UnsupportedEncodingException {
        CartMacroCommand cartMacroCommand = new CartMacroCommand();
        
        Util.createShellBuilderWithInput("cartMacroCommand", out, err)
			.addCommand(new CartCommand())
                        .addContextualCommand(cartMacroCommand)
			.getShell()
			.run();

		System.out.println(out.toString());
		System.out.println(err.toString());
                System.out.println((new CartCommand()).getUsage());

		assertTrue(out.toString().contains("Liste tous les elements du caddie"));
		assertTrue(out.toString().contains("ajoute dans le caddie le produit 101"));
                assertTrue(out.toString().contains("retire du caddie le produit 102"));
                assertTrue(out.toString().contains("achete le contenu du caddie avec le compte 103"));
    }

    


    public class CartMacroCommand extends MacroCommand {
        
		private Queue<List<String>> commands;

		@Override
		public String getName() {
			return "cartMacroCommand";
		}

		@Override
		public String getUsage() {
			return "cartMacroCommand";
		}

		@Override
		public String getShortDescription() {
			return "cart macro command";
		}

		@Override
		protected Queue<List<String>> getCommands() {
			return commands;
		}

                public CartMacroCommand(){
                    commands = new LinkedList<List<String>>();
                    List<String> args = new ArrayList<String>();
                    List<String> args1 = new ArrayList<String>();
                    List<String> args2 = new ArrayList<String>();
                    List<String> args3 = new ArrayList<String>();
                    List<String> args4 = new ArrayList<String>();
                    List<String> args5 = new ArrayList<String>();

                    args.add("cart");

                    args1.add("cart");
                    args1.add("--add");
                    args1.add("101");

                    args2.add("cart");
                    args2.add("--remove");
                    args2.add("102");

                    args3.add("cart");
                    args3.add("--buy");
                    args3.add("103");


                    commands.add(args);
                    commands.add(args1);
                    commands.add(args2);
                    commands.add(args3);
                }

	}

}