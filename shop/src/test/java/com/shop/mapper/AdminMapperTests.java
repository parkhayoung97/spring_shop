package com.shop.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.BookVO;
import com.shop.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTests {

	@Autowired
	private AdminMapper mapper;

	/* 상품 등록 */
	
	 /* @Test public void bookEnroll() throws Exception {
	  
	  BookVO book = new BookVO();
	  
	  book.setBookName("mapper 테스트1"); book.setAuthorId(12345);
	  book.setPubleYear("2021-03-18"); book.setPublisher("출판사");
	  book.setCateCode("0231"); book.setBookPrice(20000); book.setBookStock(300);
	  book.setBookDiscount(0.23); book.setBookIntro("책 소개 ");
	  book.setBookContents("책 목차 ");
	  
	  mapper.bookEnroll(book); }*/
	 

	/* 카테고리 테스트 */
	/*@Test
	public void cateList() throws Exception {

		System.out.println("cateList().........." + mapper.cateList());
		
	}*/
	
	@Test
	public void goodsGetListTest() {
		
		Criteria cri = new Criteria();
		
		/* 검색 조건 */
		cri.setKeyword("테스트");
		
		/*검색 리스트*/
		List list = mapper.goodsGetList(cri);
		for(int i = 0; i < list.size(); i++) {
			System.out.println("result............." + i + ":" + list.get(i));
		}
		
		/* 상품 총 갯수 */
		int result = mapper.goodsGetTotal(cri);
		System.err.println("result............." + result);
	}
}
