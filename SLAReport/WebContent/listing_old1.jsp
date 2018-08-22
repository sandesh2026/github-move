<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<sx:head />
    <title>ServWell</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="-1" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
</head>
<body bgcolor="#f0f0f0" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" link="#003366" vlink="#003366" alink="#660000">
	<img class="logo" src="images/servwell.jpg" width="269" height="35" border="0" alt="ServWell - HTS Service Management Framework"> 
	
<table cellpadding="5" cellspacing="0" border="0" width="100%" align="center">
	<tr>
        <td valign="top" width="50%">
    <table class="tableBorder maxWidth" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td>
            <table border="0" cellpadding="3" cellspacing="1" width="100%">
                <tr>
                    <td class="rowClear">
<style>
.datacell {
border-left: 1px black solid;
border-bottom: 1px black solid;
padding-left: 5px;
}

.datacellRightBorder {
border-left: 1px black solid;
border-right: 1px black solid;
border-bottom: 1px black solid;
padding-left: 5px;

}
.headercell {
	background-color:#3c78b5;
	color:white;
	font: bold 100% Calibri;
	font-size: 1.3em;
	border-top: 1px black solid;
	border-left: 1px black solid;
	padding-left: 5px;
}
.leftSpacecell {
	padding-left: 25px;
}
.leftSpacecell1 {
	padding-left: 35px;
}
.headercellRightBorder {
	background-color:#3c78b5;
	color:white;
	font: bold 100% Calibri;
	font-size: 1.3em;
	border-top: 1px black solid;
	border-right: 1px black solid;
        border-left: 1px black solid;
	padding-left: 5px;
}
</style>


<script type="text/javascript">
function showOptionsTRL(id,id1)
{
	var listenersDiv = document.getElementById(id1);
    var listenersArrow = document.getElementById(id);
	if (listenersDiv.style.display == 'none')
	{
		listenersDiv.style.display = '';	  
	}
	else
	 {
		listenersDiv.style.display='none';
	}
}
function show_details() {
	dojo.event.topic.publish("show_detail");
}
</script>
<table border=0 width="100%" cellspacing="0" cellpadding="0">
<tr>
	<td height="20px" class="headercell">
     <img src="images/services_offered.png" width="20" height="20">
		Services Offered
	</td>
	<td height="20px" class="headercell">
	<img src="images/what_is_smf.png" width="20" height="20">
		What is SMF?
	</td>
	<td height="20px" class="headercellRightBorder">
	<img src="images/upcoming_events.png" width="20" height="20">
		Actions / Upcoming Events
	</td>
</tr>

<tr
 valign="top"><td width="33%" class="datacell">

<img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
<a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL2');
return false;">
Bangalore Admin</a><br>
	<div id="presetValuesTRL2" style="font-size: 100%; display: none;" class="leftSpacecell">
           <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Community Service<br>
           <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Conference room and cabin booking<br>
           <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;F&amp;B<br>
           <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;General Admin<br>
           <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Telecon Related<br>
           <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Stationary Stores<br>
           <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Guest Hospitality<br>
           <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Mail Room Management<br>
           <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Voice Communication<br>
           <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Transport<br>
	</div>

<img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
<a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL3');
return false;">
Bangalore Facilities</a><br>

	<div id="presetValuesTRL3" style="font-size: 100%; display: none;" class="leftSpacecell">
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;A/C<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Power<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Lighting<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Lift<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;UPS<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Access Card<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Gym<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Relaxation Room<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Building Upkeep<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Physical Security<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;HSE<br>
	</div>

<img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
<a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL4');
return false;">
Business Legal</a><br>

	<div id="presetValuesTRL4" style="font-size: 100%; display: none;" class="leftSpacecell">
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;CI<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;ENF<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;FTP<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;IS<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;LS<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;NS<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Due Diligence<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Portfolio Analysis<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Others<br>
	</div>

<img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
<a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL5');
return false;">
Community Service</a><br>

	<div id="presetValuesTRL5" style="font-size: 100%; display: none;" class="leftSpacecell">
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Science Lab Nomination<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Native Village Program<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Native Village Medical Camp<br>
	</div>

<img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
<a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL6');
return false;">
Process Change Request</a><br>

	<div id="presetValuesTRL6" style="font-size: 100%; display: none;" class="leftSpacecell">
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;PCR<br>
	</div>

<img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
<a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL7');
return false;">
Logistics/EXIM</a><br>

	<div id="presetValuesTRL7" style="font-size: 100%; display: none;" class="leftSpacecell">
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Export of Material<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Customs Clearance & Distribution<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Kitting of Bill of Materials<br>
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Transfer of Material<br>
	</div>

<img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
<a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL8');
return false;">
Feedback</a><br>

	<div id="presetValuesTRL8" style="font-size: 100%; display: none;" class="leftSpacecell">
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Complaint<br>
		 <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Compliment<br>
	</div>


<img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
<a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL9');
return false;">
Hyderabad Operations</a><br>
	<div id="presetValuesTRL9" style="font-size: 100%; display: none;" class="leftSpacecell">
            <img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
           <a href="#"
             onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL9.1');
             return false;">
             Admin</a><br>

	        <div id="presetValuesTRL9.1" style="font-size: 100%; display: none;" class="leftSpacecell1">
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Cafeteria Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Community Services Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Conference Room<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Creche & Relaxation Room Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Gen Admin Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Gifts And Momentos Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Guest Accommodation Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Guest Lunch Coupons Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Mail Room Management<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Name Plate Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;New Joinee Guest Accommodation Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Work Environment<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Stationery Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Team Lunch/Dinner Request<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Telecon Bridge(National)<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Telecon From Office<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Telecon To Home<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Telephones Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Training Infrastructure Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Transport Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Guest Hospitality Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Guest Transport Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Procurement For Less Than 5000<br>
	       </div>

            <img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
           <a href="#"
             onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL9.2');
             return false;">
             Facilities</a><br>

	        <div id="presetValuesTRL9.2" style="font-size: 100%; display: none;" class="leftSpacecell1">
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;A/C<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Power<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Lighting<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Lift<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;UPS<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Access Card<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Gym<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Relaxation Room<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Building Upkeep<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Physical Security<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;HSE<br>
	       </div>
	</div>

<img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
<a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL10');
return false;">
Madurai Operations</a><br>

	<div id="presetValuesTRL10" style="font-size: 100%; display: none;" class="leftSpacecell">
            <img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
           <a href="#"
             onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL10.1');
             return false;">
             Admin</a><br>

	        <div id="presetValuesTRL10.1" style="font-size: 100%; display: none;" class="leftSpacecell1">
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Cafeteria Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Community Services Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Conference Room<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Creche & Relaxation Room Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Gen Admin Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Gifts And Momentos Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Guest Accommodation Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Guest Lunch Coupons Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Mail Room Management<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Name Plate Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;New Joinee Guest Accommodation Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Work Environment<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Stationery Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Team Lunch/Dinner Request<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Telecon Bridge(National)<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Telecon From Office<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Telecon To Home<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Telephones Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Training Infrastructure Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Transport Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Guest Hospitality Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Guest Transport Related<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Procurement For Less Than 5000<br>
	       </div>

            <img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
           <a href="#"
             onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL10.2');
             return false;">
             Facilities</a><br>

	        <div id="presetValuesTRL10.2" style="font-size: 100%; display: none;" class="leftSpacecell1">
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;A/C<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Power<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Lighting<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Lift<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;UPS<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Access Card<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Gym<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Relaxation Room<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Building Upkeep<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Physical Security<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;HSE<br>
	       </div>
	</div>

<img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
<a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL11');
return false;">
PKM</a><br>
<div id="presetValuesTRL11" style="font-size: 100%; display: none;" class="leftSpacecell">
            <img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
           <a href="#"
             onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL11.1');
             return false;">
             Staffing Queries</a><br>

	        <div id="presetValuesTRL11.1" style="font-size: 100%; display: none;" class="leftSpacecell1">
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Lateral hiring<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Intern hiring<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Contingency work force hiring<br>

	       </div>

            <img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
           <a href="#"
             onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL11.2');
             return false;">
             Organizational Development Queries</a><br>

	        <div id="presetValuesTRL11.2" style="font-size: 100%; display: none;" class="leftSpacecell1">
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Competency Framework<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Performance Management<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Talent Management Plan / Management Resource Review<br>
                   <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;General OD queries<br>
                 </div>
            <img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
	               <a href="#"
	                 onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL11.3');
	                 return false;">
	                 Learning & Development Services Queries</a><br>

		<div id="presetValuesTRL11.3" style="font-size: 100%; display: none;" class="leftSpacecell1">
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;General Learning queries<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;External Workshops/Seminars<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Project based training development<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Calendar based trainings<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Knowledge Center Services<br>
                 </div>

            <img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
	       <a href="#"
		 onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL11.4');
		 return false;">
		 Global HR Strategy & Excellence Queries</a><br>

		<div id="presetValuesTRL11.4" style="font-size: 100%; display: none;" class="leftSpacecell1">
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Super-annuation<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;PF<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Gratuity<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Medical<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Insurance<br>
                 </div>
            <img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
	       <a href="#"
		 onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL11.5');
		 return false;">
		 Queries to HRG's</a><br>

		<div id="presetValuesTRL11.5" style="font-size: 100%; display: none;" class="leftSpacecell1">
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Nemo<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Business Induction<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Internal Transition<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;HPD<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;GCP<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Salary structure related<br>
                 </div>
            <img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
	       <a href="#"
		 onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL11.6');
		 return false;">
		 Internal / External  Communication Queries/SR</a><br>

		<div id="presetValuesTRL11.6" style="font-size: 100%; display: none;" class="leftSpacecell1">
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Consultancy & Strategy<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Content Creation<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Content Review<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Dissemination<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Content Design, Creation & Dissemination<br>
	        </div>
	    <img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
	       <a href="#"
		 onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL11.7');
		 return false;">
		 Learning & Development Service Request</a><br>

		<div id="presetValuesTRL11.7" style="font-size: 100%; display: none;" class="leftSpacecell1">
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Project specific training requests - Internal Training<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Project specific training requests - External Training<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;E-learning modules development<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Providing Ad-Hoc Training Reports<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Ad-Hoc Requests for Training & Logistics Support<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Registration of External Workshops/Seminars<br>
		       <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Knowledge Center Services<br>
	        </div>
	</div>

<img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
<a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL12');
return false;">
Transport</a><br>

	<div id="presetValuesTRL12" style="font-size: 100%; display: none;" class="leftSpacecell">
         <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Route Card<br>
		 <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Change Route<br>
		 <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Late Night Booking<br>
		 <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Local/Outstation Transport<br>
		 <img src="images/icons/bullet_red.gif" height="4" width="4">&nbsp;Query<br>
	</div>

<img src="images/icons/bullet_blue.gif" height="4" width="4">&nbsp;
<a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL13');
return false;">
Travel</a><br>

	<div id="presetValuesTRL13" style="font-size: 100%; display: none;" class="leftSpacecell">
		 <img src="/images/icons/bullet_red.gif" height="4" width="4">&nbsp;Query<br>
	</div>
</td>
<td width="33%" class="datacell">

                  <p>Service  Management Framework (SMF) is a focused
approach with a set of processes and  practices and support
infrastructure that help a service provider deliver  continuous value to
 customers through service delivery.

				  <a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL14');
return false;">
                  more</a><br>
                  </p><div id="presetValuesTRL14" style="display: none;">
                  It provides a framework  for the service provider to
structure their activities and interactions with  customers and internal
 stakeholders. This is based on the ITIL framework, which  is considered
 as the de-facto standard for IT Service Management. Service  Management
 excellence is achieved through  the effective utilization and
management of <em>People, Process, Technology and Partners  (Vendors).
                  </em></div>

<em>                  </em><p></p>
                  <p><strong>SMF at HTS</strong></p>

                  <p>At  HTS, PKM, Admin,
Facilities, Logistics, Finance, Legal, IS-OPS, SCM and  Travel are
 adopting SMF to further  improve their service level performance and to
 drive continual improvement  culture in their respective service
delivery.<strong></strong>
                  <a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL15');
return false;">
                  more</a><br>
                  </p><div id="presetValuesTRL15" style="display: none;">
                  A  service catalogue has been created for each
function detailing their services, service levels, customer dependencies
 and other  critical information. An online services'  tracking system,
ServWell, has been created by adopting the ITIL service  management best
 practices to standardize, automate and track service requests  using
tools, alerts, escalations, and reports to enable the SMF deployment. <p></p>
                  </div>

                  <p><strong>Benefits of SMF</strong></p>

                  <p>All services  rendered by support functions will
have pre-defined service levels and/or  mutually agreed timelines.
                  <a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL16');
return false;">more</a><br>
                  </p><div id="presetValuesTRL16" style="display: none;">
                  You will be able to  know the current status of your
service requests and plan your activities  better, provide necessary
data or information to function teams, as required,  enabling prompt
servicing of your requests. Overall, it is aimed at improving  the
predictability, consistency and quality of services.
                  <p></p>
                  </div>
</td>
 <td width="33%" class="datacellRightBorder">

                  <p><strong>Actions for you</strong></p>

                  <p>Use the services' tracking systems,
ServWell/iBest/SAP,  etc., for logging your requests and avoid off-line
methods such as e-mails,  phone calls and communicator for requesting a
service.
                  <a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL17');
return false;">more</a><br>
                  </p><div id="presetValuesTRL17" style="display: none;">
                  Provide necessary data  or information to function
teams, as required, to enable prompt servicing of  your requests. Give
your feedback and suggestions in the tool, which is  critical for the
continual improvement of services. Share non-IT SMF best  practices that
 you may be aware of.
                  <strong></strong><p></p>
                  </div>

                  <p><strong>Upcoming SMF Events </strong><br>

                    <br>

                    To know the list of services that will be available
soon,
               <a href="#"
onclick="showOptionsTRL('presetValuesArrowTRL1','presetValuesTRL18');
return false;">click here</a>
                  </p><div id="presetValuesTRL18" style="display: none;">

                  <ul><li>PKM  queries and services</li>
                    <li>SCM  and Travel queries </li>
                    <li>Guest  Hospitality related services </li>

                    <li>Service  Feedback (a mechanism for you to
provide your compliments or complaints on the  services)</li>
                    <li>Availability  of Service Catalogue (clear
details of all the services provided by Support Functions)  for your
reference. </li>
                    <li>Launch  of the SMF website</li>
                  </ul>
                  <p>&nbsp;</p></div></td>


 </tr>

 </table>
</img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></tr
></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></img></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
    </td>
        <td valign="top" width="50%">
        <div class="maxWidth bgBorder">
        <div class="colHeaderLink portletLoginHeader">
        <center class="headercell" style="background-color: #3c78b5"><h2>SMF SLA Reports</h2></center>         
        </div>
        <div class="rowClear loginFormBox">
               <s:form action="InputForm" id="InputForm" theme="simple">
        <table align="center" cellpadding="4" cellspacing="0" border="0">

        <tr>
            <td valign="middle" align="right" width="25%"><b>ProjectName</b></td>
            <td valign="middle">
                <s:select theme="simple" name="lst" list="lstList1" cssStyle="min-width: 150px; max-width: 150px; width : 150px;"
				onchange="javascript:show_details();return false;"></s:select>            </td>
        </tr>
        <tr>
            <td valign="middle" align="right" width="25%"><b>Ticket Type</b></td>
            <td valign="middle">
                <s:url id="d_url" action="DetailAction" />
				<sx:div href="%{d_url}" listenTopics="show_detail" formId="InputForm" showLoadingText=""></sx:div>
            </td>
        </tr>
        <tr>
			<td valign="middle" align="right" width="25%"><b>Location</b></td>
			<td valign="middle">
				<s:select theme="simple" name="location" cssStyle="min-width: 150px; max-width: 150px; width : 150px;"
					list="#{'Bangalore':'Bangalore','Hyderabad':'Hyderabad','Madurai':'Madurai'}"
				value="0" />
			</td>
		</tr> 

		<tr>
			<td valign="middle" align="right" width="25%"><b>From Date</b></td>
			<td valign="middle">
			<s:fielderror/>
			<s:datetimepicker name="fromDate" displayFormat="yyyy-MM-dd"
			id="fromDate" required="true"/>
			</td>
		</tr>

		<tr>
			<td valign="middle" align="right" width="25%"><b>To Date</b></td>
			<td valign="middle">
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

</td></tr>
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

<span class="poweredbymessage">
    Deployed and Supported by ServWell Team
</span>

</div>
</body>
</html>

