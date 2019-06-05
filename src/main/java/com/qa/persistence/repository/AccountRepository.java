package com.qa.persistence.repository;

public interface AccountRepository {

	String getAllAccounts();
	String createAccount(String account);
	String deleteAccount(int accountNumber);
	String updateAccount(int accountNumber, String account);

}
