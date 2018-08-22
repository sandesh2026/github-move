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
    <font size="3" face="arial" color="RED"><% try{ %> Click <% 	String usr=request.getSession().getAttribute("usr").toString();%><% out.println("<a href='http://servwell.honeywell.com/SLAReport/index.jsp?usr="+usr+"'>BACK </a>");%>  To Get New Date Range Results <% }catch(Exception e){}%></font>
     <h2><s:property value="%{select}"/> <s:property  value="%{ticketType}"/> Results From: <s:property  value="%{fromDate}"/> to <s:property  value="%{toDate}"/></h2>      
<s:if test="%{ticketType=='Commercial'}">	
     <s:set  value="resultList" scope="request" name="resultList"/>
        <display:table export="true" name="resultList" uid="data" requestURI="" pagesize="100">
            <display:column property="issueKey" title="IssueKey" sortable="true" />
            <display:column property="issueServiceName" title="ServiceName" sortable="true"  />
            <display:column property="vendorIdentificationMin" title="Vendor Identification Minutes" sortable="true"  />
            <display:column property="vendorIdentificationSLA" title="Vendor Identification SLA" sortable="true"  />
			<display:column property="bidCompNegoMin" title="Bidding Compliance Negotiations Minutes" sortable="true"  />
			<display:column property="bidCompNegoSLA" title="Bidding Compliance Negotiations SLA" sortable="true"  />
			<display:column property="vendorRegMin" title="Vendor Registration Minutes" sortable="true"  />
			<display:column property="vendorRegSLA" title="Vendor Registration SLA" sortable="true"  />
			<display:column property="clearanceMin" title="Clearance Minutes" sortable="true"  />
			<display:column property="clearanceSLA" title="Clearance SLA" sortable="true"  />
			<display:column property="wtngImanyReqMin" title="Waiting Imany Request Minutes" sortable="true"  />
			<display:column property="wtngImanyReqSLA" title="Waiting Imany Request SLA" sortable="true"  />
			<display:column property="approveMin" title="Term Sheet Review Minutes" sortable="true"  />
			<display:column property="approveSLA" title="Term Sheet Review SLA" sortable="true"  />
			<display:column property="firstDraftMin" title="First Draft Minutes" sortable="true"  />
			<display:column property="firstDraftSLA" title="First Draft SLA" sortable="true"  />
			<display:column property="reqReviewMin" title="Requestor Review Minutes" sortable="true"  />
			<display:column property="reqReviewSLA" title="Requestor Review SLA" sortable="true"  />
			<display:column property="procReviewMin" title="Procurement Review Minutes" sortable="true"  />
			<display:column property="procReviewSLA" title="Procurement Review SLA" sortable="true"  />
			<display:column property="vendorAcceptMin" title="Vendor Acceptance Minutes" sortable="true"  />
			<display:column property="vendorAcceptSLA" title="Vendor Acceptance SLA" sortable="true"  />
			<display:column property="CCFMin" title="CCF Minutes" sortable="true"  />
			<display:column property="CCFSLA" title="CCF SLA" sortable="true"  />
			<display:column property="execContractMin" title="Contract Execution Minutes" sortable="true"  />
			<display:column property="execContractSLA" title="Contract Execution SLA" sortable="true"  />
			<display:column property="bulSignMin" title="BUL Signed Min" sortable="true"  />
			<display:column property="bulSignSLA" title="BUL Signed SLA" sortable="true"  />
        </display:table>  
      </s:if> 
    <s:elseif test="%{ticketType=='Non-Commercial'}">
    <s:set  value="resultList" scope="request" name="resultList"/>
        <display:table export="true" name="resultList" uid="data" requestURI="" pagesize="100">
            <display:column property="issueKey" title="IssueKey" sortable="true" />
            <display:column property="issueServiceName" title="ServiceName" sortable="true"  />
            <display:column property="wtngImanyReqMin" title="Waiting Imany Request Minutes" sortable="true"  />
			<display:column property="wtngImanyReqSLA" title="Waiting Imany Request SLA" sortable="true"  />
			<display:column property="approveMin" title="Term Sheet Review Minutes" sortable="true"  />
			<display:column property="approveSLA" title="Term Sheet Review SLA" sortable="true"  />
			<display:column property="firstDraftMin" title="First Draft Minutes" sortable="true"  />
			<display:column property="firstDraftSLA" title="First Draft SLA" sortable="true"  />
			<display:column property="reqReviewMin" title="Requestor Review Minutes" sortable="true"  />
			<display:column property="reqReviewSLA" title="Requestor Review SLA" sortable="true"  />
			<display:column property="vendorAcceptMin" title="Vendor Acceptance Minutes" sortable="true"  />
			<display:column property="vendorAcceptSLA" title="Vendor Acceptance SLA" sortable="true"  />
			<display:column property="execContractMin" title="Contract Execution Minutes" sortable="true"  />
			<display:column property="execContractSLA" title="Contract Execution SLA" sortable="true"  />
			<display:column property="bulSignMin" title="BUL Signed Min" sortable="true"  />
			<display:column property="bulSignSLA" title="BUL Signed SLA" sortable="true"  />
        </display:table>
      </s:elseif>
   </body>
</html>