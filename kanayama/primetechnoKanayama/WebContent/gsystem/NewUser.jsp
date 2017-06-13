<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
    <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="stylesheet" href="<html:rewrite page='/stylesheet/NewUserCss.css'/>" type="text/css">
<title>ユーザー登録</title>
</head>
<body>
	<h1>ユーザー登録</h1>
	<h2><bean:message key="warn"/>は入力必須です</h2>
	<strong class="error"><html:errors/></strong>
	<html:form action="/GNewUser">
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
		<html:cancel value="戻る" styleClass="newUser"/>
		<html:submit styleClass="newUser">
			<bean:message key="ready"/>
		</html:submit>
	</html:form>
</body>
</html:html>