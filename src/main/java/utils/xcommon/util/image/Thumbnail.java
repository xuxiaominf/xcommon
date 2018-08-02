package utils.xcommon.util.image;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Thumbnail {
	/**
	 * 生成缩略图 
	 * fromFileStr:原图片路径
	 *  saveToFileStr:缩略图路径 
	 *  width:缩略图的宽 
	 *  height:缩略图的高
	 */
	public static void saveImageAsJpg(File fromFile, File saveToFile,
			int width, int height,boolean equalProportion){
		BufferedImage srcImage;
        String imgType="JPEG";
        if(fromFile.getAbsolutePath().toLowerCase().endsWith(".png")){
        	imgType="PNG";
        }
        try {
			srcImage=ImageIO.read(fromFile);
	        if(width>0||height>0){
	        	srcImage=resize(srcImage,width,height,equalProportion);
	        }
	        ImageIO.write(srcImage,imgType,saveToFile);
		} catch (IOException e) {
			  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}
	}
    
	/**
	 * 将原图片的BufferedImage对象生成缩略图
	 * source：原图片的BufferedImage对象
	 * targetW:缩略图的宽
	 * targetH:缩略图的高
	 */
	public static BufferedImage resize(BufferedImage source,int targetW,int targetH,boolean equalProportion){
		int type=source.getType();
		BufferedImage target=null;
		double sx=(double)targetW/source.getWidth();
		double sy=(double)targetH/source.getHeight();
		//这里想实现在targetW，targetH范围内实现等比例的缩放
		  //如果不需要等比例的缩放则下面的if else语句注释调即可
		if(equalProportion){
			if(sx>sy){
				sx=sy;
				targetW=(int)(sx*source.getWidth());
			}else{
				sy=sx;
				targetH=(int)(sx*source.getHeight());
			}
		}
		if(type==BufferedImage.TYPE_CUSTOM){
			ColorModel cm=source.getColorModel();
			WritableRaster raster=cm.createCompatibleWritableRaster(targetW,targetH);
		    boolean alphaPremultiplied=cm.isAlphaPremultiplied();
		    target=new BufferedImage(cm,raster,alphaPremultiplied,null);
		}else{
			target=new BufferedImage(targetW,targetH,type);
			Graphics2D g=target.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			g.drawRenderedImage(source,AffineTransform.getScaleInstance(sx,sy));
			g.dispose();
		}
		return target;
	}
	
	public static void main(String[] args){
		try{
			Thumbnail.saveImageAsJpg(new File("D:\\source.png"), new File("D:\\sourceDest.png"), 480, 480,true);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
