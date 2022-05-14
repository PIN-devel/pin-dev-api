package tk.pindev.blog.board.service;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import tk.pindev.blog.board.dto.BoardDto;

public interface BoardService {
	public boolean registBoard(BoardDto params);

	public JSONObject getBoardList() throws JSONException;

	public BoardDto getBoardDetail(Long boarId);

	public boolean deleteBoard(Long boardId);
}
