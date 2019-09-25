package com.study.jsp.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.BCommentCommand;
import com.study.jsp.command.BContentCommand;
import com.study.jsp.command.BDeleteCommand;
import com.study.jsp.command.BListCommand;
import com.study.jsp.command.BListSerchCommand;
import com.study.jsp.command.BModifyCommand;
import com.study.jsp.command.BReplyCommand;
import com.study.jsp.command.BReplyViewCommand;
import com.study.jsp.command.BWriteCommand;
import com.study.jsp.command.MManagerCommand;
import com.study.jsp.command.MMember_viewCommand;
import com.study.jsp.command.PCommand;

@WebServlet("*.do")
public class PFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
	{
		actionDo(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		PCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		HttpSession session = null;
		session = request.getSession();
		
		if (com.equals("/mLogin.do")) {
			viewPage = "MLogin.jsp";
		} else if (com.equals("/mJoin.do")) {
			viewPage = "MJoin.jsp";
		} else if (com.equals("/mMain.do")) {
			viewPage = "MMain.jsp";
		} else if (com.equals("/mManager.do")) {
			command = new MManagerCommand();
			command.execute(request, response);
			viewPage = "MManager.jsp";
		} else if (com.equals("/mLogout.do")) {
			session.invalidate();
			viewPage = "MLogin.jsp";
		} else if (com.equals("/mDelete.do")) {
			viewPage = "MDelete.jsp";
		} else if (com.equals("/mMember_view.do")) {
			command = new MMember_viewCommand();
			command.execute(request, response);
			viewPage = "MMember_view.jsp";
		}
		
		else if(com.equals("/bWrite_view.do")) {
			viewPage = "BWrite_view.jsp";
		} else if (com.equals("/bWrite.do")) {
			command = new BWriteCommand();
			command.execute(request, response);
			viewPage = "bList.do";
		} else if (com.equals("/bList.do")) {
			command = new BListCommand();
			command.execute(request, response);
			viewPage = "BList.jsp";
		} else if (com.contentEquals("/bListSerch.do")) {
			command = new BListSerchCommand();
			command.execute(request, response);
			viewPage = "BList.jsp";
		} else if (com.equals("/bContent_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "BContent_view.jsp";
		} else if (com.equals("/bDelete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "bList.do";
		} else if (com.equals("/bModify_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "BModify_view.jsp";
		} else if (com.equals("/bModify.do")) {
			command = new BModifyCommand();
			command.execute(request, response);
			
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "BContent_view.jsp";
		} else if (com.equals("/bDelete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "bList.do";
		} else if (com.equals("/bReply_view.do")) {
			command = new BReplyViewCommand();
			command.execute(request, response);
			viewPage = "BReply_view.jsp";
		} else if (com.equals("/bReply.do")) {
			command = new BReplyCommand();
			command.execute(request, response);
			viewPage = "bList.do";
		} else if (com.equals("/bComment.do")) {
			command = new BCommentCommand();
			command.execute(request, response);
			
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "BContent_view.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}