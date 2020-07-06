package com.wucc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wucc.entity.MPost;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
