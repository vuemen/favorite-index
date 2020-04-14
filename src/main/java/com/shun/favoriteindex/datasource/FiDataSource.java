package com.shun.favoriteindex.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.shun.favoriteindex.util.encrypt.FiEncrypt;

/**
 * 数据源
 */
public class FiDataSource extends DruidDataSource {

    @Override
    public void setPassword(String password) {
        super.setPassword(FiEncrypt.decrypt(password));
    }
}
