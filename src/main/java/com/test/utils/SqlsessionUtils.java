package com.test.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @类名 SqlsessionUtils
 * @说明:
 * @作者 黄俊斌
 * @日期 2020/1/28
 **/
public class SqlsessionUtils {

    private static SqlSessionFactory ourSqlSessionFactory;
    private static SqlSessionFactory middleSqlSessionFactory;
    private static String OUR = "our";
    private static String MIDDLE = "middle";

    private final static String MIDDLE_SESSION = "mybatis-config-middle.xml";
    private final static String OUR_SESSION = "mybatis-config-our.xml";

    static {
        Reader middleSessionReader = null;
        Reader ourSessionReader = null;
        try {
            middleSessionReader = Resources.getResourceAsReader(MIDDLE_SESSION);
            ourSessionReader = Resources.getResourceAsReader(OUR_SESSION);
            middleSqlSessionFactory = new SqlSessionFactoryBuilder().build(middleSessionReader);
            ourSqlSessionFactory = new SqlSessionFactoryBuilder().build(ourSessionReader);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (middleSessionReader != null) {
                try {
                    middleSessionReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (ourSessionReader != null) {
                try {
                    ourSessionReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                }
        }
    }

    public static SqlSession getSqlsession(String code) {
        if (OUR.equals(code)) {
            return ourSqlSessionFactory.openSession();
        } else if (MIDDLE.equals(code)) {
            return middleSqlSessionFactory.openSession();
        }
        return null;
    }

}
