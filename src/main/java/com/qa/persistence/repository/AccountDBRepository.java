package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	public long cycleAccounts(String aName) {

		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();

		List<Account> validList = accounts.stream().filter(n -> n.getFirstName().equals(aName))
				.collect(Collectors.toList());

		return validList.size();
	}

	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();

		return util.getJSONForObject(accounts);
	}

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(int id) {

		Account accountInDB = util.getObjectForJSON(getAnAccount(id), Account.class);

		if (manager.contains(manager.find(Account.class, id))) {

			manager.remove(manager.find(Account.class, id));
		}
		return "{\"message\": \"account successfully deleted\"}";
	}

	public String getAnAccount(int id) {
		return util.getJSONForObject(manager.find(Account.class, id));
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccount(int id, String account) {

		Account transAccount = util.getObjectForJSON(account, Account.class);

		Account oldAccount = manager.find(Account.class, id);

		if (oldAccount != null) {

			oldAccount.setAccountNumber(transAccount.getAccountNumber());
			oldAccount.setFirstName(transAccount.getFirstName());
			oldAccount.setLastName(transAccount.getLastName());

			manager.persist(oldAccount);

		}


		return "{\"message\": \"account successfully updated\"}";
	}

}
