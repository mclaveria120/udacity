package com.udacity.jwdnd.course1.cloudstorage.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;

@Repository
@Mapper
public interface UserMapper {

	@Select("select * from user where username = #{username}" )
	public User findByUsername(@Param("username") String username);

	@Insert("insert into user(username,password, first_name,lastname) "
			+ "VALUES (#{user.username}, #{user.password}, #{user.firstName}, #{user.lastname})")
    @Options(useGeneratedKeys=true, keyColumn = "id", keyProperty = "user.id")
	public void save(@Param("user") User user);

	@Select("select * from user where id = #{id}" )
	public User findById(@Param("user_id")long userId);

}


