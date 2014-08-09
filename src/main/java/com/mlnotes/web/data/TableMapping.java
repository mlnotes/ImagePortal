package com.mlnotes.web.data;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class TableMapping {
    private final Map<String, Table> tables;
    
    public TableMapping(){
        tables = new HashMap<String, Table>();
    }
    
    public void addTable(String className, Table table){
        tables.put(className, table);
    }
    
    public Table getTable(String className){
        return tables.get(className);
    }
    
    public static class Table{
        private String className;
        private String name;
        private final Map<String, Column> columns;
    
        public Table(){
            columns = new HashMap<String, Column>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
        
        public void addColumn(String fieldName, Column col){
            columns.put(fieldName, col);
        }

        public Map<String, Column> getColumns() {
            return columns;
        }
    }
    
    public static class Column{
        private String name;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
