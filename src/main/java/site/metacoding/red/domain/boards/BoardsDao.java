package site.metacoding.red.domain.boards;

import java.util.List;

import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.request.users.JoinDto;

public interface BoardsDao {
	public void insert(WriteDto writeDto);
	public Boards findById(Integer id);
	public List<Boards> findAll();
	public void update(Boards boards);
	public void delete(Integer id);
}
