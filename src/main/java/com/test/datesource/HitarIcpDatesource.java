package com.test.datesource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @类名 HitarIcpDatesource
 * @说明:
 * @作者 黄俊斌
 * @日期 2020/1/28
 **/
public class HitarIcpDatesource extends UnpooledDataSourceFactory {
    public HitarIcpDatesource() {
        this.dataSource = new HikariDataSource();
    }

}
