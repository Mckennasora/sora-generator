package ${packagePath}.${tableNameLowerCase}.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ${tableComment}(${tableNameBigCamel})表实体类
 *
 * @author ${author}
 */
@SuppressWarnings("serial")
@Data
@TableName("${tableName}")
public class ${tableNameBigCamel} {

<#list columnInfos as column>
    <#if column.columnNameSmallCamel == "id">
    @TableId
    </#if>
    <#if column.columnComment??>
    //${column.columnComment}
    </#if>
    @TableField(value = "${column.columnNameUnderLine}")
    private ${column.javaType} ${column.columnNameSmallCamel};
</#list>
}

