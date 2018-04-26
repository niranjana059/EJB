package com.niranjana.stateful;

import javax.ejb.Stateful;

/**
 * Session Bean implementation class Bank
 */
@Stateful(mappedName = "bank")
public class Bank implements BankRemote {

	private int amount = 0;
    /**
     * Default constructor. 
     */
    public Bank() {
       System.out.println(this.getClass().getSimpleName()+" created...");
    }

	@Override
	public void deposite(int amount) {
		this.amount+=amount;
		System.out.println("amount deposited successfully");
		
	}

	@Override
	public boolean withdraw(int amount) {
		if(this.amount>=amount) {
			this.amount-=amount;
			System.out.println("amount windrawn successfully");
			return true;
		}else {
			System.out.println("available amount is lesser than the amount entered");
			return false;
		}
		
	}

	@Override
	public int getBalance() {
		return this.amount;
	}

}
