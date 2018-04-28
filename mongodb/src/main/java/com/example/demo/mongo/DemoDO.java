package com.example.demo.mongo;

import org.springframework.data.annotation.Id;

/**
 * @table demo
 */
public class DemoDO {
    /**
     * ID
     */
    @Id
    private String id;
    /**
     * 手机
     */
    private Integer level;
    /**
     * 手机
     */
    private String name;

    /**
     * ------------------------------------辅助字段------------------------------------
     */

    public DemoDO() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "DemoDO{" +
                "id=" + id +
                ", level=" + level +
                ", name='" + name + '\'' +
                '}';
    }
}