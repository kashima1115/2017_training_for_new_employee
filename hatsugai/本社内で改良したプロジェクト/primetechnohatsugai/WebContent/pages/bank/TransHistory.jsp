<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/normaldesign.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/javascript/themes/blue/style.css">
<script src="${pageContext.request.contextPath}/pages/javascript/jquery-latest.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/pages/javascript/jquery.tablesorter.min.js" type="text/javascript"></script>
<script type="text/javascript">
   $(document).ready(function()
       {
           $("#sorttable").tablesorter({
        		   headers:{
        			   3:{sorter:false},
        			   4:{sorter:false},
        			   5:{sorter:false}
        		   }
       			});
       }
   );
</script>

<title>取引履歴</title>
</head>
<body>
	<jsp:include page="/pages/bank/header.jsp"></jsp:include>
	<h1>取引履歴</h1>
	<logic:empty name="historyList"><h2 class="print">取引履歴はありません</h2></logic:empty>
	<logic:notEmpty name="historyList">

	<table border=1 id="sorttable" class="tablesorter">
		<thead>
			<tr><th>日付</th><th>取引先名</th><th>口座番号</th><th>入金金額</th><th>出金金額</th><th>残高</th></tr>
		</thead>
		<tbody>
		<logic:iterate id="hb" name="historyList" type="bean.HistoryBean"
		offset="<%=(String)request.getAttribute(\"offset\") %>" length="<%=(String)request.getAttribute(\"view\") %>">
			<tr>
			<td><bean:write name="hb" property="transDate"/></td>
			<td><bean:write name="hb" property="furigana"/></td>
			<td><bean:write name="hb" property="otherAccountNo" /></td>
			<td class="money"><logic:equal name="hb" property="IOFlag" value="i">
			<bean:write name="hb" property="transCash" formatKey="numberformat.currency"/>円</logic:equal></td>
			<td class="money"><logic:notEqual name="hb" property="IOFlag" value="i">
			<bean:write name="hb" property="transCash" formatKey="numberformat.currency"/>円</logic:notEqual></td>
			<td class="money"><bean:write name="hb" property="lastBalance" formatKey="numberformat.currency" />円</td>
		</logic:iterate>
		</tbody>
	</table>
	</logic:notEmpty>
	<html:form action="/TransHistoryAction">
		<logic:greaterThan name="offset" value="0">
       <div> <html:submit styleId="small_button" property="transHistoryAction">
          <bean:message key="back" />
        </html:submit></div>
      	</logic:greaterThan>

      	<bean:define id="size"
        value="<%=(String)session.getAttribute(\"length\")%>" />

      	<logic:greaterThan name="size" value="0">
      	<logic:notEqual name="view" value="<%=Integer.toString(((java.util.List<bean.HistoryBean>)session.getAttribute(\"historyList\")).size())%>">
        <logic:lessThan name="offset" value="<%=size %>">
          <div><html:submit styleId="small_button" property="transHistoryAction" >
            <bean:message key="next" />
          </html:submit></div>
        </logic:lessThan>
        </logic:notEqual>
      </logic:greaterThan>

      <logic:present name="offset">
        <html:hidden property="offset" value="<%=(String)request.getAttribute(\"offset\") %>" />
      </logic:present>
      <div>
		<p class="left">‐（マイナス）が残高についている場合はキャッシング残高です。</p>
	  </div>

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
		<br/>
		<html:submit styleId="middle_button"property="transHistoryAction">
		<bean:message key="outstanding"/>
		</html:submit></div>
	</html:form>
	<div>
		<h5>照会条件変更</h5>
		<html:form action="/SelectSearchButtonTransHistory">
		<html:text property="tYear" maxlength="4" styleId="year"></html:text>年
		<html:select property="tMonth">
			 <logic:iterate id="mb" name="BankForm" property="monthList" type="LabelValueBean">
			    <html:option value="<%=mb.getValue() %>"><bean:write name="mb" property="label"/></html:option>
			 </logic:iterate>
		</html:select>月
		 <html:select property="tDate">
			 <logic:iterate id="db" name="BankForm" property="dayList" type="LabelValueBean">
			 	<html:option value="<%=db.getValue() %>"><bean:write name="db" property="label"/></html:option>
			 </logic:iterate>
		</html:select>日～

		<html:text property="tnYear" maxlength="4" styleId="year"></html:text>年
		<html:select property="tnMonth">
			 <logic:iterate id="mb" name="BankForm" property="monthList" type="LabelValueBean">
			    <html:option value="<%=mb.getValue() %>"><bean:write name="mb" property="label"/></html:option>
			 </logic:iterate>
		</html:select>月
		 <html:select property="tnDate">
			 <logic:iterate id="db" name="BankForm" property="dayList" type="LabelValueBean">
			 	<html:option value="<%=db.getValue() %>"><bean:write name="db" property="label"/></html:option>
			 </logic:iterate>
		</html:select>日
		<br/>
		<html:select property="view">
			<html:option value="40">40件</html:option>
			<html:option value="88">全件</html:option>
		</html:select>
		<html:submit styleId="small_button" property="selectSearchTransHistory">
			<bean:message key="search"/>
		</html:submit>
		<html:submit styleId="small_button" property="selectSearchTransHistory">
			<bean:message key="resetSearch"/>
		</html:submit>
	</html:form></div>
</body>
</html:html>