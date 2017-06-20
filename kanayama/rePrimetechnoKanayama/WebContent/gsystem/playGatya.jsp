<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
    <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="stylesheet" href="<html:rewrite page='/stylesheet/playCss.css'/>" type="text/css">
<title>�K�`��</title>
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
	<h1>�K�`��</h1>
	<p>�`�b�v��<bean:message key="chipRate1"/><bean:message key="chipAmount"/>����ăK�`����</p>
	<html:form action="/PlayG">
		<html:submit property="pageControl">
			<bean:message key="gatyaLabel"/>
		</html:submit><br/>
		<p>�`�b�v��<bean:message key="chipRate2"/><bean:message key="chipAmount"/>����ăK�`����</p>
		<html:submit property="pageControl">
			<bean:message key="gatyaLabel2"/>
		</html:submit>
		<p>���j���[��ʂɖ߂�</p>
		<html:submit property="pageControl">
			<bean:message key="return"/>
		</html:submit>
	</html:form>
	<h2>���j�b�g�̏o���m��</h2>
	<table border="1">
		<tbody>
			<tr><th>���A���e�B</th><th>1�̓�����̏o���m��</th><th>����</th></tr>
			<tr class="rare"><td>��6</td><td>1��</td><td>1��</td></tr>
			<tr class="rare"><td>��5</td><td>3��</td><td>1��</td></tr>
			<tr class="normal"><td>��4</td><td>12��</td><td>8��</td></tr>
		</tbody>
	</table>
</div>
</body>
</html:html>