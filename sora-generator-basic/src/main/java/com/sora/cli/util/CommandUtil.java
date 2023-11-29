package com.sora.cli.util;

import java.util.*;

/**
 * @author sora
 * @description
 */
public class CommandUtil {


//    private static final HashMap<String,String> basicMap = new HashMap<>();
    private static final List<String> basicList = new ArrayList<>();

    static {
        basicList.add("-pn");
        basicList.add("-d");
        basicList.add("-a");
        basicList.add("-g");
    }

    public static String[] autoComplete(String[] args){

        List<String> argList = new ArrayList<>(Arrays.asList(args));

        if (argList.isEmpty()){
            return new String[0];
        }

        if (argList.get(0).equals("basic")){
            List<String> list = basicList;
            for (int i = 1; i < args.length; i++) {
                list.remove(argList.get(i));
            }
            argList.addAll(list);
        }

        return argList.toArray(new String[0]);
    }
}
