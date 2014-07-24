package com.mlnotes.web.ioc;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class Bean {
    private String id;
    private String type;
    private Map<String, Object> properties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
    
    
}
