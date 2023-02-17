package org.kh.spring.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kh.spring.member.domain.Member;
import org.kh.spring.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	@Autowired // 의존성주입!!
	private MemberService mService;
	
	// /member/detail.do에서 동작할 메소드!
	@RequestMapping(value="/member/detail.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String printOneById(HttpServletRequest request, Model model) {
		try {
			//HttpSession session = request.getSession(); 이거 아냐
			String memberId = request.getParameter("memberId");
			Member member = mService.selectOneById(memberId);
			
			if(member != null) {
				model.addAttribute("member", member);
				return "member/detail";
			}else {
				model.addAttribute("msg", "아이디가 없는디..?");
				return "common/error";
				//리턴하는게 뷰리졸버에 태우는거래....
			}
			
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/error";
		}
		
	}
	
	// /member/list.do에서 동작할 메소드!
	@RequestMapping(value="/member/list.do", method=RequestMethod.GET)
	public String printMembers(Model model){
		try {
			List<Member> mList = mService.selectMembers();
			if(!mList.isEmpty()) {
				model.addAttribute("mList", mList); //매개변수에 모델 넣어줘야 모델 쓸수있어
				return "member/list";
			}else {
				model.addAttribute("msg", "데이터가 존재하지 않습니다");
				return "common/error";
			}
			
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/error";
		}
	}

	// /member/login.do로 접속했을때 이 메소드를 동작하고싶다!
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public String memberLogin(HttpServletRequest request, Model model) {
		try {
			String memberId = request.getParameter("member-id");
			String memberPw = request.getParameter("member-pw");
			Member mParam = new Member(memberId, memberPw);
			Member member = mService.checkMemberLogin(mParam);
			
			if (member != null) {
				// 성공하면 성공페이지. 이건 안쓸거니까 
				// request.setAttribute("msg", "성공"); 이것도 안해. 모델 쓸거야.
				//model.addAttribute("msg", "성공");
				// request.getRequestDispatcher("/WEB-INF/views/..."); 이렇게 하던거 안하고 뷰리졸버에 시킴
				//return "common/success";
				
				//성공하면 세션에 정보 저장
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", member);
				return "redirect:/home.do";
			} else {
				// 실패하면 에러페이지
				model.addAttribute("msg", "실패");
				return "common/error";
			}
			
		} catch (Exception e) {
			//어노테이션 없거나 하면 이 에러 뜸
			model.addAttribute("msg", e.getMessage());
			return "common/error";
		}
	}
	
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public String memberLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//response.sendRedirect("/index.jsp"); //예외처리해야하는애... 얜 안쓴대...
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/index.jsp";
	}
}
