package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.udacity.jwdnd.course1.cloudstorage.entities.File;

@Mapper
@Repository
public interface FileMapper{

	@Select("select * from file where user_id = #{userId}")
	List<File> findAllByUserId(@Param("userId")long userId);
	
	@Delete("delete from file where id = #{fileId} and user_id = #{user_id}" )
	void deleteByIdAndUserId(@Param("fileId")long fileId, @Param("user_id")long userId);

    @Insert("insert into file(name,type,file_size,data, user_id)"
    		+ " VALUES (#{file.name}, #{file.type}, #{file.fileSize}, #{file.data}, #{user_id})")
    @Options(useGeneratedKeys=true, keyColumn = "id", keyProperty = "file.id")
     void save(@Param("file") File note, @Param("user_id") long userid);
    
    
    @Select("select * from file where id = #{id} and user_id = #{user_id}" )
    @Options()
    File findByIdAndUserId(@Param("id") long id, @Param("user_id")long userId);

    @Select("select * from file where name = #{name}" )
	File findByName(@Param("name")String name);
}
