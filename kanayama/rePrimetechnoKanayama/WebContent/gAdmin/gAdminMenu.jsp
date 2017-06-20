<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
    <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>管理者メニュー</title>
</head>
<body>
	<div id="main">
		<logic:notPresent name="logId" scope="session">
			<logic:redirect forward="logFail"/>
		</logic:notPresent>

		<table>
			<tr><td><bean:message key="adminId"/></td><td><bean:write name="logId" /></td>
			</tr>
		</table>

		<h1>管理者メニュー</h1>
		<html:form action="/gAdminMenu">
			<p>新ユニットの登録はこちら</p>
			<html:submit property="pageControl">
				<bean:message key="newCharSet"/>
			</html:submit><br/>

			<p>ログアウトはこちら</p>
			<html:submit property="pageControl">
				<bean:message key="logoutLabel"/>
			</html:submit>
		</html:form>
	</div>
</body>
</html:html>