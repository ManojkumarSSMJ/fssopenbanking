/**
 * 
 */
package com.fss.openbanking.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fss.openbanking.bean.AccountData;
import com.fss.openbanking.bean.AccountIdentifications;
import com.fss.openbanking.bean.AccountNames;
import com.fss.openbanking.bean.AccountsResponse;
import com.fss.openbanking.bean.ConsentTokenDetails;
import com.fss.openbanking.bean.DirectDebitDetails;
import com.fss.openbanking.bean.InstitutionDetails;
import com.fss.openbanking.bean.ProductDetails;
import com.fss.openbanking.bean.StandingOrderDetails;
import com.fss.openbanking.bean.TppAccountsRequest;
import com.fss.openbanking.bean.TppAccountsResponse;
import com.fss.openbanking.bean.TppBalanceResponse;
import com.fss.openbanking.bean.TppDirectDebitResponse;
import com.fss.openbanking.bean.TppProductsResponse;
import com.fss.openbanking.bean.TppStandingOrderResponse;
import com.fss.openbanking.bean.TppTransactionResponse;
import com.fss.openbanking.bean.TransactionData;
import com.fss.openbanking.bean.TransactionDetails;
import com.fss.openbanking.bean.UserDetails;
import com.fss.openbanking.dao.DaoRepository;
import com.fss.openbanking.utils.OpenBankingUtility;

/**
 * @author selvakumara
 *
 */
@Service("AccountAccessService")
public class AccountAccessServiceImpl implements AccountAccessService {

	public static final Logger LOGGER = LoggerFactory.getLogger(AccountAccessServiceImpl.class);

	@Autowired
	Environment environment;

	@Autowired
	private DaoRepository daoRepository;
	
	@Override
	public TppAccountsResponse getTppAccountsResponse(UserDetails userDetails) {
		TppAccountsResponse tppAccountsResponse = new TppAccountsResponse();
		try {
			
			List<AccountsResponse> accountsList = new ArrayList<>();
			List<InstitutionDetails> institutionDetails = new ArrayList<>();
			AccountsResponse accountsResponse = null;
			String acName = "";
			
			List<InstitutionDetails> institutions = daoRepository.fetchTppInstDetails();
			
			List<ConsentTokenDetails> consentDetailsfromDb = daoRepository.fetchConsentDetailsByUser(userDetails.getMobileNumber());

			
			if(institutions.isEmpty() || consentDetailsfromDb.isEmpty()) {
				tppAccountsResponse.setResponseFlag("0");
				tppAccountsResponse.setResponseMessage("Please link the Bank with FSS Open Banking HUB, If you already link please create consent for accounts.");
			    throw new Exception("Institution or Consent Details not found");
			}
			
			List<AccountData> accountDetails = daoRepository.fetchAccountDetailsByUserId(userDetails.getMobileNumber());
			
			if(accountDetails.isEmpty()) {
				tppAccountsResponse.setResponseFlag("2");
				throw new Exception("Account Details not found");
			}
			
			for(AccountData accountDetail : accountDetails)
			{
				List<AccountNames> accountNames = daoRepository.fetchAccountNamesDetails(accountDetail);
				
				acName = "";
				
				for(AccountNames accountName : accountNames)
					acName += accountName.getName() + ",";
				
				List<AccountIdentifications> accountIdentifications = daoRepository.fetchAccountIdentificationsDetails(accountDetail);
				
				List<AccountIdentifications> accountIdentificationsMask = new ArrayList<AccountIdentifications>();
				
				for(AccountIdentifications accountIdenti : accountIdentifications)
				{
					String identity = accountIdenti.getIdentification();
					String type = capitalize(accountIdenti.getType().split(" "));
					accountIdenti.setIdentification(OpenBankingUtility.mask(identity));
					accountIdenti.setType(type);
					accountIdentificationsMask.add(accountIdenti);
				}
				
				accountsResponse = new AccountsResponse();
				accountsResponse.setBankName(accountDetail.getBankName());
				accountsResponse.setMobileNumber(accountDetail.getMobileNumber());
				accountsResponse.setBankId(accountDetail.getBankId());
				accountsResponse.setAccountHolderName(acName.substring(0,acName.length()-1).toString());
				accountsResponse.setAccountIdentification(accountIdentificationsMask);
				accountsResponse.setAccountId(accountDetail.getId());
				accountsResponse.setCurrencyType(accountDetail.getCurrency());
				accountsResponse.setAccountType(accountDetail.getType());
				accountsResponse.setAccountSubType(accountDetail.getAccountType());
				accountsResponse.setMaskAccountId(OpenBankingUtility.mask(accountDetail.getId()));
				accountsList.add(accountsResponse);
			}
			
				
			for(InstitutionDetails institutionDetail : institutions)
			{
				String upDt = daoRepository.fetchLastUpdateDateTime(userDetails.getMobileNumber(),institutionDetail.getBankId(),institutionDetail.getBankName());
				institutionDetail.setLastUpdateDT(upDt != null ? upDt : "-");
				institutionDetails.add(institutionDetail);
			}
			
			if(accountsList.isEmpty()) {
				tppAccountsResponse.setResponseFlag("2");
				throw new Exception("Account Details not found");
			}
			
			tppAccountsResponse.setAccountsResponseList(accountsList);
			tppAccountsResponse.setInstitutionDetails(institutionDetails);
			tppAccountsResponse.setResponseFlag("1");
			tppAccountsResponse.setResponseMessage("Accounts Fetched Successfully");
			
		} catch(Exception e) {
			
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppAccountsResponse;
	}
	
	@Override
	public TppBalanceResponse gettppBalanceResponse(String userId, TppAccountsRequest tppAccountsRequest) {
		TppBalanceResponse tppBalanceResponse = new TppBalanceResponse();
		try {
			
			List<TppBalanceResponse> tppBalanceResponseList = daoRepository.fetchBalanceDetailsByBankId(tppAccountsRequest,userId);
			
			if(tppBalanceResponseList.isEmpty())
			{
				throw new Exception("Balance Details not found");
			}
			
			tppBalanceResponse.setAccountId(tppAccountsRequest.getAccountId());
			tppBalanceResponse.setMaskAccountId(OpenBankingUtility.mask(tppAccountsRequest.getAccountId()));
			tppBalanceResponse.setBankName(tppAccountsRequest.getBankName());
		    tppBalanceResponse.setTppBalanceList(tppBalanceResponseList);
			
		} catch(Exception e) {
			
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppBalanceResponse;
	}

	@Override
	public TppTransactionResponse gettppTransactionResponse(UserDetails userDetails, TppAccountsRequest tppAccountsRequest) {
		TppTransactionResponse tppTransactionResponse = new TppTransactionResponse();
		try {
			
			List<TransactionDetails> transactionDetailsList = new ArrayList<TransactionDetails>();
			TransactionDetails transactionDetail = null;
			List<TransactionData> transactionDatas = daoRepository.fetchTransactionDetails(userDetails, tppAccountsRequest);
			
			for(TransactionData transactionData : transactionDatas) {
				
				transactionDetail = new TransactionDetails();
				transactionDetail.setBankName(tppAccountsRequest.getBankName());
				transactionDetail.setTransactionId(transactionData.getId());
				transactionDetail.setAccountNumber(tppAccountsRequest.getAccountId());
				transactionDetail.setMaskAccountNumber(OpenBankingUtility.mask(tppAccountsRequest.getAccountId()));
				transactionDetail.setTransactionReference(transactionData.getReference() != null ? transactionData.getReference() : "-");
				transactionDetail.setTransactionDateTime(transactionData.getDate());
				transactionDetail.setAmount(transactionData.getAmount());
				transactionDetail.setCurrency(transactionData.getCurrency());
				transactionDetail.setTransactionStatus(transactionData.getStatus());
				transactionDetailsList.add(transactionDetail);
			}
			
			if(transactionDetailsList.isEmpty())
			{
				tppTransactionResponse.setResponseFlag("0");
				tppTransactionResponse.setResponseMessage("Transaction details not found.");
				throw new Exception("Transaction details not found");
			}
			
			tppTransactionResponse.setTransactionDetailList(transactionDetailsList);
			tppTransactionResponse.setResponseFlag("1");
			tppTransactionResponse.setResponseMessage("Transaction Fetched Successfully");
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!",e.getMessage());
		}
		return tppTransactionResponse;
	}

	@Override
	public TppTransactionResponse gettppAllTransactionResponse(UserDetails userDetails) {
		TppTransactionResponse tppTransactionResponse = new TppTransactionResponse();
		try {
			
			List<TransactionDetails> transactionDetailsList = new ArrayList<TransactionDetails>();
			TransactionDetails transactionDetail = null;
			List<TransactionData> transactionDatas = daoRepository.fetchAllTransactionDetails(userDetails);
			
			for(TransactionData transactionData : transactionDatas) {
				
				transactionDetail = new TransactionDetails();
				transactionDetail.setBankName(transactionData.getBankName());
				transactionDetail.setTransactionId(transactionData.getId());
				transactionDetail.setAccountNumber(transactionData.getAccountId());
				transactionDetail.setMaskAccountNumber(OpenBankingUtility.mask(transactionData.getAccountId()));
				transactionDetail.setTransactionReference(transactionData.getReference() != null ? transactionData.getReference() : "-");
				transactionDetail.setTransactionDateTime(transactionData.getDate());
				transactionDetail.setAmount(transactionData.getAmount());
				transactionDetail.setCurrency(transactionData.getCurrency());
				transactionDetail.setTransactionStatus(transactionData.getStatus());
				transactionDetailsList.add(transactionDetail);
			}
			
			if(transactionDetailsList.isEmpty())
			{
				tppTransactionResponse.setResponseFlag("0");
				tppTransactionResponse.setResponseMessage("Transaction details not found.");
				throw new Exception("Transaction details not found");
			}
			
			tppTransactionResponse.setTransactionDetailList(transactionDetailsList);
			tppTransactionResponse.setResponseFlag("1");
			tppTransactionResponse.setResponseMessage("Transaction Fetched Successfully");
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppTransactionResponse;
	}
	
	@Override
	public TppDirectDebitResponse getTppDirectDebitResponse(UserDetails userDetails, TppAccountsRequest tppAccountsRequest) {
		TppDirectDebitResponse tppDirectDebitResponse =  new TppDirectDebitResponse();
		try {
			
				List<DirectDebitDetails> directDebitDetailsList = new ArrayList<DirectDebitDetails>();
				List<DirectDebitDetails> directDebitDetails = daoRepository.fetchDirectDebitsDetails(userDetails,tppAccountsRequest);
			
				for(DirectDebitDetails directDebitDetail : directDebitDetails) {
					
					directDebitDetail.setAccountId(tppAccountsRequest.getAccountId());
					directDebitDetail.setMaskAccountId(OpenBankingUtility.mask(tppAccountsRequest.getAccountId()));
					directDebitDetailsList.add(directDebitDetail);
				}
				
				if(directDebitDetailsList.isEmpty()) {
					tppDirectDebitResponse.setResponseFlag("0");
					tppDirectDebitResponse.setResponseMessage("Direct Debits details not found.");
					throw new Exception("Direct Debits details not found");
				}
				
				tppDirectDebitResponse.setResponseFlag("1");
				tppDirectDebitResponse.setResponseMessage("Direct Debits fetched successfully");
				tppDirectDebitResponse.setDirectDebitDetails(directDebitDetailsList);
				
		}catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppDirectDebitResponse;
	}
	
	@Override
	public TppDirectDebitResponse getTppAllDirectDebitResponse(UserDetails userDetails) {
		TppDirectDebitResponse tppDirectDebitResponse =  new TppDirectDebitResponse();
		try {
			
				List<DirectDebitDetails> directDebitDetailsList = new ArrayList<DirectDebitDetails>();
				List<DirectDebitDetails> directDebitDetails = daoRepository.fetchAllDirectDebitsDetails(userDetails);
			
				for(DirectDebitDetails directDebitDetail : directDebitDetails) {
					
					directDebitDetail.setMaskAccountId(OpenBankingUtility.mask(directDebitDetail.getAccountId()));
					directDebitDetailsList.add(directDebitDetail);
				}
				
				if(!directDebitDetailsList.isEmpty()) {
					tppDirectDebitResponse.setResponseFlag("0");
					tppDirectDebitResponse.setResponseMessage("Direct Debits details not found.");
					throw new Exception("Direct Debits details not found");
				}
				
				tppDirectDebitResponse.setResponseFlag("1");
				tppDirectDebitResponse.setResponseMessage("Direct Debits fetched successfully");
				tppDirectDebitResponse.setDirectDebitDetails(directDebitDetailsList);
				
		}catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppDirectDebitResponse;
	}
	
	@Override
	public TppStandingOrderResponse getTppStandingOrderResponse(UserDetails userDetails, TppAccountsRequest tppAccountsRequest) {
		TppStandingOrderResponse tppStandingOrderResponse = new TppStandingOrderResponse();
		try {
			
			   List<StandingOrderDetails> standingOrderDetailList = new ArrayList<StandingOrderDetails>();
			   
			   List<StandingOrderDetails> standingOrderDetails = daoRepository.fetchStandingOrdersDetails(userDetails,tppAccountsRequest);
			   
			   for(StandingOrderDetails standingOrderDetail : standingOrderDetails) {
				  
				    List<AccountIdentifications> standingOrderIdentifications = daoRepository.fetchStandingOrdersIdentificationsDetails(standingOrderDetail);
				   
				    List<AccountIdentifications> standingOrderIdentificationsMask = new ArrayList<AccountIdentifications>();
					
					for(AccountIdentifications standingOrderIdenti : standingOrderIdentifications)
					{
						String identity = standingOrderIdenti.getIdentification();
						String type = capitalize(standingOrderIdenti.getType().split(" "));
						standingOrderIdenti.setIdentification(OpenBankingUtility.mask(identity));
						standingOrderIdenti.setType(type);;
						standingOrderIdentificationsMask.add(standingOrderIdenti);
					}
					
					standingOrderDetail.setAccountId(tppAccountsRequest.getAccountId());
					standingOrderDetail.setMaskAccountId(OpenBankingUtility.mask(tppAccountsRequest.getAccountId()));
					standingOrderDetail.setStandingOrderIdentifications(standingOrderIdentificationsMask);
					standingOrderDetailList.add(standingOrderDetail);
			   }
			   
			   if(standingOrderDetailList.isEmpty()) {
				   tppStandingOrderResponse.setResponseFlag("0");
				   tppStandingOrderResponse.setResponseMessage("Standing Orders details not found.");
				   throw new Exception("Standing Orders details not found");
				}
			   
			    tppStandingOrderResponse.setResponseFlag("1");
				tppStandingOrderResponse.setResponseMessage("Standing Orders fetched successfully");
				tppStandingOrderResponse.setStandingOrderDetailList(standingOrderDetailList);
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppStandingOrderResponse;
	}
	

	@Override
	public TppStandingOrderResponse getTppAllStandingOrderResponse(UserDetails userDetails) {
		TppStandingOrderResponse tppStandingOrderResponse = new TppStandingOrderResponse();
		try {
			
			   List<StandingOrderDetails> standingOrderDetailList = new ArrayList<StandingOrderDetails>();
			   
			   List<StandingOrderDetails> standingOrderDetails = daoRepository.fetchAllStandingOrdersDetails(userDetails);
			   
			   for(StandingOrderDetails standingOrderDetail : standingOrderDetails) {
				  
				    List<AccountIdentifications> standingOrderIdentifications = daoRepository.fetchStandingOrdersIdentificationsDetails(standingOrderDetail);
				   
				    List<AccountIdentifications> standingOrderIdentificationsMask = new ArrayList<AccountIdentifications>();
					
					for(AccountIdentifications standingOrderIdenti : standingOrderIdentifications)
					{
						String identity = standingOrderIdenti.getIdentification();
						String type = capitalize(standingOrderIdenti.getType().split(" "));
						standingOrderIdenti.setIdentification(OpenBankingUtility.mask(identity));
						standingOrderIdenti.setType(type);;
						standingOrderIdentificationsMask.add(standingOrderIdenti);
					}
					
					standingOrderDetail.setMaskAccountId(OpenBankingUtility.mask(standingOrderDetail.getAccountId()));
					standingOrderDetail.setStandingOrderIdentifications(standingOrderIdentificationsMask);
					standingOrderDetailList.add(standingOrderDetail);
			   }
			   
			   if(standingOrderDetailList.isEmpty()) {
				   tppStandingOrderResponse.setResponseFlag("0");
				   tppStandingOrderResponse.setResponseMessage("Standing Orders details not found.");
				   throw new Exception("Standing Orders details not found");
				}
			   
			    tppStandingOrderResponse.setResponseFlag("1");
			    tppStandingOrderResponse.setResponseMessage("Standing Orders fetched successfully");
				tppStandingOrderResponse.setStandingOrderDetailList(standingOrderDetailList);
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppStandingOrderResponse;
	}
	
	@Override
	public TppProductsResponse gettppProductsResponse(String userId,
			TppAccountsRequest tppAccountsRequest) {
		TppProductsResponse tppProductsResponse = new TppProductsResponse();
		try {
			tppProductsResponse.setResponseFlag("1");
			tppProductsResponse.setResponseMessage("Standing Order fetched successfully");
			List<ProductDetails>  productDetails = new ArrayList<ProductDetails>();
			ProductDetails productDetail = new ProductDetails();
			productDetail.setAccountNumber("1332222222");
			productDetail.setProductId("HSBC12234BAS");
			productDetail.setProductType("BusinessCurrentAccount");
			productDetail.setProductName("Business Current Account in Monthly Fee");
			productDetails.add(productDetail);
			productDetail = new ProductDetails();
			productDetail.setAccountNumber("1332222222");
			productDetail.setProductId("HSBC12234BAS");
			productDetail.setProductType("BusinessCurrentAccount");
			productDetail.setProductName("Business Current Account in Monthly Fee");
			productDetails.add(productDetail);
			tppProductsResponse.setProductDetails(productDetails);
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return tppProductsResponse;
	}
	
	public static String capitalize(String[] data)
	{
		String converted = "";
		try {
			
			for(String convert : data)
			{
				converted += convert.substring(0, 1).toUpperCase() + convert.substring(1).toLowerCase() + ",";
			}
		
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
	    return converted.substring(0, converted.length()-1);
	}

}
