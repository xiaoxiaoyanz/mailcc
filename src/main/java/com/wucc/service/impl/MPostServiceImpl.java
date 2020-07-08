package com.wucc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wucc.entity.MPost;
import com.wucc.mapper.MPostMapper;
import com.wucc.service.MPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wucc.utils.RedisUtil;
import com.wucc.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MPostServiceImpl extends ServiceImpl<MPostMapper, MPost> implements MPostService {

	@Autowired
	MPostMapper mPostMapper;
	@Autowired
	RedisUtil redisUtil;

	@Override
	public void initWeekRank() {



	}

	@Override
	public IPage<PostVo> paging(Page page, Long categoryId, Long userId, Integer level, Boolean recommend, String order) {
		if(level == null) {
			level = -1;
		}

		QueryWrapper wrapper = new QueryWrapper<MPost>()
				.eq(categoryId != null,  "category_id", categoryId)
				.eq(userId != null,  "user_id", userId)
				.eq(level == 0,  "level", 0)
				.gt(level > 0,  "level", 0)
				.orderByDesc(order != null, order);

		return mPostMapper.selectPosts(page, wrapper);
	}

	@Override
	public PostVo selectOnePost(QueryWrapper<MPost> wrapper) {
		return mPostMapper.selectOnePost(wrapper);
	}

	@Override
	public void putViewCount(PostVo vo) {

		String key = "rank:post:" + vo.getId();


		// 1、从缓存中获取viewcount
		Integer viewCount = (Integer) redisUtil.hget(key, "post:viewCount");

		// 2、如果没有，就先从实体里面获取，再加一
		if(viewCount != null) {
			vo.setViewCount(viewCount + 1);
		} else {
			vo.setViewCount(vo.getViewCount() + 1);
		}

		// 3、同步到缓存里面
		redisUtil.hset(key, "post:viewCount", vo.getViewCount());

	}
}
