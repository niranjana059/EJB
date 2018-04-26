package com.niranjana.stateful;

public interface BankRemote {
	public void deposite(int amount);
	public boolean withdraw(int amount);
	public int getBalance();

}
