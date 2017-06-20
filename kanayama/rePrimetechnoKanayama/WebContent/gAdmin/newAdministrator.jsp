<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
    <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>管理者登録</title>
</head>
<body>
	<div id="main">
		<h1>管理者登録</h1>
		<h2><bean:message key="warn"/>は入力必須です</h2>
		<strong class="error"><html:errors/></strong>
		<html:form action="/gAdminStra">
			<table>
				<tr><td><bean:message key="loginId"/><strong><bean:message key="warn"/></strong></td>
				<td><html:text property="newId" maxlength="8"/></td>
			</tr>
			<tr><td><bean:message key="password"/><strong><bean:message key="warn"/></strong></td>
				<td><html:password  property="newPass" maxlength="8"/></td>
			</tr>
			<tr><td><bean:message key="password"/>(確認用)<strong><bean:message key="warn"/></strong></td>
				<td><html:password  property="passCheck" maxlength="8"/></td>
				</tr>
			</table><br/>
			<html:submit styleClass="newUser">
				<bean:message key="ready"/>
			</html:submit>
		</html:form>
	</div>
</body>
</html:html>