<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="mvcBoard.*"%>
<!-- content.jsp -->

<%		 
		BoardDTO dto = (BoardDTO)request.getAttribute("getBoard"); 
%>
<html>
<head>
	<title>�۳��뺸��</title>
</head>
<body>
	<div align="center">
		<b>�۳��� ����</b><p>
		<table border="1" width="800"> 
			<tr>
				<th bgcolor="yellow" width="20%">�۹�ȣ</th>
				<td align="center" width="30%"><%=dto.getNum()%></td>
				<th bgcolor="yellow" width="20%">��ȸ��</th>
				<td align="center" width="30%"><%=dto.getReadcount()%></td>
			</tr>
			<tr>
				<th bgcolor="yellow" width="20%">�ۼ���</th>
				<td align="center" width="30%"><%=dto.getWriter()%></td>
				<th bgcolor="yellow" width="20%">�ۼ���</th>
				<td align="center" width="30%"><%=dto.getReg_date()%></td>
			</tr>
			<tr>
				<th bgcolor="yellow" width="20%">������</th>
				<td width="80%" colspan="3"><%=dto.getSubject()%></td>
			</tr>
			<tr>
				<th bgcolor="yellow" width="20%">�۳���</th>
				<td width="80%" colspan="3"><%=dto.getContent()%></td>
			</tr>
			<tr bgcolor="yellow">
				<td colspan="4" align="right">
				<input type="button" value="��۾���"
					onclick="window.location='writeForm_board.do?num=<%=dto.getNum()%>'
					+'&re_group=<%=dto.getRe_group()%>'
					+'&re_step=<%=dto.getRe_step()%>'
					+'&re_level=<%=dto.getRe_level()%>'">
					<input type="button" value="�ۼ���"
					onclick="window.location='updateForm_board.do?num=<%=dto.getNum()%>'">
					<input type="button" value="�ۻ���" 
					onclick="window.location='deleteForm_board.do?num=<%=dto.getNum()%>'">
					<input type="button" value="�۸��" onclick="window.location='list_board.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>










