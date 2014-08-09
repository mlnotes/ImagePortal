package com.mlnotes.web.data;

import com.mlnotes.web.data.exception.NoSuchTableMappingException;
import com.mlnotes.web.data.TableMapping.Column;
import com.mlnotes.web.data.TableMapping.Table;
import com.mlnotes.web.data.exception.NoSuchColumnMappingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class Session {
    private final Configuration config;
    private final TableMapping tables;
    
    private Session(Configuration config, TableMapping tables){
        this.config = config;
        this.tables = tables;
    }
    
    public Connection createConnection() throws SQLException, ClassNotFoundException{
        // load driver class
        Class.forName(config.getDriver());
        Connection conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        return conn;
    }
    
    public void save(Object obj) throws NoSuchTableMappingException, NoSuchColumnMappingException, ClassNotFoundException, SQLException{
        Class<?> clazz = obj.getClass();
        String className = clazz.getName();
        Table table = tables.getTable(className);
        if(table == null){
            throw new NoSuchTableMappingException("No mapping avaialbe for " + className);
        }
        
        String sql = "insert into " + table.getName() + "(%s) values(%s)";
        StringBuilder sbNames = new StringBuilder();
        StringBuilder sbValues = new StringBuilder();
        List<Class> types = new ArrayList<Class>();
        List<Object> values = new ArrayList<Object>();
        for(Entry<String, Column> entry : table.getColumns().entrySet()){
            String fieldName = entry.getKey();
            Column column = entry.getValue();
            String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            
            try {    
                Method getter = clazz.getMethod(getterName);
                Object value = getter.invoke(obj);
                if(sbNames.length() > 0){
                    sbNames.append(",");
                    sbValues.append(",");
                }
                types.add(getter.getReturnType());
                values.add(value);
                
                sbNames.append(column.getName());
                sbValues.append("?");
            } catch (NoSuchMethodException ex) {
                throw new NoSuchColumnMappingException("Class: " + className + "Column: " + fieldName);
            } catch (SecurityException ex) {
                throw new NoSuchColumnMappingException("Class: " + className + "Column: " + fieldName);
            } catch (IllegalAccessException ex) {
                throw new NoSuchColumnMappingException("Class: " + className + "Column: " + fieldName);
            } catch (IllegalArgumentException ex) {
                throw new NoSuchColumnMappingException("Class: " + className + "Column: " + fieldName);
            } catch (InvocationTargetException ex) {
                throw new NoSuchColumnMappingException("Class: " + className + "Column: " + fieldName);
            }
            
            sql = String.format(sql, sbNames.toString(), sbValues.toString());
            
            Connection conn = this.createConnection();
            PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
            for (int i = 0; i < values.size(); ++i) {
                setSqlValue(state, i + 1, types.get(i), values.get(i));
            }

            state.executeUpdate();
            state.close();
            conn.close();
        }
    }

    private void setSqlValue(PreparedStatement state, int index, Class clazz, Object value) throws SQLException{
        if(clazz.equals(String.class)){
            state.setString(index, (String)value);
        }else if(clazz.equals(Byte.class) || clazz.equals(byte.class)){
            state.setByte(index, (Byte)value);
        }else if(clazz.equals(Short.class) || clazz.equals(short.class)){
            state.setShort(index, (Short)value);
        }else if(clazz.equals(Integer.class) || clazz.equals(int.class)){
            state.setInt(index, (Integer)value);
        }else if(clazz.equals(Long.class) || clazz.equals(long.class)){
            state.setLong(index, (Long)value);
        }else if(clazz.equals(Float.class) || clazz.equals(float.class)){
            state.setFloat(index, (Float)value);
        }else if(clazz.equals(Double.class) || clazz.equals(double.class)){
            state.setDouble(index, (Double)value);
        }
    }

    private void test(){
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    }
}
