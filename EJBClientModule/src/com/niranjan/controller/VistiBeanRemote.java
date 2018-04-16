package com.niranjan.controller;

import javax.ejb.Remote;

@Remote
public interface VistiBeanRemote {
	public String sayHi();
}
