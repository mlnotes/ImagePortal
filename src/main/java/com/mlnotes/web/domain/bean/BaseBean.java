package com.mlnotes.web.domain.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
@MappedSuperclass
public class BaseBean implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private boolean del;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable=false)
    private Date createTime = new Date();
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable=false, updatable=false,
            columnDefinition="timestamp default current_timestamp on update current_timestamp")
    @Generated(GenerationTime.ALWAYS)
    private Date updateTime;
    
    @Version
    private int version = 0;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
