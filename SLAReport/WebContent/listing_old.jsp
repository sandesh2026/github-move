<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<sx:head />
<title>SMF</title>
</head>
<script>
function show_details() {
	dojo.event.topic.publish("show_detail");
}
</script>
<body bgcolor="#ffffff">
<center style="style="background-color:#3c78b5"">
<h2>Service Management FrameWork</h2>
</center>
<s:form action="InputForm" id="InputForm" theme="simple">
	<table border="0" align="CENTER">
		<tr>
			<td style="font-style: italic">ProjectName</td>
			<td>			 
			<s:select theme="simple" name="lst" list="lstList1" 
				onchange="javascript:show_details();return false;"></s:select></td>
		</tr>
		<tr>
			<td style="font-style: italic">Ticket Type</td>
			<td><s:url id="d_url" action="DetailAction" />			 
				<sx:div href="%{d_url}" listenTopics="show_detail" formId="InputForm" showLoadingText=""></sx:div>
			</td>			
		</tr>
		<tr>
			<td style="font-style: italic">Location</td>
			<td><s:select theme="simple" name="location" 
				list="#{'Bangalore':'Bangalore','Hyderabad':'Hyderabad','Madurai':'Madurai'}"
				value="0" /></td>
		</tr>
		<tr>
			<td style="font-style: italic">From Date</td>
			<td><s:datetimepicker name="fromDate" displayFormat="yyyy-MM-dd"
				id="fromDate" required="true"/></td>
		</tr>
		<tr>
			<td style="font-style: italic">To Date</td>
			<td><s:datetimepicker name="toDate" displayFormat="yyyy-MM-dd"
				id="toDate" required="true"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<s:submit value="Generate Report" />
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>
