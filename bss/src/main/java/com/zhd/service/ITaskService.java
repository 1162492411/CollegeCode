package com.zhd.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhd.pojo.Task;

/**
 * <p>
 * 调度任务表 服务类
 * </p>
 *
 * @author zyg
 * @since 2018-02-05
 */
public interface ITaskService extends IService<Task> {

    boolean checkTask(Integer id);

}
