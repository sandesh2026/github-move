package com.hts.smf.report.beans;


public class IssuesBean {
	
	private String issueNo;
	private String issueOpenDateandTime;
	private String changeStatus;
	private String issueStatusChangeTime;
	private String priority;	
	private String serviceName;
	private String businessUnit;
	private String location;
	private String assignee;
	private String description;
	private String reasonForReopen;
	private String reasonForReject;
	private String procurementType;
	private String ticketType;	
	private String title;
	private String hldTicketNo;
	private double estimatedEffort;
	private String projectReceiptDate;
	private String effectiveProjectRecDate;
	private String requestedCompletionDate;
	private String plannedCompletionDate;
	private double sapEffort;
	private String actualCompletionDate;
	private String milestoneValue;
	private double remainingEffort;
	private String reporter;
	private String units;
	private String expectedResolutionTime;
	private String status;
	
	private String eid;
	
	
	private String incidentID;
	private String incidentType;
	private String notes;
	private String corporateID;
	private String summary;
	private String firstName;
	private String submitterName;
	private String productCategorizationTier1;
	private String productCategorizationTier2;
	private String productCategorizationTier3;
	private String productName;
	private String site;
	private String impact;
	
	private String urgency;
	private String lastResolvedDate;
	private String lastModifiedDate;
	private String lastModifiedBy;
	
	private String reportedSource;
	private String ownerGroup;
	private String owner;
	private String assignedGroup;
	
	private String resolution;
	private String reopenedDate;
	private String reportedDate;
	private String projectName;
	
	private String subServiceName;
	private String networkNumber;
	private String activityNumber;
	private String ioNumber;
	private String ccNumber;
	
	private String costCenterGroupOne;
	private String costCenterGroupTwo;
	private String siteName;
	private String commodity;
	
	//For OTD Automation
	private String dueDate;
	private String dueMonth;
	private String dueYear;
	private int passFailStatus;
	private int scheduleStatus;
	private String tatOffer;
	private String tatJoin;
	private String costCode;
	private String ccgName;
	private String rfpNum;
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDueMonth() {
		return dueMonth;
	}
	public void setDueMonth(String dueMonth) {
		this.dueMonth = dueMonth;
	}
	public String getDueYear() {
		return dueYear;
	}
	public void setDueYear(String dueYear) {
		this.dueYear = dueYear;
	}
	public int getPassFailStatus() {
		return passFailStatus;
	}
	public void setPassFailStatus(int passFailStatus) {
		this.passFailStatus = passFailStatus;
	}
	public int getScheduleStatus() {
		return scheduleStatus;
	}
	public void setScheduleStatus(int scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	public String getTatOffer() {
		return tatOffer;
	}
	public void setTatOffer(String tatOffer) {
		this.tatOffer = tatOffer;
	}
	public String getTatJoin() {
		return tatJoin;
	}
	public void setTatJoin(String tatJoin) {
		this.tatJoin = tatJoin;
	}
	public String getCostCode() {
		return costCode;
	}
	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}
	public String getCcgName() {
		return ccgName;
	}
	public void setCcgName(String ccgName) {
		this.ccgName = ccgName;
	}
	public String getRfpNum() {
		return rfpNum;
	}
	public void setRfpNum(String rfpNum) {
		this.rfpNum = rfpNum;
	}
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getNetworkNumber() {
		return networkNumber;
	}
	public void setNetworkNumber(String networkNumber) {
		this.networkNumber = networkNumber;
	}
	public String getActivityNumber() {
		return activityNumber;
	}
	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
	}
	public String getIoNumber() {
		return ioNumber;
	}
	public void setIoNumber(String ioNumber) {
		this.ioNumber = ioNumber;
	}
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getSubServiceName() {
		return subServiceName;
	}
	public void setSubServiceName(String subServiceName) {
		this.subServiceName = subServiceName;
	}
	public String getIncidentID() {
		return incidentID;
	}
	public void setIncidentID(String incidentID) {
		this.incidentID = incidentID;
	}
	public String getIncidentType() {
		return incidentType;
	}
	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getCorporateID() {
		return corporateID;
	}
	public void setCorporateID(String corporateID) {
		this.corporateID = corporateID;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSubmitterName() {
		return submitterName;
	}
	public void setSubmitterName(String submitterName) {
		this.submitterName = submitterName;
	}
	public String getProductCategorizationTier1() {
		return productCategorizationTier1;
	}
	public void setProductCategorizationTier1(String productCategorizationTier1) {
		this.productCategorizationTier1 = productCategorizationTier1;
	}
	public String getProductCategorizationTier2() {
		return productCategorizationTier2;
	}
	public void setProductCategorizationTier2(String productCategorizationTier2) {
		this.productCategorizationTier2 = productCategorizationTier2;
	}
	public String getProductCategorizationTier3() {
		return productCategorizationTier3;
	}
	public void setProductCategorizationTier3(String productCategorizationTier3) {
		this.productCategorizationTier3 = productCategorizationTier3;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getLastResolvedDate() {
		return lastResolvedDate;
	}
	public void setLastResolvedDate(String lastResolvedDate) {
		this.lastResolvedDate = lastResolvedDate;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public String getReportedSource() {
		return reportedSource;
	}
	public void setReportedSource(String reportedSource) {
		this.reportedSource = reportedSource;
	}
	public String getOwnerGroup() {
		return ownerGroup;
	}
	public void setOwnerGroup(String ownerGroup) {
		this.ownerGroup = ownerGroup;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getAssignedGroup() {
		return assignedGroup;
	}
	public void setAssignedGroup(String assignedGroup) {
		this.assignedGroup = assignedGroup;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getReopenedDate() {
		return reopenedDate;
	}
	public void setReopenedDate(String reopenedDate) {
		this.reopenedDate = reopenedDate;
	}
	public String getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExpectedResolutionTime() {
		return expectedResolutionTime;
	}
	public void setExpectedResolutionTime(String expectedResolutionTime) {
		this.expectedResolutionTime = expectedResolutionTime;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getMilestoneValue() {
		return milestoneValue;
	}
	public void setMilestoneValue(String milestoneValue) {
		this.milestoneValue = milestoneValue;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHldTicketNo() {
		return hldTicketNo;
	}
	public void setHldTicketNo(String hldTicketNo) {
		this.hldTicketNo = hldTicketNo;
	}
	public double getEstimatedEffort() {
		return estimatedEffort;
	}
	public void setEstimatedEffort(double sestimatedEfforttimatedEffort) {
		this.estimatedEffort = sestimatedEfforttimatedEffort;
	}
	public String getProjectReceiptDate() {
		return projectReceiptDate;
	}
	public void setProjectReceiptDate(String projectReceiptDate) {
		this.projectReceiptDate = projectReceiptDate;
	}
	public String getEffectiveProjectRecDate() {
		return effectiveProjectRecDate;
	}
	public void setEffectiveProjectRecDate(String effectiveProjectRecDate) {
		this.effectiveProjectRecDate = effectiveProjectRecDate;
	}
	public String getRequestedCompletionDate() {
		return requestedCompletionDate;
	}
	public void setRequestedCompletionDate(String requestedCompletionDate) {
		this.requestedCompletionDate = requestedCompletionDate;
	}
	public String getPlannedCompletionDate() {
		return plannedCompletionDate;
	}
	public void setPlannedCompletionDate(String plannedCompletionDate) {
		this.plannedCompletionDate = plannedCompletionDate;
	}
	public double getSAPEffort() {
		return sapEffort;
	}
	public void setSAPEffort(double sapEffort) {
		this.sapEffort = sapEffort;
	}
	public String getActualCompletionDate() {
		return actualCompletionDate;
	}
	public void setActualCompletionDate(String actualCompletionDate) {
		this.actualCompletionDate = actualCompletionDate;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getIssueOpenDateandTime() {
		return issueOpenDateandTime;
	}
	public void setIssueOpenDateandTime(String issueOpenDateandTime) {
		this.issueOpenDateandTime = issueOpenDateandTime;
	}
	public String getChangeStatus() {
		return changeStatus;
	}
	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus;
	}
	public String getIssueStatusChangeTime() {
		return issueStatusChangeTime;
	}
	public void setIssueStatusChangeTime(String issueStatusChangeTime) {
		this.issueStatusChangeTime = issueStatusChangeTime;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getProcurementType() {
		return procurementType;
	}
	public void setProcurementType(String procurementType) {
		this.procurementType = procurementType;
	}
	public double getRemainingEffort() {
		return remainingEffort;
	}
	public void setRemainingEffort(double remainingEffort) {
		this.remainingEffort = remainingEffort;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getSapEffort() {
		return sapEffort;
	}
	public void setSapEffort(double sapEffort) {
		this.sapEffort = sapEffort;
	}
	public String getReasonForReopen() {
		return reasonForReopen;
	}
	public void setReasonForReopen(String reasonForReopen) {
		this.reasonForReopen = reasonForReopen;
	}
	public String getReasonForReject() {
		return reasonForReject;
	}
	public void setReasonForReject(String reasonForReject) {
		this.reasonForReject = reasonForReject;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCostCenterGroupOne() {
		return costCenterGroupOne;
	}
	public void setCostCenterGroupOne(String costCenterGroupOne) {
		this.costCenterGroupOne = costCenterGroupOne;
	}
	public String getCostCenterGroupTwo() {
		return costCenterGroupTwo;
	}
	public void setCostCenterGroupTwo(String costCenterGroupTwo) {
		this.costCenterGroupTwo = costCenterGroupTwo;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}	
}
