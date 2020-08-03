package com.test.business.impl;

import com.test.business.DealBusi;
import com.test.constant.DataStatusConstant;
import com.test.our.domain.Student;
import com.test.utils.SqlsessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @类名 DealBusiImpl
 * @说明:
 * @作者 黄俊斌
 * @日期 2020/2/1
 **/
public class DealBusiImpl implements DealBusi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DealBusiImpl.class);

    public void deal(List data) {

        List<Student> students = adApter(data);
        SqlSession sqlsession = null;
        for (Student student : students) {
            try {
                student.setName(student.getName() + "##test");
                sqlsession = SqlsessionUtils.getSqlsession("our");
                sqlsession.update("com.test.middle.our.StudentMapper.insertSelective", student);
                sqlsession.commit();
                modifyMiddle(student.getId(),DataStatusConstant.FINISHING);
        } catch (Exception e) {
                LOGGER.error("异常", e);
                e.printStackTrace();
                modifyMiddle(student.getId(), DataStatusConstant.ERROR);
            }finally {
                sqlsession.close();
            }
        }


    }

    private void modifyMiddle(int id, String status) {
        com.test.middle.domain.Student student = new com.test.middle.domain.Student();
        student.setId(id);
        student.setHd_symble(status);
        student.setDeal_time(new Date());
        SqlSession middle = SqlsessionUtils.getSqlsession("middle");
        try {
            middle.update("com.test.middle.mapper.StudentMapper.updateStatusById", student);
            middle.commit();
        } catch (Exception e) {
            LOGGER.error("修改中间表失败",e);
            e.printStackTrace();
        }finally {
            middle.close();
        }
    }

    private List<Student> adApter(List<com.test.middle.domain.Student> students) {

        List<Student> result = new ArrayList<Student>();
        for (com.test.middle.domain.Student student : students) {
            Student student1 = new Student();
            student1.setId(student.getId());
            student1.setBirth(student.getBirth());
            student1.setDepartment(student.getDepartment());
            student1.setName(student.getName());
            student1.setSex(student.getSex());
            student1.setCreate_time(new Date());
            result.add(student1);
        }
        return result;


    }
 }
