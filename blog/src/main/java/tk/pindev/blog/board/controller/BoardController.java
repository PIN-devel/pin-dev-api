package tk.pindev.blog.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.pindev.blog.board.dto.BoardDto;
import tk.pindev.blog.board.service.BoardService;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "http://localhost:3000")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@PostMapping
	private boolean postBoard(@RequestBody BoardDto params) {
		return boardService.registBoard(params);
	}

	@GetMapping
	private String getBoardList() throws JSONException {
		return boardService.getBoardList().toString();
	}

	@GetMapping("/{boardId}")
	private BoardDto getBoardDetail(@PathVariable Long boardId) throws JSONException {
		return boardService.getBoardDetail(boardId);
	}

	@PutMapping("/{boardId}")
	private boolean updateBoard(@PathVariable Long boardId, @RequestBody BoardDto params) {
		params.setBoardId(boardId);
		return boardService.registBoard(params);
	}

	@DeleteMapping("/{boardId}")
	private boolean deleteBoard(@PathVariable Long boardId) {
		return boardService.deleteBoard(boardId);
	}

}
