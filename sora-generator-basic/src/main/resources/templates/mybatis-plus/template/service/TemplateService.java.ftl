package ${packagePath}.${tableNameLowerCase}.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ${packagePath}.${tableNameLowerCase}.entity.${tableNameBigCamel};
import ${packagePath}.${tableNameLowerCase}.entity.dto.Page${tableNameBigCamel}Dto;

/**
 * ${tableComment}(${tableNameBigCamel})表服务接口
 *
 * @author ${author}
 */
public interface ${tableNameBigCamel}Service extends IService<${tableNameBigCamel}> {

    Page<${tableNameBigCamel}> page${tableNameBigCamel}(Page${tableNameBigCamel}Dto page${tableNameBigCamel}dto);
}

