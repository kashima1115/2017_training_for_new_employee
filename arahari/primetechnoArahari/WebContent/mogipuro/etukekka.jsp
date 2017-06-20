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

<title>アンケート結果画面</title>
</head>
<body>


<!-- 不正アクセス処理 -->
	<logic:notPresent name="logid" scope="session">
		<logic:redirect forward="logerror"/>
	</logic:notPresent>



	<h1>アンケート結果</h1>

	<html:form action="/EtukekkaAction">


	アンケート名　<bean:write name="MakeForm" property="radio" />
		<br />


		<logic:iterate id="bn" name="kaitouList" type="mogipuro.MakeForm">

有効回答者数　　　<bean:write name="bn" property="kaitou" />人<br />

			</logic:iterate>


		<logic:iterate id="kai" name="kekkaList" type="mogipuro.MakeForm">
			<br />
			<logic:notEmpty name="kai" property="question">
	---------<bean:write name="kai" property="question" />---------------
	<br />
			</logic:notEmpty>
			<logic:notEmpty name="kai" property="choice">

				<bean:write name="kai" property="choice" />
				<br />
				<bean:write name="kai" property="answered" />人
	</logic:notEmpty>
		</logic:iterate>


		<br />


		<html:submit property="ekControl" styleClass="deg_btn">
			<bean:message key="home" />
		</html:submit>
		<br />


		<html:submit property="ekControl" styleClass="deg_btn">
			<bean:message key="etuback" />
		</html:submit>
		<br />
		<html:submit property="ekControl" styleClass="deg_btn">
			<bean:message key="eiback" />
		</html:submit>
		<br />
		<html:submit property="ekControl" styleClass="deg_btn">
			<bean:message key="ekback" />
		</html:submit>
		<br />
		<html:submit property="ekControl" styleClass="deg_btn">
			<bean:message key="ejback" />
		</html:submit>
		<br />

		<html:submit property="ekControl" styleClass="deg_btn">
			<bean:message key="emback" />
		</html:submit>
		<br />

	</html:form>

</body>
</html:html>