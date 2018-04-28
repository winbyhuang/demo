package com.example.demo.domain;

import winby.model.BaseDO;

/**
 * @table demo
 */
public class DemoDO extends BaseDO {
    /**     ID      */
    private Long id;
    /**     手机      */
    private Integer level;
    /**     手机      */
    private String name;
    /**------------------------------------辅助字段------------------------------------*/

    public DemoDO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}