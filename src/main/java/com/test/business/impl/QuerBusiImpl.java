package com.test.business.impl;

import com.test.business.QuerBusi;
import com.test.middle.domain.Student;
import com.test.utils.SqlsessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @类名 QuerBusiImpl
 * @说明:
 * @作者 黄俊斌
 * @日期 2020/1/29
 **/
public class QuerBusiImpl implements QuerBusi
{
    private static final Logger LOGGER = LoggerFactory.getLogger(QuerBusiImpl.class);
    public List queryList(int count) {
        SqlSession middle = SqlsessionUtils.getSqlsession("middle");
        List<Object> list = null;
        try {
            list = middle.selectList("com.test.middle.mapper.StudentMapper.selectList", count);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getStackTrace().toString());
        } finally {
            if (middle != null) {
                middle.close();
            }

        }
        return list;
    }

    public int modifyListStatus(List data, String status) {
        List<Student> students = data;
        SqlSession middle = SqlsessionUtils.getSqlsession("middle");
        int count = 0;

        try {
            for (Student student : students) {
                student.setHd_symble(status);
                count += middle.update("com.test.middle.mapper.StudentMapper.updateByPrimaryKey", student);
                middle.commit();
            }
        } catch (Exception e) {
            middle.rollback();
            e.printStackTrace();
            LOGGER.error(e.getStackTrace().toString());

        } finally {
            if (middle != null) {
                middle.close();

            }
        }
        return count;
    }
}
