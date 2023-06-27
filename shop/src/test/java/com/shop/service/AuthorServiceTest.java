package com.shop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.AuthorVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorServiceTest {

	@Autowired(required = true)
	private AuthorService service;

	/*
	 * @Test public void authorEnrollerTest() throws Exception {
	 * 
	 * AuthorVO author = new AuthorVO();
	 * 
	 * author.setNationId("01"); author.setAuthorName("테스트");
	 * author.setAuthorIntro("테스트 소개");
	 * 
	 * service.authorEnroll(author); }
	 */

	@Test
	public void authorModify() throws Exception {

		AuthorVO author = new AuthorVO();

		author.setAuthorId(1);
		System.out.println("(service)수정 전............" + service.authorGetDetail(author.getAuthorId()));

		author.setAuthorName("(service)수정");
		author.setNationId("01");
		author.setAuthorIntro("(service)소개 수정 하였습니다.");

		service.authorModify(author);
		System.out.println("(service)수정 후.........." + service.authorGetDetail(author.getAuthorId()));

	}

}
