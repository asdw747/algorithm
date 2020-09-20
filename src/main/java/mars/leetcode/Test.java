package mars.leetcode;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {

    private static int WIDTH = 100;
    private static int HEIGHT = 30;

    public static void main(String [] args) {
        try {
            //创建图片对象
            BufferedImage image = ImageIO.read(new File("/home/mi/project_tmp/a.png"));
            //基于图片对象打开绘图
            Graphics2D graphics = image.createGraphics();

            //调用方法画 框+文字
            //x:横坐标   y:纵坐标   text:文字内容   textSize:文字大小
            drawRectangleAndText(graphics, 10,10, "哈哈哈", 20);

            //关闭流
            graphics.dispose();
            //输出到新的图片
            ImageIO.write(image, "jpeg", new File("/home/mi/project_tmp/abc.jpeg"));
            System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void drawRectangleAndText(Graphics2D graphics, int x, int y, String text, int textSize) {
        //定义矩形形状，位置（通过x、y指定横纵坐标, WIDTH、HEIGHT指定矩形长宽）
        Rectangle2D.Double ellipse = new Rectangle2D.Double(x, y, WIDTH, HEIGHT);
        //把上面那个矩形输出到graphics上面
        graphics.draw(ellipse);

        //指定颜色
        graphics.setColor(new Color(142, 229, 238));
        //用刚才的颜色填充到ellipse定义的矩形里面
        graphics.fillRect(x, y, WIDTH, HEIGHT);

        //定义文字样式，颜色
        Font font = new Font("微软雅黑", Font.BOLD, textSize);
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);

        //把text内容输出到graphics上面
        graphics.drawString(text, x, y + textSize);
    }


}
