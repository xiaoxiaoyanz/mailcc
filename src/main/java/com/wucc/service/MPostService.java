package com.wucc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wucc.entity.MPost;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wucc.vo.PostVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wucc
 * @since 2020-07-04
 */
public interface MPostService extends IService<MPost> {

	void initWeekRank();
    
	IPage paging(Page page, Long categoryId, Long userId, Integer level, Boolean recommend, String order);
	
	PostVo selectOnePost(QueryWrapper<MPost> wrapper);

	void putViewCount(PostVo vo);
}
