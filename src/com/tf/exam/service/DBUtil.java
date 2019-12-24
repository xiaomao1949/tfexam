package com.tf.exam.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:exam";
	private String user = "davidwei";
	private String psw = "exam";
	private Connection con = null;

	public Connection getConnection() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, psw);
			con.setAutoCommit(false); 
			if (con == null)
				throw new Exception("Get DB connection faild.");
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void commit() {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
