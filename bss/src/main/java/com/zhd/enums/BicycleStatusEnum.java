package com.zhd.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 车辆状态枚举类
 */
@AllArgsConstructor
@Getter
public enum BicycleStatusEnum implements IEnum{

    UNKNOWN(0,"未知"),

    UNUSED(1,"空闲中"),

    USING(2,"使用中"),

    WAIT_MOVE(3,"待移动"),

    WAIT_REPAIR(4,"待维修"),

    WAIT_SCRAP(5,"待报废"),

    WAIT_DELETE(6,"待删除");

    private int code;
    private String status;

    public static BicycleStatusEnum getByCode(int code){
        if(code <= 0) return UNKNOWN;
        for (BicycleStatusEnum bicycleStatusEnum : values()) {
            if(code == bicycleStatusEnum.getCode()) return bicycleStatusEnum;
        }
        return UNKNOWN;
    }

    public static int getByStatus(String status){
        if(StringUtils.isNotBlank(status)){
            switch(status){
                case "空闲中" : return UNUSED.getCode();
                case "使用中" : return USING.getCode();
                case "待移动" : return WAIT_MOVE.getCode();
                case "待维修" : return WAIT_REPAIR.getCode();
                case "待报废" : return WAIT_SCRAP.getCode();
                case "待删除" : return WAIT_DELETE.getCode();
            }
        }
        return -1;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
