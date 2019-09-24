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

public class BDao {
	
	DataSource dataSource;
	
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void write(String bName, String bTitle, String bContent, String[] bType) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board " +
						   "(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bType) " +
						   "values " +
						   "(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			for (String type : bType) {
				pstmt.setString(4, type);
			}
			int rn = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public ArrayList<BDto> list() {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * " +
						   " from mvc_board " +
						   " order by bGroup desc, bStep asc";
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String bType = resultSet.getString("bType");
				
				String type = null;
				if (bType.equals("t1")) {
					type = "공지사항";
				} else if (bType.equals("t2")) {
					type = "자유 게시판";
				} else {
					type = "자료실";
				}

				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, type);
				dtos.add(dto);
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
		return dtos;
	}
	
	public ArrayList<BDto> listSerch(String[] bType_ser, String[] bSerch, String serch) {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "";
			
			if (bSerch[0].equals("sname")) {
				query = "select * from mvc_board where bType = ? and bname = ? order by bGroup desc, bStep asc";
			} else {
				query = "select * from mvc_board where bType = ? and btitle = ? order by bGroup desc, bStep asc";
			}
			
			pstmt = con.prepareStatement(query);
			for (String type : bType_ser) {
				pstmt.setString(1, type);
			}
			pstmt.setString(2, serch);
			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String bType = resultSet.getString("bType");
				
				String type = null;
				if (bType.equals("t1")) {
					type = "공지사항";
				} else if (bType.equals("t2")) {
					type = "자유 게시판";
				} else {
					type = "자료실";
				}
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, type);
				dtos.add(dto);
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
		return dtos;
	}

	public BDto contentView(String strID) {
		//upHit(strID);
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			if (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String bType = resultSet.getString("bType");
				
				String type = null;
				if (bType.equals("t1")) {
					type = "공지사항";
				} else if (bType.equals("t2")) {
					type = "자유 게시판";
				} else {
					type = "자료실";
				}
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, type);
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
		return dto;
		
	}

}
