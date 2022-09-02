package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor //final이 붙은 애들만 생성자 만들어준다
@RestController //데이터 반환
public class BoardsController {
	
	private final BoardsDao boardsDao; // 의존하는 코드를 컴퍼지션
	// private 모든 상태는 직접접근을 허용하면 안된다
	// IOC컨테이너 띄울때 리플렉션해서 코드분석해서 생성자 때린다
	// 매개변수 있으면 알아서 주입
	// new가 될때 final이 있으면 무조건 초기화를 해준다
	
	@PostMapping("/boards")
	public RespDto<?> insert(WriteDto writeDto){ // x-www-머시기(key=value)
		boardsDao.insert(writeDto);
		return new RespDto<>(1, "글쓰기 성공", null);
	}
	
	@GetMapping("/boards")
	public RespDto<?> getBoards(){
		return new RespDto<>(1,"글 조회 성공", boardsDao.findAll());
	}
	
	@GetMapping("/boards/{id}")
	public RespDto<?> getBoardsList(@PathVariable Integer id){
		return new RespDto<>(1,"글 한건 조회 성공", boardsDao.findById(id));
	}
	
	@PutMapping("/boards/{id}")
	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
		//영속화
		Boards boardsPS = boardsDao.findById(id);
		//변경
		boardsPS.글전체수정(updateDto);
		//업데이트 실행
		boardsDao.update(boardsPS);
		
		return new RespDto<>(1,"글 전체 수정 성공", null);
	}
	
	@PutMapping("/boards/{id}/content")
	public RespDto<?> updateContent(@PathVariable Integer id, String content){
		Boards boardsPS = boardsDao.findById(id);
		boardsPS.글한건수정(content);
		boardsDao.update(boardsPS);
		return new RespDto<>(1, "content 수정 완료", null);
	}
	
	@DeleteMapping("/boards/{id}")
	public RespDto<?> delete(@PathVariable Integer id){
		boardsDao.delete(id);
		return new RespDto<>(1, "글 삭제 완료", null);
	}

}
