package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;

@Mapper
@Repository
public interface NoteMapper{

	@Select("select * from note where user_id = #{userId}")
	List<Note> findAllByUserId(@Param("userId")long userId);
	
	@Delete("delete from note where id = #{noteId} and user_id = #{user_id}" )
	void deleteByIdAndUserId(@Param("noteId")long noteId, @Param("user_id")long userId);

	
    @Insert("insert into note(tittle,description, user_id) VALUES (#{note.tittle}, #{note.description}, #{user_id})")
    @Options(useGeneratedKeys=true, keyColumn = "id", keyProperty = "note.id")
     void save(@Param("note") Note note, @Param("user_id") long userid);
    
    
    @Select("select * from note where id = #{id} and user_id = #{user_id}" )
    Note findByIdAndUserId(@Param("id") long id, @Param("user_id")long userId);
    
    @Update("update note set tittle = #{note.tittle}, description = #{note.description} WHERE id = #{note.id}")
    public void updateNote(@Param("note")Note note);
}
