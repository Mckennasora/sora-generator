package com.sora.cli.util;

import java.util.*;

/**
 * @author sora
 * @description
 */
public class CommandUtil {


//    private static final HashMap<String,String> basicMap = new HashMap<>();
    private static final List<String> basicList = new ArrayList<>();
    private static final List<String> easyList = new ArrayList<>();

    static {
        basicList.add("-pn");
        basicList.add("-d");
        basicList.add("-a");
        basicList.add("-g");
    }
    static {
        easyList.add("-tn");
        easyList.add("-tc");
        easyList.add("-u");
        easyList.add("-n");
        easyList.add("-pwd");
        easyList.add("-d");
        easyList.add("-p");
        easyList.add("-a");
    }

    public static String[] autoComplete(String[] args){

        List<String> argList = new ArrayList<>(Arrays.asList(args));

        if (argList.isEmpty()){
            return new String[0];
        }
        if (argList.contains("--help")||argList.contains("-h")){
            return argList.toArray(new String[0]);
        }

        List<String> list = new ArrayList<>();
        if (argList.get(0).equals("basic")){
            list = basicList;

        }else if (argList.get(0).equals("easyCode")){
            list = easyList;
        }
        for (int i = 1; i < args.length; i++) {
            list.remove(argList.get(i));
        }
        argList.addAll(list);

        return argList.toArray(new String[0]);
    }
}
