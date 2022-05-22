package mvcBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProBoardCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String num = req.getParameter("num");
		String passwd = req.getParameter("passwd");
		BoardDAO dao = new BoardDAO();
		int res = dao.deleteBoard(Integer.parseInt(num), passwd);
		String msg = null, url = null;
		if (res>0){
			msg = "글삭제 성공!! 글목록페이지로 이동합니다.";
			url = "list_board.do";
		}else if (res<0){
			msg = "비밀번호가 틀렸습니다. 다시 입력해 주세요";
			url = "deleteForm_board.do?num=" + num;
		}else {
			msg = "글삭제 실패!! 글내용보기페이지로 이동합니다.";
			url = "content_board.do?num=" + num;
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
