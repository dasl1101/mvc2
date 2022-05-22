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
			msg = "�ۼ��� ����!! �۸���������� �̵��մϴ�.";
			url = "list_board.do";
		}else if (res<0){
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է��� �ּ���";
			url = "updateForm_board.do?num=" + dto.getNum();
		}else {
			msg = "�ۼ��� ����!! �۳��뺸���������� �̵��մϴ�.";
			url = "content_board.do?num=" + dto.getNum();
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
