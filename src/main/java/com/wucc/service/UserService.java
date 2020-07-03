package com.wucc.service;

import com.wucc.entity.User;
import com.wucc.pub.util.MailException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-07-01 12:10:19
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 登录
     * @param req
     * @param resp
     * @throws UnsupportedEncodingException
     */
    String login(HttpServletRequest req, HttpServletResponse resp) throws Exception;

    /**
     * 注册
     * @param req
     * @param resp
     */
    void register(HttpServletRequest req, HttpServletResponse resp) throws IOException, MailException;

}
