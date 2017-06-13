<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/normaldesign.css">
<title>�����ϐU���ꗗ</title>
</head>
<body>
	<jsp:include page="/pages/bank/header.jsp"></jsp:include>
	<h1>�����ϐU���ꗗ</h1>
	<logic:empty name="outstandingList"><h2 class="print">�����ϐU���͂���܂���</h2></logic:empty>
	<logic:notEmpty name="outstandingList">
	<table border=1>
		<tr><th>���ϗ\���</th><th>����於</th><th>�����ԍ�</th><th>�U�����z</th><th>���</th><tr>
		<logic:iterate id="ob" name="outstandingList" type="bean.OutstandingBean">
			<tr>
			<td><bean:write name="ob" property="reservedDate"/></td>
			<td><bean:write name="ob" property="furigana"/></td>
			<td><bean:write name="ob" property="otherAccountNumber" /></td>
			<td class="money"><bean:write name="ob" property="transCash" formatKey="numberformat.currency"/>�~</td>
			<td style="background-color:#ffff00;"><html:form action="/OutstandingAction">
				<html:hidden name="ob" property="reservedDate"/>
				<html:hidden name="ob" property="furigana"/>
				<html:hidden name="ob" property="otherAccountNumber" />
				<html:hidden name="ob" property="transCash"/>
				<html:hidden name="ob" property="transID"/>
				<html:submit styleId="small_alert_button" property="outstandingAction">
					<bean:message key="deleteOutstandingConf" />
				</html:submit>
			</html:form></td></tr>
		</logic:iterate>
	</table>
	</logic:notEmpty>

	<html:form action="/OutstandingAction">
	<div><html:submit styleId="middle_button" property="outstandingAction">
		<bean:message key="transHistory" />
	</html:submit>
	<html:submit styleId="middle_button" property="outstandingAction">
		<bean:message key="menu" />
	</html:submit>
	</div>
	</html:form>
</body>
</html:html>