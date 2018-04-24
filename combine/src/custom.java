
import java.io.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;


public class custom {
	
	public static class MapClass extends Mapper<LongWritable,Text,Text,Text>
	   {
	      public void map(LongWritable key, Text value, Context context)
	      {	    	  
	         try{
	            String[] str = value.toString().split(",");	 
	            //long vol = Long.parseLong(str[7]);
	            context.write(new Text(str[0]),new Text(str[1]));
	         }
	         catch(Exception e)
	         {
	            System.out.println(e.getMessage());
	         }
	      }
	   }
	public static class CombineClass extends Reducer<Text,Text,Text,Text>
	   {
		    private Text result = new Text();
		    
		    public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
		      int sum = 0;
		      int temp = 0;
		      int tot = 0;
		      String s = "";
				
		         for (Text val : values)
		         {       	
		        	s = val.toString();
		        	temp = Integer.parseInt(s);
		        	tot += temp;
		        	sum++;
		         }
		         String fin = tot + "," + sum;
		      result.set(fin);		      
		      context.write(key, result);
		      //context.write(key, new LongWritable(sum));
		      
		    }
	   }
	
	  public static class ReduceClass extends Reducer<Text,Text,Text,FloatWritable>
	   {
		    private FloatWritable res = new FloatWritable();
		    
		    public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
		      float sum = 0;
		      float num = 0;
		      float count = 0;
		      float avg  = 0;
		      float fin_count = 0;
				
		         for (Text val : values)
		         {       	
		            String line[] = val.toString().split(",");
		            num = Float.parseFloat(line[0]);
		            count = Float.parseFloat(line[1]);
		            sum += num; 
		            fin_count += count;
		         }
		         avg = sum/fin_count;
		         
		      res.set(avg);		      
		      context.write(key, res);
		      //context.write(key, new LongWritable(sum));
		      
		    }
	   }
	  public static void main(String[] args) throws Exception {
		    Configuration conf = new Configuration();
		    //conf.set("name", "value")
		    //conf.set("mapreduce.input.fileinputformat.split.minsize", "134217728");
		    Job job = Job.getInstance(conf, "Volume Count");
		    job.setJarByClass(custom.class);
		    job.setMapperClass(MapClass.class);
		    job.setCombinerClass(CombineClass.class);
		    job.setReducerClass(ReduceClass.class);
		    //job.setNumReduceTasks(0);
		    job.setMapOutputKeyClass(Text.class);
		    job.setMapOutputValueClass(Text.class);
		    job.setOutputKeyClass(Text.class);
		    job.setOutputValueClass(FloatWritable.class);
		    FileInputFormat.addInputPath(job, new Path(args[0]));
		    FileOutputFormat.setOutputPath(job, new Path(args[1]));
		    System.exit(job.waitForCompletion(true) ? 0 : 1);
		  }
}
