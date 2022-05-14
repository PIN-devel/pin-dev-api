package tk.pindev.blog.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import tk.pindev.blog.board.dto.BoardDto;
import tk.pindev.blog.board.mapper.BoardMapper;

@SpringBootTest
public class MapperTest {
	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void testInsert() {
		BoardDto params = new BoardDto();
		params.setTitle("테스트제목");
		params.setContent("테스트 내용 입니다.");
		try {

			int result = boardMapper.insertBoard(params);
			System.out.println("결과 : " + result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testSelectList() {
		int boardTotalCount = 1;
		if (boardTotalCount > 0) {
			List<BoardDto> boardList = boardMapper.selectBoardList();
			if (CollectionUtils.isEmpty(boardList) == false) {
				for (BoardDto board : boardList) {
					System.out.println("=========================");
					System.out.println(board.getBoardId());
					System.out.println(board.getTitle());
//					String createdAt = new SimpleDateFormat("MM/dd/yyyy").format(board.getCreated_at());
					System.out.println(board.getCreatedAt());
//					String updatedAt = new SimpleDateFormat("MM/dd/yyyy").format(board.getUpdated_at());
					System.out.println(board.getUpdatedAt());
					System.out.println("=========================");
				}
			}
		}
	}

	@Test
	public void testOfSelectDetail() {
		BoardDto board = boardMapper.selectBoardDetail((long) 1);
		try {
			// String boardJson = new ObjectMapper().writeValueAsString(board);
			String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

			System.out.println("=========================");
			System.out.println(boardJson);
			System.out.println("=========================");

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testOfUpdate() {
		BoardDto params = new BoardDto();
		params.setTitle("1번 게시글 제목을 수정합니다.");
		params.setContent("1번 게시글 내용을 수정합니다.");
		params.setBoardId((long) 1);
		try {

			int result = boardMapper.updateBoard(params);
			if (result == 1) {
				BoardDto board = boardMapper.selectBoardDetail((long) 1);
				try {
					// String boardJson = new ObjectMapper().writeValueAsString(board);
					String boardJson = new ObjectMapper().registerModule(new JavaTimeModule())
							.writeValueAsString(board);

					System.out.println("=========================");
					System.out.println(boardJson);
					System.out.println("=========================");

				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testOfDelete() {
		int result = boardMapper.deleteBoard((long) 4);
		System.out.println("결과 : " + result);
//		if (result == 1) {
//			BoardDto board = boardMapper.selectBoardDetail((long) 3);
//			try {
//				// String boardJson = new ObjectMapper().writeValueAsString(board);
//				String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
//
//				System.out.println("=========================");
//				System.out.println(boardJson);
//				System.out.println("=========================");
//
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
//		}
	}
}
