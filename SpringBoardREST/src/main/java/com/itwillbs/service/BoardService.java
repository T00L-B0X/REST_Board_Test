package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {

	// 게시판 글쓰기
	public void boardAdd(BoardVO vo) throws Exception;

	// 글 번호 확인
	public int checkBoardBno(Integer bno) throws Exception;

	// 해당 글 조회
	public BoardVO boardRead(Integer bno) throws Exception;

	// 마지막 글 조회
	public BoardVO lastBoardRead() throws Exception;

	// 글 정보 조회
	public BoardVO boardReadTotal(Integer bno) throws Exception;

	// 글 정보 조회(boardReadTotal)
	public BoardVO boardReadTotal1(Integer bno) throws Exception;

	// 글 목록 조회(최신 10개)
	public List<BoardVO> boardList() throws Exception;

	// 글 수정
	public int boardModify(BoardVO vo) throws Exception;

	// 글 삭제
	public int boardDelete(Integer bno) throws Exception;

}
