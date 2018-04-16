package com.niranjan.stateless;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class VistiBean
 */
@Stateless(mappedName="visit")
public class VistiBean implements VistiBeanRemote {

    /**
     * Default constructor. 
     */
    public VistiBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String sayHi() {		
		return "Hi";
	}
    

}
