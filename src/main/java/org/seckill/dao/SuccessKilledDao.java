package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * Created by s on 2017/7/6.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     * @param secKillId
     * @param userPhone
     * @return
     */

    int insertSuccessKilled(@Param("secKillId") long secKillId,@Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param secKillId
     * @return
     */
    SuccessKilled queryByIdWithSecKill(@Param("secKillId") long secKillId,@Param("userPhone") long userPhone);

}
