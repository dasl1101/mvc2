<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*, mvcBoard.*"%> 
<!-- list.jsp -->
<html>
<head>
	<title>�۸��</title>
</head>
<body>
	<div align="center">
		<b>�� �� ��</b>
		<table border="1" width="800">
			<tr bgcolor="yellow">
				<td colspan="6" align="right"><a href="writeForm_board.do">�۾���</a></td>			
			</tr>
			<tr bgcolor="green">
				<th>��ȣ</th>
				<th width="30%">����</th>
				<th>�ۼ���</th>
				<th>�ۼ���</th>
				<th>��ȸ</th>
				<th>IP</th>
			</tr>
<%
		List<BoardDTO> listBoard = (List)request.getAttribute("listBoard");
		if (listBoard == null || listBoard.size()==0){%>
			<tr>
				<td colspan="6">��ϵ� �Խñ��� �����ϴ�.</td>
			</tr>
<%	}else{ 
			for(BoardDTO dto : listBoard){%>		
			<tr>
				<td align="right"><%=dto.getNum()%></td>
				<td>
<%			if (dto.getRe_level()>0){ %>
					<img src="img/level.gif" width="<%=dto.getRe_level()*10%>">
					<img src="img/re.gif">
<%			} %>			
					<a href="content_board.do?num=<%=dto.getNum()%>">
						<%=dto.getSubject()%>
					</a>
<%			if (dto.getReadcount()>10){ %>
					<img src="img/hot.gif">
<%			} %>		
				</td>
				<td align="center"><%=dto.getWriter()%></td>
				<td align="center"><%=dto.getReg_date()%></td>
				<td align="center"><%=dto.getReadcount()%></td>
				<td align="center"><%=dto.getIp()%></td>
			</tr>		
<%		}
		}%>						
		</table>
	</div>
</body>
</html>