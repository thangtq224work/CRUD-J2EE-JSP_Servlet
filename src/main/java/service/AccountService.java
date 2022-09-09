package service;

import java.util.List;

import model.Account;
import repository.AccountRepository;

public class AccountService {
	private final AccountRepository accountRepository = new AccountRepository(); 
	public Account getUser(String username,String password) {
		Account account = accountRepository.getAccountByUsername(username);
		System.out.println(account);
		if(account != null && password.equals(account.getPassword()) && !account.getDeleted()) {
			return account;
		}
		else {
			return null;
		}
	}
	public void addAccount(Account account) {
		accountRepository.insertAccount(account);
	}
	public List<Account> getAllAccount() {
		return accountRepository.getAccountList();
	}
	
}
