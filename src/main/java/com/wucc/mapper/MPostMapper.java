package com.wucc.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wucc.entity.MPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wucc.vo.PostVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wucc
 * @since 2020-07-04
 */
public interface MPostMapper extends BaseMapper<MPost> {

	IPage<PostVo> selectPosts(Page page, @Param(Constants.WRAPPER) QueryWrapper wrapper);

	PostVo selectOnePost(@Param(Constants.WRAPPER)QueryWrapper<MPost> wrapper);
}
