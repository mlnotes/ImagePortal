package com.mlnotes.web.domain.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */

@Entity
@Table
public class Image extends BaseBean{
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
