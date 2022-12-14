package com.solvd.cafe.dao.mybatis.Impl;

import com.solvd.cafe.connection.MyBatisConnectionUtil;
import com.solvd.cafe.dao.IMenuDAO;
import com.solvd.cafe.models.Menu;
import org.apache.ibatis.session.SqlSession;


import java.util.LinkedList;
import java.util.List;

public class MenuMapperDAO implements IMenuDAO {

    @Override
    public Menu getById(int id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        Menu menu = session.selectOne("src.main.resources.myBatis.mappers.MenuMapper.getById", id);
        session.close();
        return menu;
    }

    @Override
    public List<Menu> getAllRecords() {
        List<Menu> menu = new LinkedList<>();
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        menu = session.selectList("src.main.resources.myBatis.mappers.MenuMapper.getAllRecords", menu);
        session.close();
        return menu;
    }

    @Override
    public void create(Menu object) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.myBatis.mappers.MenuMapper.create", object);
        session.commit();
        session.close();

    }

    @Override
    public void update(Menu menu) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.myBatis.mappers.MenuMapper.update", menu);
        session.commit();
        session.close();

    }

    @Override
    public void delete(int id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.myBatis.mappers.MenuMapper.delete", id);
        session.commit();
        session.close();

    }
}
