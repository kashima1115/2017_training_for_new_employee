<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
    <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>�Ǘ��ғo�^�m�F���</title>
</head>
<body>
	<div id="main">
		<h1>�Ǘ��ғo�^�m�F���</h1>
		<logic:notPresent name="NewUserForm" property="newId" scope="session">
			<logic:redirect forward="formFail"/>
		</logic:notPresent>
		<logic:notPresent name="NewUserForm" property="newPass" scope="session">
			<logic:redirect forward="formFail"/>
		</logic:notPresent>

		<html:form action="/adminCheck">
			<table>
				<tr><td><bean:message key="adminId"/></td>
					<td><strong><bean:write name="NewUserForm" property="newId"/></strong></td>
					</tr>
			</table><br/>

			<html:submit property="pageControl">
				<bean:message key="return"/>
			</html:submit>

			<html:submit property="pageControl">
				<bean:message key="regist"/>
			</html:submit>
		</html:form>
	</div>
</body>
</html:html>