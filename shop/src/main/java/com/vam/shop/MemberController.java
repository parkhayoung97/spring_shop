package com.vam.shop;

import static org.hamcrest.CoreMatchers.nullValue;

import java.lang.ProcessBuilder.Redirect;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.MemberVO;
import com.shop.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	BCryptPasswordEncoder pwEncoder;

	// 회원가입 페이지 이동
	@GetMapping("/join")
	public void joinGET() {

		logger.info("회원가입 페이지 진입");

	}

	// 로그인 페이지 이동
	@GetMapping("/login")
	public void loginGET() {

		logger.info("로그인 페이지 진입");

	}

	@PostMapping("/join")
	public String joinPOST(MemberVO member) throws Exception {

		/*
		 * logger.info("join 진입"); // 회우언 가입 서비스 실행 memberService.memberJoin(member);
		 * logger.info("회원 가입 성공");
		 */

		String rawPw = ""; // 인코딩 전 비밀번호
		String encodePw = ""; // 인코딩 후 비밀번호

		rawPw = member.getMemberPw(); // 비밀번호 데이터 얻음
		encodePw = pwEncoder.encode(rawPw); // 비밀번호 인코딩
		member.setMemberPw(encodePw); // 인코딩된 비밀번호 member객체에 다시 저장
		// 회원 가입
		memberService.memberJoin(member);

		return "redirect:/main";
	}

	// 아이디 중복 검사
	@PostMapping("/memberIdChk")
	@ResponseBody
	public String memberIdChkPOST(String memberId) throws Exception {

		logger.info("memberIdChk() 진입");

		int result = memberService.idCheck(memberId);

		if (result != 0) {
			return "fail"; // 중복 아이디가 존재하는 경우
		} else {
			return "success"; // 중복 아이지가 존재하지 않는 경우
		}

	} // memberIdChkPOST() 종료

	// 이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {
		logger.info("이메일 데이터 전송 확인");
		logger.info("인증 메일 : " + email);

		/* 인증번호(난수) 생성 */
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		logger.info("인증번호 " + checkNum);

		/* 이메일 보내기 */
		String setFrom = "pakhayoung97@naver.com";
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = "홈페이지를 방문해주셔서 감사합니다." + "<br><br>" + "인증 번호는 " + checkNum + "입니다." + "<br>"
				+ "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String num = Integer.toString(checkNum);
		return num;

	}

	@PostMapping("/login.do")
	public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception {

		// System.out.println("login 메서드 진입");
		// System.out.println("전달된 데이터:"+member);

		HttpSession session = request.getSession();
		String rawPw = "";
		String encodePw = "";
		MemberVO lvo = memberService.memberLogin(member);

		if (lvo != null) { // 아이디와 비밀번호 일치(로그인 성공시)

			rawPw = member.getMemberPw(); // 사용자가 제출한 비밀번호
			encodePw = lvo.getMemberPw(); // DB에 저장된 인코딩 된 비밀번호
			if (true == pwEncoder.matches(rawPw, encodePw)) {
				lvo.setMemberPw("");
				session.setAttribute("member", lvo);
				return "redirect:/main";

			} else {
				rttr.addFlashAttribute("result", 0);
				return "redirect:/member/login";
			}

		} else { // 일치하는 아이디가 없을때(로그인 실패시)

			rttr.addAttribute("result", 0);
			return "redirect:/member/login"; // 로그인 페이지로 이동

		}

		/*
		 * if (lvo == null) { int result = 0; rttr.addFlashAttribute("result", result);
		 * return "redirect:/member/login"; // 일치하지 않는 아이디,비밀번호 입력 경우 }
		 * session.setAttribute("member", lvo); return "redirect:/main"; // 일치하는
		 * 아이디,비밀번호 입력 경우(로그인 성공)
		 */
	}

	@GetMapping("/logout.do")
	public String logoutGET(HttpServletRequest request) throws Exception {

		logger.info("logoutMainGET메서드 진입");

		HttpSession session = request.getSession();

		session.invalidate();

		return "redirect:/main";
	}

	/* 비동기방식 로그아웃 메서드 */
	@PostMapping("/logout.do")
	@ResponseBody
	public void logoutPOST(HttpServletRequest request) throws Exception {
		logger.info("비동기 로그아웃 메서드 진입");

		HttpSession session = request.getSession();
		session.invalidate();
	}

}
