package imageboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import imageboard.bean.ImageBoardDTO;

public class ImageBoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DataSource ds;
	
	public static ImageBoardDAO instance;
	
	public static ImageBoardDAO getInstance(){
		synchronized(ImageBoardDAO.class){
			if(instance==null)
				instance = new ImageBoardDAO();
		}
		return instance;
		
	}
	public ImageBoardDAO() {
		try {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void imageboardWrite(ImageBoardDTO imageBoardDTO) {
		String sql = "insert into imageboard values(seq_imageboard.nextval"
													+",?"
													+",?"
													+",?"
													+",?"
													+",?"
													+",?"
													+",sysdate)";
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,  imageBoardDTO.getImageId());
			pstmt.setString(2,  imageBoardDTO.getImageName());
			pstmt.setInt(3,  imageBoardDTO.getImagePrice());
			pstmt.setInt(4,  imageBoardDTO.getImageQty());
			pstmt.setString(5,  imageBoardDTO.getImageContent());
			pstmt.setString(6,  imageBoardDTO.getImage1());
			
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
				try {
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	
	}
	public List<ImageBoardDTO> imageboardList(int startNum, int endNum) {
		
		List<ImageBoardDTO> list = new ArrayList<ImageBoardDTO>();
		String sql ="select * from " 
					+"(select rownum rn, tt.* from" 
					+"(select * from imageboard order by seq desc)tt)" 
					+"where rn>=? and rn <=?";
		
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement(sql);//생
			
			
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();//실행
			
			while(rs.next()) {
				ImageBoardDTO imageboardDTO = new ImageBoardDTO();
				imageboardDTO.setSeq(rs.getInt("seq"));
				imageboardDTO.setImageId(rs.getString("imageId"));
				imageboardDTO.setImageName(rs.getString("imageName"));
				imageboardDTO.setImagePrice(rs.getInt("imagePrice"));
				imageboardDTO.setImageQty(rs.getInt("imageQty"));
				imageboardDTO.setImageContent(rs.getString("imageContent"));
				imageboardDTO.setImage1(rs.getString("image1"));
				imageboardDTO.setLogtime(rs.getDate("logtime"));

				
				list.add(imageboardDTO);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			list=null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public int getTotalA() {
		int totalA=0;
		String sql = "select count(*) from imageboard";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			totalA = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalA;
	}
	
	public void imageboardDelete(String[] seq) {
		String sql = "delete imageboard where seq=?";
		
		try {
			conn=ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			for(String data: seq) {
				pstmt.setInt(1, Integer.parseInt(data));
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
