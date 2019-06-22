package com.jnshu.tiles.service.impl;

import com.jnshu.tiles.dao.StudentMapper;
import com.jnshu.tiles.entity.Student;
import com.jnshu.tiles.service.StudnetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudnetService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Student record) {
        return 0;
    }

    @Override
    public Student selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> students = studentMapper.selectAll();
        return students;
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return 0;
    }
}
