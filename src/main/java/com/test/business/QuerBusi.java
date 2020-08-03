package com.test.business;

import java.util.List;

/**
 * @类名 QuerBusi
 * @说明: 查询逻辑
 * @作者 黄俊斌
 * @日期 2020/1/28
 **/
public interface QuerBusi {

    List queryList(int count);

    int modifyListStatus(List data, String status);

}
