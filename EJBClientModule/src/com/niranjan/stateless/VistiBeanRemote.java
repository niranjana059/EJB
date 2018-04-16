package com.niranjan.stateless;

import javax.ejb.Remote;

@Remote
public interface VistiBeanRemote {
	public String sayHi();
}
