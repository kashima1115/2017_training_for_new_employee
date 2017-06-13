<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/normaldesign.css">
<title>振込完了</title>
</head>
<body>
	<jsp:include page="/pages/bank/header.jsp"></jsp:include>
	<h1>振込完了</h1>
	<h1 class="printer">振込手続きが完了しました。</h1>
	<html:form action="/TranscationResultAction">
		<div><html:submit styleId="middle_button" property="transcationResultAction">
			<bean:message key="continueTranscation"/>
		</html:submit>
		<html:submit styleId="middle_button" property="transcationResultAction">
			<bean:message key="menu"/>
		</html:submit></div>
	</html:form>
</body>
</html:html>