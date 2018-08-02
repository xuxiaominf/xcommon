/**
 * <b>项目名：</b>系统项目名称<br/>
 * <b>包名：</b>utils.xcommon.util.hadoop<br/>
 * <b>文件名：</b>NumberOfUrlMapperReducer.java<br/>
 * <b>Copyright (c)</b> 2016DHCC公司-版权所有<br/>
 * 
 */
package utils.xcommon.util.hadoop;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * @description 这里是该类的描述
 * @author xuxiaoming
 * @createTime 2016年9月10日 下午3:39:48
 * @modifyTime 2016年9月10日 下午3:39:48
 * @version V1.0  
 * 
 */
public class NumberOfUrlMapperReducer {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();  
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();  
        if (otherArgs.length < 2) {  
            System.err.println("Usage: NumberOfUrlMapperReducer <input path> <output path>");
            System.exit(-1);  
        }  
        
        Job job = Job.getInstance(conf, "event count");  
		job.setJarByClass(NumberOfUrlMapperReducer.class);
		job.setJobName("NumberOfSpecialUrl");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(TempMapper.class);
		job.setReducerClass(TempReducer.class);
		
		job.setOutputKeyClass(Text.class);  
        job.setOutputValueClass(IntWritable.class);  
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);  
	}
}
