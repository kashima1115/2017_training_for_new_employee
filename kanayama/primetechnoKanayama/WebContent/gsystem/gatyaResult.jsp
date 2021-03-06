<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
    <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
    <%@ page import="java.lang.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="stylesheet" href="<html:rewrite page='/stylesheet/gatyaResultCss.css'/>" type="text/css">
<title>ガチャ結果</title>
</head>
<body>
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
	<h1>ガチャ結果</h1>
	<bean:define id="imageFile" name="image" type="java.lang.String" scope="session"/>
	<html:img page="<%=imageFile %>"/>

	<logic:greaterEqual name="select" value="2">
		<h2  Class="normal"><bean:write name="item" scope="session" /></h2>
	</logic:greaterEqual>

	<logic:lessThan name="select" value="2">
		<h2  Class="rare"><bean:write name="item" scope="session"/></h2>
	</logic:lessThan>

	<html:form action="/gatyaResult">
		<p>メニュー画面はこちら</p>
		<html:submit property="pageControl">
			<bean:message key="menuLabel"/>
		</html:submit>
		<p>ログアウトはこちら</p>
		<html:submit property="pageControl">
			<bean:message key="logoutLabel"/>
		</html:submit>
	</html:form>

</body>
</html:html>