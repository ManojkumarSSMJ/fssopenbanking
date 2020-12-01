package com.fss.openbanking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fss.openbanking.bean.AccountBalances;
import com.fss.openbanking.bean.AccountData;
import com.fss.openbanking.bean.CreateResponseData;
import com.fss.openbanking.bean.DirectDebitData;
import com.fss.openbanking.bean.InstitutionData;
import com.fss.openbanking.bean.StandingOrderData;
import com.fss.openbanking.bean.TransactionData;
import com.google.gson.Gson;

@Service
public class JSONResponseWrapper {

	Logger LOGGER = LoggerFactory.getLogger(JSONResponseWrapper.class);

	public CreateResponseData getCreateResponseDetails(String response)
			throws JsonMappingException, JsonProcessingException {

		Gson gson = new Gson();
		CreateResponseData createResponseData = gson.fromJson(response, CreateResponseData.class);
		LOGGER.info(createResponseData.toString());
		return createResponseData;
	}

	public TransactionData getTxnDataDetails(String response) {

		Gson gson = new Gson();
		TransactionData data = gson.fromJson(response, TransactionData.class);
		LOGGER.info(data.toString());
		return data;
	}

	public AccountBalances getBalanceDataDetails(String response) {

		Gson gson = new Gson();
		AccountBalances balance = gson.fromJson(response, AccountBalances.class);
		LOGGER.info(balance.toString());
		return balance;
	}

	public AccountData getAccountDataDetails(String response) {

		Gson gson = new Gson();
		AccountData data = gson.fromJson(response, AccountData.class);
		LOGGER.info(data.toString());
		return data;
	}

	public DirectDebitData getAccountDirectDebitDetails(String response) {

		Gson gson = new Gson();
		DirectDebitData data = gson.fromJson(response, DirectDebitData.class);
		LOGGER.info(data.toString());
		return data;
	}

	public StandingOrderData getAccountStandingOrdersDetails(String response) {

		Gson gson = new Gson();
		StandingOrderData data = gson.fromJson(response, StandingOrderData.class);
		LOGGER.info(data.toString());
		return data;
	}

	public InstitutionData getInstitutionsDetails(String response) {

		Gson gson = new Gson();
		InstitutionData data = gson.fromJson(response, InstitutionData.class);
		LOGGER.info(data.toString());
		return data;
	}

}
