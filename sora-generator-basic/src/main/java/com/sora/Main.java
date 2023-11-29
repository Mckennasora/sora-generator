package com.sora;

import com.sora.cli.CommandExecutor;
import com.sora.cli.util.CommandUtil;

public class Main {

    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        String[] myArgs = CommandUtil.autoComplete(args);
        commandExecutor.doExecute(myArgs);
    }
}