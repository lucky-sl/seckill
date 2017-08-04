package org.seckill.exception;

/**
 * 秒杀关闭异常
 * Created by s on 2017/7/30.
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillCloseException(String message) {

        super(message);
    }
}
