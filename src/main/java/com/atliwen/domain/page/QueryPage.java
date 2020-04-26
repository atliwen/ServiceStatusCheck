package com.atliwen.domain.page;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 分页类
 * @author 李文
 * @create 2019-08-15 17:29
 **/
@Data
public class QueryPage<T>
{
    private Page<T> page;
    private T where;

}
