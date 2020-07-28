package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board.bean.BoardDTO;

public class BoardDAO {
	
	private DataSource ds;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	

	public BoardDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public static BoardDAO boardDAO;
	
	public static BoardDAO instance;
	
	public static BoardDAO getInstance() {
		synchronized(BoardDAO.class){
			if (boardDAO == null) boardDAO=new BoardDAO();
			
		}
		return boardDAO;
	}
	
	public void write(Map<String,String> map) {
		String sql = "insert into board values(seq_board.nextval"
												+ ",?"
												+ ",?"
												+ ",?"
												+ ",?"
												+ ",?"
												+ ",seq_board.currval"//원글의 그룹번호 (seq)
												+ ",0"
												+ ",0"
												+ ",0"
												+ ",0"
												+ ",0"
												+ ",sysdate)";
//		String sql = "insert into board(id,name,email,subject,content) values(seq_board.nextval"
//				+ ",?"
//				+ ",?"
//				+ ",?"
//				+ ",?"
//				+ ",?"
//				+ ",select seq_board.currval";//원글의 그룹번호 (seq)
//			
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("name"));
			pstmt.setString(3, map.get("email"));
			pstmt.setString(4, map.get("subject"));
			pstmt.setString(5, map.get("content"));
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();	//null일 때 에러가 발생할 수 있기 때문에 
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public ArrayList<BoardDTO> boardList(int startPg, int endPg) {
		String sql = "select * from "
				+"(select rownum rn, tt.* from "
				+ "(select seq, id, name, email, subject, content, ref, lev, step, pseq, reply, hit, to_char(logtime,'YYYY.MM.DD') "
				+ "from board order by ref desc, step asc)tt) where rn>="+startPg+" and rn<="+endPg;
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setSeq(rs.getInt(2));
				boardDTO.setId(rs.getString(3));
				boardDTO.setName(rs.getString(4));
				boardDTO.setEmail(rs.getString(5));
				boardDTO.setSubject(rs.getString(6));
				boardDTO.setContent(rs.getString(7));
				boardDTO.setRef(rs.getInt(8));
				boardDTO.setLev(rs.getInt(9));
				boardDTO.setStep(rs.getInt(10));
				boardDTO.setPseq(rs.getInt(11));
				boardDTO.setReply(rs.getInt(12));
				boardDTO.setHit(rs.getInt(13));
				boardDTO.setLogtime(rs.getString(14));
				list.add(boardDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list=null;
		}finally {
			try {
				if(rs!=null)pstmt.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();	//null일 때 에러가 발생할 수 있기 때문에 
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return list;
	}
	
	public BoardDTO selectList(int seq) {
		String sql = "select seq, id, name, email, subject, content, ref, lev, step, pseq, reply, hit, to_char(logtime,'YYYY.MM.DD') from board where seq ="+seq;
		BoardDTO boardDTO=null;

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setSeq(rs.getInt(1));
				boardDTO.setId(rs.getString(2));
				boardDTO.setName(rs.getString(3));
				boardDTO.setEmail(rs.getString(4));
				boardDTO.setSubject(rs.getString(5));
				boardDTO.setContent(rs.getString(6));
				boardDTO.setRef(rs.getInt(7));
				boardDTO.setLev(rs.getInt(8));
				boardDTO.setStep(rs.getInt(9));
				boardDTO.setPseq(rs.getInt(10));
				boardDTO.setReply(rs.getInt(11));
				boardDTO.setHit(rs.getInt(12));
				boardDTO.setLogtime(rs.getString(13));
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)pstmt.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();	//null일 때 에러가 발생할 수 있기 때문에 
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return boardDTO;
	}
	public void boardUpdate(BoardDTO boardDTO, int seq) {
		String sql = "update board set subject =?"
										+",content=?"
										+" where seq=?";
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getSubject());
			pstmt.setString(2, boardDTO.getContent());
			pstmt.setInt(3, seq);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();	//null일 때 에러가 발생할 수 있기 때문에 
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}

	}
	public void hitUpdate(BoardDTO boardDTO, int seq) {
		int hit = boardDTO.getHit();
		hit=hit+1;
		boardDTO.setHit(hit);
		String sql = "update board set hit =?"
										+" where seq=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardDTO.getHit());
			pstmt.setInt(2, seq);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();	//null일 때 에러가 발생할 수 있기 때문에 
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}

	}
	public int getTotal(){
		String sql ="select count(*) from board";
		int su=0;
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				su=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();	//null일 때 에러가 발생할 수 있기 때문에 
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return su;
	}

	public void boardReply(Map<String, String> map) {
		//원글
		BoardDTO pDTO = selectList(Integer.parseInt(map.get("pseq")));
		
		
		//답글
		
		try {
			conn = ds.getConnection();
			String sql_step = "update board set step=step+1 where ref=? and step>?";

			//step 글순서 update
			pstmt = conn.prepareStatement(sql_step);
			pstmt.setInt(1, pDTO.getRef());
			pstmt.setInt(2, pDTO.getStep());
			pstmt.executeUpdate();
			pstmt.close();

			//insert
			String sql = "insert into board values("
						  + "seq_board.nextval"
						  + ",?"//id
						  + ",?"//name
						  + ",?"//email
						  + ",?"//subject
						  + ",?"//content
						  + ",?"//ref ==원글 ref
						  + ",?"//lev== 원글 lev+1
						  + ",?"//step==원글 step+1
						  + ",?"//pseq
						  + ",0"//reply
						  + ",0"//hit
						  + ",sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("name"));
			pstmt.setString(3, map.get("email"));
			pstmt.setString(4, map.get("subject"));
			pstmt.setString(5, map.get("content"));
			pstmt.setInt(6, pDTO.getRef());
			pstmt.setInt(7, pDTO.getLev()+1);
			pstmt.setInt(8, pDTO.getStep()+1);
			pstmt.setInt(9, pDTO.getSeq());
			pstmt.executeUpdate();
			pstmt.close();
			
			//답글 수 업데이트
			String sql_reply = "update board set reply=reply+1 where seq=?";
			pstmt = conn.prepareStatement(sql_reply);
			pstmt.setInt(1, pDTO.getSeq());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();	//null일 때 에러가 발생할 수 있기 때문에 
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
		}

	public void boardDelete(int seq) {
		String sql1="update board set reply=reply-1"
				+ " where seq=(select pseq from board where seq=?)";
		String sql2="update board set subject='[원글이 삭제되었습니다.]'||subject where pseq=?";

		String sql="delete board where seq=?";
		try {
			conn=ds.getConnection();
			
			pstmt=conn.prepareStatement(sql1);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt=conn.prepareStatement(sql2);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();	//null일 때 에러가 발생할 수 있기 때문에 
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
	}
}
