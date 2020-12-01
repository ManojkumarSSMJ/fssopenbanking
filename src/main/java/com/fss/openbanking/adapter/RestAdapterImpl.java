package com.fss.openbanking.adapter;

import static org.asynchttpclient.Dsl.asyncHttpClient;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.http.entity.ContentType;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fss.openbanking.bean.AccountAdapter;
import com.fss.openbanking.bean.AdapterResponse;
import com.fss.openbanking.constants.TppConstants;

import okhttp3.OkHttpClient;
import okhttp3.Request;

@Service("restAdapter")
public class RestAdapterImpl implements RestAdapter {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(RestAdapterImpl.class);
	
	private AsyncHttpClient  fundTransferAsyncClient;
	
	private AsyncHttpClient  consentAsyncClient;
	
	private AsyncHttpClient  payAuthAsyncClient;
	
	private AsyncHttpClient  payAsyncClient;
	
	private AsyncHttpClient  institutionAsyncClient;
	
	 @PostConstruct
	 public void init() {
		 LOGGER.info("Init");
		 
		 DefaultAsyncHttpClientConfig asyncFundTransferConfig = 
					new DefaultAsyncHttpClientConfig.Builder().setMaxConnections(500)
							.setMaxConnectionsPerHost(200)
							.setMaxConnections(600)
							.setPooledConnectionIdleTimeout(100).setConnectionTtl(500)
							.setConnectTimeout(30000)
							.setReadTimeout(30000).build();
		 fundTransferAsyncClient = asyncHttpClient(asyncFundTransferConfig);	
		 
		 DefaultAsyncHttpClientConfig asyncConsentConfig = 
					new DefaultAsyncHttpClientConfig.Builder().setMaxConnections(500)
							.setMaxConnectionsPerHost(200)
							.setMaxConnections(600)
							.setPooledConnectionIdleTimeout(100).setConnectionTtl(500)
							.setConnectTimeout(30000)
							.setReadTimeout(30000).build();
		 consentAsyncClient = asyncHttpClient(asyncConsentConfig);	
		 
		 DefaultAsyncHttpClientConfig asyncPayAuthConfig = 
					new DefaultAsyncHttpClientConfig.Builder().setMaxConnections(500)
							.setMaxConnectionsPerHost(200)
							.setMaxConnections(600)
							.setPooledConnectionIdleTimeout(100).setConnectionTtl(500)
							.setConnectTimeout(30000)
							.setReadTimeout(30000).build();
		 payAuthAsyncClient = asyncHttpClient(asyncPayAuthConfig);	
		 
		 DefaultAsyncHttpClientConfig asyncPayConfig = 
					new DefaultAsyncHttpClientConfig.Builder().setMaxConnections(500)
							.setMaxConnectionsPerHost(200)
							.setMaxConnections(600)
							.setPooledConnectionIdleTimeout(100).setConnectionTtl(500)
							.setConnectTimeout(30000)
							.setReadTimeout(30000).build();
		 payAsyncClient = asyncHttpClient(asyncPayConfig);	
		 
		 DefaultAsyncHttpClientConfig asyncInstitutionConfig = 
					new DefaultAsyncHttpClientConfig.Builder().setMaxConnections(500)
							.setMaxConnectionsPerHost(200)
							.setMaxConnections(600)
							.setPooledConnectionIdleTimeout(100).setConnectionTtl(500)
							.setConnectTimeout(30000)
							.setReadTimeout(30000).build();
		 institutionAsyncClient = asyncHttpClient(asyncInstitutionConfig);	
		 
	 }
	 
	 
	 
	 
	 @PreDestroy
	 public void destory() {
		 LOGGER.info("Destroy");
		
			try {
				 if(payAuthAsyncClient != null)
					 payAuthAsyncClient.close();
				 if(payAsyncClient != null)
					 payAsyncClient.close();
				 if(institutionAsyncClient != null)
					 institutionAsyncClient.close();
				 if(fundTransferAsyncClient != null)
					 fundTransferAsyncClient.close();
				 if(consentAsyncClient != null)
					 consentAsyncClient.close();
			} catch (IOException e) {
				LOGGER.error("catch block");
				LOGGER.error("Failed!", e);
			}
	 }
	 
	 
	@Override
	public AdapterResponse callAccountsDetailsApi(AccountAdapter accountAdapter) {
		AdapterResponse adapterResponse = new AdapterResponse();
		try {
			
			OkHttpClient client = new OkHttpClient.Builder()
				      .connectTimeout(30000, TimeUnit.MILLISECONDS)
				      .build();
			
			 Request request = new Request.Builder()
			     .url(accountAdapter.getUrl()).addHeader("Consent", accountAdapter.getToken())
			     .addHeader(TppConstants.headerAuthorization, "Basic NDdiOTdjZTktNTcwYS00Mjc5LTg0MjQtZWFiNmIwNGFlYjFkOmUwYTlhM2FhLTQ1ZDMtNDAxNC1iYmFhLTBlNzlhZDA5ZTQ1OQ==")
			     .addHeader("User-Agent","OkHttp Bot") .get() .build();
			 
			 okhttp3.Response response = client.newCall(request).execute();
			
			if(response != null) {
				
				  adapterResponse.setStatusCode(response.code()+"");
				  adapterResponse.setData(response.body().string());
				 
				LOGGER.info("Response Code "+adapterResponse.getStatusCode()+" Data "+adapterResponse.getData());
				
				if(!"200".equals(adapterResponse.getStatusCode()))
				{
					JSONObject responseJSONObject = new JSONObject(adapterResponse.getData());
					JSONObject responseJSON= new JSONObject(responseJSONObject.get("error").toString());
					adapterResponse.setStatusCode(adapterResponse.getStatusCode());
					adapterResponse.setStatusMsg(responseJSON.getString("message").split("\\.")[0]);
					adapterResponse.setStatusFlag(TppConstants.failure);
				}
			}
		} catch(SocketTimeoutException e) {
			 adapterResponse.setStatusCode(TppConstants.timeoutCode);
			 adapterResponse.setStatusMsg(TppConstants.timeoutMsg);
			 adapterResponse.setStatusFlag(TppConstants.timeout);
		}
		catch(UnknownHostException e) {
			 adapterResponse.setStatusCode(TppConstants.failureCode);
			 adapterResponse.setStatusMsg("Unknown Host Exception");
			 adapterResponse.setStatusFlag(TppConstants.failure);
		}
		catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return adapterResponse;
	}

	@Override
	public AdapterResponse callFundTransferConsent(AccountAdapter accountAdapter) {
		AdapterResponse adapterResponse = new AdapterResponse();
		try {
			JSONObject fundTransferRequest = new JSONObject();
			fundTransferRequest.put("amount", accountAdapter.getAmount());
			fundTransferRequest.put("remarks", accountAdapter.getRemarks());
			fundTransferRequest.put("beneficiaryAccountNumber", accountAdapter.getBeneficiaryAccountNumber());
			fundTransferRequest.put("beneficiaryName", accountAdapter.getBeneficiaryName());
		    fundTransferRequest.put("remitterAccountNumber", accountAdapter.getRemitterAccountNumber());
		    LOGGER.info(fundTransferRequest.toString());
			Response response = fundTransferAsyncClient.preparePost(accountAdapter.getUrl())
					.setBody(fundTransferRequest.toString())
					.addHeader("Content-Type", ContentType.APPLICATION_JSON)
					.addHeader(TppConstants.headerAuthorization, "Bearer "+accountAdapter.getToken())
					.execute()
					.get();
			if(response != null) {
				adapterResponse.setStatusCode(response.getStatusCode()+"");
				adapterResponse.setData(response.getResponseBody());
				LOGGER.info("Response Code "+adapterResponse.getStatusCode()+" Data "+adapterResponse.getData());
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return adapterResponse;
	}


	@Override
	public AdapterResponse callConsentInitiation(AccountAdapter accountAdapter) {
		AdapterResponse adapterResponse = new AdapterResponse();
		try {	
			
			JSONObject requestJSONObject = new JSONObject();
			
			requestJSONObject.put("userUuid", accountAdapter.getUserUuid());
			requestJSONObject.put("institutionId", accountAdapter.getInstitutionId());
			requestJSONObject.put("callback", accountAdapter.getCallback());
			
			JSONObject accountReq = new JSONObject();
			accountReq.put("expiresAt", accountAdapter.getAccountRequest().getExpiresAt());
			accountReq.put("transactionFrom", accountAdapter.getAccountRequest().getTransactionFrom());
			accountReq.put("transactionTo", accountAdapter.getAccountRequest().getTransactionTo());
			
			JSONArray featureScope = new JSONArray();
			for(String features : accountAdapter.getAccountRequest().getFeatureScope())
				featureScope.put(features);
			
			accountReq.put("featureScope", featureScope);

			
			requestJSONObject.put("accountRequest", accountReq);
			
			
			LOGGER.info("Consent Request "+requestJSONObject.toString());
			
			Response response = consentAsyncClient.preparePost(accountAdapter.getUrl())
					.setBody(requestJSONObject.toString())
					.addHeader("Content-Type", ContentType.APPLICATION_JSON)
					.addHeader(TppConstants.headerAuthorization, "Basic NDdiOTdjZTktNTcwYS00Mjc5LTg0MjQtZWFiNmIwNGFlYjFkOmUwYTlhM2FhLTQ1ZDMtNDAxNC1iYmFhLTBlNzlhZDA5ZTQ1OQ==")
					.execute()
					.get();
			if(response != null) {
				adapterResponse.setStatusCode(response.getStatusCode()+"");
				adapterResponse.setData(response.getResponseBody());
				LOGGER.info("Response Code "+adapterResponse.getStatusCode()+" Data "+adapterResponse.getData());
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return adapterResponse;
	}




	@Override
	public AdapterResponse callFetchConsent(AccountAdapter accountAdapter) {
		AdapterResponse adapterResponse = new AdapterResponse();
		try {
			LOGGER.info(accountAdapter.getUrl());
			Response response = consentAsyncClient.prepareGet(accountAdapter.getUrl()).addHeader(TppConstants.headerAuthorization, "Bearer obc").execute().get();
			if(response != null) {
				adapterResponse.setStatusCode(response.getStatusCode()+"");
				adapterResponse.setData(response.getResponseBody());
				LOGGER.info("Response Code "+adapterResponse.getStatusCode()+" Data "+adapterResponse.getData());
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return adapterResponse;
	}




	@Override
	public AdapterResponse callDeleteConsent(AccountAdapter accountAdapter) {
		AdapterResponse adapterResponse = new AdapterResponse();
		try {
			LOGGER.info(accountAdapter.getUrl());
			Response response = consentAsyncClient.prepareDelete(accountAdapter.getUrl()).addHeader(TppConstants.headerAuthorization, "Basic NDdiOTdjZTktNTcwYS00Mjc5LTg0MjQtZWFiNmIwNGFlYjFkOmUwYTlhM2FhLTQ1ZDMtNDAxNC1iYmFhLTBlNzlhZDA5ZTQ1OQ==").execute().get();
			if(response != null) {
				adapterResponse.setStatusCode(response.getStatusCode()+"");
				adapterResponse.setData(response.getResponseBody());
				LOGGER.info("Response Code "+adapterResponse.getStatusCode()+" Data "+adapterResponse.getData());
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return adapterResponse;
	}

	void invokeSSL() {
		
	}

	@Override
	public AdapterResponse callPayAuthorization(AccountAdapter accountAdapter, JSONObject payAuthReq) {
		AdapterResponse adapterResponse = new AdapterResponse();
		try {
			Response response = payAuthAsyncClient.preparePost(accountAdapter.getUrl())
					.setBody(payAuthReq.toString())
					.addHeader("Content-Type", ContentType.APPLICATION_JSON)
					.addHeader(TppConstants.headerAuthorization, "Basic NDdiOTdjZTktNTcwYS00Mjc5LTg0MjQtZWFiNmIwNGFlYjFkOmUwYTlhM2FhLTQ1ZDMtNDAxNC1iYmFhLTBlNzlhZDA5ZTQ1OQ==")
					.execute()
					.get();
			if(response != null) {
				adapterResponse.setStatusCode(response.getStatusCode()+"");
				adapterResponse.setData(response.getResponseBody());
				LOGGER.info("Response Code "+adapterResponse.getStatusCode()+" Data "+adapterResponse.getData());
			}
	} catch(Exception e) {
		LOGGER.error("catch block");
		LOGGER.error("Failed!", e);
	}
	return adapterResponse;
	}




	@Override
	public AdapterResponse callPayment(AccountAdapter accountAdapter) {
		AdapterResponse adapterResponse = new AdapterResponse();
		try {
			Response response = payAsyncClient.preparePost(accountAdapter.getUrl())
					.setBody(accountAdapter.getPaymentData())
					.addHeader("Content-Type", ContentType.APPLICATION_JSON)
					.addHeader("Consent", accountAdapter.getToken())
					.addHeader(TppConstants.headerAuthorization, "Basic NDdiOTdjZTktNTcwYS00Mjc5LTg0MjQtZWFiNmIwNGFlYjFkOmUwYTlhM2FhLTQ1ZDMtNDAxNC1iYmFhLTBlNzlhZDA5ZTQ1OQ==").execute().get();
			if(response != null) {
				adapterResponse.setStatusCode(response.getStatusCode()+"");
				adapterResponse.setData(response.getResponseBody());
				LOGGER.info("Response Code "+adapterResponse.getStatusCode()+" Data "+adapterResponse.getData());
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return adapterResponse;
	}




	@Override
	public AdapterResponse callInstitutions(AccountAdapter accountAdapter) {
		AdapterResponse adapterResponse = new AdapterResponse();
		try {
			Response response = institutionAsyncClient.prepareGet(accountAdapter.getUrl())
					.addHeader(TppConstants.headerAuthorization, "Basic NDdiOTdjZTktNTcwYS00Mjc5LTg0MjQtZWFiNmIwNGFlYjFkOmUwYTlhM2FhLTQ1ZDMtNDAxNC1iYmFhLTBlNzlhZDA5ZTQ1OQ==").execute().get();
			if(response != null) {
				adapterResponse.setStatusCode(response.getStatusCode()+"");
				adapterResponse.setData(response.getResponseBody());
				LOGGER.info("Response Code "+adapterResponse.getStatusCode()+" Data "+adapterResponse.getData());
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return adapterResponse;
	}




	@Override
	public AdapterResponse callCreateUser(AccountAdapter accountAdapter) {
		AdapterResponse adapterResponse = new AdapterResponse();
		try {	
			
			JSONObject requestJSONObject = new JSONObject();
			
			requestJSONObject.put("applicationUserId", accountAdapter.getMobileNumber());
			requestJSONObject.put("referenceId", accountAdapter.getCustomerName());
			
			LOGGER.info("Consent Request "+requestJSONObject.toString());
			
			Response response = consentAsyncClient.preparePost(accountAdapter.getUrl())
					.setBody(requestJSONObject.toString())
					.addHeader("Content-Type", ContentType.APPLICATION_JSON)
					.addHeader(TppConstants.headerAuthorization, "Basic NDdiOTdjZTktNTcwYS00Mjc5LTg0MjQtZWFiNmIwNGFlYjFkOmUwYTlhM2FhLTQ1ZDMtNDAxNC1iYmFhLTBlNzlhZDA5ZTQ1OQ==")
					.execute()
					.get();
			if(response != null) {
				adapterResponse.setStatusCode(response.getStatusCode()+"");
				adapterResponse.setData(response.getResponseBody());
				LOGGER.info("Response Code "+adapterResponse.getStatusCode()+" Data "+adapterResponse.getData());
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return adapterResponse;
	}
}
