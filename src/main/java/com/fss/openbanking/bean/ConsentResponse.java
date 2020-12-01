/**
 * 
 */
package com.fss.openbanking.bean;

/**
 * @author selvakumara
 *
 */
public class ConsentResponse {
	
	private String id;
	private String userUuid;
	private String applicationUserId;
	private String referenceId;
	private String institutionId;
	private String status;
	private String createdAt;
	private String expiresAt;
	private String[] featureScope;
	private String authorisationUrl;
	private String qrCodeUrl;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
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
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String[] getFeatureScope() {
		return featureScope;
	}
	public void setFeatureScope(String[] featureScope) {
		this.featureScope = featureScope;
	}
	public String getAuthorisationUrl() {
		return authorisationUrl;
	}
	public void setAuthorisationUrl(String authorisationUrl) {
		this.authorisationUrl = authorisationUrl;
	}
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	public String getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(String expiresAt) {
		this.expiresAt = expiresAt;
	}
}
