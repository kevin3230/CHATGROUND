package com.members.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.members.model.*;

@WebServlet("/Members/Members.do")
@MultipartConfig
public class MembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MembersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		res.getWriter().append("Served at: ").append(req.getContextPath());
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = res.getWriter();
		HttpSession session = req.getSession();
		
		String action = req.getParameter("action");
		
		if("signUp".equals(action)) { //來自MembersSignUp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//從req取得value
				String memAcc = req.getParameter("account");
				String memPw = req.getParameter("password");
				String memNickName = req.getParameter("nickname");
				String memEmail = req.getParameter("email");
				Date memBirth = Date.valueOf(req.getParameter("birthday"));
				String memGender = req.getParameter("gender");
				Timestamp memRegdate = new Timestamp(System.currentTimeMillis());
				Integer memStatus = 1;
				String memSalt = req.getParameter("password");
				String memAuthority = "11111";
				byte[] memPic = getPicBytes(req.getPart("mempic"));
				
				/*********************檢查參數是否格式正確*********************/
				//檢查參數是否格式正確

				//檢查參數是否格式正確
				
				//取得salty與hashPW
				String[] saltyAndPW = MembersService.hashPW(memPw);
				memPw = saltyAndPW[1];
				memSalt = saltyAndPW[0];
				
				//參數放入MembersVO中
				MembersVO membersVO = new MembersVO();
				membersVO.setMemAcc(memAcc);
				membersVO.setMemPw(memPw);
				membersVO.setMemNickName(memNickName);
				membersVO.setMemEmail(memEmail);
				membersVO.setMemBirth(memBirth);
				membersVO.setMemGender(memGender);
				membersVO.setMemRegdate(memRegdate);
				membersVO.setMemStatus(memStatus);
				membersVO.setMemSalt(memSalt);
				membersVO.setMemAuthority(memAuthority);
				membersVO.setMemPic(memPic);
				
				//如果參數格式不正確,轉送回MembersSignUp.jsp註冊頁面
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("membersVO", membersVO);	//含有錯誤格式的VO也存入req
					errorMsgs.add("新增會員失敗:");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/members/MembersSignUp.jsp");
					failureView.forward(req, res);
				}
				
				/*********************透過MembersService新增會員到資料庫*********************/
				MembersService memSVC = new MembersService();
				memSVC.insert(membersVO);
				
				//新增完成,頁面轉交首頁
				res.sendRedirect("/ChatGround/front-end/index/index.jsp");
				
			}catch (Exception e){
				errorMsgs.add("新增會員失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/members/MembersSignUp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("signIn".equals(action)) { //來自MembersSignIn.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer memNo = (Integer)session.getAttribute("membersVO_memNo");
				if(null == memNo) {	//會員尚未登入,存入session
					String memAcc = req.getParameter("account");
					String memPw = req.getParameter("password");
					
					MembersService memSVC = new MembersService();
					MembersVO membersVO = memSVC.findByMemAcc(memAcc).get(0);
					if(MembersService.verifyPW(membersVO, memPw)){	//如果密碼正確,轉回到首頁或上一頁
						session.setAttribute("membersVO_memNo", membersVO.getMemNo());
						System.out.println("登入成功");
						res.sendRedirect("/ChatGround/front-end/index/index.jsp");
					
					}else{	//密碼錯誤,返回登入頁面
						req.setAttribute("membersVO", membersVO);
						errorMsgs.add("登入會員失敗:");
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/members/MembersSignIn.jsp");
						failureView.forward(req, res);
					}
					
					
				} else {	//已登入,轉回到首頁或上一頁
//					System.out.println("已登入");
					res.sendRedirect("/ChatGround/front-end/index/index.jsp");
				}
			}catch(Exception e){
//				System.out.println(e.getMessage());
				errorMsgs.add("登入會員失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/members/MembersSignIn.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		
		
		
		
	}

	
	
	
	// 將Part轉成byte[]
	protected static byte[] getPicBytes(Part part) throws IOException {
		InputStream is = part.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = is.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			baos.flush();
		}
		baos.close();
		is.close();
		
		byte[] camp_pic = baos.toByteArray(); // 將baos轉為資料流並存入byte[] camp_pic
		return camp_pic;
	}
}
