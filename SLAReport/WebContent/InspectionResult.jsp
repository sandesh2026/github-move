<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page language="java" buffer = "16kb" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>
     <body bgcolor="#f0f0f0">
    <div class="maxWidth bgBorder">
        <div class="colHeaderLink portletLoginHeader">
    <center class="headercell" style="background-color: #3c78b5"><h2>Service Management Framework</h2></center>
    <%-- <font size="3" face="arial" color="RED"> Click <a href="http://servwell.honeywell.com/SLAReport"> BACK </a> To Get New Date Range Results</font> --%> 
     <h2><s:property value="%{select}"/> <s:property  value="%{ticketType}"/> Results From: <s:property  value="%{fromDate}"/> to <s:property  value="%{toDate}"/></h2>
     <%-- <s:set  value="servicewiseData" scope="request" name="servicewiseData"/>
        <display:table export="true" name="servicewiseData" uid="row" requestURI="" pagesize="20">
            <display:column property="services" title="Services" sortable="true" />
            <display:column property="cat1" title="Cat1" sortable="true"  />
            <display:column property="cat2" title="Cat2" sortable="true"  />
            <display:column property="cat3" title="Cat3" sortable="true"  />
            <display:column property="cat4" title="Cat4" sortable="true"  />
            <display:column property="cat5" title="Cat5" sortable="true"  />
            <display:column property="cat6" title="Cat6" sortable="true"  />
            <display:column property="cat7" title="Cat7" sortable="true"  />
            <display:column property="cat8" title="Cat8" sortable="true"  />
            <display:column property="cat9" title="Cat9" sortable="true"  />
            <display:column property="cat10" title="Cat10" sortable="true"  />
            <display:column property="cat11" title="Cat11" sortable="true"  />
            <display:column property="cat12" title="Cat12" sortable="true"  />
            <display:column property="nonStandard" title="NonStandard" sortable="true"  />
            <display:column property="serviceTotal" title="Service Total" sortable="true"  />
            <display:column property="resolved" title="Resolved" sortable="true"  />
            <display:column property="rejected" title="Rejected" sortable="true"  />
            <display:column property="open" title="Open" sortable="true"  />
            <display:column property="slaComplaince" title="%SLA Compliance" sortable="true"  />
            <display:column property="avgResolutionTime" title="Avg Resolution Time" sortable="true"  />                    
        </display:table> --%>   
     
     <s:set  value="resultList" scope="request" name="resultList"/>
        
        <display:table export="true" name="resultList" uid="data" requestURI="" pagesize="100">
            
            <display:column property="issueKey" title="IssueKey" sortable="true" />
            <display:column property="issueServiceName" title="ServiceName" sortable="true"  />
            <display:column property="issueBusinessUnit" title="BusinessUnit" sortable="true"  />
            <display:column property="issueDescription" title="Description" sortable="true" maxWords="5" />
            <display:column property="issueLocation" title="Location" sortable="true"  />            
            <display:column property="issueReOpenReason" title="ReasonForReOpen" sortable="true"  />
            <display:column property="issuePriority" title="Priority" sortable="true"  />            
            <display:column property="issueAssignee" title="Assignee" sortable="true"  />
            <display:column property="issueOpenDate" title="IssueOpenDate" sortable="true"  />
            <display:column property="issueAssignedDate" title="AssignedDate" sortable="true"  />
            <display:column property="issueResolvedDate" title="ResolvedDate" sortable="true"  />
            <display:column property="issueStatus" title="IssueStatus" sortable="true"  />
            <display:column property="responseTime" title="Response" sortable="true"  />
            <display:column property="resolutionTime" title="Resolution" sortable="true"  />
            <display:column property="onHoldTime" title="OnHold" sortable="true"  />
            <display:column property="responseSLA" title="ResponseSLA" sortable="true"  />
            <display:column property="resolutionSLA" title="ResolutionSLA" sortable="true"  /> 
                          
        </display:table>  
    
   </body>
</html>