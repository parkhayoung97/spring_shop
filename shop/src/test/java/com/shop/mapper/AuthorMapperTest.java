package com.shop.mapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.AuthorVO;
import com.shop.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorMapperTest {

	@Autowired
	private AuthorMapper mapper;

	/*
	 * @Test public void authorEnroll() throws Exception {
	 * 
	 * AuthorVO author = new AuthorVO();
	 * 
	 * author.setNationId("01"); author.setAuthorName("테스트");
	 * author.setAuthorIntro("테스트 소개");
	 * 
	 * mapper.authorEnroll(author);
	 * 
	 * }
	 */

	/* 작가 목록 테스트 */
	/*
	 * @Test public void authorGetList() throws Exception {
	 * 
	 * Criteria cri = new Criteria(3, 10); // 3페이지&10개 행 표시
	 * 
	 * List<AuthorVO> list = mapper.authorGetList(cri);
	 * 
	 * for (int i = 0; i < list.size(); i++) { System.out.println("list" + i +
	 * "..............." + list.get(i)); } }
	 */

	/*
	 * @Test public void authorGetTotal() throws Exception {
	 * 
	 * Criteria cri = new Criteria(); cri.setKeyword("박하영");
	 * 
	 * int total = mapper.authorGetTotal(cri); System.out.println("total......" +
	 * total); }
	 */

	/*
	 * @Test public void authorGetDetail() {
	 * 
	 * int authorId = 30;
	 * 
	 * AuthorVO author = mapper.authorGetDetail(authorId);
	 * 
	 * System.out.println("author.........." + author); }
	 */

	@Test
	public void authorModify() {

		AuthorVO author = new AuthorVO();

		author.setAuthorId(1);
		System.out.println("수정 전............" + mapper.authorGetDetail(author.getAuthorId()));

		author.setAuthorName("수정");
		author.setNationId("01");
		author.setAuthorIntro("소개 수정 하였습니다.");

		mapper.authorModify(author);
		System.out.println("수정 후.........." + mapper.authorGetDetail(author.getAuthorId()));
	}
}
