package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by s on 2017/7/6.
 * 配置spring和junit整合，junit启动时加载spring-ioc容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SecKillDaoTest {
    //注入Dao实现类
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill secKill = seckillDao.queryById(id);
        System.out.println(secKill.getName());
        System.out.println(secKill);
    }

    /*
    org.apache.ibatis.binding.BindingException: Parameter 'offset' not found.
    Available parameters are [0, 1, param1, param2]
     */
    //List<SecKill> queryAll(int offset,int limit)
    //java没有保存形参的记录：queryAll(int offset,int limit) ->queryAll(arg0,arg1)

    @Test
    public void queryAll() throws Exception {
        List<Seckill> secKills = seckillDao.queryAll(0,100);
        for(Seckill secKill:secKills){
            System.out.println(secKill.toString());
        }
    }

    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L,killTime);
        System.out.println("updateCount="+updateCount);
    }


}