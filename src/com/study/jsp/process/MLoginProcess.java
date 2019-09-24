package com.study.jsp.process;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.MDao;
import com.study.jsp.dto.MDto;

@WebServlet("/MLoginProcess")
public class MLoginProcess extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
		{
			request.setCharacterEncoding("UTF-8");
			
			String json_data = "";
			MDao dao = MDao.getInstance();
			MDto dto = new MDto();
			HttpSession session = request.getSession();

			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = "";

			dto.setmId(id);
			dto.setmPw(pw);

			int checkNum = dao.userCheck(id, pw);
			if(checkNum == -1) {
				json_data = "{\"code\":\"fail\",\"desc\":\"아이디가 존재하지 않습니다. \"}";
					
			} else if(checkNum == 0) {
				json_data = "{\"code\":\"fail\",\"desc\":\"비밀번호 오류 \"}";

			} else if(checkNum == 1) {
				dto = dao.getMember(id);
					
				name = dto.getmName();

				session.setAttribute("id", id);
				session.setAttribute("name", name);
				session.setAttribute("ValidMem", "yes");
					
				json_data = "{\"code\":\"success\",\"desc\":\"메인화면으로 이동합니다. \"}";
			}
			
			
			//response.setContentType("text/html; charset=UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println(json_data);
			writer.close();
		}
}