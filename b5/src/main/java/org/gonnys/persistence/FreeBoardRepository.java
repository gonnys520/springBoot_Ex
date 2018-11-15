package org.gonnys.persistence;


import java.util.List;

import org.gonnys.domain.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long>{
	
	@Query("select b from FreeBoard b where b.bno > 0")
	public List<FreeBoard> List(Pageable pageable);
	
	@Query("select b, count(r) from FreeBoard b "
			+ "left outer join FreeReply r on b.bno = r.board "
			+ "group by b")
	public Page<Object[]> listPage(Pageable pageable);
	
	
	@Query("select b, count(r) from FreeBoard b "
			+ "left outer join FreeReply r on b.bno = r.board "
			+ "where b.title like %?1% "
			+ "group by b ")
	public Page<Object[]> listByTitlePage(String keyword, Pageable pageable);
	
	
	@Query("select b, count(r) from FreeBoard b "
			+ "left outer join FreeReply r on b.bno = r.board "
			+ "where (b.title like %?1% or b.content like %?1% ) "
			+ "group by b ")
	public Page<Object[]> listByTitleOrContentPage(String keyword, Pageable pageable);

}
