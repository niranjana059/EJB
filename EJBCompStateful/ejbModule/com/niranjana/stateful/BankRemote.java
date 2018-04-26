package com.niranjana.stateful;

import javax.ejb.Remote;

@Remote
public interface BankRemote {
	public void deposite(int amount);
	public boolean withdraw(int amount);
	public int getBalance();

}
