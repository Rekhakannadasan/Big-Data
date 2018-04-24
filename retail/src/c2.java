

import java.io.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;

import org.apache.hadoop.fs.*;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

import org.apache.hadoop.util.*;


public class c2
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
	            String net = String.format("%d", profit);
	            
	            String age = str[2];
        	 //String[] str = value.toString().split(",");
            //String gender=str[3];
            //String name = str[1];
            //String age = str[2];
            //String salary = str[4];
            //String myValue = name + ',' + age + ',' + salary;
	            String out = net + ";" + str[2].trim();
            context.write(new Text(prodid.toString()), new Text(out));
         }
         catch(Exception e)
         {
            System.out.println(e.getMessage());
         }
      }
   }
   
   //Reducer class
	
   public static class ReduceClass extends Reducer<Text,Text,Text,LongWritable>
   {
      public long max = 0;
      public long sum = 0;
      private Text outputKey = new Text();
      public void reduce(Text key, Iterable <Text> values, Context context) throws IOException, InterruptedException
      {
         max = 0;
			
         for (Text val : values)
         {
        	
//        	outputKey.set(key);
        	String [] str = val.toString().split(";");
        	max += Long.parseLong(str[0]);
        	sum += max;
        	
                     }
         if(max < 0)
         {	
         	
        	 context.write(key, new LongWritable(max));
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
         String ag = str[1].trim();
         
         
         if(ag.equals("A"))
         {
            return 0 % numReduceTasks;
         }
         else if(ag.equals("B"))
         {
            return 1 % numReduceTasks;
         }
         else if(ag.equals("C"))
         {
            return 2 % numReduceTasks;
         }
         else if(ag.equals("D"))
         {
            return 3 % numReduceTasks;
         }
         else if(ag.equals("E"))
         {
            return 4 % numReduceTasks;
         }
         else if(ag.equals("F"))
         {
            return 5 % numReduceTasks;
         }
         else if(ag.equals("G"))
         {
            return 6 % numReduceTasks;
         }
         else if(ag.equals("H"))
         {
            return 7 % numReduceTasks;
         }
         else if(ag.equals("I"))
         {
            return 8 % numReduceTasks;
         }
         else
         {
            return 9 % numReduceTasks;
         }
      }
      
   }
   

/*   public int run(String[] arg) throws Exception
   {
	
	   
	  Configuration conf = new Configuration();
	  Job job = Job.getInstance(conf);
	  job.setJarByClass(c2.class);
	  job.setJobName("Top Salaried Employees");
      FileInputFormat.setInputPaths(job, new Path(arg[0]));
      FileOutputFormat.setOutputPath(job,new Path(arg[1]));
		
      job.setMapperClass(MapClass.class);
		
      job.setMapOutputKeyClass(Text.class);
      job.setMapOutputValueClass(Text.class);
      
      //set partitioner statement
		
      //job.setPartitionerClass(CaderPartitioner.class);
      //job.setReducerClass(ReduceClass.class);
      //job.setNumReduceTasks(10);
      //job.setInputFormatClass(TextInputFormat.class);
		
      //job.setOutputFormatClass(TextOutputFormat.class);
      //job.setOutputKeyClass(Text.class);
      //job.setOutputValueClass(LongWritable.class);
		
      System.exit(job.waitForCompletion(true)? 0 : 1);
      return 0;
   }
   
   public static void main(String ar[]) throws Exception
   {
      ToolRunner.run(new Configuration(), new c2(),ar);
      System.exit(0);
   }*/
   public static void main(String[] args) throws Exception {
	    Configuration conf = new Configuration();
	    //conf.set("name", "value")
	    //conf.set("mapreduce.input.fileinputformat.split.minsize", "134217728");
	    Job job = Job.getInstance(conf, "Volume Count");
	    job.setJarByClass(c2.class);
	    job.setMapperClass(MapClass.class);
	    job.setPartitionerClass(CaderPartitioner.class);
	    job.setReducerClass(ReduceClass.class);
	    job.setNumReduceTasks(10);
	    job.setMapOutputKeyClass(Text.class);
	    job.setMapOutputValueClass(Text.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(LongWritable.class);
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	  }

}
