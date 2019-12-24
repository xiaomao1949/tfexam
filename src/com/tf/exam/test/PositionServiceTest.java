package com.tf.exam.test;

import org.junit.Test;
import com.tf.exam.service.*;
import com.tf.exam.model.*;

public class PositionServiceTest {

	private PositionService service = new PositionService();
	@Test
	public void testQueryAllSecurityPosition() {
		try {
			service.QueryAllSecurityPosition();
		} catch (Exception e) {
			System.out.println("Query All Security Postion faild");;
		}
		
	}
	
	@Test
	public void testUpdateCurrentPosition() {
		try {
			Transaction tran = new Transaction(0, 99, 1, "INF", 59, Action.INSERT, Operation.Buy);
			service.UpdateCurrentPosition(tran);
		} catch (Exception e) {
			System.out.println("Update Current Position faild");;
		}
		
	}
	
	@Test
	public void testInsertTransaction() {
		try {
			Transaction tran = new Transaction(0, 99, 1, "REL", 33, Action.UPDATE, Operation.Buy);
			service.InsertTransaction(tran);
		} catch (Exception e) {
			System.out.println("Insert Transaction faild");;
		}
		
	}
	
	@Test
	public void testCalTradeResult() {
		try {
			service.CalTradeResult((long) 4);
		} catch (Exception e) {
			System.out.println("CalTrade Result faild");;
		}
		
	}
	
	@Test
	public void testGetOriginalPositionBySecurityCode() {
		try {
			service.GetOriginalPositionBySecurityCode("INF");
		} catch (Exception e) {
			System.out.println("Get Original Position By Security Code faild");;
		}
		
	}
	
	@Test
	public void testQueryTranByTrade() {
		try {
			service.QueryTranByTrade(3);
		} catch (Exception e) {
			System.out.println("Query Tran By Trade faild");;
		}
		
	}
	
	@Test
	public void testQueryTradeBySecurityCode() {
		try {
			service.QueryTradeBySecurityCode("INF");
		} catch (Exception e) {
			System.out.println("Query Trade By Security Code faild");;
		}
		
	}

}
