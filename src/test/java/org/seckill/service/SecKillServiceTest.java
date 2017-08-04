package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})

public class SecKillServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService secKillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = secKillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000l;
        Seckill secKill = secKillService.getById(id);
        logger.info("secKill={}",secKill);
    }

    @Test
    public void exportSecKillUrl() throws Exception {
        long id = 1000l;
        Exposer exposer = secKillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer.toString());
        //exposer=Exposer{exposed=true, md5='92bb80166dae2a588aac7649a55ebcf6', secKillId=1000, now=0, start=0, end=0}
    }

    //集成测试代码完整逻辑，注意可重复执行
    @Test
    public void exportSecKilllogic() throws Exception, SeckillExecution {
        long id = 1001l;
        Exposer exposer = secKillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}",exposer.toString());
            long phone = 13161850429l;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution = secKillService.executeSeckill(id, phone, md5);
                logger.info("result={}", execution.toString());
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        }else {
            logger.warn("exposer={}",exposer.toString());
        }

        //exposer=Exposer{exposed=true, md5='92bb80166dae2a588aac7649a55ebcf6', secKillId=1000, now=0, start=0, end=0}
    }

    @Test
    public void executeSecKill() throws Exception, SeckillExecution {
        long id = 1000l;
        long phone = 13161850428l;
        String md5 = "92bb80166dae2a588aac7649a55ebcf6";
        try {
            SeckillExecution execution = secKillService.executeSeckill(id, phone, md5);
            logger.info("result={}", execution.toString());
        } catch (RepeatKillException e) {
            logger.error(e.getMessage());
        } catch (SeckillCloseException e) {
            logger.error(e.getMessage());
        }

    }

    @Test
    public void executeSeckillProcedure(){
        long seckillId = 1003;
        long phone = 13161850000l;
        Exposer exposer = secKillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution execution = secKillService.executeSeckillProcedure(seckillId, phone, md5);
            logger.info(execution.getStateInfo());
        }
        String md5 = null;
    }

}