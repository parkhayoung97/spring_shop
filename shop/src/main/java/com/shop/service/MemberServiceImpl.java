package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.MemberMapper;
import com.shop.model.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper memberMapper;

	@Override
	public void memberJoin(MemberVO vo) {
		memberMapper.memberJoin(vo);

	}

	@Override
	public int idCheck(String memberId) throws Exception {

		return memberMapper.idCheck(memberId);
	}

	@Override
	public MemberVO memberLogin(MemberVO member) throws Exception {
		
		return memberMapper.memberLogin(member);
	}

}
