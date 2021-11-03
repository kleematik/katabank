package com.kleematik.katabank;

import com.kleematik.katabank.application.logging.PrintStatement;
import com.kleematik.katabank.domain.model.account.Account;
import com.kleematik.katabank.domain.model.transaction.TransactionType;
import com.kleematik.katabank.domain.repository.TransactionRepository;
import com.kleematik.katabank.infra.data.repository.inmemory.InMemoryTransactionRepositoryImpl;
import com.kleematik.katabank.infra.logging.ConsolePrintStatement;

public class KataBankApplication {
	public static void main(String[] args) throws InterruptedException {

		final TransactionRepository repository = InMemoryTransactionRepositoryImpl.getInstance();
		final PrintStatement printStatement = ConsolePrintStatement.getInstance();

		final Account account = Account.builder()
				.transactionRepository(repository)
				.printStatement(printStatement)
				.build();

		account.make(TransactionType.DEPOSIT, "200");
		Thread.sleep(4000);

		account.make(TransactionType.WITHDRAW, "200");
		Thread.sleep(2000);

		account.make(TransactionType.WITHDRAW, "150");
		Thread.sleep(3000);

		account.make(TransactionType.DEPOSIT, "200");
		Thread.sleep(1000);

		account.make(TransactionType.WITHDRAW, "300");
		Thread.sleep(2000);

		account.make(TransactionType.WITHDRAW, "150");

		account.printStatement();
	}

}
