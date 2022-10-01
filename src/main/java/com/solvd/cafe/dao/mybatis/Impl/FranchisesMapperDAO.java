package com.solvd.cafe.dao.mybatis.Impl;

import com.solvd.cafe.connection.MyBatisConnectionUtil;
import com.solvd.cafe.dao.IFranchisesDAO;
import com.solvd.cafe.models.Franchises;
import org.apache.ibatis.session.SqlSession;

import java.util.LinkedList;
import java.util.List;

public class FranchisesMapperDAO implements IFranchisesDAO {


    @Override
    public Franchises getById(int id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        Franchises franchises = session.selectOne("src.main.resources.myBatis.mappers.FranchisesMapper.getById", id);
        session.close();
        return franchises;
    }

    @Override
    public List<Franchises> getAllRecords() {
        List<Franchises> franchises = new LinkedList<>();
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        franchises = session.selectList("src.main.resources.myBatis.mappers.FranchisesMapper.getAllRecords", franchises);
        session.close();
        return franchises;
    }

    @Override
    public void create(Franchises object) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.myBatis.mappers.FranchisesMapper.create", object);
        session.commit();
        session.close();

    }

    @Override
    public void update(Franchises franchises) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.myBatis.mappers.FranchisesMapper.update", franchises);
        session.commit();
        session.close();

    }

    @Override
    public void delete(int id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.myBatis.mappers.FranchisesMapper.delete", id);
        session.commit();
        session.close();
    }
}
