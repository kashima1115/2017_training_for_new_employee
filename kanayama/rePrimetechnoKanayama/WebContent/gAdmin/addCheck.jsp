<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
    <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>追加内容確認</title>
</head>
<body>
	<div id="main">
		<h1>追加内容確認</h1>
		<html:form action="/addCheck">
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