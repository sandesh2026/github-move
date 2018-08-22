package com.hts.smf.report.beans;

public class BLServicewiseReportBean {
	private String services;
	private String sla;
	private long serviceTotal;
	private String ticketTypes;
	private double avgTAT;
	private double avgEffort;
	private double avgProductivity;
	private long noOfCrossedSLA;
	private long resolved;
	private long rejected;
	private long open;	
	private long budgetAllocation;
	private String issueStatus;
	private String hldTicketNo;
	private String title;
	private double estimatedEffort;
	private double sapEffort;
	private double costPerformanceIndex;
	private String actualCompletionDate;
	private long tat;
	private double remainingDays;
	private long allotedBudget;
	private long targetEarnings;
	private long totalBudgetUtilization;
	private long budgetUtilizationforDateRange;
	
	public String getServices() {
		return services;
	}
	
	public void setServices(String services) {
		this.services = services;
	}
	public long getServiceTotal() {
		return serviceTotal;
	}
	public void setServiceTotal(long serviceTotal) {
		this.serviceTotal = serviceTotal;
	}
	public long getResolved() {
		return resolved;
	}
	public void setResolved(long resolved) {
		this.resolved = resolved;
	}
	public long getRejected() {
		return rejected;
	}
	public void setRejected(long rejected) {
		this.rejected = rejected;
	}
	public long getOpen() {
		return open;
	}
	public void setOpen(long open) {
		this.open = open;
	}
	public double getAvgTAT() {
		return avgTAT;
	}
	public void setAvgTAT(double avgTAT) {
		this.avgTAT = avgTAT;
	}
	public String getTicketTypes() {
		return ticketTypes;
	}
	public void setTicketTypes(String ticketTypes) {
		this.ticketTypes = ticketTypes;
	}
	public double getAvgEffort() {
		return avgEffort;
	}
	public void setAvgEffort(double avgEffort) {
		this.avgEffort = avgEffort;
	}
	public double getAvgProductivity() {
		return avgProductivity;
	}
	public void setAvgProductivity(double avgProductivity) {
		this.avgProductivity = avgProductivity;
	}
	public long getNoOfCrossedSLA() {
		return noOfCrossedSLA;
	}
	public void setNoOfCrossedSLA(long noOfCrossedSLA) {
		this.noOfCrossedSLA = noOfCrossedSLA;
	}
	public long getBudgetAllocation() {
		return budgetAllocation;
	}
	public void setBudgetAllocation(long budgetAllocation) {
		this.budgetAllocation = budgetAllocation;
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
	
	public double getCostPerformanceIndex() {
		return costPerformanceIndex;
	}
	public void setCostPerformanceIndex(double costPerformanceIndex) {
		this.costPerformanceIndex = costPerformanceIndex;
	}
	public String getActualCompletionDate() {
		return actualCompletionDate;
	}
	public void setActualCompletionDate(String actualCompletionDate) {
		this.actualCompletionDate = actualCompletionDate;
	}
	public long getTat() {
		return tat;
	}
	public void setTat(long tat) {
		this.tat = tat;
	}
	public double getRemainingDays() {
		return remainingDays;
	}
	public void setRemainingDays(double remainingDays) {
		this.remainingDays = remainingDays;
	}
	public String getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

	public String getSla() {
		return sla;
	}

	public void setSla(String sla) {
		this.sla = sla;
	}

	public long getAllotedBudget() {
		return allotedBudget;
	}

	public void setAllotedBudget(long allotedBudget) {
		this.allotedBudget = allotedBudget;
	}

	public long getTotalBudgetUtilization() {
		return totalBudgetUtilization;
	}

	public void setTotalBudgetUtilization(long totalBudgetUtilization) {
		this.totalBudgetUtilization = totalBudgetUtilization;
	}

	public long getBudgetUtilizationforDateRange() {
		return budgetUtilizationforDateRange;
	}

	public void setBudgetUtilizationforDateRange(long budgetUtilizationforDateRange) {
		this.budgetUtilizationforDateRange = budgetUtilizationforDateRange;
	}

	public long getTargetEarnings() {
		return targetEarnings;
	}

	public void setTargetEarnings(long targetEarnings) {
		this.targetEarnings = targetEarnings;
	}

	public double getSapEffort() {
		return sapEffort;
	}

	public void setSapEffort(double sapEffort) {
		this.sapEffort = sapEffort;
	}	
}
