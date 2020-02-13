package com.restful.factory.impl;

import com.restful.enumeration.TransactionTypeEnum;
import com.restful.factory.TransactionFactory;
import com.restful.model.Transaction;

public class TransactionFactoryImpl implements TransactionFactory {

	@Override
	public Transaction createTransaction(String type) {
		if(TransactionTypeEnum.MONEY.getValue().equals(type)) {
			return new Transaction(TransactionTypeEnum.MONEY);
		}
		return new Transaction(TransactionTypeEnum.CARD);
	}

}
