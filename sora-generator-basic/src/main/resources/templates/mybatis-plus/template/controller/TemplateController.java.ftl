package ${packagePath}.${tableNameLowerCase}.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${packagePath}.${tableNameLowerCase}.entity.${tableNameBigCamel};
import ${packagePath}.${tableNameLowerCase}.entity.dto.Page${tableNameBigCamel}Dto;
import ${packagePath}.${tableNameLowerCase}.service.${tableNameBigCamel}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * ${tableComment}(${tableNameBigCamel})表控制层
 *
 * @author ${author}
 */
@Api("${tableNameBigCamel}-Module")
@RestController
@RequestMapping("${tableNameSmallCamel}")
public class ${tableNameBigCamel}Controller {
    /**
     * service对象
     */
    @Resource
    private ${tableNameBigCamel}Service ${tableNameSmallCamel}Service;

    /**
     * 分页查询所有数据
     *
     * @param page${tableNameBigCamel}Dto 查询实体
     * @return 所有数据
     */
    @GetMapping
    @ApiOperation(value = "模板-分页查询", notes = "模板-分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "当页显示的条数"),
            @ApiImplicitParam(name = "ascList", value = "升序字段"),
            @ApiImplicitParam(name = "descList", value = "降序字段"),
    })
    public Result<Page<${tableNameBigCamel}>> selectAll(Page${tableNameBigCamel}Dto page${tableNameBigCamel}Dto) {
        return Result.success(${tableNameSmallCamel}Service.page${tableNameBigCamel}(page${tableNameBigCamel}Dto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "模板-单一查询", notes = "模板-单一查询")
    public Result<${tableNameBigCamel}> selectOne(@PathVariable Serializable id) {
        return Result.success(${tableNameSmallCamel}Service.getById(id));
    }

    /**
     * 新增数据
     *
     * @param ${tableNameLowerCase} 实体对象
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation(value = "模板-新增", notes = "模板-新增")
    public Result<Boolean> insert(@RequestBody ${tableNameBigCamel} ${tableNameLowerCase}) {
        return Result.success(${tableNameSmallCamel}Service.save(${tableNameLowerCase}));
    }

    /**
     * 修改数据
     *
     * @param ${tableNameLowerCase} 实体对象
     * @return 修改结果
     */
    @PutMapping
    @ApiOperation(value = "模板-修改", notes = "模板-修改")
    public Result<Boolean> update(@RequestBody ${tableNameBigCamel} ${tableNameLowerCase}) {
        return Result.success(${tableNameSmallCamel}Service.updateById(${tableNameLowerCase}));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @ApiOperation(value = "模板-删除", notes = "模板-删除")
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(${tableNameSmallCamel}Service.removeByIds(idList));
    }
}

