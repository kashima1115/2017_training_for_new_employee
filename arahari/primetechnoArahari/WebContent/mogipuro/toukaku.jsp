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

<title>登録確認画面</title>
</head>
<body>
	<h1>ユーザ登録確認</h1>

	<a class="error"> <html:errors />
	</a>

	<html:form action="/ToukakuAction">
	ユーザID  <bean:write name="QuestionnaireForm" property="id" />
		<br />



		<html:submit property="kakuninControl" styleClass="deg_btn">
			<bean:message key="nextkaku" />
		</html:submit>
<br/>
		<html:submit property="kakuninControl" styleClass="deg_btn">
			<bean:message key="backkaku" />
		</html:submit>

	</html:form>

</body>
</html:html>