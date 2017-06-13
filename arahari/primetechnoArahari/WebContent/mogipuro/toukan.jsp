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

<title>登録完了画面</title>
</head>
<body>
	<h1>ユーザ登録完了</h1>
	<html:form action="/ToukanAction">



		<a class="square_btn"> ありがとうございます！<br /> ユーザが登録されました。<br />
			注意事項を守って楽しくご利用してください。
		</a>
		<br />
		<html:submit value="ログイン画面" styleClass="deg_btn" />


	</html:form>
</body>
</html:html>