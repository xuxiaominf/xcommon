/**
 * <b>项目名：</b>系统项目名称<br/>
 * <b>包名：</b>utils.xcommon.util.hadoop<br/>
 * <b>文件名：</b>TempMapper.java<br/>
 * <b>Copyright (c)</b> 2016DHCC公司-版权所有<br/>
 * 
 */
package utils.xcommon.util.hadoop;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @description 这里是该类的描述
 * @author xuxiaoming
 * @createTime 2016年9月10日 上午11:35:35
 * @modifyTime 2016年9月10日 上午11:35:35
 * @version V1.0  
 * 
 */
public class TempMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String request = line.split(" ")[6];
		
		if(Pattern.matches(".+cat-co1813.+jf1170.+", request)){
			context.write(new Text("cat-co1813"), new IntWritable(1));
		}else if(Pattern.matches(".+zhongshijiaju.+", request)){
			context.write(new Text("zhongshijiaju"), new IntWritable(1));
		}else{
			context.write(new Text("else"), new IntWritable(1));
		}
	}
}
