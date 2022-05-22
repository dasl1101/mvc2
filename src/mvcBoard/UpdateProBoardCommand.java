package mvcBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProBoardCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BoardDTO dto = new BoardDTO();
		dto.setNum(Integer.parseInt(req.getParameter("num")));
		dto.setWriter(req.getParameter("writer"));
		dto.setEmail(req.getParameter("email"));
		dto.setSubject(req.getParameter("subject"));
		dto.setPasswd(req.getParameter("passwd"));
		dto.setContent(req.getParameter("content"));
		BoardDAO dao = new BoardDAO();
		int res = dao.updateBoard(dto);
		String msg = null, url = null;
		if (res>0){
			msg = "글수정 성공!! 글목록페이지로 이동합니다.";
			url = "list_board.do";
		}else if (res<0){
			msg = "비밀번호가 틀렸습니다. 다시 입력해 주세요";
			url = "updateForm_board.do?num=" + dto.getNum();
		}else {
			msg = "글수정 실패!! 글내용보기페이지로 이동합니다.";
			url = "content_board.do?num=" + dto.getNum();
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
