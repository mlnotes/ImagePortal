package com.mlnotes.web.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class TableCreator {
    public static void main(String[] args){
        TableCreator tc = new TableCreator();
        tc.createTable(Person.class);
    }
    
    public void createTable(Class<?> entity){
        String strSql = explainAnnotation(entity);
        Connection conn = getConnection();
        System.out.println("SQL: " + strSql);
        
        try {
            PreparedStatement psql = conn.prepareStatement(strSql);
            psql.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TableCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection(){
        String user = "image";
        String password = "123123";
        String serverUrl = "jdbc:mysql://localhost:3306/imageportal?zeroDateTimeBehavior=convertToNull";
        try{
            Connection con = DriverManager.getConnection(serverUrl, user, password);
            return con;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    private String explainAnnotation(Class<?> entity){
        DBTable table = entity.getAnnotation(DBTable.class);
        String tableName = table.name();
        if(tableName.length() < 1){
            tableName = entity.getSimpleName().toUpperCase();
        }
        
        List<String> columnDefs = new ArrayList<String>();
        for(Field field : entity.getDeclaredFields()){
            String columnName = null;
            Annotation[] anns = field.getDeclaredAnnotations();
            if(anns.length > 0){
                if(anns[0] instanceof SQLInteger){
                    SQLInteger sInt = (SQLInteger)anns[0];
                    if(sInt.name().length() < 1){
                        columnName = field.getName().toUpperCase();
                    }else{
                        columnName = sInt.name();
                    }
                    columnDefs.add(String.format("%s Int %s", columnName, getConstraints(sInt.constraints())));
                }else if(anns[0] instanceof SQLString){
                    SQLString sString = (SQLString)anns[0];
                    if(sString.name().length() < 1){
                        columnName = field.getName().toUpperCase();
                    }else{
                        columnName = sString.name();
                    }
                    columnDefs.add(String.format("%s VARCHAR(%d) %s", columnName,
                            sString.length(), getConstraints(sString.constraints())));
                }
            }
        }
        
        String strBuilder = "CREATE TABLE " + tableName + " (" + String.join(",", columnDefs) + ")";
        return strBuilder;
    }
    
    String getConstraints(Constraints con){
        String result = "";
        if(!con.allowNull()){
            result += " NOT NULL ";
        }
        if(con.primaryKey()){
            result += " PRIMARY KEY ";
        }
        if(con.unique()){
            result += " UNIQUE ";
        }

        return result;
    }
}
