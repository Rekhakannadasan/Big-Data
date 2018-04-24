
import java.io.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;

import org.apache.hadoop.fs.*;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

import org.apache.hadoop.util.*;

public class C1 extends Configured implements Tool
{
   //Map class
	
   public static class MapClass extends Mapper<LongWritable,Text,Text,Text>
   {
      public void map(LongWritable key, Text value, Context context)
      {
         try{
        	 	String[] str = value.toString().split(";");
	            Long prodcat = Long.parseLong(str[4]);
	            Long prodid = Long.parseLong(str[5]);
	            Long sales = Long.parseLong(str[8]);
	            Long cost = Long.parseLong(str[7]);
	            Long profit = sales - cost;
	            
	            String age = str[2];
        	 //String[] str = value.toString().split(",");
            //String gender=str[3];
            //String name = str[1];
            //String age = str[2];
            //String salary = str[4];
            //String myValue = name + ',' + age + ',' + salary;
	            String out = profit + ";" + age;
            context.write(new Text(prodid.toString()), new Text(out));
         }
         catch(Exception e)
         {
            System.out.println(e.getMessage());
         }
      }
   }
   
   //Reducer class
	
   public static class ReduceClass extends Reducer<Text,Text,Text,IntWritable>
   {
      public int max = 0;
      public int sum = 0;
      private Text outputKey = new Text();
      public void reduce(Text key, Iterable <Text> values, Context context) throws IOException, InterruptedException
      {
         max = 0;
			
         for (Text val : values)
         {
        	
//        	outputKey.set(key);
        	String [] str = val.toString().split(";");
        	max = Integer.parseInt(str[0]);
        	sum += max;
        	
                     }
         if(sum > 0)
         {	
         	
        	 context.write(key, new IntWritable(sum));
         }	

			
         
      }
   }
   
   //Partitioner class
	
   public static class CaderPartitioner extends
   Partitioner < Text, Text >
   {
      @Override
      public int getPartition(Text key, Text value, int numReduceTasks)
      {
         String[] str = value.toString().split(";");
        // int age = Integer.parseInt(str[2]);
         String ag = str[1];
         
         
         if(ag.contains("A"))
         {
            return 0;
         }
         else if(ag.contains("B"))
         {
            return 1 ;
         }
         else if(ag.contains("C"))
         {
            return 2;
         }
         else if(ag.contains("D"))
         {
            return 3;
         }
         else if(ag.contains("E"))
         {
            return 4;
         }
         else if(ag.contains("F"))
         {
            return 5;
         }
         else if(ag.contains("G"))
         {
            return 6;
         }
         else if(ag.contains("H"))
         {
            return 7;
         }
         else if(ag.contains("I"))
         {
            return 8;
         }
         else
         {
            return 9;
         }
      }
      
   }
   

   public int run(String[] arg) throws Exception
   {
	
	   
	  Configuration conf = new Configuration();
	  Job job = Job.getInstance(conf);
	  job.setJarByClass(C1.class);
	  job.setJobName("Top Salaried Employees");
      FileInputFormat.setInputPaths(job, new Path(arg[0]));
      FileOutputFormat.setOutputPath(job,new Path(arg[1]));
		
      job.setMapperClass(MapClass.class);
		
      job.setMapOutputKeyClass(Text.class);
      job.setMapOutputValueClass(Text.class);
      
      //set partitioner statement
		
      job.setPartitionerClass(CaderPartitioner.class);
      job.setReducerClass(ReduceClass.class);
      job.setNumReduceTasks(10);
      job.setInputFormatClass(TextInputFormat.class);
		
      job.setOutputFormatClass(TextOutputFormat.class);
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(IntWritable.class);
		
      System.exit(job.waitForCompletion(true)? 0 : 1);
      return 0;
   }
   
   public static void main(String ar[]) throws Exception
   {
      ToolRunner.run(new Configuration(), new C1(),ar);
      System.exit(0);
   }
}