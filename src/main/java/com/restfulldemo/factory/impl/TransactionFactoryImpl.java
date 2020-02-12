package com.restfulldemo.factory.impl;

import com.restfulldemo.enumeration.TransactionTypeEnum;
import com.restfulldemo.factory.TransactionFactory;
import com.restfulldemo.model.Transaction;

public class TransactionFactoryImpl implements TransactionFactory {

	@Override
	public Transaction createTransaction(String type) {
		if(TransactionTypeEnum.MONEY.getValue().equals(type)) {
			return new Transaction(TransactionTypeEnum.MONEY);
		}
		return new Transaction(TransactionTypeEnum.CARD);
	}

}
