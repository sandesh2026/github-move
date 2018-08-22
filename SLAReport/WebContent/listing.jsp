<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<script>
function show_details() {
	dojo.event.topic.publish("show_detail");
}
</script>
<sx:head />
    <title>ServWell</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="-1" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" link="#003366" vlink="#003366" alink="#660000">
	<img class="logo" src="images/servwell.jpg" width="269" height="35" border="0" alt="ServWell - HTS Service Management Framework"> 
<div class="colHeaderLink portletLoginHeader">
        <div class="navigatemenu" style="background-color: #3c78b5; padding-top:2; padding-bottom:2; padding-left:10"><font style="font-family:calibri; color:#FFFFFF; text-align:left" size=3><a href="http://servwell.honeywell.com" style="font-family:calibri; color:#FFFFFF; text-align:left; padding-top:3; padding-bottom:5; padding-left:10">HOME</a></font></div>         
        </div>	
        <BR>
<%
 request.getSession().getAttribute("usr");
%>
<table cellpadding="5" cellspacing="0" border="0" width="100%" align="center">
	<tr>
        <td valign="top" width="50%" align="center">
        <font style="font-family:calibri; color:#003366; text-align: center" size=6><b>ServWell - HTS Services Tracking System</b></font><br>
    <img src="images/SMF.png">
    </td>
        <td valign="top" width="50%">
        <div class="maxWidth bgBorder">
        <div class="colHeaderLink portletLoginHeader">
        <center class="headercell" style="background-color: #3c78b5"><font style="font-family:calibri; color:#FFFFFF" size=4>SMF SLA Reports</font></center><br>         
        </div>
        <div class="rowClear loginFormBox">
               <s:form action="InputForm" id="InputForm" theme="simple">
        <table align="center" cellpadding="3" cellspacing="3" border="0">

        <tr>
            <td valign="middle" align="right" width="25%"><font style="font-family: calibri"><b>Project Name</b></font></td>
            <td valign="middle" align="left">
                <s:select theme="simple" name="lst" list="lstList1" cssStyle="min-width: 150px; max-width: 150px; width : 150px;"
				onchange="javascript:show_details();return false;"></s:select>            </td>
        </tr>
        <tr>
            <td valign="middle" align="right" width="25%"><font style="font-family: calibri"><b>Ticket Type</b></font></td>
            <td valign="middle" align="left">
                <s:url id="d_url" action="DetailAction"/>
				<sx:div href="%{d_url}" listenTopics="show_detail"  formId="InputForm" showLoadingText=""></sx:div>
            </td>
        </tr>
        <tr>
			<td valign="middle" align="right" width="25%"><font style="font-family: calibri"><b>Location</b></font></td>
			<td valign="middle" align="left">
				<s:select theme="simple" name="location" cssStyle="min-width: 150px; max-width: 150px; width : 150px;"
					list="#{'Bangalore':'Bangalore','Hyderabad':'Hyderabad','Madurai':'Madurai'}"
				value="0" />
			</td>
		</tr> 

		<tr>
			<td valign="middle" align="right" width="25%"><font style="font-family: calibri"><b>From Date</b></font></td>
			<td valign="middle" align="left">
			<s:fielderror/>
			<s:datetimepicker name="fromDate" displayFormat="yyyy-MM-dd"
			id="fromDate" required="true"/>
			</td>
		</tr>

		<tr>
			<td valign="middle" align="right" width="25%"><font style="font-family: calibri"><b>To Date</b></font></td>
			<td valign="middle" align="left">
			<s:fielderror/>
			<s:datetimepicker name="toDate" displayFormat="yyyy-MM-dd"
				id="toDate" required="true"/>
			</td>
		</tr>

        <tr>
            <td valign="middle" align="center" colspan="2">
                <s:submit value="Generate Report" />
            </td>
        </tr>
        </table>

        <input type="hidden" name="os_destination" value="/secure/" />
 </s:form>       
            </div>
    </div>
            &nbsp;<br/>
                                                        <div id="portletcc-11001">    </div>

  
    </td>
    </tr>
</table>


    <div class="footer">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                <td bgcolor="#bbbbbb"><img src="/s/360/1/_/images/border/spacer.gif" height="1" width="100" border="0" alt=""></td>
            </tr>
    <tr>
        <td height="12" style="background-image:url(/s/360/1/_/images/border/border_bottom.gif)"><img src="/s/360/1/_/images/border/spacer.gif" width="1" height="1" border="0" alt=""></td>
    </tr>
</table>
<center>
<span class="poweredbymessage">
    <font style="font-family:calibri; color:#003366; text-align:center;" size=3>Deployed and Supported by ServWell Team</font>
</span>
</center>
</div>
</body>
</html>

