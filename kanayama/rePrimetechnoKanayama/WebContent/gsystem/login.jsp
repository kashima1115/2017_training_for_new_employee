<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
    <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="stylesheet" href="<html:rewrite page='/stylesheet/logCss.css'/>" type="text/css">
<title>���O�C��</title>
</head>
<body>
<div id="main">
	<h1>���O�C��</h1>
	<strong><html:errors/></strong>
	<html:form action="/gLogin">
		<table>
			<tr><td><bean:message key="loginId"/></td>
				<td><html:text property="loginId" maxlength="8"/></td>
			</tr>
			<tr><td><bean:message key="password"/></td>
				<td><html:password  property="pass" maxlength="8"/></td>
			</tr>
		</table><br/>
		<html:submit styleClass="logButton" >
			<bean:message key="loginLabel"/>
		</html:submit><br/>
		<p>���߂Ă̕��̓��[�U�o�^�����Ă��������B</p>
		<html:cancel value="���[�U�[�o�^" styleClass="logButton"/>
	</html:form>
</div>
</body>
</html:html>