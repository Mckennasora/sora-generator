package com.sora.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

import static com.sora.generator.springboot.SpringbootGenerator.DYNAMIC_TEMPLATE_PATH;
import static com.sora.generator.springboot.SpringbootGenerator.STATIC_TEMPLATE_PATH;

@Command(name = "list", description = "查看文件列表", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {

    public void run() {
        String projectPath = System.getProperty("user.dir");
        // 输入路径
        String inputPath = new File(projectPath + File.separator + STATIC_TEMPLATE_PATH).getAbsolutePath();
        List<File> files = FileUtil.loopFiles(inputPath);
        inputPath = new File(projectPath + File.separator + DYNAMIC_TEMPLATE_PATH).getAbsolutePath();
        files.addAll(FileUtil.loopFiles(inputPath));
        for (File file : files) {
            System.out.println(file.getName());
        }
    }
}