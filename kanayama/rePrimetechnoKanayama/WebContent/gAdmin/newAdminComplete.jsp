<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
    <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>管理者登録完了</title>
</head>
<body>
	<div id="main">
		<logic:notPresent name="logId" scope="session">
			<logic:redirect forward="logFail"/>
		</logic:notPresent>
		<table>
			<tr><td><bean:message key="adminId"/></td>
				<td><bean:write name="NewUserForm" property="newId"/></td>
			</tr>
		</table>
		<h1>管理者登録完了</h1>
		<table>
			<tr><td><bean:message key="adminId"/></td>
				<td><strong><bean:write name="logId" /></strong></td>
			</tr>
		</table>
		<p>上記IDでユーザが登録されました。</p>

		<html:form action="/adminComplete">
			<html:submit property="pageControl">
				<bean:message key="adminMenu"/>
			</html:submit>
			<html:submit property="pageControl">
				<bean:message key="logoutLabel"/>
			</html:submit>
		</html:form>
	</div>
</body>
</html:html>