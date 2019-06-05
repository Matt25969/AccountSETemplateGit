package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

public class AccountMapRepository implements AccountRepository{
	
	Map<Integer, Account> accountMap = new HashMap<Integer, Account>();
	
	//You must provide concrete implementation for each of these methods
	//do not change the method signature
	//THINK - if the parameter is a String
	//AND - We are required to create an account from this String
	//How can I convert to a String from an Object?
	//What utility methods do I have available?
	
	//You must complete this section using TDD
	//You can use the suggested tests or build your own.

	public String getAllAccounts() {

		return new JSONUtil().getJSONForObject(accountMap);
		
	}

	public String createAccount(String account) {
		Account newAccount = new JSONUtil().getObjectForJSON(account, Account.class);
		accountMap.put(newAccount.getId(), newAccount);
		return "Account successfuly created";
	}

	public String deleteAccount(int accountNumber) {
		accountMap.remove((Integer)accountNumber);
		return "Account successfully removed";
	}

	public String updateAccount(int accountNumber, String account) {
		Account accToUpdate = new JSONUtil().getObjectForJSON(account, Account.class);
		accountMap.put(accountNumber, accToUpdate);
		
		return "Account successfully updated";
	}
	
	public long cycleAccounts(String aName) {
		
		return 0L;
	}
	
	public Map<Integer, Account> getAccountMap() {
		return accountMap;
	}

	public void setAccountMap(Map<Integer, Account> accountMap) {
		this.accountMap = accountMap;
	}
	


}
