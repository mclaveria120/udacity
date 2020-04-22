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

import com.udacity.jwdnd.course1.cloudstorage.entities.Credential;

@Mapper
@Repository
public interface CredentialMapper{

	@Select("select * from credential where user_id = #{userId}")
	List<Credential> findAllByUserId(@Param("userId")long userId);
	
	@Delete("delete from credential where id = #{credentialId} and user_id = #{user_id}" )
	void deleteByIdAndUserId(@Param("credentialId")long credentialId, @Param("user_id")long userId);

	
    @Insert("insert into credential(url, username, password, key, user_id) "
    		+ "values (#{credential.url}, #{credential.username} ,  #{credential.password} , #{credential.key}, #{user_id})")
    @Options(useGeneratedKeys=true, keyColumn = "id", keyProperty = "credential.id")
    void save(@Param("credential") Credential credential, @Param("user_id") long userid);
    
    
    @Select("select * from credential where id = #{id} and user_id = #{user_id}" )
    Credential findByIdAndUserId(@Param("id") long id, @Param("user_id")long userId);
    
    @Update("update credential set url = #{credential.url}, username = #{credential.username}, password = #{credential.password},"
    		+ " key = #{credential.key} "
    		+ " where id = #{credential.id}")
    public void updateCredential(@Param("credential")Credential credential);


}
