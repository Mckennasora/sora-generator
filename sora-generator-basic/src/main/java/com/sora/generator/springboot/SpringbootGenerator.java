package com.sora.generator.springboot;

import cn.hutool.core.util.ArrayUtil;
import com.sora.model.GenerateSpringBootConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author sora
 * @description
 */
@Slf4j
public class SpringbootGenerator {

    public static final String DEST_PATH = "D:\\demo";

    public static final String DYNAMIC_TEMPLATE_PATH = "src/main/resources/templates/dynamic/springboot-template";

    public static final String STATIC_TEMPLATE_PATH = "src/main/resources/templates/static/springboot-template";

    public static void main(String[] args) throws TemplateException, IOException {
        /**
         * 方案一
         * 复制所有文件夹，再按模板分别写入文件
         * ----> 动态文件和静态文件分开放，给出父文件夹，分别遍历
         */
        //模型
        GenerateSpringBootConfig generateSpringBootConfig = new GenerateSpringBootConfig();
        generateSpringBootConfig.setProjectName("fuckYCL-site");

        //判断是否有该文件夹
        File file = new File(DEST_PATH + File.separator + generateSpringBootConfig.getProjectName());
        if(file.exists()){
            throw new RuntimeException("文件夹已存在，请重试");
        }


        String projectPath = System.getProperty("user.dir");
        if (log.isDebugEnabled()) {
            log.debug("projectPath:{}", projectPath);
        }

        //目标文件夹
        String destPath = DEST_PATH + File.separator;

        // 静态复制
        String staticTemplatePath = projectPath + File.separator + STATIC_TEMPLATE_PATH;
        copyFileByRecursive(new File(staticTemplatePath), new File(destPath));

        // 动态生成
        String dynamicTemplatePath = projectPath + File.separator + DYNAMIC_TEMPLATE_PATH;

        //生成
        dynamicGeneratorByRecursive(new File(dynamicTemplatePath), new File(destPath), generateSpringBootConfig);
        dynamicRenameByRecursive(new File(destPath + "springboot-template"), generateSpringBootConfig);

    }

    /**
     * 文件 A => 目录 B，则文件 A 放在目录 B 下
     * 文件 A => 文件 B，则文件 A 覆盖文件 B
     * 目录 A => 目录 B，则目录 A 放在目录 B 下
     * <p>
     * 核心思路：先创建目录，然后遍历目录内的文件，依次复制
     *
     * @param inputFile
     * @param outputFile
     */
    private static void dynamicGeneratorByRecursive(File inputFile, File outputFile, Object model) {
        // 区分是文件还是目录
        if (inputFile.isDirectory()) {
            File destOutputFile = new File(outputFile, inputFile.getName());
            // 如果是目录，首先创建目标目录
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }
            // 获取目录下的所有文件和子目录
            File[] files = inputFile.listFiles();
            // 无子文件，直接结束
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                // 递归拷贝下一层文件
                dynamicGeneratorByRecursive(file, destOutputFile, model);
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("当前文件名：{}", inputFile.getName());
            }
            try {
                doGenerate(inputFile.getAbsolutePath(), outputFile.getAbsolutePath(), model);
                if (log.isDebugEnabled()) {
                    log.debug("{}---->{},生成成功", inputFile.getAbsolutePath(), outputFile.getAbsolutePath());
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("{}---->{},生成出错", inputFile.getAbsolutePath(), outputFile.getAbsolutePath());
            }
        }
    }

    private static void dynamicRenameByRecursive(File srcFile, Object modelObj) {
        GenerateSpringBootConfig model = (GenerateSpringBootConfig) modelObj;

        String srcFileName = srcFile.getName();
        if (srcFileName.contains("springboot-template")) {
            String newSrcFile = srcFile.getParentFile() + File.separator + model.getProjectName();
            srcFile.renameTo(new File(newSrcFile));
            srcFile = new File(newSrcFile);
        } else if (srcFileName.contains("springboot_template")) {
            String newSrcFile = srcFile.getParentFile() + File.separator + model.getProjectNameUnderLine();
            srcFile.renameTo(new File(newSrcFile));
            srcFile = new File(newSrcFile);
        }
        if (srcFile.isDirectory()) {        // 区分是文件还是目录
            File[] files = srcFile.listFiles();
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                // 递归拷贝下一层文件
                dynamicRenameByRecursive(file, model);
            }
        }
    }

    /**
     * 生成文件
     *
     * @param inputPath  模板文件输入路径
     * @param outputPath 输出路径
     * @param model      数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        // 生成
        String outputName = templateName.substring(0, templateName.lastIndexOf("."));
        if (outputName.contains("SpringbootTemplate")) {
            GenerateSpringBootConfig config = (GenerateSpringBootConfig) model;
            outputName = outputName.replace("SpringbootTemplate", config.getProjectNameBigCamel());
        }

        Writer out = new FileWriter(outputPath + File.separator + outputName);
        template.process(model, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }

    /**
     * 文件 A => 目录 B，则文件 A 放在目录 B 下
     * 文件 A => 文件 B，则文件 A 覆盖文件 B
     * 目录 A => 目录 B，则目录 A 放在目录 B 下
     * <p>
     * 核心思路：先创建目录，然后遍历目录内的文件，依次复制
     *
     * @param inputFile
     * @param outputFile
     * @throws IOException
     */
    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        // 区分是文件还是目录
        if (inputFile.isDirectory()) {

            File destOutputFile = new File(outputFile, inputFile.getName());
            // 如果是目录，首先创建目标目录
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }
            // 获取目录下的所有文件和子目录
            File[] files = inputFile.listFiles();
            // 无子文件，直接结束
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                // 递归拷贝下一层文件
                copyFileByRecursive(file, destOutputFile);
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("当前文件名：{}", inputFile.getName());
            }
            // 是文件，直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
