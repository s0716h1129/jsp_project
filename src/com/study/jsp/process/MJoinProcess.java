package com.study.jsp.process;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.MDao;
import com.study.jsp.dto.MDto;

@WebServlet("/MJoinProcess")
public class MJoinProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
		{
			request.setCharacterEncoding("UTF-8");
			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String email = request.getParameter("eMail");
			String address = request.getParameter("address");
			
			MDao dao = MDao.getInstance();
			MDto dto = new MDto();
			dto.setmId(id);
			dto.setmPw(pw);
			dto.setmName(name);
			dto.setmEmail(email);
			dto.setmAddress(address);
			dto.setmRdate(new Timestamp(System.currentTimeMillis()));
			
			String json_data = "";
			if(dao.confirmId(dto.getmId()) == MDao.MEMBER_EXISTENT) {
				json_data = "{\"code\":\"fail\",\"desc\":\"아이디가 이미 존재합니다. \"}";
			} else {
				int ri = dao.insertMember(dto);
				if(ri == MDao.MEMBER_JOIN_SUCCESS) {
					HttpSession session = request.getSession();
					session.setAttribute("id", dto.getmId());
					
					json_data = "{\"code\":\"success\",\"desc\":\"회원 가입을축하 합니다. \"}";
				}
			}
			
			//response.setContentType("text/html; charset=UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println(json_data);
			writer.close();
			
		}

}

