<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
    <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="stylesheet" href="<html:rewrite page='/stylesheet/buyCheckCss.css'/>" type="text/css">
<title>購入確認画面</title>
</head>
<body>
<div id="main">
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
	<h1>購入確認画面</h1>
	<h2><bean:message key="warn"/>実際にはお金は消費されません</h2>
	<table>
		<tr><td><bean:message key="buyMoney"/></td>

			<logic:equal name="HopeBuyForm" property="buyChip" value="1">
				<td><bean:message key="100rate"/></td>
			</logic:equal>

			<logic:equal name="HopeBuyForm" property="buyChip" value="6">
				<td><bean:message key="500rate"/></td>
			</logic:equal>

			<logic:equal name="HopeBuyForm" property="buyChip" value="13">
				<td><bean:message key="1000rate"/></td>
			</logic:equal>

			<logic:equal name="HopeBuyForm" property="buyChip" value="40">
				<td><bean:message key="3000rate"/></td>
			</logic:equal>

			<logic:equal name="HopeBuyForm" property="buyChip" value="70">
				<td><bean:message key="5000rate"/></td>
			</logic:equal>

			<logic:equal name="HopeBuyForm" property="buyChip" value="150">
				<td><bean:message key="10000rate"/></td>
			</logic:equal>
		</tr>
		<tr><td><bean:message key="afterBuyChip"/></td>
			<td><bean:write name="haveChip" />→
				<bean:write name="afterChip" /><bean:message key="chipAmount"/></td>
		</tr>
	</table>
	<html:form action="/hbCheck">
		<html:submit  property="pageControl">
			<bean:message key="return"/>
		</html:submit>
		<html:submit property="pageControl">
			<bean:message key="buyChip"/>
		</html:submit>
	</html:form>
</div>
</body>
</html:html>