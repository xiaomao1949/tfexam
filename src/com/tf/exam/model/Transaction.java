package com.tf.exam.model;



public class Transaction {
	private long transactionId;
	private long tradeId;
	private int version;
	private String securityCode;
	private int quantity;
	private Action action;
	private Operation operation;

	
	public Transaction(long transactionId, long tradeId, int version, String securityCode, int quantity, Action action,
			Operation operation) {
		super();
		this.transactionId = transactionId;
		this.tradeId = tradeId;
		this.version = version;
		this.securityCode = securityCode;
		this.quantity = quantity;
		this.action = action;
		this.operation = operation;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getTradeId() {
		return tradeId;
	}

	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

}

