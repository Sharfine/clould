package com.sharfine.validate.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: Sharfine
 * @createTime: 2020/8/6 10:21
 */
@ApiModel("分页信息")
public class PagingInfo<T> {
    @ApiModelProperty(
            value = "数据",
            required = true
    )
    private List<T> items;
    @ApiModelProperty(
            value = "当前页码 (最小为1)",
            required = true,
            example = "1"
    )
    private Integer page;
    @ApiModelProperty(
            value = "每页个数",
            required = true,
            example = "50"
    )
    private Integer numPerPage;
    @ApiModelProperty(
            value = "总页数",
            required = true,
            example = "5"
    )
    private Integer totalPageNum;
    @ApiModelProperty(
            value = "总数",
            required = true,
            example = "50"
    )
    private Long totalNum;
    @ApiModelProperty(
            value = "是否还有前一页",
            required = true,
            example = "false"
    )
    private Boolean hasPrev;
    @ApiModelProperty(
            value = "是否还有后一页",
            required = true,
            example = "true"
    )
    private Boolean hasNext;

    public PagingInfo(List<T> items, int page, int numPerPage, long totalNum) {
        this.items = items;
        this.numPerPage = numPerPage;
        this.totalNum = totalNum;
        this.totalPageNum = (int) Math.ceil((double) totalNum / (double) numPerPage);
        this.page = page > this.totalPageNum ? this.totalPageNum : page;
        if (this.page == 0) {
            this.page = 1;
        }

        this.hasPrev = page > 1;
        this.hasNext = page < this.totalPageNum;
    }

    public List<T> getItems() {
        return this.items;
    }

    public void setItems(final List<T> items) {
        this.items = items;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(final Integer page) {
        this.page = page;
    }

    public Integer getNumPerPage() {
        return this.numPerPage;
    }

    public void setNumPerPage(final Integer numPerPage) {
        this.numPerPage = numPerPage;
    }

    public Integer getTotalPageNum() {
        return this.totalPageNum;
    }

    public void setTotalPageNum(final Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public Long getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(final Long totalNum) {
        this.totalNum = totalNum;
    }

    public Boolean getHasPrev() {
        return this.hasPrev;
    }

    public void setHasPrev(final Boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public Boolean getHasNext() {
        return this.hasNext;
    }

    public void setHasNext(final Boolean hasNext) {
        this.hasNext = hasNext;
    }

    @Override
    public String toString() {
        return "PagingInfo(items=" + this.getItems() + ", page=" + this.getPage() + ", numPerPage=" + this.getNumPerPage() + ", totalPageNum=" + this.getTotalPageNum() + ", totalNum=" + this.getTotalNum() + ", hasPrev=" + this.getHasPrev() + ", hasNext=" + this.getHasNext() + ")";
    }
}

