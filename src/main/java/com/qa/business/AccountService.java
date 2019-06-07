package com.qa.business;

public interface AccountService {

	//C
	String addAccount(String movie);
	
	//R
	String getAllAccounts();
	
	String getAnAccount(int id);

	//U
	String updateAccount(int id, String movie);

	//D
	String deleteAccount(int id);
	
	long cycleAccounts(String aName);


}