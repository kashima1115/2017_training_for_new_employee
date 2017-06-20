<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
    <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="stylesheet" href="<html:rewrite page='/stylesheet/userCompleteCss.css'/>" type="text/css">
<title>ユーザ登録完了</title>
</head>
<body>
<div id="main">
	<logic:notPresent name="logId" scope="session">
		<logic:redirect forward="logFail"/>
	</logic:notPresent>
	<logic:notPresent name="haveChip" scope="session">
		<logic:redirect forward="logFail"/>
	</logic:notPresent>

	<table>
		<tr><td><bean:message key="loginId"/></td><td><bean:write name="logId" /></td>
			<td><bean:message key="chipLabel"/></td>
			<td><bean:write name="haveChip" /></td><td><bean:message key="chipAmount"/></td>
		</tr>
	</table>
	<h1>ユーザ登録完了</h1>
	<table>
	<tr><td><bean:message key="loginId"/></td>
		<td><strong><bean:write name="logId" /></strong></td>
	</tr>
	</table>
	<p>上記IDでユーザが登録されました。</p>
	<html:form action="/GComplete">
		<html:submit property="pageControl">
			<bean:message key="menuLabel"/>
		</html:submit>

		<html:submit property="pageControl">
			<bean:message key="logoutLabel"/>
		</html:submit>
	</html:form>
</div>
</body>
</html:html>