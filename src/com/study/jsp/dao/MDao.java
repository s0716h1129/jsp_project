package com.study.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp.dto.BDto;
import com.study.jsp.dto.MDto;

public class MDao {
	DataSource dataSource;
	
	public MDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	public static final int MEMBER_DELETE= 1;
	
	private static MDao instance = new MDao();
	
	public static MDao getInstance() {
		return instance;
	}
	
	public int insertMember(MDto dto) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into members values(?,?,?,?,?,?)";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getmId());
			pstmt.setString(2, dto.getmPw());
			pstmt.setString(3, dto.getmName());
			pstmt.setString(4, dto.getmEmail());
			pstmt.setTimestamp(5, dto.getmRdate());
			pstmt.setString(6, dto.getmAddress());
			pstmt.executeUpdate();
			ri = MDao.MEMBER_LOGIN_SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int confirmId(String id) {
		int ri = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from members where id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()) {
				ri = MDao.MEMBER_EXISTENT;
			} else {
				ri = MDao.MEMBER_NONEXISTENT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return ri;
	}
	
	public int userCheck(String id, String pw) {
		int ri = 0;
		String dbPw;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from members where id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if (set.next()) {
				dbPw = set.getString("pw");
				if (dbPw.equals(pw)) {
					System.out.println("login ok");
					ri = MDao.MEMBER_LOGIN_SUCCESS;	// 로그인 ok
				} else {
					System.out.println("login fail");
					ri = MDao.MEMBER_LOGIN_PW_NO_GOOD; // 비밀번호 x
				} 
			} else {
				System.out.println("login fail");
				ri = MDao.MEMBER_LOGIN_IS_NOT;			// 아이디 x
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public MDto getMember (String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from members where id = ?";
		MDto dto = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dto = new MDto();
				dto.setmId(set.getString("id"));
				dto.setmPw(set.getString("pw"));
				dto.setmName(set.getString("name"));
				dto.setmEmail(set.getString("eMail"));
				dto.setmRdate(set.getTimestamp("rDate"));
				dto.setmAddress(set.getString("address"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public int userDel (String id, String pw) {
		int ri = 0;

		return ri;
	}
	
	public void deleteMeber(String mId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String query = "delete from members where Id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			int rn = pstmt.executeUpdate();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e2){
				e2.printStackTrace();
			}
		}
		
	}
	

	private Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection con = null;
		
		try {
			// lookup 함수의 파라메터는 context.xml에 설정된 name과 동일해야함
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public ArrayList<MDto> list() {
		ArrayList<MDto> mdtos = new ArrayList<MDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * " +
						   " from members " +
						   " order by id asc";
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				String mId = resultSet.getString("id");
				String mName = resultSet.getString("name");
				String mEmail = resultSet.getString("email");
				Timestamp mRdate = resultSet.getTimestamp("rdate");
				String mAddress = resultSet.getString("Address");
				MDto mdto = new MDto(mId, mName, mEmail, mRdate, mAddress);
				mdtos.add(mdto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) resultSet.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return mdtos;
	}

	public MDto memberView(String Id) {
		MDto mdto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from members where Id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Id);
			resultSet = pstmt.executeQuery();
			
			if (resultSet.next()) {
				String mId = resultSet.getString("Id");
				String mName = resultSet.getString("Name");
				String mEmail = resultSet.getString("email");
				Timestamp mRdate = resultSet.getTimestamp("rdate");
				String mAddress = resultSet.getString("address");
				
				mdto = new MDto(mId, mName, mEmail, mRdate, mAddress);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) resultSet.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return mdto;
	}
	
}

