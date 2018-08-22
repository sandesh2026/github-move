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
           <display:column property="businessUnit" title="BusinessUnit" sortable="true" />
           <display:column property="janPercentage" title="Jan" sortable="true" />
           <display:column property="febPercentage" title="Feb" sortable="true" />
           <display:column property="marPercentage" title="Mar" sortable="true" />
           <display:column property="aprPercentage" title="Apr" sortable="true" />
           <display:column property="mayPercentage" title="May" sortable="true" />
           <display:column property="junPercentage" title="Jun" sortable="true" />
           <display:column property="julPercentage" title="Jul" sortable="true" />
           <display:column property="augPercentage" title="Aug" sortable="true" />
           <display:column property="sepPercentage" title="Sep" sortable="true" />
           <display:column property="octPercentage" title="Oct" sortable="true" />
           <display:column property="novPercentage" title="Nov" sortable="true" />
           <display:column property="decPercentage" title="Dec" sortable="true" />   
           <display:column property="totalYearPercentage" title="YTD Percentage" sortable="true" />                           
        </display:table>
    
     <s:set  value="resultList" scope="request" name="resultList"/>
        <display:table export="true" name="resultList" uid="data" requestURI="" pagesize="100">
            <display:column property="rfpNum" title="Requisition ID" sortable="true" />
            <display:column property="dueDate" title="DueMonth" sortable="true" />
            <display:column property="dueYear" title="DueYear" sortable="true"  />
            <display:column property="dueMonth" title="DueDate" sortable="true"  />
            <display:column property="passFailStatus" title="PassFailStatus" sortable="true"  />
            <display:column property="scheduleStatus" title="ScheduleStatus" sortable="true"  />
            <display:column property="tatOffer" title="TAT_Offer" sortable="true"  />
            <display:column property="tatJoin" title="TAT_Join" sortable="true"  />
            <display:column property="costCode" title="CCCode" sortable="true"  />
            <display:column property="ccgName" title="CCG1Name" sortable="true"  />       
         </display:table>  
    
   </body>
</html>