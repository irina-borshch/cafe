package com.solvd.cafe.dao.mybatis.Impl;

import com.solvd.cafe.connection.MyBatisConnectionUtil;
import com.solvd.cafe.dao.ICafeAddressesDAO;
import com.solvd.cafe.models.CafeAddresses;
import org.apache.ibatis.session.SqlSession;

import java.util.LinkedList;
import java.util.List;

public class CafeAddressesMapperDAO implements ICafeAddressesDAO {



    @Override
    public CafeAddresses getById(int id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        CafeAddresses cafeAddresses = session.selectOne("src.main.resources.myBatis.mappers.CafeAddressesMapper.getById", id);
        session.close();
        return cafeAddresses;
    }

    @Override
    public List<CafeAddresses> getAllRecords() {
        List<CafeAddresses> cafeAddresses = new LinkedList<>();
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        cafeAddresses = session.selectList("src.main.resources.myBatis.mappers.CafeAddressesMapper.getAllRecords", cafeAddresses);
        session.close();
        return cafeAddresses;
    }

    @Override
    public void create(CafeAddresses object) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.myBatis.mappers.CafeAddressesMapper.create", object);
        session.commit();
        session.close();

    }

    @Override
    public void update(CafeAddresses cafeAddresses) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.myBatis.mappers.CafeAddressesMapper.update", cafeAddresses);
        session.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.myBatis.mappers.CafeAddressesMapper.delete", id);
        session.commit();
        session.close();

    }

}
