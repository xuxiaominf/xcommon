/**
 * <b>项目名：</b>系统项目名称<br/>
 * <b>包名：</b>utils.xcommon.util.hadoop<br/>
 * <b>文件名：</b>TempReducer.java<br/>
 * <b>Copyright (c)</b> 2016DHCC公司-版权所有<br/>
 * 
 */
package utils.xcommon.util.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @description 这里是该类的描述
 * @author xuxiaoming
 * @createTime 2016年9月10日 下午3:02:32
 * @modifyTime 2016年9月10日 下午3:02:32
 * @version V1.0  
 * 
 */
public class TempReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int num = 0;
		for (IntWritable value : values) {
				num += value.get();
		}
		context.write(key, new IntWritable(num));
	}
}
