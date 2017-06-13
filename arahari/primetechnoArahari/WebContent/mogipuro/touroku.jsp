<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
    <link rel="stylesheet" href="<html:rewrite page='/style/sheet.css'/>" type="text/css">

    <title>登録画面</title>
</head>
<body>
	<h1>ユーザ登録</h1>
	※　は入力必須です。<br/>
	<a class="error"> <html:errors />
	</a>
	<html:form action="/TourokuAction">


	ユーザ名※<html:text property="id"/><br/>
	パスワード※<html:password property="pass"/><br/>
	パスワード（確認用）<html:password property="passkaku"/><br/>

		<html:submit value="確認" styleClass="deg_btn"/><br/>
		<html:cancel value="戻る" styleClass="deg_btn"/>



</html:form>

</body>
</html:html>