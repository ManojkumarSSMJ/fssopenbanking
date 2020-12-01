package com.fss.openbanking.bean;

public class CreateResponseData {

	private String uuid;
	private String applicationUuid;
	private String applicationUserId;
	private String referenceId;
	private String[] institutionConsents;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getApplicationUuid() {
		return applicationUuid;
	}

	public void setApplicationUuid(String applicationUuid) {
		this.applicationUuid = applicationUuid;
	}

	public String getApplicationUserId() {
		return applicationUserId;
	}

	public void setApplicationUserId(String applicationUserId) {
		this.applicationUserId = applicationUserId;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String[] getInstitutionConsents() {
		return institutionConsents;
	}

	public void setInstitutionConsents(String[] institutionConsents) {
		this.institutionConsents = institutionConsents;
	}

}
