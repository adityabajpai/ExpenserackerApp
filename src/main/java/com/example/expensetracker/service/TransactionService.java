package com.example.expensetracker.service;

import java.util.List;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.model.Transaction;

public interface TransactionService {
	
	List<Transaction> fetchAllTransactions(int userId, int categoryId) throws EtBadRequestException;
	
	Transaction fetchTransactionById(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException;
	
	Transaction addTransaction(int userId, int categoryId, double amount, String note, long transactionDate) throws EtBadRequestException;
	
	void updateTransaction(int userId, int categoryId, int transactionId, Transaction transaction) throws EtBadRequestException;
	
	void removeTransaction(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException;

}
