package com.wucc.service.impl;

import com.wucc.entity.MUser;
import com.wucc.mapper.MUserMapper;
import com.wucc.service.MUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wucc
 * @since 2020-07-04
 */
@Service
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements MUserService {

}
