package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);

	private static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper";

	@Inject
	private SqlSession sqlSession;

	@Override
	public void boardAdd(BoardVO vo) throws Exception {
		logger.debug("boardAdd(BoardVO vo) 호출");

		sqlSession.insert(NAMESPACE + ".boardAdd", vo);
	}

	@Override
	public int checkBoardBno(Integer bno) throws Exception {
		logger.debug("checkBoardBno(Integer bno) 호출");

		return sqlSession.selectOne(NAMESPACE + ".checkBoardBno", bno);
	}

	@Override
	public BoardVO boardRead(Integer bno) throws Exception {
		logger.debug("boardRead(Integer bno) 호출");

		return sqlSession.selectOne(NAMESPACE + ".boardRead", bno);
	}

	@Override
	public BoardVO lastBoardRead() throws Exception {
		logger.debug("lastBoardRead() 호출");

		return sqlSession.selectOne(NAMESPACE + ".lastBoardRead");
	}

	@Override
	public BoardVO boardReadTotal(Integer bno) throws Exception {
		logger.debug("boardRead(Integer bno) 호출");

		return sqlSession.selectOne(NAMESPACE + ".boardReadTotal", bno);
	}

	@Override
	public List<BoardVO> boardList() throws Exception {
		logger.debug("boardList() 호출");

		return sqlSession.selectList(NAMESPACE + ".boardList");
	}

	@Override
	public int boardModify(BoardVO vo) throws Exception {
		logger.debug("boardModify(BoardVO vo) 호출");

		return sqlSession.update(NAMESPACE + ".boardModify", vo);
	}

	@Override
	public int boardDelete(Integer bno) throws Exception {
		logger.debug("boardDelete(Integer bno) 호출");

		return sqlSession.delete(NAMESPACE + ".boardDelete", bno);
	}
}
