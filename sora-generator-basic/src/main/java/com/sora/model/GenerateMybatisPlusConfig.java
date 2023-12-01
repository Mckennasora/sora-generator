package com.sora.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author sora
 * @description
 */
@Data
public class GenerateMybatisPlusConfig {
    private String tableName = "template_table";
    private String tableComment = "模板";
    private String url = "jdbc:mysql://localhost:3306/template_schema";
    private String schema = "template_schema";
    private String username = "root";
    private String password = "123456";
    private String tableNameBigCamel;
    private String tableNameSmallCamel;
    private String tableNameLowerCase;
    private String destPath = "D:\\";
    private String packagePath = "com.yyh";
    private String author = "sora";
    private List<ColumnInfo> columnInfos;

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.tableNameLowerCase = tableName.replace("_", "");
        String[] words = tableName.split("_");
        this.tableNameBigCamel = Arrays.stream(words).map(word -> {
            String firstLetter = word.substring(0, 1);
            String restLetter = word.substring(1);
            return firstLetter.toUpperCase() + restLetter;
        }).collect(Collectors.joining());
        this.tableNameSmallCamel = this.tableNameBigCamel.substring(0, 1).toLowerCase() + this.tableNameBigCamel.substring(1);
    }

    public void setUrl(String url) {
        this.url = url;
        String[] split = url.split("/");
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(split));
        this.schema = strings.get(strings.size()-1);
    }
}

//    ${tableName}
//    ${tableComment}
//    ${url}
//    ${password}
//    ${user}
//    ${tableNameBigCamel}
//    ${tableNameSmallCamel}
//    ${tableNameLowerCase}
//    ${destPath}
//    ${tableNameBigCamel}
//    ${author}
