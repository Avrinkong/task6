package com.jnshu.tiles.service;

import com.jnshu.tiles.entity.Student;

import java.util.List;

public interface StudnetService {

    public int deleteByPrimaryKey(Long id);

    public int insert(Student record);

    public Student selectByPrimaryKey(Long id);

    public List<Student> selectAll();

    public int updateByPrimaryKey(Student record);
}
