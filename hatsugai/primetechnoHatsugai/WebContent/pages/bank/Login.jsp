<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/normaldesign.css">
<title>ログイン</title>
</head>
<body>
	<div id="header"></div>
	<h1>ログイン</h1>
		<html:errors />
		<html:form action="/LoginAction">
		<table>
			<tr><td><bean:message key="accountNumber" /></td>
			<td><html:text property="accountNumber" maxlength="10" /></td></tr>
			<tr><td><bean:message key="password" /></td>
			<td><html:password property="password" /></td></tr>
		</table>
		<div><html:submit styleId="middle_button" property="loginAction">
			<bean:message key="login" />
		</html:submit>
		<html:submit styleId="middle_button" property="loginAction">
			<bean:message key="createAccount" />
		</html:submit></div>
		</html:form>
</body>
</html:html>