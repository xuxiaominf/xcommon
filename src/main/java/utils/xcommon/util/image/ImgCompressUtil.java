package utils.xcommon.util.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;

/**
 * 图片压缩
 * 
 * @author xuxiaoming
 * @version $Id: ImgCompressUtil.java, v 0.1 2016年8月1日 下午4:58:20 xuxiaoming Exp $
 */
public class ImgCompressUtil {
    private Image img;

    private File file;

    private String contentType;

    private int width;

    private int height;

    private static ImgCompressUtil imgCompressUtil = null;

    public static ImgCompressUtil getInstance() {
        if (imgCompressUtil == null) {
            imgCompressUtil = new ImgCompressUtil();
        }
        return imgCompressUtil;
    }

    private ImgCompressUtil() {

    }

    /**
     * 方法名: ImgCompress 方法功能描述: 按指定长宽像素大小压缩图片
     * 
     * @param:
     * @return:
     * @Create Date: 2015年3月31日 下午5:45:10
     */
    public boolean ImgCompress(File file, String contentType, int w, int h) {
        try {
            this.img = ImageIO.read(file);
            this.file = file;
            this.contentType = contentType.split("/")[1];
            this.width = img.getWidth(null);
            this.height = img.getHeight(null);

            if (width > w || height > h) {
                resizeFix(w, h);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 方法名: ImgQulityCompress 方法功能描述: 按质量压缩图片
     * 
     * @param:
     * @return:
     * @Create Date: 2015年3月31日 下午5:45:51
     */
    public boolean ImgQulityCompress(File srcFile, String contentType) {
        try {
            // 指定写图片的方式
            ImageWriter imageWriter = ImageIO.getImageWritersByFormatName(contentType.split("/")[1]).next();

            // 设置写图片的参数，要使用压缩，必须指定压缩方式为MODE_EXPLICIT
            ImageWriteParam imgWriteParam = new JPEGImageWriteParam(null);
            imgWriteParam.setCompressionMode(imgWriteParam.MODE_EXPLICIT);

            // 这里指定压缩的程度，参数quality是取值0~1范围内
            imgWriteParam.setCompressionQuality((float) 0.3);
            imgWriteParam.setProgressiveMode(imgWriteParam.MODE_DISABLED);
            ColorModel colorModel = ColorModel.getRGBdefault();
            // 指定压缩时使用的色彩模式
            imgWriteParam.setDestinationType(new ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

            // 将图片文件转换成数据流
            BufferedImage src = ImageIO.read(srcFile);
            FileOutputStream out = new FileOutputStream(srcFile);

            // 必须先指定out值，才能调用write方法，ImageOutputStream可以通过任何OutputStream构造
            imageWriter.reset();
            imageWriter.setOutput(ImageIO.createImageOutputStream(out));

            // 调用write方法，就可以向输入流写图片
            imageWriter.write(null, new IIOImage(src, null, null), imgWriteParam);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 方法名: resizeFix 方法功能描述: 按照宽度还是高度进行压缩
     * 
     * @param: w
     *             最大宽度，h 最大高度
     * @return:
     * @Create Date: 2015年3月31日 下午5:46:58
     */
    public void resizeFix(int w, int h) {
        try {
            if (width / height > w / h) {
                resizeByWidth(w);
            } else {
                resizeByHeight(h);
            }
        } catch (Exception e) {
        }
    }

    /**
     * 方法名: resizeByWidth 方法功能描述: 以宽度为基准，等比例放缩图片
     * 
     * @param: w
     *             新宽度
     * @return:
     * @Create Date: 2015年3月31日 下午5:47:28
     */
    public void resizeByWidth(int w) {
        try {
            int h = (int) (height * w / width);
            resize(w, h);
        } catch (Exception e) {
        }
    }

    /**
     * 方法名: resizeByHeight 方法功能描述: 以高度为基准，等比例缩放图片
     * 
     * @param: h
     *             新高度
     * @return:
     * @Create Date: 2015年3月31日 下午5:47:56
     */
    public void resizeByHeight(int h) {
        try {
            int w = (int) (width * h / height);
            resize(w, h);
        } catch (Exception e) {
        }
    }

    /**
     * 方法名: resize 方法功能描述: 强制压缩/放大图片到固定的大小
     * 
     * @param: w
     *             新宽度, h 新高度
     * @return:
     * @Author: 李飞
     * @Create Date: 2015年3月31日 下午5:48:27
     */
    public void resize(int w, int h) {
        try {
            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图

            ImageIO.write(image, contentType, file);
        } catch (Exception e) {
        }
    }

}
