package com.sora.cli.command;

import com.sora.generator.mybatisplus.MybatisGenerator;
import com.sora.model.ColumnInfo;

import java.util.ArrayList;

import com.sora.generator.springboot.SpringbootGenerator;
import com.sora.model.GenerateMybatisPlusConfig;
import com.sora.model.GenerateSpringBootConfig;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Data
@Command(name = "easyCode", description = "生成增删改查代码", mixinStandardHelpOptions = true)
public class EasyCodeCommand implements Callable<Integer> {

    @Option(names = {"-tn", "--tableName"}, arity = "0..1", description = "表名", interactive = true, echo = true)
    private String tableName = "template_table";

    @Option(names = {"-tc", "--tableComment"}, arity = "0..1", description = "表注释", interactive = true, echo = true)
    private String tableComment = "模板";

    @Option(names = {"-u", "--url"}, arity = "0..1", description = "数据库url", interactive = true, echo = true)
    private String url = "jdbc:mysql://localhost:3306/template_schema";

    @Option(names = {"-n", "--username"}, arity = "0..1", description = "用户名", interactive = true, echo = true)
    private String username = "root";

    @Option(names = {"-pwd", "--password"}, arity = "0..1", description = "密码", interactive = true, echo = true)
    private String password = "123456";

    @Option(names = {"-d", "--destPath"}, arity = "0..1", description = "目标路径", interactive = true, echo = true)
    private String destPath = "D:\\";

    @Option(names = {"-p", "--packagePath"}, arity = "0..1", description = "包路径", interactive = true, echo = true)
    private String packagePath = "com.yyh";

    @Option(names = {"-a", "--author"}, arity = "0..1", description = "作者", interactive = true, echo = true)
    private String author = "sora";

    public Integer call() throws Exception {

        GenerateMybatisPlusConfig generateMybatisPlusConfig = new GenerateMybatisPlusConfig();
        generateMybatisPlusConfig.setTableName(this.tableName);
        generateMybatisPlusConfig.setTableComment(this.tableComment);
        generateMybatisPlusConfig.setUrl(this.url);
        generateMybatisPlusConfig.setUsername(this.username);
        generateMybatisPlusConfig.setPassword(this.password);
        generateMybatisPlusConfig.setDestPath(this.destPath);
        generateMybatisPlusConfig.setPackagePath(this.packagePath);
        generateMybatisPlusConfig.setAuthor(this.author);

        MybatisGenerator.generate(generateMybatisPlusConfig);

        return 0;
    }
}