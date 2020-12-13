package com.li.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.domain.Notice;
import com.li.mapper.NoticeMapper;
import com.li.service.NoticeService;
 /**  
  * @program : coinexchange
  * @author : LiWenBo
  * @description : ${description}
  * @date : 2020-12-13 15:08:03
  */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService{

}
