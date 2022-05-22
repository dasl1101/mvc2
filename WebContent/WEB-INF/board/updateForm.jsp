<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="mvcBoard.*"%>
<!-- updateForm.jsp -->
<%		 
		BoardDTO dto = (BoardDTO)request.getAttribute("getBoard"); 
%>
<html>
<head>
	<title>글쓰기</title>
	<script type="text/javascript">
		function check(){
			if (f.writer.value==""){
				alert("이름을 입력해 주세요!!")
				f.writer.focus()
				return false
			}
			if (f.subject.value==""){
				alert("제목을 입력해 주세요!!")
				f.subject.focus()
				return false
			}
			if (f.content.value==""){
				alert("내용을 입력해 주세요!!")
				f.content.focus()
				return false
			}
			if (f.passwd.value==""){
				alert("비밀번호를 입력해 주세요!!")
				f.passwd.focus()
				return false
			}
			return true
		}
	</script>
</head>
<body>
	<div align="center">
		<form name="f" action="updatePro_board.do" method="post" onsubmit="return check()">
			<input type="hidden" name="num" value="<%=dto.getNum()%>"/>
			<font size="3">글 수 정</font><p>
			<table border="1" width="500">
				<tr>
					<th bgcolor="yellow" width="20%">이 름</th>
					<td><input type="text" name="writer" value="<%=dto.getWriter()%>"></td>
				</tr>
				<tr>
					<th bgcolor="yellow" width="20%">제 목</th>
					<td><input type="text" name="subject" size="50" value="<%=dto.getSubject()%>"></td>
				</tr>
				<tr>
					<th bgcolor="yellow" width="20%">Email</th>
					<td><input type="text" name="email" size="50"  value="<%=dto.getEmail()%>"></td>
				</tr>
				<tr>
					<th bgcolor="yellow" width="20%">내 용</th>
					<td><textarea name="content" rows="11" cols="50"><%=dto.getContent()%>"</textarea></td>
				</tr>
				<tr>
					<th bgcolor="yellow" width="20%">비밀번호</th>
					<td><input type="password" name="passwd"></td>
				</tr>
				<tr bgcolor="yellow">
					<td align="center" colspan="2">
						<input type="submit" value="글수정">
						<input type="reset" value="다시작성">
						<input type="button" value="목록보기" onclick="window.location='list_board.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>