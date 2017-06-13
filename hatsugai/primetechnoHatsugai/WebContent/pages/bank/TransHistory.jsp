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
<title>�������</title>
</head>
<body>
	<jsp:include page="/pages/bank/header.jsp"></jsp:include>
	<h1>�������</h1>
	<logic:empty name="historyList"><h2 class="print">��������͂���܂���</h2></logic:empty>
	<logic:notEmpty name="historyList">
	<table border=1>
		<tr><th>���t</th><th>����於</th><th>�����ԍ�</th><th>���o���z</th><th>�c��</th></tr>
		<logic:iterate id="hb" name="historyList" type="bean.HistoryBean"
		offset="<%=(String)request.getAttribute(\"offset\") %>" length="20">
			<tr>
			<td><bean:write name="hb" property="transDate"/></td>
			<td><bean:write name="hb" property="furigana"/></td>
			<td><bean:write name="hb" property="otherAccountNo" /></td>
			<td><logic:equal name="hb" property="IOFlag" value="i">+</logic:equal>
			<logic:notEqual name="hb" property="IOFlag" value="i">-</logic:notEqual>
			<bean:write name="hb" property="transCash" formatKey="numberformat.currency"/>�~</td>
			<td><bean:write name="hb" property="lastBalance" formatKey="numberformat.currency" />�~</td>
		</logic:iterate>
	</table>
	</logic:notEmpty>
	<div>
	<p class="left">�]�i�}�C�i�X�j���c���ɂ��Ă���ꍇ�̓L���b�V���O�c���ł��B</p></div>
	<html:form action="/TransHistoryAction">
		<logic:greaterThan name="offset" value="0">
       <div> <html:submit styleId="small_button" property="transHistoryAction">
          <bean:message key="back" />
        </html:submit></div>
      	</logic:greaterThan>

      	<bean:define id="size"
        value="<%=Integer.toString(((java.util.List<bean.HistoryBean>)session.getAttribute(\"historyList\")).size()-20)%>" />

      	<logic:greaterThan name="size" value="0">
        <logic:lessThan name="offset" value="<%=size %>">
          <div><html:submit styleId="small_button" property="transHistoryAction" >
            <bean:message key="next" />
          </html:submit></div>
        </logic:lessThan>
      </logic:greaterThan>

      <logic:present name="offset">
        <html:hidden property="offset" value="<%=(String)request.getAttribute(\"offset\") %>" />
      </logic:present>
	<br/>

		<div><html:submit styleId="middle_button"property="transHistoryAction">
		<bean:message key="menu"/>
		</html:submit>
		<html:submit styleId="middle_button"property="transHistoryAction">
		<bean:message key="transcation"/>
		</html:submit>
		<html:submit styleId="middle_button"property="transHistoryAction">
		<bean:message key="zandaka"/>
		</html:submit>
		<html:submit styleId="middle_button"property="transHistoryAction">
		<bean:message key="outstanding"/>
		</html:submit></div>
	</html:form>
</body>
</html:html>