package com.example.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.repositories.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public List<Transaction> fetchAllTransactions(int userId, int categoryId) throws EtBadRequestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction fetchTransactionById(int userId, int categoryId, int transactionId)
			throws EtResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction addTransaction(int userId, int categoryId, double amount, String note, long transactionDate)
			throws EtBadRequestException {
		int transactionId = transactionRepository.create(userId, categoryId, amount, note, transactionDate);
		return transactionRepository.findById(userId, categoryId, transactionId);
	}

	@Override
	public void updateTransaction(int userId, int categoryId, int transactionId, Transaction transaction)
			throws EtBadRequestException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTransaction(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException {
		// TODO Auto-generated method stub

	}

}
