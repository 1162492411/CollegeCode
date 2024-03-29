package com.zhd.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhd.mapper.SupplierMapper;
import com.zhd.pojo.Supplier;
import com.zhd.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商表 服务实现类
 * </p>
 *
 * @author zyg
 * @since 2018-02-05
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public boolean checkSupplier(Integer id) {
        return supplierMapper.selectById(id) != null;
    }


    @Override
    public List<Map> selectAllSupplier() {
        return supplierMapper.selectAllSupplier();
    }
}
