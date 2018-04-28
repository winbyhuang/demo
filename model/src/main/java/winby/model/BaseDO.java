package winby.model;

import java.io.Serializable;
import java.util.Date;

public class BaseDO implements Serializable {
    private static final long serialVersionUID = -9070066568668349050L;
    private Integer isDeleted;
    private Date createTime;
    private String creator;
    private Date modifyTime;
    private String modifier;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * set:当前页码 ,get:当前分页起始数
     */
    private Integer pageIndex;

    /**
     * 分页起始页
     */
    private Integer pageStartNum;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageStartNum() {
        if (pageSize == null || pageIndex == null) {
            pageStartNum = null;
        } else {
            if (pageIndex <= 0) {
                pageStartNum = 0;
            } else {
                pageStartNum = (pageIndex - 1) * pageSize;
            }
        }
        return pageStartNum;
    }

    public void setPageStartNum(Integer pageStartNum) {
        this.pageStartNum = pageStartNum;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
