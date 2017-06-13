<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/normaldesign.css">
<title>�����J�݊m�F</title>
</head>
<body>
	<h1>�����J�݊m�F</h1>
	<table>
		<tr><td><bean:message key="name"/></td><td><bean:write name="CreateAccountForm" property="name"/></td></tr>
		<tr><td><bean:message key="furigana"/></td><td><bean:write name="CreateAccountForm" property="katakana"/></td></tr>
		<tr><td><bean:message key="birthday"/></td><td><bean:write name="CreateAccountForm" property="bYear"/>�N
			<bean:write name="CreateAccountForm" property="fMonth"/>��
			<bean:write name="CreateAccountForm" property="fDate"/>��</td>
		</tr>
		<tr><td><bean:message key="uAddress"/></td>
			<td><bean:write name="CreateAccountForm" property="uPrefAddress"/></td></tr>
		<tr><td></td>
			<td><bean:write name="CreateAccountForm" property="uCityAddress"/></td></tr>
		<tr><td></td>
			<td><bean:write name="CreateAccountForm" property="uConstructure"/></td>
		</tr>
		<tr><td><bean:message key="tel"/></td><td><bean:write name="CreateAccountForm" property="tel"/></td></tr>
		<tr><td><bean:message key="mobile"/></td><td><bean:write name="CreateAccountForm" property="mobile"/></td></tr>
		<tr><td><bean:message key="mail"/></td><td><bean:write name="CreateAccountForm" property="mail"/></td></tr>
		<tr><td><bean:message key="zokusei"/></td><td><logic:equal parameter="zokusei" value="0">�l</logic:equal>
				<logic:equal parameter="zokusei" value="1">�@�l</logic:equal></td></tr>
		<tr><td><bean:message key="password"/></td><td>���S�̂��ߕ\�����Ă��܂���B</td></tr>
		<tr><td><bean:message key="passno"/></td><td>���S�̂��ߕ\�����Ă��܂���B</td></tr>
		<tr><td><bean:message key="worknm"/></td><td><bean:write name="CreateAccountForm" property="worknm"/></td></tr>
		<tr><td><bean:message key="workad"/></td>
			<td><bean:write name="CreateAccountForm" property="wPrefAddress"/></td></tr>
		<tr><td></td>
			<td><bean:write name="CreateAccountForm" property="wCityAddress"/></td>
		</tr>
		<tr><td></td>
			<td><bean:write name="CreateAccountForm" property="wConstructure"/></td></tr>
		<tr><td><bean:message key="worktel"/></td><td><bean:write name="CreateAccountForm" property="worktel"/></td></tr>
	</table>
	<html:form action="/CreateAccountConfAction">
		<div><html:submit styleId="middle_button" property="createAccountConfAction">
			<bean:message key="create"/>
		</html:submit>
		<html:submit styleId="middle_button" property="createAccountConfAction">
			<bean:message key="return" />
		</html:submit></div>
	</html:form>
</body>
</html:html>