package ${packagePath}.${tableNameLowerCase}.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${packagePath}.${tableNameLowerCase}.dao.${tableNameBigCamel}Dao;
import ${packagePath}.${tableNameLowerCase}.entity.${tableNameBigCamel};
import ${packagePath}.${tableNameLowerCase}.entity.dto.Page${tableNameBigCamel}Dto;
import ${packagePath}.${tableNameLowerCase}.service.${tableNameBigCamel}Service;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ${tableComment}(${tableNameBigCamel})表服务实现类
 *
 * @author ${author}
 */
@Service("${tableNameSmallCamel}Service")
public class ${tableNameBigCamel}ServiceImpl extends ServiceImpl<${tableNameBigCamel}Dao, ${tableNameBigCamel}> implements ${tableNameBigCamel}Service {

    @Override
    public Page<${tableNameBigCamel}> page${tableNameBigCamel}(Page${tableNameBigCamel}Dto ${tableNameSmallCamel}) {
        Page<${tableNameBigCamel}> page = new Page<>(${tableNameSmallCamel}.getCurrent(), ${tableNameSmallCamel}.getSize());
        List<OrderItem> orderItems = new ArrayList<>();
        //条件排序
        if(CollUtil.isNotEmpty(${tableNameSmallCamel}.getAscList())) {
            OrderItem.ascs(${tableNameSmallCamel}.getAscList().toArray(new String[0]));
            page.setOrders(orderItems);
        }else if(CollUtil.isNotEmpty(${tableNameSmallCamel}.getDescList())){
            orderItems.addAll(OrderItem.descs(${tableNameSmallCamel}.getDescList().toArray(new String[0])));
            page.setOrders(orderItems);
        }
        //分页条件查询
        return this.page(page, new QueryWrapper<>(${tableNameSmallCamel}));
    }

}

