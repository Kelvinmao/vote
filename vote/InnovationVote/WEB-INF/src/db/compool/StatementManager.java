/**
 * StatementManager.java
 * 
 * Copyright (c) 2015,北京邮电大学科技创新大本营
 * All rights reserved.
 * 
 * @author LiuHaiYang
 * @version 1.0
 * Date:2015-3-25
 */
package db.compool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>数据库操作</p>
 */

public class StatementManager 
{

    private Connection conn = null;
    private boolean commitState = true;
    private Statement st = null;


	/**
	 * 执行修改数据的SQL语句
	 * @param sql 需执行的SQL语句
	 * @return 执行成功返回true
	 */
    public int execute(String sql){
        int success = 0;
        PreparedStatement pst = null;
        if ( sql==null || sql.equals("")){
            return 0;
        }
        if (!getConnection()){
        	success = 0;
        }else{
 	        try {
	            if (!commitState){
	            	try{
	            		conn.rollback();
	            	}catch(Exception se1){
	            	}
	            }
	            pst = conn.prepareStatement(sql);
	            success = pst.executeUpdate(sql);
	            if (!commitState){
	            	conn.commit() ;
	            }
	        }catch(SQLException se){
	            try{
	                conn.rollback();
	            }catch(SQLException se1){}
	            success = 0;
	        }finally{
	        	try{
		        	pst.close();
	        	}catch (Exception e){ 
	        	}
	        }
        }
        return success;
    }

	/**
	 * 执行查询SQL语句
	 * @param sql 查询语句
	 * @return 执行成功返回一个记录集
	 */
    public ResultSet query(String sql){
		ResultSet rs =  null;
		if ( sql==null || sql.equals("")){
			return null;
		}
		if (!getConnection()){
			rs = null;
		}else{
			try {
				if (st == null)
				{
					st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY  );
				} 
				rs = st.executeQuery(sql);
			}catch(SQLException se){
				rs = null;
			}
		}
		return rs;

    }

    
    /*
     * 获取数据库连接
     * @return 成功时返回true
     */
    private boolean getConnection(){
    	if (conn != null ){
    		return true;
    	}else{
    		try{
    			conn = ConnectionManager.getConnection() ;
    			try{
    				commitState = conn.getAutoCommit() ;
    			}catch (Exception se){}
    			return true;
    		}catch(Exception e){
    			System.out.println("获取数据库连接失败！");
    			e.printStackTrace();
    			return false;
    		}
    	}
    }
    
    /**
     * 释放资源
     */
    public void close(){
			try{ 
				if (st != null){
				st.close();
				st = null;
				}
			}catch (Exception e){
			}
			ConnectionManager.closeConnection( conn );
    }
    
    protected void finalize()
    {
		close();
    }

}