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

<title>ホーム画面</title>
</head>
<body>
<!-- 不正アクセス処理 -->
	<logic:notPresent name="logid" scope="session">
		<logic:redirect forward="logerror"/>
	</logic:notPresent>



	<h1>ホーム画面</h1>
	<a class="error"> <html:errors />
	</a>
	<html:form action="/HomeAction">

		<a class="square_btn"> ようこそ<bean:write name="LoginForm"
				property="logid" />さん！<br /> <logic:iterate id="bn"
				name="pointList" type="mogipuro.LoginForm">

あなたは現在<bean:write name="bn" property="point" />Pです<br />

			</logic:iterate>
		</a>


		<br />
		<br />

		<html:submit property="homeControl" styleClass="deg_btn">
			<bean:message key="ank" />
		</html:submit>
		<br />
		<br />

		<html:submit property="homeControl" styleClass="deg_btn">
			<bean:message key="kai" />
		</html:submit>
		<br />
		<br />

		<html:submit property="homeControl" styleClass="deg_btn">
			<bean:message key="etu" />
		</html:submit>
		<br />
		<br />

		<html:submit property="homeControl" styleClass="deg_btn">
			<bean:message key="del" />
		</html:submit>
		<br />
		<br />




		<html:submit property="homeControl" styleClass="deg_btn">
			<bean:message key="logout" />
		</html:submit>
	</html:form>

</body>
</html:html>