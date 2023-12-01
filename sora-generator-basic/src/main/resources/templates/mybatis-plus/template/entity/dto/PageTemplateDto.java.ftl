package ${packagePath}.${tableNameLowerCase}.entity.dto;

import ${packagePath}.${tableNameLowerCase}.entity.${tableNameBigCamel};
import lombok.Data;

import java.util.List;
/**
 * 分页实体
 * @author ${author}
 */
@Data
public class Page${tableNameBigCamel}Dto extends ${tableNameBigCamel} {
    private long current = 0;
    private long size = 10;
    private List<String> ascList;
    private List<String> descList;
}