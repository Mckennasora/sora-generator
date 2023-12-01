package com.sora.generator.mybatisplus;

import cn.hutool.core.util.ArrayUtil;
import com.sora.cli.util.DBUtil;
import com.sora.model.ColumnInfo;
import com.sora.model.GenerateMybatisPlusConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.util.List;

/**
 * @author sora
 * @description
 */
@Slf4j
public class MybatisGenerator {

    public static final String TEMPLATE_PATH = "src/main/resources/templates/mybatis-plus/template";

    public static void generate(GenerateMybatisPlusConfig generateConfig) {

        String projectPath = System.getProperty("user.dir");
        if (log.isDebugEnabled()) {
            log.debug("projectPath:{}", projectPath);
        }

        //目标文件夹
        String destPath = generateConfig.getDestPath() + File.separator;
        //获取模型
        getColumns(generateConfig);

        //循环写模板
        dynamicGeneratorByRecursive(new File(projectPath + File.separator + TEMPLATE_PATH), new File(generateConfig.getDestPath()), generateConfig);

    }

//    public static void main(String[] args) {
//        GenerateMybatisPlusConfig generateMybatisPlusConfig = new GenerateMybatisPlusConfig();
//        generateMybatisPlusConfig.setTableName("mailhistory");
//        generateMybatisPlusConfig.setTableComment("我爱邓紫棋次数表");
//        generateMybatisPlusConfig.setUrl("jdbc:mysql://localhost:3306/amail_mail");
//        generateMybatisPlusConfig.setUsername("root");
//        generateMybatisPlusConfig.setPassword("123456");
//        generateMybatisPlusConfig.setDestPath(DEST_PATH);
//        generateMybatisPlusConfig.setPackagePath("cn.love.gem");
//        generateMybatisPlusConfig.setAuthor("gem");
//
//        generate(generateMybatisPlusConfig);
//    }

    private static void getColumns(GenerateMybatisPlusConfig generateConfig) {
        List<ColumnInfo> columnInfos = DBUtil.getColumnInfo(generateConfig.getUrl(), generateConfig.getUsername(), generateConfig.getPassword(), generateConfig.getTableName(), generateConfig.getSchema());
//        Connection connection = DBUtil.getConnection(generateConfig.getUrl(), generateConfig.getUsername(), generateConfig.getPassword());
//        List<ColumnInfo> columnInfos = DBUtil.getColumnInfo(connection, generateConfig.getTableName());
        generateConfig.setColumnInfos(columnInfos);
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
    private static void dynamicGeneratorByRecursive(File inputFile, File outputFile, Object modelObj) {
        // 区分是文件还是目录
        if (inputFile.isDirectory()) {
            String inputFileName = inputFile.getName();
            File destOutputFile = null;
            if (inputFileName.equals("template")) {
                GenerateMybatisPlusConfig model = (GenerateMybatisPlusConfig) modelObj;
                destOutputFile = new File(outputFile, model.getTableNameLowerCase());
            } else {
                destOutputFile = new File(outputFile, inputFileName);
            }
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
                dynamicGeneratorByRecursive(file, destOutputFile, modelObj);
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("当前文件名：{}", inputFile.getName());
            }
            try {
                doGenerate(inputFile.getAbsolutePath(), outputFile.getAbsolutePath(), modelObj);
                if (log.isDebugEnabled()) {
                    log.debug("{}---->{},生成成功", inputFile.getAbsolutePath(), outputFile.getAbsolutePath());
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("{}---->{},生成出错", inputFile.getAbsolutePath(), outputFile.getAbsolutePath());
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
    private static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
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
        if (outputName.contains("Template")) {
            GenerateMybatisPlusConfig config = (GenerateMybatisPlusConfig) model;
            outputName = outputName.replace("Template", config.getTableNameBigCamel());
        }

        Writer out = new FileWriter(outputPath + File.separator + outputName);
        template.process(model, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }

}


