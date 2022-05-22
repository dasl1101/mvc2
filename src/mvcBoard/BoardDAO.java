package mvcBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;



public class BoardDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private static DataSource ds;
	static {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	} 
	
	public List<BoardDTO> listBoard(){
		try {
			con = ds.getConnection();
			String sql = "select * from mvcboard order by re_group desc, re_step asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<BoardDTO> list = makeList(rs);
			return list;
		}catch(SQLException e) {
			System.out.println("listBoard 메소드 실행 중 오류 발생!!");
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (con != null) con.close();
			}catch(SQLException e) {}
		}
		return null;
	}
	
	public List<BoardDTO> makeList(ResultSet rs) throws SQLException {
		List<BoardDTO> list = new ArrayList<>();
		while(rs.next()) {
			BoardDTO dto = new BoardDTO();
			dto.setNum(rs.getInt("num"));
			dto.setWriter(rs.getString("writer"));
			dto.setEmail(rs.getString("email"));
			dto.setSubject(rs.getString("subject"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setReg_date(rs.getString("reg_date"));
			dto.setReadcount(rs.getInt("readcount"));
			dto.setContent(rs.getString("content"));
			dto.setIp(rs.getString("ip"));
			dto.setRe_group(rs.getInt("re_group"));
			dto.setRe_step(rs.getInt("re_step"));
			dto.setRe_level(rs.getInt("re_level"));
			list.add(dto);
		}
		return list;
	}
	
	public int insertBoard(BoardDTO dto) {
		try {
			con = ds.getConnection();
			String sql = null;
			if (dto.getNum() == 0) {
				sql = "select max(num) from mvcboard";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				rs.next();
				int maxNum = rs.getInt(1);
				dto.setRe_group(maxNum + 1);
			}else {
				sql = "update mvcboard set re_step=re_step+1 where re_step>? and re_group=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, dto.getRe_step());
				ps.setInt(2, dto.getRe_group());	
				ps.executeUpdate();
				dto.setRe_step(dto.getRe_step()+1);
				dto.setRe_level(dto.getRe_level()+1);
			}
			
			sql = "insert into mvcboard values(mvcboard_seq.nextval, ?,?,?,?,sysdate,0,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getWriter());
			ps.setString(2, dto.getEmail());
			ps.setString(3, dto.getSubject());
			ps.setString(4, dto.getPasswd());
			ps.setString(5, dto.getContent());
			ps.setString(6, dto.getIp());
			ps.setInt(7, dto.getRe_group());
			ps.setInt(8, dto.getRe_step());
			ps.setInt(9, dto.getRe_level());
			int res = ps.executeUpdate();
			return res;
		}catch(SQLException e) {
			System.out.println("insert 메소드 실행 중 오류 발생!!");
			e.printStackTrace();
		}finally {
			try {
				if (ps != null) ps.close();
				if (con != null) con.close();
			}catch(SQLException e) {}
		}
		return 0;
	}
	
	protected void plusReadcount(int num) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "update mvcboard set readcount = readcount + 1 where num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			ps.executeUpdate();
		}finally {
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
	
	public BoardDTO getBoard(int num, String mode) {
		try {
			if (mode.equals("content")) {
				plusReadcount(num);
			}
			con = ds.getConnection();
			String sql = "select * from mvcboard where num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			List<BoardDTO> list = makeList(rs);
			return list.get(0);
		}catch(SQLException e) {
			System.out.println("getBoard 메소드 실행 중 오류 발생!!");
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (con != null) con.close();
			}catch(SQLException e) {}
		}
		return null;
	}
	
	protected boolean isPassword(int num, String passwd){
		BoardDTO dto = getBoard(num, "password");
		if (dto.getPasswd().equals(passwd)) {
			return true;
		}else {
			return false;
		}
	}
	
	public int deleteBoard(int num, String passwd) {
		boolean isPassword = isPassword(num, passwd);
		if (isPassword) {
			try {
				con = ds.getConnection();
				String sql = "delete from mvcboard where num = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, num);
				int res = ps.executeUpdate();
				return res;
			}catch(SQLException e) {
				System.out.println("deleteBoard 메소드 실행 중 오류 발생!!");
				e.printStackTrace();
			}finally {
				try {
					if (ps != null) ps.close();
					if (con != null) con.close();
				}catch(SQLException e) {}
			}
			return 0;
		}else {
			return -1;
		}
	}
	
	public int updateBoard(BoardDTO dto) {
		boolean isPassword = isPassword(dto.getNum(), dto.getPasswd());
		if (isPassword) {
			try {
				con = ds.getConnection();
				String sql = "update mvcboard set writer=?, email=?, subject=?, content=? "
								+ "where num = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getWriter());
				ps.setString(2, dto.getEmail());
				ps.setString(3, dto.getSubject());
				ps.setString(4, dto.getContent());
				ps.setInt(5, dto.getNum());
				int res = ps.executeUpdate();
				return res;
			}catch(SQLException e) {
				System.out.println("updateBoard 메소드 실행 중 오류 발생!!");
				e.printStackTrace();
			}finally {
				try {
					if (ps != null) ps.close();
					if (con != null) con.close();
				}catch(SQLException e) {}
			}
			return 0;
		}else {
			return -1;
		}
	}
}








