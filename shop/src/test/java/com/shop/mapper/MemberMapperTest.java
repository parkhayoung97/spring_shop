package com.shop.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
	
	/*@Test
	public void memberJoin() throws Exception{
	
		MemberVO member = new MemberVO();
		member.setMemberId("shop_test1");
		member.setMemberPw("shop_test1");
		member.setMemberName("shop_test1");
		member.setMemberMail("shop_test");
		member.setMemberAddr1("shop_test");
		member.setMemberAddr2("shop_test");
		member.setMemberAddr3("shop_test");
		
		memberMapper.memberJoin(member);
	
	
	}*/
	
	
	// 아이디 중복검사
		/*@Test
		public void memberIdChk() throws Exception{
			String id = "34";	// 존재하는 아이디
			String id2 = "test123";	// 존재하지 않는 아이디
			memberMapper.idCheck(id);
			memberMapper.idCheck(id2);
		}*/
	
	/*@Test
	public void memberLogin() throws Exception{
		MemberVO member=new MemberVO();
		
		member.setMemberId("admin23");
		member.setMemberPw("admin");
		
		memberMapper.memberLogin(member);
		System.out.println("결과 값:"+memberMapper.memberLogin(member));
		
	}*/
	
	
	
	
	
	
	
	

}
