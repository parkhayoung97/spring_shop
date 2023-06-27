package com.vam.shop;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.mapper.AdminMapper;
import com.shop.model.AuthorVO;
import com.shop.model.BookVO;
import com.shop.model.Criteria;
import com.shop.model.PageDTO;
import com.shop.service.AdminService;
import com.shop.service.AuthorService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AuthorService authorService;

	@Autowired
	private AdminService adminService;

	@GetMapping("/main")
	public void adminMainGET() throws Exception {

		logger.info("관리자 페이지 이동");
	}

	// 상품 관리
	@GetMapping("/goodsManage")
	public void goodsManageGET() throws Exception {

		logger.info("상품 관리 페이지 접속");
	}

	// 상품 등록
	@GetMapping("/goodsEnroll")
	public void goodsEnrollGET(Model model) throws Exception {

		logger.info("상품 등록 페이지 접속");

		ObjectMapper objm = new ObjectMapper();

		List list = adminService.cateList();

		String cateList = objm.writeValueAsString(list);

		model.addAttribute("cateList", cateList);

		logger.info("변경 전........." + list);
		logger.info("변경 후........." + cateList);
	}

	// 작가 관리 페이지 접속
	@GetMapping("/authorManage")
	public void authorManageGET(Criteria cri, Model model) throws Exception {
		logger.info("작가 관리 페이지 접속........" + cri);

		/* 작가 목록 출력 데이터 */
		List list = authorService.authorGetList(cri);

		if (!list.isEmpty()) {
			model.addAttribute("list", list); // 작가가 존재할 경우
		} else {
			model.addAttribute("listCheck", "empty"); // 작가가 존재하지 않을 경우
		}

		/* 페이지 이동 인터페이스 데이터 */
		int total = authorService.authorGetTotal(cri);

		PageDTO pageMaker = new PageDTO(cri, total);

		model.addAttribute("pageMaker", pageMaker);

		/*
		 * 페이지 이동 인터페이스 데이터 다른 예시 작가 목록 출력 데이터 List list =
		 * authorService.authorGetList(cri); model.addAttribute("list", list);
		 * model.addAttribute("pageMaker", new PageDTO(cri,authorGetTotal(cri)));
		 */
	}

	/* 작가 상세 페이지 */
	@GetMapping({ "/authorDetail", "/authorModify" })
	public void authorGetInfoGET(int authorId, Criteria cri, Model model) throws Exception {

		logger.info("authorDetail......." + authorId);

		/* 작가 관리 페이지 정보 */
		model.addAttribute("cri", cri);

		/* 선택 작가 정보 */
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));

	}

	// 작가 정보 수정
	@PostMapping("/authorModify")
	public String authorModifyPOST(AuthorVO author, RedirectAttributes rttr) throws Exception {

		logger.info("authorModifyPOST......." + author);

		int result = authorService.authorModify(author);

		rttr.addAttribute("modify_result", result);

		return "redirect:/admin/authorManage";
	}

	// 상품 등록
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(BookVO book, RedirectAttributes rttr) {

		logger.info("goodsEnrollPOST......." + book);
		
		System.out.println("book" + book);

		adminService.bookEnroll(book);

		rttr.addFlashAttribute("enroll_result", book.getBookName());

		return "redirect:/admin/goodsManage";
	}

	// 작가 검색 팝업창
	@GetMapping("/authorPop")
	public void authorPopGET(Criteria cri, Model model) throws Exception {

		logger.info("authorPopGET..........");

		cri.setAmount(5);

		/* 게시물 출력 데이터 */
		List list = authorService.authorGetList(cri);

		if (!list.isEmpty()) {
			model.addAttribute("list", list); // 작가가 존재할 경우
		} else {
			model.addAttribute("listCheck", "empty"); // 작가가 존재하지 않을 경우
		}

		/* 페이지 이동 인터페이스 데이터 */
		model.addAttribute("pageMaker", new PageDTO(cri, authorService.authorGetTotal(cri)));

	}

	// 작가 등록
	@GetMapping("/authorEnroll")
	public void authorEnrollGET() throws Exception {
		logger.info("작가 등록 페이지 접속");
	}

	@PostMapping("authorEnroll.do")
	public String authorEnrollPOST(AuthorVO author, RedirectAttributes rttr) throws Exception {

		logger.info("authorEnroll:" + author);

		// 작가 등록 쿼리 실행
		authorService.authorEnroll(author);

		rttr.addFlashAttribute("enroll_result", author.getAuthorName());

		return "redirect:/admin/authorManage";
	}
}
