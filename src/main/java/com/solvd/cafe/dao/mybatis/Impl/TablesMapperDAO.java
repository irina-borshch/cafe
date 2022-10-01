package com.solvd.cafe.dao.mybatis.Impl;

import com.solvd.cafe.connection.MyBatisConnectionUtil;
import com.solvd.cafe.dao.ITablesDAO;
import com.solvd.cafe.models.Tables;
import org.apache.ibatis.session.SqlSession;

import java.util.LinkedList;
import java.util.List;

public class TablesMapperDAO implements ITablesDAO {
    @Override
    public Tables getById(int id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        Tables tables = session.selectOne("src.main.resources.myBatis.mappers.TablesMapper.getById", id);
        session.close();
        return tables;
    }

    @Override
    public List<Tables> getAllRecords() {
        List<Tables> tables = new LinkedList<>();
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        tables = session.selectList("src.main.resources.myBatis.mappers.TablesMapper.getAllRecords", tables);
        session.close();
        return tables;
    }

    @Override
    public void create(Tables object) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.myBatis.mappers.TablesMapper.create", object);
        session.commit();
        session.close();

    }

    @Override
    public void update(Tables tables) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.myBatis.mappers.TablesMapper.update", tables);
        session.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.myBatis.mappers.TablesMapper.create", id);
        session.commit();
        session.close();
    }


}
