package com.wucc.service.impl;

import com.wucc.entity.User;
import com.wucc.dao.UserDao;
import com.wucc.pub.util.CheckImg;
import com.wucc.pub.util.MailException;
import com.wucc.pub.util.StringUtil;
import com.wucc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-07-01 12:10:19
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String id) {
        return userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public String login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String errorMsg = null;
        try {
            //从登录页面拿到用户输入的用户名
            String name = req.getParameter("user");
            //从登录页面拿到用户输入的密码
            String pwd = req.getParameter("password");
            //在控制台上输出看是否拿到的帐号密码
            /*System.out.println("用户名：" +name);
            System.out.println("密码："+ pwd);*/

            User user = new User();
            if(StringUtil.isNotEmpty(name)){
                user.setName(name);
                if(StringUtil.isNotEmpty(pwd)){
                    user.setPssword(pwd);
                    List<User> list = userDao.queryAll(user);
                    //判断用户输入的帐号是否在数据库中
                    if (CollectionUtils.isEmpty(list)){
                        errorMsg = "用户名或密码有误，请重新登陆！";
                    }
                }else{
                    errorMsg = "密码不能为空！";
                }
            }else{
                errorMsg = "用户名不能为空！";
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return errorMsg;
    }

    @Override
    public void register(HttpServletRequest req, HttpServletResponse resp) throws MailException {

        //获得请求过来的资源
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        //这里将获取到的请求数据都在控制台上打印一遍
        //看是否拿到了这些数据
        System.out.println(userName);
        System.out.println(password);
        System.out.println(repassword);
        System.out.println(sex);
        System.out.println(email);
        //这里只加了简单的判断，判断帐号是否填写，以及两次密码是否一致
        //判断信息是否填写
        if(userName==null||password==null||repassword==null||sex==null||email == null){
            throw new MailException("所有的数据都不能为空，请重新填写");
        }
        //判断两次密码是否一致
        if(!password.equals(repassword)){
            throw new MailException("两次密码输入不一致，请重新填写");

        }
        //判断验证码输入是否正确
        //CheckImg checkImg = new CheckImg();
        /*if(!CheckImg.checkImg(req,resp).equalsIgnoreCase(check)){
            resp.getWriter().write("验证码输入错误");
            return;
        }*/

        User user = new User();
        user.setName(userName);
        List<User> list = userDao.queryAll(user);
        if (!CollectionUtils.isEmpty(list)) {
            throw new MailException("用户名已存在！");
        }
        user.setPssword(password);
        user.setEmail(email);
        user.setSex(sex);
        int i = userDao.insert(user);
        //添加成功上边这个执行器就会返回1
        if (i == 1) {
            //throw new MailException("注册成功!");
        } else {
            throw new MailException("注册失败，请重新编辑!");
        }

    }
}
