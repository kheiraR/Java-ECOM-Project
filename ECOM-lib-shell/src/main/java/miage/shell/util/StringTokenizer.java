package miage.shell.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public class StringTokenizer {

	private StringTokenizer() {

	}

	/**
     * @param resultBuffer the List temporarily storing the resulting
     * argument array.
     * @param buf the StringBuffer storing the current argument.
     */
    private static void appendToBuffer(
        List<String> resultBuffer,
        StringBuffer buf) {
        if (buf.length() > 0) {
            resultBuffer.add(buf.toString());
            buf.setLength(0);
        }
    }


	/**
	 * Analyse la ligne de commande et la découpe en un tableau de String.
	 * Les arguments qui contiennent des espaces doivent être écrits entre guillemet
	 * Les guillemet doivent être échapés en utilisant le char '\'
	 * Les caractère '\' doivent être échapés en utilisant '\\'
	 * 
	 * @param commandLine La ligne de commande à découper
     * @return une liste d'argument résultant de la ligne de commande.
     */
    public static List<String> tokenize(String commandLine) {
        List<String> resultBuffer = new ArrayList<String>();

        if (commandLine != null) {
            int z = commandLine.length();
            boolean insideQuotes = false;
            StringBuffer buf = new StringBuffer();

            for (int i = 0; i < z; ++i) {
                char c = commandLine.charAt(i);
                if (c == '"') {
                    appendToBuffer(resultBuffer, buf);
                    insideQuotes = !insideQuotes;
                } else if (c == '\\') {
                    if ((z > i + 1)
                        && ((commandLine.charAt(i + 1) == '"')
                            || (commandLine.charAt(i + 1) == '\\'))) {
                        buf.append(commandLine.charAt(i + 1));
                        ++i;
                    } else {
                        buf.append("\\");
                    }
                } else {
                    if (insideQuotes) {
                        buf.append(c);
                    } else {
                        if (Character.isWhitespace(c)) {
                            appendToBuffer(resultBuffer, buf);
                        } else {
                            buf.append(c);
                        }
                    }
                }
            }
            appendToBuffer(resultBuffer, buf);

        }

        return resultBuffer;
    }

}