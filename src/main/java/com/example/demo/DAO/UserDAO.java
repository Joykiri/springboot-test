package com.example.demo.DAO;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.DTO.UserDTO;

@Mapper
public interface UserDAO {

    int getUser(UserDTO userDTO);

    String getUserName(UserDTO userDTO);
    String getPassword(UserDTO userDTO);
}