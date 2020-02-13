package com.restful.factory;

import com.restful.model.Transaction;

public interface TransactionFactory {

	Transaction createTransaction(String type);
}
