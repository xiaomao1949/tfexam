package com.tf.exam.service;

import com.tf.exam.model.*;



import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.sql.ResultSet;

public class PositionService {
	private DBUtil db = new DBUtil();

	public ArrayList<SecurityPosition> QueryAllSecurityPosition() throws Exception{
		ArrayList<SecurityPosition> list = new ArrayList<SecurityPosition>();
		String sql = "select * from security_position ";
		Connection con = db.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			 String securityCode = rs.getString("securityCode");
			 int originalPosition = rs.getInt("originalPosition");
			 int currentPosition = rs.getInt("currentPosition");
			 
			list.add(new SecurityPosition(securityCode, originalPosition, currentPosition));
		}
		db.commit();
		db.close();
		return list;
	}
	
	
	/**
	 * To do the calculation for a security code by all existing transactions
	 * @param tran
	 * @throws Exception
	 */
	public void UpdateCurrentPosition(Transaction tran) throws Exception{
		int orignalPosition = GetOriginalPositionBySecurityCode(tran.getSecurityCode());
		InsertTransaction(tran);
		ArrayList<Long> tradeList = QueryTradeBySecurityCode(tran.getSecurityCode());
		for (Long code : tradeList) {
			int tradeResult = CalTradeResult(code);
			orignalPosition +=tradeResult;
		}
		
		
		
		String sql = "update security_position set currentPosition = ? where securityCode = ?";
		Connection con = db.getConnection();
		PreparedStatement pst;
		 
		pst = con.prepareStatement(sql);
		
		pst.setInt(0,orignalPosition);
		pst.setString(0,tran.getSecurityCode());
		pst.executeUpdate();
		db.commit();
		db.close();
		
		
		
	}
	
	/**
	 * Insert a new transaction record into the data table
	 * @param tran
	 * @throws Exception
	 */
	public void InsertTransaction (Transaction tran) throws Exception{
		String sql = "insert into transaction(transactionId,tradeId,version,securityCode,quantity,action,operation,createdTime) values (?,?,?,?,?,?,?,?)";
		Connection con = db.getConnection();
		PreparedStatement pst;
		 
		pst = con.prepareStatement(sql);
		
		pst.setLong(1, tran.getTradeId());
		pst.setInt(2, tran.getVersion());
		pst.setString(3, tran.getSecurityCode());
		pst.setInt(4, tran.getQuantity());
		pst.setString(5, tran.getAction().toString());
		pst.setString(6, tran.getOperation().toString());
		pst.executeUpdate();
		db.commit();
		db.close();
			
	 
	}
	
	
	
	
	
	/**
	 * Calculate the impact quantity of a security code buy the trade of all versions
	 * @param tradeCode
	 * @return
	 * @throws Exception
	 */
	public int CalTradeResult(Long tradeId) throws Exception{
		int result = 0;
		ArrayList<Transaction> tranList = QueryTranByTrade(tradeId);
		
		for (Transaction tran : tranList) {
			switch (tran.getAction()) {
			case INSERT:
				if(tran.getOperation()==Operation.Buy)
				{
					result +=tran.getQuantity();
					
				}
				else {
					result -= tran.getQuantity();
				}
				break;
			case UPDATE:
				result = tran.getQuantity();
				break;
			case CANCEL:
				result = 0;
				break;
			default:
				break;
			}
			
			 
		}
		
		return result;
	}
	
	/**
	 * Get the initial position of a security code 
	 * @param securityCode
	 * @return
	 * @throws Exception
	 */
	public int GetOriginalPositionBySecurityCode(String securityCode) throws Exception{
		String sql = "select originalPosition from security_position where securityCode=?";
		Connection con = db.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(0, securityCode);
		ResultSet rs = pst.executeQuery();
		int position = 0;
		while (rs.next()) {
			position = rs.getInt("originalPosition");
			
		}
		db.commit();
		db.close();
		return position;
	}
	
	/**
	 * Get all transactions within the same trade code, order by version
	 * @param tradeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Transaction> QueryTranByTrade(long tradeId) throws Exception{
		ArrayList<Transaction> list = new ArrayList<Transaction>();
		
		String sql = "select * from transaction where tradeId = ? order by version";
		Connection con = db.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setLong(0, tradeId);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			 long transactionId = rs.getLong("transactionId");
			
			 int version = rs.getInt("version");
			 String securityCode = rs.getString("securityCode");
			 int quantity = rs.getInt("quantity");
			 Action action =Action.valueOf(rs.getString("action")) ;
			 Operation operation = Operation.valueOf( rs.getString("operation"));
			list.add(new Transaction(transactionId, tradeId, version,
					securityCode, quantity,action,operation));
		}
		db.commit();
		db.close();

		return list;
		
	}
	
	/**
	 * Get all trade code by the security code
	 * @param securityCode
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Long> QueryTradeBySecurityCode(String securityCode) throws Exception{
		ArrayList<Long> list = new ArrayList<Long>();
		String sql = "select distinct tradeId from transaction where securityCode=?";
		Connection con = db.getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(0, securityCode);
		ResultSet rs = pst.executeQuery();
		
		while (rs.next()) {
		 
			list.add(rs.getLong("tradeId"));
		}
		db.commit();
		db.close();

		return list;
		
	} 
}
