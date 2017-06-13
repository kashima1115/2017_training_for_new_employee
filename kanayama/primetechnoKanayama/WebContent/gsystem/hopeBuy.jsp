<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
    <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="stylesheet" href="<html:rewrite page='/stylesheet/hopeBuyCss.css'/>" type="text/css">
<title>希望購入金額</title>
</head>
<body>
	<logic:notPresent name="logId" scope="session">
		<logic:redirect forward="logFail"/>
	</logic:notPresent>
	<logic:notPresent name="haveChip" scope="session">
		<logic:redirect forward="logFail"/>
	</logic:notPresent>

	<table class="label">
		<tr><td><bean:message key="loginId"/></td><td><bean:write name="logId" /></td>
			<td><bean:message key="chipLabel"/></td>
			<td><bean:write name="haveChip" /></td><td><bean:message key="chipAmount"/></td>
		</tr>
	</table>
	<h1>希望購入金額を選択してください</h1>
	<h2><bean:message key="warn"/>実際にはお金は消費されません</h2>
	<strong><html:errors/></strong>
	<html:form action="/hBuy">

	<table>
		<tr><td><html:radio property="buyChip" value="1"/>
			<bean:message key="100rate"/></td>
		</tr>
		<tr><td><html:radio property="buyChip" value="6"/>
			<bean:message key="500rate"/></td>
		</tr>
		<tr><td><html:radio property="buyChip" value="13"/>
			<bean:message key="1000rate"/></td>
		</tr>
		<tr><td><html:radio property="buyChip" value="40"/>
			<bean:message key="3000rate"/></td>
		</tr>
		<tr><td><html:radio property="buyChip" value="70"/>
			<bean:message key="5000rate"/></td>
		</tr>
		<tr><td><html:radio property="buyChip" value="150"/>
			<bean:message key="10000rate"/></td>
		</tr>
	</table><br/>
		<html:cancel value="戻る" styleClass="hope"/>
		<html:submit value="確認" styleClass="hope"/>
	</html:form>
</body>
</html>