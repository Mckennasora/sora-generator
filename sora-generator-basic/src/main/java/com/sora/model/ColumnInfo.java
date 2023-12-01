package com.sora.model;

import com.sora.cli.util.DBUtil;
import lombok.Data;

import java.util.Map;

/**
 * @author sora
 * @description
 */
@Data
public class ColumnInfo {

    private String columnType;
    private String columnComment;
    private String columnNameUnderLine;
    private String columnNameSmallCamel;
    private String javaType;

    public void setColumnType(String columnType){
        this.columnType = columnType;
        Map<String, String> fieldMapping = DBUtil.getFieldMapping();
        if(fieldMapping.containsKey(columnType)){
            this.javaType = fieldMapping.get(columnType);
        }else {
            throw new RuntimeException("NoSuchJavaType");
        }
    }
}
