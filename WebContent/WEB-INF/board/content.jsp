<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="mvcBoard.*"%>
<!-- content.jsp -->

<%		 
		BoardDTO dto = (BoardDTO)request.getAttribute("getBoard"); 
%>
<html>
<head>
	<title>글내용보기</title>
</head>
<body>
	<div align="center">
		<b>글내용 보기</b><p>
		<table border="1" width="800"> 
			<tr>
				<th bgcolor="yellow" width="20%">글번호</th>
				<td align="center" width="30%"><%=dto.getNum()%></td>
				<th bgcolor="yellow" width="20%">조회수</th>
				<td align="center" width="30%"><%=dto.getReadcount()%></td>
			</tr>
			<tr>
				<th bgcolor="yellow" width="20%">작성자</th>
				<td align="center" width="30%"><%=dto.getWriter()%></td>
				<th bgcolor="yellow" width="20%">작성일</th>
				<td align="center" width="30%"><%=dto.getReg_date()%></td>
			</tr>
			<tr>
				<th bgcolor="yellow" width="20%">글제목</th>
				<td width="80%" colspan="3"><%=dto.getSubject()%></td>
			</tr>
			<tr>
				<th bgcolor="yellow" width="20%">글내용</th>
				<td width="80%" colspan="3"><%=dto.getContent()%></td>
			</tr>
			<tr bgcolor="yellow">
				<td colspan="4" align="right">
				<input type="button" value="답글쓰기"
					onclick="window.location='writeForm_board.do?num=<%=dto.getNum()%>'
					+'&re_group=<%=dto.getRe_group()%>'
					+'&re_step=<%=dto.getRe_step()%>'
					+'&re_level=<%=dto.getRe_level()%>'">
					<input type="button" value="글수정"
					onclick="window.location='updateForm_board.do?num=<%=dto.getNum()%>'">
					<input type="button" value="글삭제" 
					onclick="window.location='deleteForm_board.do?num=<%=dto.getNum()%>'">
					<input type="button" value="글목록" onclick="window.location='list_board.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>










