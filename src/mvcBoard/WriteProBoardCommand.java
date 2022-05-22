package mvcBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteProBoardCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BoardDTO dto = new BoardDTO();
		dto.setWriter(req.getParameter("writer"));
		dto.setEmail(req.getParameter("email"));
		dto.setSubject(req.getParameter("subject"));
		dto.setPasswd(req.getParameter("passwd"));
		dto.setContent(req.getParameter("content"));
		dto.setIp(req.getRemoteAddr());
		dto.setNum(Integer.parseInt(req.getParameter("num")));
		dto.setRe_group(Integer.parseInt(req.getParameter("re_group")));
		dto.setRe_step(Integer.parseInt(req.getParameter("re_step")));
		dto.setRe_level(Integer.parseInt(req.getParameter("re_level")));
		BoardDAO dao = new BoardDAO();
		int res = dao.insertBoard(dto);    
		String msg = null, url = null;
		if (res>0) {
			msg = "게시글등록 성공!! 게시글목록페이지로 이동합니다.";
			url = "list_board.do";
		}else {
			msg = "게시글등록 실패!! 게시글목록페이지로 이동합니다.";
			url = "list_board.do";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";  
	}

}
