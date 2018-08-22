package com.hts.smf.report.beans;

import java.io.Serializable;


public class ResultBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String issueKey;
	private String issueServiceName;
	private String issueBusinessUnit;
	private String issueLocation;
	private String issueProcurementType;
	private String issuePriority;
	private String issueAssignee;
	private String issueAssigneeEID;
	private String issueDescription;
	private String issueReOpenReason;
	private String issueRejectReason;
	private String issueOpenDate;
	private String issueAssignedDate;
	private String issueResolvedDate;
	private String issueStatus;
	private long responseTime;
	private long resolutionTime;
	private long onHoldTime;
	private String responseSLA;
	private String resolutionSLA;
	private double issueAgeing;
	private String ticketType;
	private String hldTicketNo;
	private String title;
	private double estimatedEffort;
	private double sapEffort;
	private double costPerformanceIndex;
	private String milestoneValue;	
	private String actualCompletionDate;
	private long tat;
	private double remainingDays;
	private long issueTargetSLA;
	private String issueReporter;
	private long vendorIdentificationMin;
	private long bidCompNegoMin;
	private long vendorRegMin;
	private long clearanceMin;
	private long wtngImanyReqMin;
	private long termsheetRevMin;
	private long approveMin;
	private long firstDraftMin;
	private long reqReviewMin;
	private long procReviewMin;
	private long vendorReviewMin;
	private long vendorAcceptMin;
	private long CCFMin;
	private long execContractMin;
	private long bulSignMin;
	private long onHoldTimeinMinutes;
	private long resolvedTimeInMinutes;	
	private long totalTimeInMinutes;
	private String tstfield;
	private String units;
	private String expResTime;
			
	private String vendorIdentificationSLA;
	private String bidCompNegoSLA;
	private String vendorRegSLA;
	private String clearanceSLA;
	private String wtngImanyReqSLA;
	private String termsheetRevSLA;
	private String approveSLA;
	private String firstDraftSLA;
	private String reqReviewSLA;
	private String procReviewSLA;
	private String vendorReviewSLA;
	private String vendorAcceptSLA;
	private String CCFSLA;
	private String execContractSLA;
	private String bulSignSLA;
	
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
	private String priority;
	private String urgency;
	private String lastResolvedDate;
	private String lastModifiedDate;
	private String lastModifiedBy;
	private String status;
	private String reportedSource;
	private String ownerGroup;
	private String owner;
	private String assignedGroup;
	private String assignee;
	private String resolution;
	private String reopenedDate;
	private String reportedDate;
	private String projectName;
	private String subServiceName;
	private long wifTimeinMinutes;
	
	private String networkNumber;
	private String activityNumber;
	private String ioNumber;
	private String ccNumber;
	
	private String commodity;
	private String reporter;
	private String siteName;
	private String costCenterGroupOne;
	private String costCenterGroupTwo;
	
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
	private String businessUnit;
	
	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

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

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
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

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getIssueReporter() {
		return issueReporter;
	}

	public void setIssueReporter(String issueReporter) {
		this.issueReporter = issueReporter;
	}

	public long getIssueTargetSLA() {
		return issueTargetSLA;
	}

	public void setIssueTargetSLA(long issueTargetSLA) {
		this.issueTargetSLA = issueTargetSLA;
	}

	public double getRemainingDays() {
		return remainingDays;
	}
	
	public void setRemainingDays(double remainingDays) {
		this.remainingDays = remainingDays;
	}
	
	public long getTat() {
		return tat;
	}
	
	public void setTat(long tat) {
		this.tat = tat;
	}
	
	public String getTicketType() {
		return ticketType;
	}
	
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	
	public String getHldTicketNo() {
		return hldTicketNo;
	}
	
	public void setHldTicketNo(String hldTicketNo) {
		this.hldTicketNo = hldTicketNo;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public double getEstimatedEffort() {
		return estimatedEffort;
	}
	
	public void setEstimatedEffort(double estimatedEffort) {
		this.estimatedEffort = estimatedEffort;
	}
	
	public double getSAPEffort() {
		return sapEffort;
	}
	
	public void setSAPEffort(double sapEffort) {
		this.sapEffort = sapEffort;
	}
	
	public double getCostPerformanceIndex() {
		return costPerformanceIndex;
	}
	
	public void setCostPerformanceIndex(double costPerformanceIndex) {
		this.costPerformanceIndex = costPerformanceIndex;
	}
	
	public ResultBean(){		
	}
	
	public String getIssueKey() {
		return issueKey;
	}
	
	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}
	
	public String getIssueServiceName() {
		return issueServiceName;
	}
	
	public void setIssueServiceName(String issueServiceName) {
		this.issueServiceName = issueServiceName;
	}
	
	public String getIssuePriority() {
		return issuePriority;
	}
	
	public void setIssuePriority(String issuePriority) {
		this.issuePriority = issuePriority;
	}
	
	public String getIssueOpenDate() {
		return issueOpenDate;
	}
	
	public void setIssueOpenDate(String issueOpenDate) {
		this.issueOpenDate = issueOpenDate;
	}
	
	public String getIssueAssignedDate() {
		return issueAssignedDate;
	}
	
	public void setIssueAssignedDate(String issueAssignedDate) {
		this.issueAssignedDate = issueAssignedDate;
	}
	
	public String getIssueResolvedDate() {
		return issueResolvedDate;
	}
	
	public void setIssueResolvedDate(String issueResolvedDate) {
		this.issueResolvedDate = issueResolvedDate;
	}
	
	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTimeInMinutes) {
		this.responseTime = responseTimeInMinutes;
	}
	
	public long getResolutionTime() {
		return resolutionTime;
	}
	
	public void setResolutionTime(long resolutionTime) {
		this.resolutionTime = resolutionTime;
	}
	
	public String getResponseSLA() {
		return responseSLA;
	}
	
	public void setResponseSLA(String responseSLA) {
		this.responseSLA = responseSLA;
	}
	
	public String getResolutionSLA() {
		return resolutionSLA;
	}
	
	public void setResolutionSLA(String resolutionSLA) {
		this.resolutionSLA = resolutionSLA;
	}
	
	public long getOnHoldTime() {
		return onHoldTime;
	}
	
	public void setOnHoldTime(long onHoldTime) {
		this.onHoldTime = onHoldTime;
	}
	
	public String getIssueStatus() {
		return issueStatus;
	}
	
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getIssueBusinessUnit() {
		return issueBusinessUnit;
	}
	public void setIssueBusinessUnit(String issueBusinessUnit) {
		this.issueBusinessUnit = issueBusinessUnit;
	}
	public String getIssueLocation() {
		return issueLocation;
	}
	public void setIssueLocation(String issueLocation) {
		this.issueLocation = issueLocation;
	}
	public String getIssueAssignee() {
		return issueAssignee;
	}
	public void setIssueAssignee(String issueAssignee) {
		this.issueAssignee = issueAssignee;
	}
	public String getIssueProcurementType() {
		return issueProcurementType;
	}
	public void setIssueProcurementType(String issueProcurementType) {
		this.issueProcurementType = issueProcurementType;
	}
	public String getActualCompletionDate() {
		return actualCompletionDate;
	}
	public void setActualCompletionDate(String actualCompletionDate) {
		this.actualCompletionDate = actualCompletionDate;
	}
	public String getMilestoneValue() {
		return milestoneValue;
	}
	public void setMilestoneValue(String milestoneValue) {
		this.milestoneValue = milestoneValue;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public double getSapEffort() {
		return sapEffort;
	}

	public void setSapEffort(double sapEffort) {
		this.sapEffort = sapEffort;
	}

	public String getIssueReOpenReason() {
		return issueReOpenReason;
	}

	public void setIssueReOpenReason(String issueReOpenReason) {
		this.issueReOpenReason = issueReOpenReason;
	}

	public double getIssueAgeing() {
		return issueAgeing;
	}

	public void setIssueAgeing(double issueAgeing) {
		this.issueAgeing = issueAgeing;
	}

	public String getIssueRejectReason() {
		return issueRejectReason;
	}

	public void setIssueRejectReason(String issueRejectReason) {
		this.issueRejectReason = issueRejectReason;
	}

	public String getIssueAssigneeEID() {
		return issueAssigneeEID;
	}

	public void setIssueAssigneeEID(String issueAssigneeEID) {
		this.issueAssigneeEID = issueAssigneeEID;
	}

	public long getVendorIdentificationMin() {
		return vendorIdentificationMin;
	}

	public void setVendorIdentificationMin(long vendorIdentificationMin) {
		this.vendorIdentificationMin = vendorIdentificationMin;
	}

	public long getBidCompNegoMin() {
		return bidCompNegoMin;
	}

	public void setBidCompNegoMin(long bidCompNegoMin) {
		this.bidCompNegoMin = bidCompNegoMin;
	}

	public long getVendorRegMin() {
		return vendorRegMin;
	}

	public void setVendorRegMin(long vendorRegMin) {
		this.vendorRegMin = vendorRegMin;
	}

	public long getClearanceMin() {
		return clearanceMin;
	}

	public void setClearanceMin(long clearanceMin) {
		this.clearanceMin = clearanceMin;
	}

	public long getWtngImanyReqMin() {
		return wtngImanyReqMin;
	}

	public void setWtngImanyReqMin(long wtngImanyReqMin) {
		this.wtngImanyReqMin = wtngImanyReqMin;
	}

	public long getTermsheetRevMin() {
		return termsheetRevMin;
	}

	public void setTermsheetRevMin(long termsheetRevMin) {
		this.termsheetRevMin = termsheetRevMin;
	}

	public long getApproveMin() {
		return approveMin;
	}

	public void setApproveMin(long approveMin) {
		this.approveMin = approveMin;
	}

	public long getFirstDraftMin() {
		return firstDraftMin;
	}

	public void setFirstDraftMin(long firstDraftMin) {
		this.firstDraftMin = firstDraftMin;
	}

	public long getReqReviewMin() {
		return reqReviewMin;
	}

	public void setReqReviewMin(long reqReviewMin) {
		this.reqReviewMin = reqReviewMin;
	}

	public long getProcReviewMin() {
		return procReviewMin;
	}

	public void setProcReviewMin(long procReviewMin) {
		this.procReviewMin = procReviewMin;
	}

	public long getVendorReviewMin() {
		return vendorReviewMin;
	}

	public void setVendorReviewMin(long vendorReviewMin) {
		this.vendorReviewMin = vendorReviewMin;
	}

	public long getVendorAcceptMin() {
		return vendorAcceptMin;
	}

	public void setVendorAcceptMin(long vendorAcceptMin) {
		this.vendorAcceptMin = vendorAcceptMin;
	}

	public long getCCFMin() {
		return CCFMin;
	}

	public void setCCFMin(long min) {
		CCFMin = min;
	}

	public long getExecContractMin() {
		return execContractMin;
	}

	public void setExecContractMin(long execContractMin) {
		this.execContractMin = execContractMin;
	}

	public long getBulSignMin() {
		return bulSignMin;
	}

	public void setBulSignMin(long bulSignMin) {
		this.bulSignMin = bulSignMin;
	}

	public long getOnHoldTimeinMinutes() {
		return onHoldTimeinMinutes;
	}

	public void setOnHoldTimeinMinutes(long onHoldTimeinMinutes) {
		this.onHoldTimeinMinutes = onHoldTimeinMinutes;
	}

	public long getResolvedTimeInMinutes() {
		return resolvedTimeInMinutes;
	}

	public void setResolvedTimeInMinutes(long resolvedTimeInMinutes) {
		this.resolvedTimeInMinutes = resolvedTimeInMinutes;
	}

	public String getVendorIdentificationSLA() {
		return vendorIdentificationSLA;
	}

	public void setVendorIdentificationSLA(String vendorIdentificationSLA) {
		this.vendorIdentificationSLA = vendorIdentificationSLA;
	}

	public String getBidCompNegoSLA() {
		return bidCompNegoSLA;
	}

	public void setBidCompNegoSLA(String bidCompNegoSLA) {
		this.bidCompNegoSLA = bidCompNegoSLA;
	}

	public String getVendorRegSLA() {
		return vendorRegSLA;
	}

	public void setVendorRegSLA(String vendorRegSLA) {
		this.vendorRegSLA = vendorRegSLA;
	}

	public String getClearanceSLA() {
		return clearanceSLA;
	}

	public void setClearanceSLA(String clearanceSLA) {
		this.clearanceSLA = clearanceSLA;
	}

	public String getWtngImanyReqSLA() {
		return wtngImanyReqSLA;
	}

	public void setWtngImanyReqSLA(String wtngImanyReqSLA) {
		this.wtngImanyReqSLA = wtngImanyReqSLA;
	}

	public String getTermsheetRevSLA() {
		return termsheetRevSLA;
	}

	public void setTermsheetRevSLA(String termsheetRevSLA) {
		this.termsheetRevSLA = termsheetRevSLA;
	}

	public String getApproveSLA() {
		return approveSLA;
	}

	public void setApproveSLA(String approveSLA) {
		this.approveSLA = approveSLA;
	}

	public String getFirstDraftSLA() {
		return firstDraftSLA;
	}

	public void setFirstDraftSLA(String firstDraftSLA) {
		this.firstDraftSLA = firstDraftSLA;
	}

	public String getReqReviewSLA() {
		return reqReviewSLA;
	}

	public void setReqReviewSLA(String reqReviewSLA) {
		this.reqReviewSLA = reqReviewSLA;
	}

	public String getProcReviewSLA() {
		return procReviewSLA;
	}

	public void setProcReviewSLA(String procReviewSLA) {
		this.procReviewSLA = procReviewSLA;
	}

	public String getVendorReviewSLA() {
		return vendorReviewSLA;
	}

	public void setVendorReviewSLA(String vendorReviewSLA) {
		this.vendorReviewSLA = vendorReviewSLA;
	}

	public String getVendorAcceptSLA() {
		return vendorAcceptSLA;
	}

	public void setVendorAcceptSLA(String vendorAcceptSLA) {
		this.vendorAcceptSLA = vendorAcceptSLA;
	}

	public String getCCFSLA() {
		return CCFSLA;
	}

	public void setCCFSLA(String ccfsla) {
		CCFSLA = ccfsla;
	}

	public String getExecContractSLA() {
		return execContractSLA;
	}

	public void setExecContractSLA(String execContractSLA) {
		this.execContractSLA = execContractSLA;
	}

	public String getBulSignSLA() {
		return bulSignSLA;
	}

	public void setBulSignSLA(String bulSignSLA) {
		this.bulSignSLA = bulSignSLA;
	}

	public String getTstfield() {
		return tstfield;
	}

	public void setTstfield(String tstfield) {
		this.tstfield = tstfield;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getExpResTime() {
		return expResTime;
	}

	public void setExpResTime(String expResTime) {
		this.expResTime = expResTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSubServiceName() {
		return subServiceName;
	}

	public void setSubServiceName(String subServiceName) {
		this.subServiceName = subServiceName;
	}

	public long getWifTimeinMinutes() {
		return wifTimeinMinutes;
	}

	public void setWifTimeinMinutes(long wifTimeinMinutes) {
		this.wifTimeinMinutes = wifTimeinMinutes;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public long getTotalTimeInMinutes() {
		return totalTimeInMinutes;
	}

	public void setTotalTimeInMinutes(long totalTimeInMinutes) {
		this.totalTimeInMinutes = totalTimeInMinutes;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
}
