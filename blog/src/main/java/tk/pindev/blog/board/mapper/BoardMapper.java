package tk.pindev.blog.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import tk.pindev.blog.board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public int insertBoard(BoardDto params);

	public List<BoardDto> selectBoardList();

	public BoardDto selectBoardDetail(Long boardId);

	public int updateBoard(BoardDto parmas);

	public int deleteBoard(Long boardId);

	public int selectBoardTotalCount();
}
