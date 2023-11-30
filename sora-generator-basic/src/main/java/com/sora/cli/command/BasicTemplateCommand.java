package com.sora.cli.command;

import com.sora.generator.springboot.SpringbootGenerator;
import com.sora.model.GenerateSpringBootConfig;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "basic", description = "生成基础代码", mixinStandardHelpOptions = true)
@Data
public class BasicTemplateCommand implements Callable<Integer> {


    @Option(names = {"-pn", "--projectName"}, arity = "0..1", description = "项目名", interactive = true, echo = true)
    private String projectName = "springboot-template";

    @Option(names = {"-d", "--destPath"}, arity = "0..1", description = "输出路径", interactive = true, echo = true)
    private String destPath = "D:\\";

    @Option(names = {"-a", "--author"}, arity = "0..1", description = "作者", interactive = true, echo = true)
    private String author = "sora";

    @Option(names = {"-g", "--groupId"}, arity = "0..1", description = "groupId", interactive = true, echo = true)
    private String groupId = "com.yyh";

//    @Option(names = {"-k", "--isKnife4jConfig"}, arity = "0..1", description = "输出文本", interactive = true, echo = true)
//    private boolean isKnife4jConfig;

//    @Option(names = {"-c", "--isCorsConfig"}, arity = "0..1", description = "输出文本", interactive = true, echo = true)
//    private boolean isCorsConfig;

    public Integer call() throws Exception {
        GenerateSpringBootConfig generateSpringBootConfig = new GenerateSpringBootConfig();
        generateSpringBootConfig.setProjectName(this.projectName);
        generateSpringBootConfig.setDestPath(this.destPath);
        generateSpringBootConfig.setAuthor(this.author);
        generateSpringBootConfig.setGroupId(this.groupId);
//        generateSpringBootConfig.setKnife4jConfig(false);
//        generateSpringBootConfig.setCorsConfig(false);

        System.out.println("配置信息：" + generateSpringBootConfig);

        SpringbootGenerator.generate(generateSpringBootConfig);
        return 0;
    }
}