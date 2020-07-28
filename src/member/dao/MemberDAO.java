package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

public class MemberDAO {
	private static MemberDAO instance;
	
//	private String driver = "oracle.jdbc.driver.OracleDriver";
//	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	private String user = "java";
//	private String password = "itbank";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;	
	
	private DataSource ds;
	
	public static MemberDAO getInstance() {
		if(instance == null) {
			synchronized(MemberDAO.class) {//여러 사용자가 못 들어오게 막아라
				instance = new MemberDAO();
			}
		}
		return instance;
	}
	
	
	
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
			//tomcat일 경우 java:comp/env/를 꼭 붙인다.
			//lookup-꺼내 와서 datasource에 넘긴
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	

	public int write(MemberDTO memberDTO) {
		int su=0;
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPwd());
			pstmt.setString(4, memberDTO.getGender());
			pstmt.setString(5, memberDTO.getEmail1());
			pstmt.setString(6, memberDTO.getEmail2());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());
			pstmt.setString(10, memberDTO.getZipcode());
			pstmt.setString(11, memberDTO.getAddr1());
			pstmt.setString(12, memberDTO.getAddr2());
			su = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();	//null일 때 에러가 발생할 수 있기 때문에 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return su;
	}



	public Map<String,String> login(String id, String pwd) {
		Map <String, String> map=null;
		String sql = "select name, email1, email2 from member where id='"+id+"' and pwd='"+pwd+"'";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				map = new HashMap<String, String>();
				map.put("name",rs.getString("name"));
				map.put("email1", rs.getString("email1"));
				map.put("email2", rs.getString("email2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();	//null일 때 에러가 발생할 수 있기 때문에 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return map;
	}
	
	
	public boolean isExistId(String id) {
		boolean check=true;
		String sql = "select id from member where id='"+id+"'";
		//String sql = "select id from member where id=?";

		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getString("id")!=null) check=false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();	//null일 때 에러가 발생할 수 있기 때문에 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return check;
	}
	
	public MemberDTO getDTO(String id) {
		String sql = "select name,id,gender,email1,email2,tel1,tel2,tel3,zipcode,addr1,addr2 from member where id='"+id+"'";
		MemberDTO memberDTO = new MemberDTO();
		//MemberDTO memberDTO = null; error 발생 시 
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {//한 줄만 나오기 때문에 while를 사용할 필요가 없다.
				//meberDTO = new MemeberDTO();
				memberDTO.setName(rs.getString("name"));
				memberDTO.setId(rs.getString("id"));
				memberDTO.setGender(rs.getString("gender"));
				memberDTO.setEmail1(rs.getString("email1"));
				memberDTO.setEmail2(rs.getString("email2"));
				memberDTO.setTel1(rs.getString("tel1"));
				memberDTO.setTel2(rs.getString("tel2"));
				memberDTO.setTel3(rs.getString("tel3"));
				memberDTO.setZipcode(rs.getString("zipcode"));
				memberDTO.setAddr1(rs.getString("addr1"));
				memberDTO.setAddr2(rs.getString("addr2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();	//null일 때 에러가 발생할 수 있기 때문에 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return memberDTO;
	}
	
	public boolean update(MemberDTO memberDTO) {
		boolean check=true;

		String sql = "update member SET name=?"
				+ ", id=?"
				+ ",gender=?"
				+ ",email1=?"
				+ ",email2=?"
				+ ",tel1=?"
				+ ",tel2=?"
				+ ",tel3=?"
				+ ",zipcode=?"
				+ ",addr1=?"
				+ ",addr2=?"
				+ ",pwd=?"
				+ " where id = '"+memberDTO.getId()+"'";
		

		try {
			conn = ds.getConnection();
				
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setString(1,memberDTO.getName());
			pstmt.setString(2,memberDTO.getId());
			pstmt.setString(3,memberDTO.getGender());
			pstmt.setString(4,memberDTO.getEmail1());
			pstmt.setString(5,memberDTO.getEmail2());
			pstmt.setString(6,memberDTO.getTel1());
			pstmt.setString(7,memberDTO.getTel2());
			pstmt.setString(8,memberDTO.getTel3());
			pstmt.setString(9,memberDTO.getZipcode());
			pstmt.setString(10,memberDTO.getAddr1());
			pstmt.setString(11,memberDTO.getAddr2());
			pstmt.setString(12,memberDTO.getPwd());

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			check=false;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return check;
	}
	public ArrayList <ZipcodeDTO> getZipcodeList(String sido,
												String sigungu,
												String roadname) {
		ArrayList <ZipcodeDTO> list = new ArrayList <ZipcodeDTO>();
		
		String sql = "select * from newzipcode where sido like ? and nvl(sigungu,' ') like ? and roadname like ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+sido+"%");
			pstmt.setString(2, "%"+sigungu+"%");
			pstmt.setString(3, "%"+roadname+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipcodeDTO zipcodeDTO = new ZipcodeDTO();
				zipcodeDTO.setZipcode(rs.getString(1));
				zipcodeDTO.setSido(rs.getString(2));
				zipcodeDTO.setSigungu(rs.getString(3)==null ? "" : rs.getString(3));
				zipcodeDTO.setYubmyumdong(rs.getString(4));
				zipcodeDTO.setRi(rs.getString(5)==null ? "" : rs.getString(5));
				zipcodeDTO.setRoadname(rs.getString(6));
				zipcodeDTO.setBuildingname(rs.getString(7) == null ? "" : rs.getString(7));
				list.add(zipcodeDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			list=null;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		return list;
	}
	
	
}
