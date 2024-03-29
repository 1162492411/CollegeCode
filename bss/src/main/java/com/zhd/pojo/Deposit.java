package com.zhd.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 押金记录表
 * </p>
 *
 * @author zyg
 * @since 2018-02-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Deposit implements Serializable, BaseModel{

    private static final long serialVersionUID = 1L;

    /**
     * 记录编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 押金金额
     */
    private BigDecimal amount;

    /**
     * 操作类型
     */
    @NotNull(groups = {Insert.class, Update.class})
    private Integer type;

    /**
     * 用户编号
     */
    @TableField("user_id")
    private String userId;

    /**
     * 操作时间
     */
    @TableField("operate_time")
    private String operateTime;

}
