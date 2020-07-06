package com.wucc.pub.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 生成验证码工具类
 * @author wxd
 * @date 2020.7.2
 */
public class CheckImg extends HttpServlet {

    public static void checkImg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //准备一张画纸，将验证码中的数字字母写到这张画纸中
        int width = 120;
        int height = 30;
        BufferedImage bufi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //这里面的width、height、就是这张画纸的长宽。BufferedImage.TYPE_INT_RGB就是这张画纸基于
        //RGB三原色来进行画
        //获取一个画笔对象，给图片上画画
        Graphics2D g = (Graphics2D) bufi.getGraphics();
        //设置画笔颜色
        g.setColor(Color.WHITE);
        //将这个颜色填充到整个画纸
        g.fillRect(0,0,width,height);
        //定义图片上可以写什么数据
        String data = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
        //定义书写在画纸上的起始位置
        int x =15;
        int y =25;
        //定义一个随机数
        Random r = new Random();
        //创建一个字符串缓冲区
        StringBuilder sb = new StringBuilder();
        //定义一个循环给画纸上写四个数
        for(int i = 0; i < 4; i++){
            //从data中随机获取一个下标的数据
            char c = data.charAt(r.nextInt(data.length()));
            sb.append(c+"");
            //随机生成画笔的颜色,RGB三原色随机在0-256中随机生成
            g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            //设置字体
            g.setFont(new Font("黑体",Font.BOLD,26));
            double theta =(30 - (r.nextInt(60)))*Math.PI/180;
            g.rotate(theta,x,24);
            //设置数据旋转
            //g.rotate((20)*Math.PI/180,x,y);

            //将数据写到画纸上
            g.drawString(c+"",x,y);
            g.rotate(-theta,x,24);
            //设置完旋转要调回，防止数据旋转的看不到
            //g.rotate(-((20)*Math.PI/180),x,y);
            //每写完一个调整下一个数据写的位置
            x += 25;
        }
        HttpSession session = req.getSession();
        session.setAttribute("checkNum",sb.toString());
        //添加线类型的干扰信息
        for(int i = 0; i < 15 ; i++){
            //同样设置线的颜色
            g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            //开始划线,这里需要的四个参数中前两个是线开头的左边，后边两个是线结束的坐标
            g.drawLine(r.nextInt(width),r.nextInt(height),r.nextInt(width),r.nextInt(height));
        }
        //添加点类型干扰信息
        for (int i = 0 ; i < 150 ; i++){
            //设置点的颜色
            g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
            //开始画点,实质上这是画椭圆，将上半轴半径，左半轴半径设置为0就可以看成是一个点
            g.drawOval(r.nextInt(width),r.nextInt(height),0,0);
        }

        //这个时候并没有在这张纸上书写任何内容，但是已经可以像客户端响应请求了
        ImageIO.write(bufi, "jpg", resp.getOutputStream());
    }
}
