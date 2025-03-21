package com.cnu.teamProj.teamProj.manage.service;

import com.cnu.teamProj.teamProj.manage.dto.ClassInfoDto;
import com.cnu.teamProj.teamProj.manage.entity.ClassInfo;
import com.cnu.teamProj.teamProj.manage.repository.ClassRepository;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    private ClassRepository classRepository;
    @Autowired
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public int enrollClass(ClassInfoDto dto) {
        if(classRepository.existsById(dto.getClassId())) return 0;
        ClassInfo classInfo = new ClassInfo();
        classInfo.setProjCnt(0);
        classInfo.setProfessor(dto.getProfessor());
        classInfo.setClassName(dto.getClassName());
        classInfo.setClassId(dto.getClassId());

        try{
            classRepository.save(classInfo);
            return 1;
        } catch(SqlScriptException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
