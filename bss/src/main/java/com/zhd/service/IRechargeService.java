package com.zhd.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhd.pojo.Recharge;

/**
 * <p>
 * 充值记录表 服务类
 * </p>
 *
 * @author zyg
 * @since 2018-02-05
 */
public interface IRechargeService extends IService<Recharge> {

    boolean checkRecharge(Integer id);

    boolean recharge(Recharge recharge);//用户充值账户余额

}
