<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<link rel="stylesheet" href="<html:rewrite page='/style/sheet.css'/>"
	type="text/css">

<title>�A���P�[�g�m�F���</title>
</head>
<body>


<!-- �s���A�N�Z�X���� -->
	<logic:notPresent name="logid" scope="session">
		<logic:redirect forward="logerror"/>
	</logic:notPresent>



	<h1>�A���P�[�g�m�F</h1>

		<a class="error"> <html:errors />
	</a>
	<html:form action="/AnkkakuAction">

�A���P�[�g���@<bean:write name="MakeForm" property="ankName" />
		<br />
		<br />

������e�P<bean:write name="MakeForm" property="question1" />
		<br />
�I����<br />
		<bean:write name="MakeForm" property="choices11" />
		<br />
		<bean:write name="MakeForm" property="choices12" />
		<br />
		<bean:write name="MakeForm" property="choices13" />
		<br />
		<bean:write name="MakeForm" property="choices14" />
		<br />
		<br />

������e�Q<bean:write name="MakeForm" property="question2" />
		<br />
�I����<br />
		<bean:write name="MakeForm" property="choices21" />
		<br />
		<bean:write name="MakeForm" property="choices22" />
		<br />
		<bean:write name="MakeForm" property="choices23" />
		<br />
		<bean:write name="MakeForm" property="choices24" />
		<br />
		<br />

������e�R<bean:write name="MakeForm" property="question3" />
		<br />
�I����<br />
		<bean:write name="MakeForm" property="choices31" />
		<br />
		<bean:write name="MakeForm" property="choices32" />
		<br />
		<bean:write name="MakeForm" property="choices33" />
		<br />
		<bean:write name="MakeForm" property="choices34" />
		<br />
		<br />

������e�S<bean:write name="MakeForm" property="question4" />
		<br />
�I����<br />
		<bean:write name="MakeForm" property="choices41" />
		<br />
		<bean:write name="MakeForm" property="choices42" />
		<br />
		<bean:write name="MakeForm" property="choices43" />
		<br />
		<bean:write name="MakeForm" property="choices44" />
		<br />
		<br />

������e�T<bean:write name="MakeForm" property="question5" />
		<br />
�I����<br />
		<bean:write name="MakeForm" property="choices51" />
		<br />
		<bean:write name="MakeForm" property="choices52" />
		<br />
		<bean:write name="MakeForm" property="choices53" />
		<br />
		<bean:write name="MakeForm" property="choices54" />
		<br />
		<br />

���{����
		<bean:write name="MakeForm" property="year" />�N
		<bean:write name="MakeForm" property="month" />��
		<bean:write name="MakeForm" property="day" />��<br />


		<br />


		<html:submit property="akControl" styleClass="deg_btn">
			<bean:message key="aknext" />
		</html:submit>
		<br/>
				<html:submit property="akControl" styleClass="deg_btn">
			<bean:message key="akback" />
		</html:submit>

	</html:form>
</body>
</html:html>