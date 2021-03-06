package mvcBoard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListBoardCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = dao.listBoard();
		req.setAttribute("listBoard", list);
		return "WEB-INF/board/list.jsp";
	}

}
