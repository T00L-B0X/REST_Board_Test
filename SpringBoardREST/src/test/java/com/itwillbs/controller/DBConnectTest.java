package com.itwillbs.controller;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.dao.BoardDAO;
import com.itwillbs.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DBConnectTest {

	private static final Logger logger = LoggerFactory.getLogger(DBConnectTest.class);

	@Inject
	private SqlSession sqlSession;

	@Inject
	private BoardDAO bdao;

	@Inject
	private BoardService bService;

	@Test
	public void testConnectDB() throws Exception {
		logger.debug("testConnectDB() 호출");

		logger.debug("sqlSession:" + sqlSession);
	}

	@Test
	public void testBoardRead() throws Exception {
		logger.debug("testBoardRead() 호출");

		int bno = 3;

		bService.boardReadTotal(bno);
	}

	@Test
	public void testBoardReadTotal1() throws Exception {
		logger.debug("testBoardReadTotal1() 호출");

		int bno = 3;

		bService.boardReadTotal1(bno);
	}

	// @Test
	public void testBoardList() throws Exception {
		logger.debug("testBoardList() 호출");

		bdao.boardList();
	}
}
