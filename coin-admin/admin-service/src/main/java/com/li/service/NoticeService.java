package com.li.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.domain.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : ${description}
 * @date : 2020-12-13 15:08:03
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 条件查询公告
     *
     * @param page      分页参数
     * @param title     公告的标签
     * @param startTime 公告创建的开始时间
     * @param endTime   公告创建的结束时间
     * @param status    公告当前的状态
     * @return
     */
    Page<Notice> findByPage(Page<Notice> page, String title, String startTime, String endTime, Integer status);
}
