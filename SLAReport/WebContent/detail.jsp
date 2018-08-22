<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="lstList != null"> 
<s:select name="ticketType" list="lstList"></s:select>	
</s:if> 
