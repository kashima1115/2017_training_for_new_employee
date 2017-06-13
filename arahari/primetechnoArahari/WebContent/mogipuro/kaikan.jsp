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

<title>回答完了画面</title>
</head>
<body>
	<h1>回答が完了しました</h1>
	<a class="square_btn"> アンケート回答ありがとうございます！ 1P獲得しました </a>
	<html:form action="/KaikanAction">
		<html:submit property="kkanControl" styleClass="deg_btn">
			<bean:message key="kannext" />
		</html:submit>
		<br />


		<html:submit property="kkanControl" styleClass="deg_btn">
			<bean:message key="logout" />
		</html:submit>


	</html:form>

</body>
</html:html>