package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.dao.BoardDAO;
import com.itwillbs.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Inject
	private BoardDAO bdao;

	@Override
	public void boardAdd(BoardVO vo) throws Exception {
		logger.debug("boardAdd(BoardVO vo) 호출");

		logger.debug("bdao: " + bdao);

		bdao.boardAdd(vo);
	}

	@Override
	public int checkBoardBno(Integer bno) throws Exception {
		logger.debug("checkBoardBno(Integer bno) 호출");

		return bdao.checkBoardBno(bno);
	}

	@Override
	public BoardVO boardRead(Integer bno) throws Exception {
		logger.debug("boardRead(Integer bno) 호출");

		return bdao.boardRead(bno);
	}

	@Override
	public BoardVO lastBoardRead() throws Exception {
		logger.debug("lastBoardRead()");

		return bdao.lastBoardRead();
	}

	@Override
	public BoardVO boardReadTotal(Integer bno) throws Exception {
		logger.debug("boardReadTotal(Integer bno) 호출");

		// 특정 글번호가 존재하는지 체크
		int result = checkBoardBno(bno);
		logger.debug("result: " + result);

		BoardVO vo = new BoardVO();

		if (result == 1) {
			// 전달 받은 bno에 해당하는 글 정보 가져오기
			vo = boardRead(bno);
		} else {
			// 해당 bno가 없는 경우 가장 마지막 글 정보 가져오기
			vo = lastBoardRead();
		}
		logger.debug("vo: " + vo);

		return vo;
	}

	@Override
	public BoardVO boardReadTotal1(Integer bno) throws Exception {
		logger.debug("boardReadTotal() 호출");

		return bdao.boardReadTotal(bno);
	}

	@Override
	public List<BoardVO> boardList() throws Exception {
		logger.debug("boardList() 호출");

		return bdao.boardList();
	}

	@Override
	public int boardModify(BoardVO vo) throws Exception {
		logger.debug("boardModify(BoardVO vo) 호출");

		return bdao.boardModify(vo);
	}

	@Override
	public int boardDelete(Integer bno) throws Exception {
		logger.debug("boardDelete(Integer bno) 호출");

		return bdao.boardDelete(bno);
	}

}
