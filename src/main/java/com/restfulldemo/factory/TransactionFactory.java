package com.restfulldemo.factory;

import com.restfulldemo.model.Transaction;

public interface TransactionFactory {

	Transaction createTransaction(String type);
}
