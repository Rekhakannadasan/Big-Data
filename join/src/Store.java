
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;



public class Store {
	
	
	public static class MyMapper extends Mapper<LongWritable,Text, Text, Text> {
        
		
		private Map<String, String> abMap = new HashMap<String, String>();
		//private Map<String, String> abMap1 = new HashMap<String, String>();
		private Text outputKey = new Text();
		private Text outputValue = new Text();
		
		protected void setup(Context context) throws java.io.IOException, InterruptedException{
			
			super.setup(context);

		    URI[] files = context.getCacheFiles(); // getCacheFiles returns null

		    Path p = new Path(files[0]);
		
		    //Path p1 = new Path(files[1]);
		    
		    FileSystem fs = FileSystem.get(context.getConfiguration());		    
		
			if (p.getName().equals("store_master")) {
//					BufferedReader reader = new BufferedReader(new FileReader(p.toString()));
					BufferedReader reader = new BufferedReader(new InputStreamReader(fs.open(p)));

					String line = reader.readLine();
					while(line != null) {
						String[] tokens = line.split(",");
						String store_id = tokens[0];
						String state = tokens[2];
						abMap.put(store_id, state);
						line = reader.readLine();
					}
					reader.close();
				}
			/*if (p1.getName().equals("desig.txt")) {
//				BufferedReader reader = new BufferedReader(new FileReader(p1.toString()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(fs.open(p1)));
				String line = reader.readLine();
				while(line != null) {
					String[] tokens = line.split(",");
					String emp_id = tokens[0];
					String emp_desig = tokens[1];
					abMap1.put(emp_id, emp_desig);
					line = reader.readLine();
				}
				reader.close();
			}*/
		
			
			if (abMap.isEmpty()) {
				throw new IOException("MyError:Unable to load salary data.");
			}

			/*if (abMap1.isEmpty()) {
				throw new IOException("MyError:Unable to load designation data.");
			}*/

		}

		
        protected void map(LongWritable key, Text value, Context context)
            throws java.io.IOException, InterruptedException {
        	
        	
        	String row = value.toString();//reading the data from Employees.txt
        	String[] tokens = row.split(",");
        	String prod_id = tokens[1];
        	String store_id = tokens[0];
        	String qty = tokens[2];
        	String state = abMap.get(store_id);
        	//String desig = abMap1.get(emp_id);
        	String fin = qty + "," + state; 
        	outputKey.set(prod_id);
        	outputValue.set(fin);
      	  	context.write(outputKey,outputValue);
        }  
}
	public static class CaderPartitioner extends
	   Partitioner < Text, Text >
	   {
	      @Override
	      public int getPartition(Text key, Text value, int numReduceTasks)
	      {
	         String[] str = value.toString().split(",");
	        // int age = Integer.parseInt(str[2]);
	         String st = str[1].trim();
	         
	         
	         if(st.equalsIgnoreCase("MAH"))
	         {
	            return 0 % numReduceTasks;
	         }
	         else 
	         {
	            return 1 % numReduceTasks;
	         }
	         
	      
	   }
	   }
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
	        	
//	        	outputKey.set(key);
	        	String [] str = val.toString().split(",");
	        	max += Long.parseLong(str[0]);
	        	//sum += max;
	        	
	         }	
	         	
	        	 context.write(key, new LongWritable(max));
	         	

				
	         
	      }
	   }
	   
	
  public static void main(String[] args) 
                  throws IOException, ClassNotFoundException, InterruptedException {
    
	Configuration conf = new Configuration();
	conf.set("mapreduce.output.textoutputformat.separator", ",");
	Job job = Job.getInstance(conf);
    job.setJarByClass(Store.class);
    job.setJobName("Map Side Join");
    job.setMapperClass(MyMapper.class);
    job.addCacheFile(new Path(args[1]).toUri());
    //job.addCacheFile(new Path(args[2]).toUri());
    job.setPartitionerClass(CaderPartitioner.class);
    job.setReducerClass(ReduceClass.class);
    job.setNumReduceTasks(2);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(LongWritable.class);
    
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[2]));
    
    job.waitForCompletion(true);
    
    
  }
}