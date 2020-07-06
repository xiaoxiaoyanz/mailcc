package com.wucc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *
 * <p>
 *
 * @author wudingjia
 * @date 2020-07-04 14:40
 */
@Controller
public class IndexController extends BaseController{

	@RequestMapping({"","/","/index"})
	public String index() {

		// 1分页信息 2分类 3用户 4置顶  5精选 6排序
		IPage results = mPostService.paging(getPage(), null, null, null, null, "created");

		req.setAttribute("pageData", results);
		req.setAttribute("currentCategoryId",0);
		return "index";
	}
}
