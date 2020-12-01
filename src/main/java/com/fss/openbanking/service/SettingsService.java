package com.fss.openbanking.service;

import java.util.List;

import com.fss.openbanking.bean.ConsentTokenDetails;
import com.fss.openbanking.bean.InstitutionDetails;
import com.fss.openbanking.bean.TppConsentForm;
import com.fss.openbanking.bean.TppConsentResponse;
import com.fss.openbanking.bean.UserDetails;

public interface SettingsService {

	List<InstitutionDetails> fetchInstDetails(String userId);

	TppConsentResponse processTppConsentResponse(UserDetails userDetails, TppConsentForm tppConsentForm);

	List<ConsentTokenDetails> fetchExistingConsentDetails(UserDetails userDetails);

	TppConsentResponse revokeConsent(String userId, TppConsentForm tppConsentForm);

	TppConsentResponse processAccountTokenConsent(UserDetails userDetails, TppConsentForm tppConsentForm, String token);

	TppConsentResponse processPaymentTokenConsent(UserDetails userDetails, TppConsentForm tppConsentForm, String token);

	TppConsentResponse fetchfetchActivitiesDetails(UserDetails userDetails);

}
