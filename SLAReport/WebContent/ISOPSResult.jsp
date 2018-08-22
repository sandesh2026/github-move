<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" buffer = "16kb" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body bgcolor="#ffffff">
        <div class="maxWidth bgBorder">
        <div class="colHeaderLink portletLoginHeader">
    <center class="headercell" style="background-color: #3c78b5"><h2>Service Management Framework</h2></center>
    </div> 
    <%-- <font size="3" face="arial" color="RED"><% try{ %> Click <% 	String usr=request.getSession().getAttribute("usr").toString();%><% out.println("<a href='http://servwell.honeywell.com/SLAReport/index.jsp?usr="+usr+"'>BACK </a>");%>  To Get New Date Range Results <% }catch(Exception e){}%></font> --%>
     <h2><s:property value="%{select}"/> <s:property  value="%{ticketType}"/> Results From: <s:property  value="%{fromDate}"/> to <s:property  value="%{toDate}"/></h2>      
	
    <s:set  value="servicewiseData" scope="request" name="servicewiseData"/>
    	<display:table export="true" name="servicewiseData" uid="row" requestURI="" pagesize="50">
           <display:column property="services" title="Services" sortable="true" />
            <display:column property="s0" title="S0" sortable="true"  />
            <display:column property="avgResolutionTimeS0" title="avgResolutionTime_S0" sortable="true"  />
            <display:column property="s1" title="S1" sortable="true"  />
            <display:column property="avgResolutionTimeS1" title="avgResolutionTime_S1" sortable="true"  />
            <display:column property="s2" title="S2" sortable="true"  />
            <display:column property="avgResolutionTimeS2" title="avgResolutionTime_S2" sortable="true"  />
            <display:column property="s3" title="S3" sortable="true"  />
            <display:column property="avgResolutionTimeS3" title="avgResolutionTime_S3" sortable="true"  />
            <display:column property="s4" title="S4" sortable="true"  />
            <display:column property="avgResolutionTimeS4" title="avgResolutionTime_S4" sortable="true"  />
            <display:column property="serviceTotal" title="Service Total" sortable="true"  />
            <display:column property="resolved" title="Resolved" sortable="true"  />
            <display:column property="slaComplaince" title="%SLA Compliance" sortable="true"  />
            <display:column property="avgResolutionTime" title="Avg Resolution Time" sortable="true"  />                  
        </display:table>
    
     <s:set  value="resultList" scope="request" name="resultList"/>
        <display:table export="true" name="resultList" uid="data" requestURI="" pagesize="100">
            <display:column property="issueKey" title="IssueKey" sortable="true" />
            <display:column property="issueServiceName" title="ServiceName" sortable="true"  />
            <display:column property="issueAssignee" title="Assignee" sortable="true"  />
            <display:column property="eid" title="EID" sortable="true"  />
            <display:column property="issuePriority" title="Priority" sortable="true"  />
            <display:column property="issueAssignedDate" title="AssignedDate" sortable="true"  />
            <display:column property="issueResolvedDate" title="ResolvedDate" sortable="true"  />
            <display:column property="issueStatus" title="IssueStatus" sortable="true"  />
            <display:column property="resolutionTime" title="Resolution" sortable="true"  />
            <display:column property="resolutionSLA" title="ResolutionSLA" sortable="true"  />
            
            
            <display:column property="incidentType" title="incidentType" sortable="true"  />
            <display:column property="notes" title="notes" sortable="true"  />
            
            <display:column property="summary" title="summary" sortable="true"  />
            <display:column property="firstName" title="firstName" sortable="true"  />
            <display:column property="submitterName" title="submitterName" sortable="true"  />
            <display:column property="productCategorizationTier1" title="productCategorizationTier1" sortable="true"  />
            <display:column property="productCategorizationTier2" title="productCategorizationTier2" sortable="true"  />
            <display:column property="productCategorizationTier3" title="productCategorizationTier3" sortable="true"  />
            <display:column property="productName" title="productName" sortable="true"  />
            <display:column property="site" title="site" sortable="true"  />
            <display:column property="impact" title="impact" sortable="true"  />
            
            <display:column property="urgency" title="urgency" sortable="true"  />
            <display:column property="lastResolvedDate" title="lastResolvedDate" sortable="true"  />
            <display:column property="lastModifiedDate" title="lastModifiedDate" sortable="true"  />
            <display:column property="lastModifiedBy" title="lastModifiedBy" sortable="true"  />
            <display:column property="status" title="status" sortable="true"  />
            <display:column property="reportedSource" title="reportedSource" sortable="true"  />
            <display:column property="ownerGroup" title="ownerGroup" sortable="true"  />
            <display:column property="owner" title="owner" sortable="true"  />
            <display:column property="assignedGroup" title="assignedGroup" sortable="true"  />
            
            <display:column property="resolution" title="resolution" sortable="true"  />
            <display:column property="reopenedDate" title="reopenedDate" sortable="true"  />
            <display:column property="reportedDate" title="reportedDate" sortable="true"  />
            
            
            
            
                             
         </display:table>  
    
   </body>
</html>