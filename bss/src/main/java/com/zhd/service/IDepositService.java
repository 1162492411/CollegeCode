package com.zhd.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhd.pojo.Deposit;

/**
 * <p>
 * 押金记录表 服务类
 * </p>
 *
 * @author zyg
 * @since 2018-02-05
 */
public interface IDepositService extends IService<Deposit> {

    boolean recharge(Deposit deposit) throws Exception;//用户缴纳押金

    boolean refund(Deposit deposit) throws Exception;//用户提取押金

}
