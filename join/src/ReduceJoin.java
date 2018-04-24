import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ReduceJoin {

	public static class CustsMapper extends
			Mapper<LongWritable, Text, Text, Text> {
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			String record = value.toString();
			String[] parts = record.split(",");
			String name = parts[1] + parts[2];
			context.write(new Text(parts[0]), new Text("custs\t" + name));
		}
	}

	public static class TxnsMapper extends
			Mapper<LongWritable, Text, Text, Text> {
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			String record = value.toString();
			String[] parts = record.split(",");
			String outtran = parts[3] + "," + parts[8];
			context.write(new Text(parts[2]), new Text("txns\t" + outtran));
		}
	}

	public static class ReduceJoinReducer extends
			Reducer<Text, Text, Text, Text> {
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			String name = "";
			double totalc = 0.0;
			double totalcash = 0.0;
			int count = 0;
			for (Text t : values) {
				String parts[] = t.toString().split("\t");
				if (parts[0].equals("txns")) {
					count++;
					String tn[] = parts[1].split(",");
					if(tn[1].equals("credit"))
					{
						totalc += Float.parseFloat(tn[0]);
					}
					else
					{
						totalcash += Float.parseFloat(tn[0]); 
					}
					//total += Float.parseFloat(parts[1]);
				} else if (parts[0].equals("custs")) {
					name = parts[1];
				}
			}
			String str = count + " " + totalc + " " +  " credit" + " " + totalcash + " " + " cash";
			context.write(new Text(name), new Text(str));
		}
	}

	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
	    job.setJarByClass(ReduceJoin.class);
	    job.setJobName("Reduce Side Join");
		job.setReducerClass(ReduceJoinReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		//job.setNumReduceTasks(0);
		MultipleInputs.addInputPath(job, new Path(args[0]),TextInputFormat.class, CustsMapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]),TextInputFormat.class, TxnsMapper.class);
		
		Path outputPath = new Path(args[2]);
		FileOutputFormat.setOutputPath(job, outputPath);
		//outputPath.getFileSystem(conf).delete(outputPath);
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}