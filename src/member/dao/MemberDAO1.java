package member.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

public class MemberDAO1 {
	private SqlSessionFactory sqlSessionFactory;
	private static MemberDAO1 instance;

	
	public static MemberDAO1 getInstance() {
		if(instance == null) {
			synchronized(MemberDAO1.class) {//여러 사용자가 못 들어오게 막아라
				instance = new MemberDAO1();
			}
		}
		return instance;
	}
	
	public MemberDAO1() {
		try {
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public int write(MemberDTO memberDTO) {
		int su=0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		su = sqlSession.insert("memberSQL.write", memberDTO);
		sqlSession.commit();
		sqlSession.close();
		return su;	
	}


	
	public Map<String,String> login(String id, String pwd) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map <String, String> map = new HashMap<String, String>();
		Map <String, String> returnMap = new HashMap<String, String>();
		
		map.put("id",id);
		map.put("pwd", pwd);
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.login", map);
		sqlSession.close();		
		
		returnMap.put("name", memberDTO.getName());
		returnMap.put("email1", memberDTO.getEmail1());
		returnMap.put("email2", memberDTO.getEmail2());
		
		return returnMap;
	}
	
	/*
	public boolean isExistId(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		boolean check=true;
		String result = sqlSession.selectOne("memberSQL.login", id);
		
		if(result!=null) check=false;
		
		return check;
	}
	*/
	public MemberDTO getDTO(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO memberDTO = sqlSession.selectOne("memberSQL.getDTO", id);
		sqlSession.close();		

		return memberDTO;
	}
	
	public boolean memberUpdate(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("memberSQL.memberUpdate",memberDTO);
		boolean check=true;
		sqlSession.commit();
		sqlSession.close();		

		return check;
	}
	
	
	public List <ZipcodeDTO> getZipcodeList(String sido,
												String sigungu,
												String roadname) {
		Map <String,String> map = new HashMap<String, String>();
		map.put("sido", sido);
		map.put("sigungu", sigungu);
		map.put("roadname", roadname);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List <ZipcodeDTO> list = sqlSession.selectList("memberSQL.getZipcodeList", map);
		sqlSession.close();
		
		return list;
	}
	
	
}
