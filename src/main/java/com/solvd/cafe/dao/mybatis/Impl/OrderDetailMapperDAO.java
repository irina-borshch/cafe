package com.solvd.cafe.dao.mybatis.Impl;

import com.solvd.cafe.connection.MyBatisConnectionUtil;
import com.solvd.cafe.dao.IOrderDetailsDAO;
import com.solvd.cafe.models.OrderDetails;
import org.apache.ibatis.session.SqlSession;

import java.util.LinkedList;
import java.util.List;

public class OrderDetailMapperDAO implements IOrderDetailsDAO {
    @Override
    public OrderDetails getById(int id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        OrderDetails orderDetails = session.selectOne("src.main.resources.myBatis.mappers.OrderDetailsMapper.getById", id);
        session.close();
        return orderDetails;
    }

    @Override
    public List<OrderDetails> getAllRecords() {
        List<OrderDetails> orderDetails = new LinkedList<>();
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        orderDetails = session.selectList("src.main.resources.myBatis.mappers.OrderDetailsMapper.getAllRecords", orderDetails);
        return orderDetails;
    }

    @Override
    public void create(OrderDetails object) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.myBatis.mappers.OrderDetailsMapper.create", object);
        session.commit();
        session.close();
    }

    @Override
    public void update(OrderDetails orderDetails) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.myBatis.mappers.OrderDetailsMapper.update", orderDetails);
        session.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.myBatis.mappers.OrderDetailsMapper.delete", id);
        session.commit();
        session.close();
    }
}
