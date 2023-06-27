package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AdminMapper;
import com.shop.model.BookVO;
import com.shop.model.CateVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public void bookEnroll(BookVO book) {

		log.info("(service)bookEnroll......");

		adminMapper.bookEnroll(book);

	}

	@Override
	public List<CateVO> cateList() {

		log.info("(service)cateList......");

		return adminMapper.cateList();
	}

}
