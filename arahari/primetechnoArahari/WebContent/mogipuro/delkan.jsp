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

<title>削除完了画面</title>
</head>
<body>



<!-- 不正アクセス処理 -->
	<logic:notPresent name="logid" scope="session">
		<logic:redirect forward="logerror"/>
	</logic:notPresent>




	<h1>削除が完了しました</h1>
	<a class="square_btn"> アンケートの削除が正常に行われました。 </a>

	<br/>
	<html:form action="/DelkanAction">
		<html:submit property="dkanControl" styleClass="deg_btn">
			<bean:message key="kannext" />
		</html:submit>
		<br />


		<html:submit property="dkanControl" styleClass="deg_btn">
			<bean:message key="logout" />
		</html:submit>


	</html:form>

</body>
</html:html>