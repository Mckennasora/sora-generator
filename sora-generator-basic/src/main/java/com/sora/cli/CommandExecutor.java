package com.sora.cli;


import com.sora.cli.command.*;
import com.sora.cli.util.CommandUtil;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "sora",version = "1.0.0",mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable {

    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this)
                .addSubcommand(new BasicTemplateCommand())
                .addSubcommand(new EasyCodeCommand())
                .addSubcommand(new ExtraTemplateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        // 不输入子命令时，给出友好提示
        System.out.println("请输入具体命令，或者输入 --help 查看命令提示");
    }

    /**
     * 执行命令
     *
     * @param args
     * @return
     */
    public Integer doExecute(String[] args) {
        return commandLine.execute(args);
    }

    public static void main(String[] args) {
//        String[] params = new String[]{"basic"};
//        String[] params = new String[]{"basic","-pn","i-fuck-gem","-d","-a"};
//        String[] params = new String[]{"basic","-pn","test-basic-code","-d","d:\\test","-a","gem","-g","cn.gem.ilove.concert"};
//        String[] params = new String[]{"basic","-pn","test-for-groupid","-d","d:\\test","-a","yyhadsf","-g","cn.gem.ilove.gem"};
        String[] params = new String[]{"easyCode","-tn","yza_responselist","-tc","责任边界清单","-u","jdbc:mysql://192.168.155.31:54322/smart_security","-n","yunzhian","-pwd","ZwiLoBRVf40nU0En","-d","D:\\test\\test-basic-code\\src\\main\\java\\cn\\gem\\ilove\\concert\\test_basic_code\\module","-p","cn.gem.ilove.concert.test_basic_code.module","-a","gemboyfriend"};
//        String[] params = new String[]{"easyCode","-h"};
//        String[] params = new String[]{"basic","-h"};
        params = CommandUtil.autoComplete(params);
        new CommandLine(new CommandExecutor())
                .addSubcommand(new ListCommand())
                .addSubcommand(new EasyCodeCommand())
                .addSubcommand(new ExtraTemplateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new BasicTemplateCommand())
                .execute(params);
    }
}