package com.qa.business;

import javax.inject.Inject;

import com.qa.persistence.repository.AccountRepository;

public class AccountServiceImplementation implements AccountService {

	@Inject
	AccountRepository accountRepo;

	public String getAllAccounts() {

		return accountRepo.getAllAccounts();
	}

	@Override
	public String addAccount(String account) {

		return accountRepo.createAccount(account);
	}

	@Override
	public String deleteAccount(int id) {
		return accountRepo.deleteAccount(id);
	}

	public void setRepo(AccountRepository repo) {
		this.accountRepo = repo;
	}

	@Override
	public long cycleAccounts(String aName) {

		return accountRepo.cycleAccounts(aName);

	}

	@Override
	public String getAnAccount(int id) {
		return accountRepo.getAnAccount(id);

	}

	@Override
	public String updateAccount(int id, String movie) {
		return accountRepo.updateAccount(id, movie);
	}

}
