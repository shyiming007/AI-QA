package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.entity.Account;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from db_account where username = #{text} or email = #{text}")
    Account findAccountByNameOrEmail(String text);
}
