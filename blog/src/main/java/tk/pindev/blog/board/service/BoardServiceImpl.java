package tk.pindev.blog.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import tk.pindev.blog.board.dto.BoardDto;
import tk.pindev.blog.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean registBoard(BoardDto params) {
		int queryResult = 0;

		if (params.getBoardId() == null) {
			queryResult = boardMapper.insertBoard(params);
		} else {
			queryResult = boardMapper.updateBoard(params);
		}

		return queryResult == 1;
	}

	@Override
	public JSONObject getBoardList() throws JSONException {
		List<BoardDto> boardList = Collections.emptyList();

		int boardTotalCount = boardMapper.selectBoardTotalCount();

		JSONArray jsonArray = new JSONArray();
		if (boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList();
			for (BoardDto board : boardList) {
				JSONObject tmpObject = new JSONObject();
				tmpObject.put("boardId", board.getBoardId());
				tmpObject.put("title", board.getTitle());
				tmpObject.put("content", board.getContent());
				tmpObject.put("createdAt", board.getCreatedAt());
				tmpObject.put("updatedAt", board.getUpdatedAt());
				jsonArray.put(tmpObject);
			}
		}
		JSONObject ret = new JSONObject();
		ret.put("totalCnt", boardTotalCount);
		ret.put("data", jsonArray.toString());
		return ret;
	}

	@Override
	public BoardDto getBoardDetail(Long boardId) {
		return boardMapper.selectBoardDetail(boardId);
	}

	@Override
	public boolean deleteBoard(Long boardId) {
		int queryResult = boardMapper.deleteBoard(boardId);
		return queryResult == 1;
	}
}
