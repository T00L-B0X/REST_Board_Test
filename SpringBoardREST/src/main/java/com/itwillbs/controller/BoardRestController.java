package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@RestController
@RequestMapping(value = "/boards")
public class BoardRestController {

	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);

	@Inject
	private BoardService bService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> boardAdd(@RequestBody BoardVO vo) throws Exception {
		logger.debug("boardAdd() - POST 호출");

		logger.debug("vo: " + vo);
		// logger.debug("bService: " + bService);

		ResponseEntity<String> result = null;

		try {
			bService.boardAdd(vo);
			result = ResponseEntity.ok().contentType(MediaType.valueOf("text/plain; charset=UTF-8")).body("글쓰기 성공");
		} catch (Exception e) {
			result = ResponseEntity.badRequest().contentType(MediaType.valueOf("text/plain; charset=UTF-8")).body("글쓰기 실패");
		}

		return result;
	}

	@RequestMapping(value = "/{bno}", method = RequestMethod.GET)
	public ResponseEntity<BoardVO> boardRead(@PathVariable("bno") int bno) throws Exception {
		logger.debug("boardRead() - GET 호출");

		logger.debug("bno:: " + bno);

		//		// 특정 글번호가 존재하는지 체크
		//		int result = bService.checkBoardBno(bno);
		//		logger.debug("result: " + result);
		//
		//		BoardVO vo = null;
		//
		//		if (result == 1) {
		//			// 전달 받은 bno에 해당하는 글 정보 가져오기
		//			vo = bService.boardRead(bno);
		//		} else {
		//			// 해당 bno가 없는 경우 가장 마지막 글 정보 가져오기
		//			vo = bService.lastBoardRead();
		//		}
		//		logger.debug("vo: " + vo);

		BoardVO vo = bService.boardReadTotal(bno);

		ResponseEntity<BoardVO> resp = null;

		if (vo != null) {
			resp = new ResponseEntity<BoardVO>(vo, HttpStatus.OK);
		} else {
			resp = new ResponseEntity<BoardVO>(vo, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> boardList() throws Exception {
		logger.debug("boardList() - GET 호출");

		List<BoardVO> boardList = bService.boardList();
		logger.debug("boardList: " + boardList.size());

		return new ResponseEntity<List<BoardVO>>(boardList, HttpStatus.OK);
	}

	@RequestMapping(value = "/{bno}", method = RequestMethod.PUT)
	public ResponseEntity<String> boardModify(@PathVariable("bno") int bno, @RequestBody BoardVO vo) throws Exception {
		logger.debug("boardModify() - PUT 호출");

		// logger.debug("vo: " + vo);
		vo.setBno(bno);
		// logger.debug("vo: " + vo);

		ResponseEntity<String> resp = null;

		int result = bService.boardModify(vo);

		if (result == 1) {
			resp = new ResponseEntity<String>("Update OK.", HttpStatus.OK);
		} else {
			resp = new ResponseEntity<String>("Update Noop.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resp;
	}

	@RequestMapping(value = "/{bno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> boardDelete(@PathVariable("bno") int bno) throws Exception {
		logger.debug("boardDelete() - DELETE 호출");

		ResponseEntity<String> resp = null;

		int result = bService.boardDelete(bno);

		if (result == 1) {
			resp = new ResponseEntity<String>("Delete OK.", HttpStatus.OK);
		} else {
			resp = new ResponseEntity<String>("Delete Noop.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resp;
	}

}
