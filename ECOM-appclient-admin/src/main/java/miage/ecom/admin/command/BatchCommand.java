/*
 *  The MIT License
 * 
 *  Copyright 2011 MRABARIS.
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package miage.ecom.admin.command;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import miage.shell.Command;
import miage.shell.Context;
import miage.shell.command.MacroCommand;
import miage.shell.command.option.BaseOption;

/**
 *
 * @author MRABARIS
 */
public class BatchCommand extends MacroCommand{

    private Queue<List<String>> commands;

    @Override
    public String getName() {
        return "bacth";
    }

    @Override
    public String getUsage() {
        return "batch [--stopOnError] <filePath>";
    }

    @Override
    public String getShortDescription() {
        return "batch command";
    }

    @Override
    public Queue<List<String>> getCommands() {
        return this.commands;
    }

    public BatchCommand(Context context) {
        this.setContext(context);
        this.commands = new LinkedList<List<String>>();
        this.setShowCommandInput(true);
    }


    public BatchCommand() {
        this.commands = new LinkedList<List<String>>();
        this.setShowCommandInput(true);
    }

    @Override
    public boolean isStopOnError() {
        boolean result = true;
        if (dataArguments != null) {
            String soe = dataArguments.getFirst();
            if (soe != null) {
                if (soe.equals("--stopOnError") || soe.equals("-s")) {
                    result = false;
                }
            }
        }
        return result;
    }

    @Override
    protected void beforeExecute(PrintStream out, PrintStream err) {


        if (dataArguments != null) {
            String soe = dataArguments.getFirst();
            if (soe != null) {
                if (!(soe.equals("--stopOnError") || soe.equals("-s"))) {
                    setStopOnError(false);
                }
            }



            String pathToFile;
            if (!isStopOnError()) {
                pathToFile = dataArguments.getFirst();
            } else {
                pathToFile = dataArguments.getLast();
            }
            if (pathToFile != null && !pathToFile.isEmpty()) {
                try {
                    Scanner scanner = new Scanner(new FileInputStream(pathToFile), "UTF-8");
                    String commandLine;
                    LinkedList<String> ll;
                    String[] cmds;
                    while (scanner.hasNextLine()) {
                        commandLine = scanner.nextLine();
                        if (commandLine != null) {
                            cmds = commandLine.split(" ");
                            if (cmds != null && cmds.length > 0) {
                                ll = new LinkedList<String>();
                                ll.addAll(Arrays.asList(cmds));
                                this.commands.add(ll);
                            }
                        }
                    }
                } catch (Exception e) {
                    out.println("Can't load file");
                }
            } else {
                out.println("Unknown path");
            }








        } else {
            out.println("Unknown batch to execute");
        }
    }

    @Override
    protected void afterExecute(PrintStream out, PrintStream err, boolean hasErrors) {
        if (hasErrors) {
            err.println("There were errors");
        } else {
            out.println("Batch successfully complete");
        }
    }
}
