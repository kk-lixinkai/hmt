package com.mybestcoding.hmt.model.dto;

import com.github.pagehelper.PageInfo;
import com.mybestcoding.hmt.converter.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: lixinkai
 * @description: 分页结果封装
 * @date: 2021/2/24 16:29
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageResult implements Converter<PageResult, PageInfo> {
    /**
     * 总数据记录数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 页码
     */
    private int pageNo;
    /**
     * 当前页的记录数
     */
    private int pageSize;

    @Override
    public PageResult convert(PageInfo obj) {
        return new PageResult()
                .setTotal(obj.getTotal())
                .setPages(obj.getPages())
                .setPageNo(obj.getPageNum())
                .setPageSize(obj.getPageSize());
    }
}
