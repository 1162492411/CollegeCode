package com.zhd.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zyg
 * @since 2018-02-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable,BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(value = "id", type = IdType.INPUT)
    @NotNull(groups = {Insert.class, Update.class, Delete.class})
    private String id;
    /**
     * 昵称
     */
    @NotBlank(groups = {Insert.class, Update.class})
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 账户类型
     */
    @Range(min = 0, max = 3, groups = {Insert.class})
    private Integer type;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 信用分
     */
    private Integer credit;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 账户余额
     */
    @Range(min = 0)
    @TableField("account_balance")
    private BigDecimal accountBalance;

    /**
     * 押金余额
     */
    @TableId("deposit_balance")
    private BigDecimal depositBalance;

    /**
     * 包月截止时间
     */
    @TableField("monthly_time")
    private String monthlyTime;
}
