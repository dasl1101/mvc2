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
			msg = "�ۻ��� ����!! �۸���������� �̵��մϴ�.";
			url = "list_board.do";
		}else if (res<0){
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է��� �ּ���";
			url = "deleteForm_board.do?num=" + num;
		}else {
			msg = "�ۻ��� ����!! �۳��뺸���������� �̵��մϴ�.";
			url = "content_board.do?num=" + num;
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
