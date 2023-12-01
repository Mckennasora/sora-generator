package ${packagePath}.${tableNameLowerCase}.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${packagePath}.${tableNameLowerCase}.entity.${tableNameBigCamel};
import org.springframework.stereotype.Repository;

/**
 * ${tableComment}(${tableNameBigCamel})表数据库访问层
 *
 * @author ${author}
 */
@Repository
public interface ${tableNameBigCamel}Dao extends BaseMapper<${tableNameBigCamel}> {

}

