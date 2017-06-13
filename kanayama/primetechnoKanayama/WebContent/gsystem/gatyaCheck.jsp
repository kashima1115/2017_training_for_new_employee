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
<link rel="stylesheet" href="<html:rewrite page='/stylesheet/gatyaCheckCss.css'/>" type="text/css">
<title>�K�`�����s��̏�Ԋm�F</title>
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
	<h1>�K�`�����s��̏�Ԋm�F</h1>
	<strong><html:errors/></strong>
	<table>
		<tr><td><bean:message key="useChipLabel"/></td>
			<td><bean:write name="chipRate" /><bean:message key="chipAmount"/></td>
		</tr>
		<tr><td><bean:message key="playChipLabel"/></td>
			<td><bean:write name="haveChip" />��
				<bean:write name="afterUseChip" /><bean:message key="chipAmount"/></td>
		</tr>
	</table>
	<html:form action="/useCheck">
		<logic:greaterEqual name="afterUseChip" value="0">
			<html:submit property="pageControl">
				<bean:message key="stopLabel"/>
			</html:submit>

			<html:submit property="pageControl">
				<bean:message key="gatyaLabel"/>
			</html:submit>
		</logic:greaterEqual>

		<logic:lessThan name="afterUseChip" value="0">
			<html:submit property="pageControl">
				<bean:message key="stopLabel"/>
			</html:submit>
		</logic:lessThan>
	</html:form>
</body>
</html:html>