<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.hts.smf.report.action.ListingAction" %>
<%@taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ page buffer = "50kb" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>
     <body bgcolor="#f0f0f0"><% 
     	String usr=request.getSession().getAttribute("usr").toString();
     out.println(usr);
     %>
    <div class="maxWidth bgBorder">
        <div class="colHeaderLink portletLoginHeader">
    <center class="headercell" style="background-color: #3c78b5"><h2>Service Management Framework</h2></center>
    <font size="3" face="arial" color="RED"> Click <%out.println("<a href='http://servwell.honeywell.com/SLAReport/index.jsp?usr="+usr+"'>BACK </a>");%>  To Get New Date Range Results</font>  
     <h2><s:property value="%{select}"/> Results From: <s:property  value="%{fromDate}"/> to <s:property  value="%{toDate}"/></h2>      
	  <h2>CPI Report For Resolved Projects</h2>  
     <s:set  value="resResultList" scope="request" name="resResultList"/>
        <display:table export="true" name="resResultList" uid="data" requestURI="" pagesize="10">
            <display:column property="services" title="SBG" sortable="true"  />
            <display:column property="ticketTypes" title="TicketType" sortable="true"  />
            <display:column property="hldTicketNo" title="HLDTicketNo" sortable="true"  />
            <display:column property="actualCompletionDate" title="ActualCompletionDate" sortable="true"  />
            <display:column property="issueStatus" title="IssueStatus" sortable="true"  />
            <display:column property="estimatedEffort" title="EstimatedEffort" sortable="true"  />
            <display:column property="sapEffort" title="Effort Spent in(SAP)" sortable="true"  />
            <display:column property="costPerformanceIndex" title="CostPerformanceIndex" sortable="true"  />                            
        </display:table>      
             
	<h2>SLA Report For Resolved Projects</h2> 
	<s:set value="blServicewiseData" scope="request" name="blServicewiseData"/>
        <display:table export="true" name="blServicewiseData" uid="row" requestURI="" pagesize="25">        
            <display:column property="services" title="SBG" sortable="true" />
            <display:column property="ticketTypes" title="TicketTypes" sortable="true"  />
            <display:column property="resolved" title="Closed Projects" sortable="true"  />
            <display:column property="avgTAT" title="Avg TAT(In Hrs)" sortable="true"  />
            <display:column property="avgEffort" title="Avg Effort" sortable="true"  />
            <display:column property="avgProductivity" title="Avg Productivity" sortable="true"  />
            <display:column property="noOfCrossedSLA" title="SLA Crossed" sortable="true"  /> 		                         
        </display:table> 
        
        <h2>Earning Report</h2> 
	<s:set value="earningServiceWiseData" scope="request" name="earningServiceWiseData"/>
        <display:table export="true" name="earningServiceWiseData" uid="row1" requestURI="" pagesize="9">        
            <display:column property="services" title="SBG" sortable="true" />
            <display:column property="allotedBudget" title="AllotedBudget" sortable="true"  />
            <display:column property="targetEarnings" title="TargetEarnings" sortable="true"  />
            <display:column property="totalBudgetUtilization" title="TotalBudgetUtilization" sortable="true"  />
            <display:column property="budgetUtilizationforDateRange" title="BudgetUtilizationforDateRange" sortable="true"  />            		                         
        </display:table> 
     
     
      <h2>CPI Report For Work In Progress/Assigned Projects</h2>   
        <s:set value="wipResultList" scope="request" name="wipResultList"/>
        <display:table export="true" name="wipResultList" uid="data2" requestURI="" pagesize="10">
            <display:column property="services" title="SBG" sortable="true"  />
            <display:column property="ticketTypes" title="TicketType" sortable="true"  />
            <display:column property="hldTicketNo" title="HLDTicketNo" sortable="true"  />
            <display:column property="issueStatus" title="IssueStatus" sortable="true"  />
            <display:column property="estimatedEffort" title="EstimatedEffort" sortable="true"  />
            <display:column property="sapEffort" title="Effort spent (SAP)" sortable="true"  />
            <display:column property="remainingDays" title="Remaining Hrs" sortable="true"  />            
            <display:column property="costPerformanceIndex" title="CostPerformanceIndex" sortable="true" />
            <display:column property="tat" title="TAT" sortable="true" />       
            <display:column property="sla" title="SLA" sortable="true"/>                                                 
        </display:table>  
    
     
     <h2>Remaining Hours For WIP/Assignned Projects</h2>
      <s:set value="openTicketsServiceWiseData" scope="request" name="openTicketsServiceWiseData"/>
        <display:table export="true" name="openTicketsServiceWiseData" uid="row3" requestURI="" pagesize="9">        
            <display:column property="services" title="SBG" sortable="true" />
            <display:column property="ticketTypes" title="TicketTypes" sortable="true"  />
            <display:column property="open" title="Open" sortable="true"  />
            <display:column property="remainingDays" title="RemainingHrs" sortable="true"  /> 		                         
        </display:table> 
   </body>
</html>