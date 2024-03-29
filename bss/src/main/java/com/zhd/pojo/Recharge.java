package com.zhd.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 充值记录表
 * </p>
 *
 * @author zyg
 * @since 2018-02-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recharge implements Serializable, BaseModel{

    private static final long serialVersionUID = 1L;

    /**
     * 充值记录编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = {Update.class, Delete.class})
    private Integer id;
    /**
     * 用户编号
     */
    @TableField("user_id")
    @NotNull(groups = {Insert.class ,Update.class})
    private String userId;
    /**
     * 充值渠道
     */
    @NotNull(groups = {Insert.class, Update.class})
    private Integer type;
    /**
     * 充值时间
     */
    @TableField("recharge_time")
    private String rechargeTime;
    /**
     * 充值金额
     */
    @NotNull(groups = {Insert.class, Update.class})
    @Range(min = 1, max = 9999, groups = {Insert.class, Update.class})
    private BigDecimal amount;

}
