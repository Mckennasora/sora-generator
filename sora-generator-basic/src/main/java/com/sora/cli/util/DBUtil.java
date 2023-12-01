package com.sora.cli.util;

import com.sora.model.ColumnInfo;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sora
 * @description
 */
@Slf4j
public class DBUtil {
    public static Map<String, String> getFieldMapping() {
        Map<String, String> typeMap = new HashMap<>();
// 添加映射关系
        typeMap.put("CHAR", "String");
        typeMap.put("VARCHAR", "String");
        typeMap.put("LONGVARCHAR", "String");
        typeMap.put("BINARY", "Byte[]");
        typeMap.put("VARBINARY", "Byte[]");
        typeMap.put("LONGVARBINARY", "Byte[]");
        typeMap.put("BIT", "Boolean");
        typeMap.put("TINYINT", "Integer");
        typeMap.put("SMALLINT", "Short");
        typeMap.put("INTEGER", "Integer");
        typeMap.put("INT", "Integer");
        typeMap.put("BIGINT", "Long");
        typeMap.put("FLOAT", "Float");
        typeMap.put("REAL", "Float");
        typeMap.put("DOUBLE", "Double");
        typeMap.put("NUMERIC", "java.math.BigDecimal");
        typeMap.put("DECIMAL", "java.math.BigDecimal");
        typeMap.put("DATE", "java.sql.Date");
        typeMap.put("DATETIME", "java.sql.Date");
        typeMap.put("TIME", "java.sql.Time");
        typeMap.put("TIMESTAMP", "java.sql.Timestamp");
        typeMap.put("BLOB", "java.sql.Blob");
        typeMap.put("CLOB", "java.sql.Clob");
        typeMap.put("ARRAY", "java.sql.Array");
        typeMap.put("REF", "java.sql.Ref");
        typeMap.put("STRUCT", "java.sql.Struct");
        typeMap.put("JAVA_OBJECT", "Object");
        return typeMap;
    }

    // 获取数据库连接的方法
    public static Connection getConnection(String url, String username, String password) {
        Connection conn = null;
        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            conn = DriverManager.getConnection(url, username, password);
            log.info("数据库连接成功:{}", url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 获取表中字段的信息的方法
    public static List<ColumnInfo> getColumnInfo(String DBUrl, String username, String password, String tableName, String schema) {
        Connection conn = DBUtil.getConnection(DBUrl, username, password);
        return getColumnInfo(conn, tableName, schema);
    }

    // 获取表中字段的信息的方法
    public static List<ColumnInfo> getColumnInfo(Connection conn, String tableName, String schema) {
        List<ColumnInfo> columnInfoList = new ArrayList<>();
        try {
            // 获取数据库的元数据
            DatabaseMetaData dbmd = conn.getMetaData();
            // 获取指定表的字段信息
            ResultSet rs = dbmd.getColumns(schema, "%", tableName, "%");
            // 遍历结果集，封装字段信息
            while (rs.next()) {
                // 获取字段名称
                String columnName = rs.getString("COLUMN_NAME");
                // 获取字段类型
                String columnType = rs.getString("TYPE_NAME");
                // 获取字段注释
                String columnComment = rs.getString("REMARKS");
                // 将字段信息放入map中
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.setColumnType(columnType);
                columnInfo.setColumnComment(columnComment);
                columnInfo.setColumnNameUnderLine(columnName);
                columnInfo.setColumnNameSmallCamel(underLineToSmallCamel(columnName));
                // 将map放入list中
                columnInfoList.add(columnInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("表信息获取成功连接成功");
        return columnInfoList;
    }

    private static String underLineToSmallCamel(String columnName) {
        String[] words = columnName.split("_");
        String bigCamel = Arrays.stream(words).map(word -> {
            String firstLetter = word.substring(0, 1);
            String restLetter = word.substring(1);
            return firstLetter.toUpperCase() + restLetter;
        }).collect(Collectors.joining());
        return bigCamel.substring(0, 1).toLowerCase() + bigCamel.substring(1);
    }
}
