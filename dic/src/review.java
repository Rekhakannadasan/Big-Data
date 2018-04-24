
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;



public class review {
	
	
	public static class MyMapper extends Mapper<LongWritable,Text, Text, Text> {
        
		
		private Map<String, Integer> abMap = new HashMap<String, Integer>();
		//private Map<String, String> abMap1 = new HashMap<String, String>();
		private Text outputKey = new Text();
		private Text outputValue = new Text();
		
		protected void setup(Context context) throws java.io.IOException, InterruptedException{
			
			super.setup(context);

		    URI[] files = context.getCacheFiles(); // getCacheFiles returns null

		    Path p = new Path(files[0]);
		
		    //Path p1 = new Path(files[1]);
		    
		    FileSystem fs = FileSystem.get(context.getConfiguration());		    
		
			if (p.getName().equals("AFINN.txt")) {
//					BufferedReader reader = new BufferedReader(new FileReader(p.toString()));
					BufferedReader reader = new BufferedReader(new InputStreamReader(fs.open(p)));

					String line = reader.readLine();
					while(line != null) {
						String[] tokens = line.split("\t");
						String wrds = tokens[0];
						int value = Integer.parseInt(tokens[1]);
						abMap.put(wrds, value);
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
        	
        	int count =0;
        	int pos =0;
        	int neg =0;
        	int val = 0;
        	String wrd ="";
        	//String row = value.toString();//reading the data from Employees.txt
        	StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
          	  String myword = itr.nextToken().toLowerCase();
          	  if(abMap.get(myword)!=null)
          	  {
          	  int res = abMap.get(myword);
          	  if(res > 0)
          	  {
          		   pos += res;
          		   wrd = "positive";
          	  }
          	  else if(res < 0)
          	  {
          		  neg += res;
          		  wrd = "negative";
          	  }
          	  else
          	  {
          		  val =0;
          		  wrd = "neutral";
          	  }
          	String out = myword + "," + pos + "," + neg + "," + val;
          	outputValue.set(out);
          	outputKey.set(wrd);
          	context.write(outputKey, outputValue);
          	  }
          	  
              //context.write(word, new IntWritable(1));
            }
        	/*String[] tokens = row.split(",");
        	String prod_id = tokens[1];
        	String store_id = tokens[0];
        	String qty = tokens[2];
        	String price = tokens[3];
        	int sales = Integer.parseInt(qty) * Integer.parseInt(price);
        	String state = abMap.get(store_id);
        	//String desig = abMap1.get(emp_id);
        	String fin = sales + "," + state; 
        	outputKey.set(prod_id);
        	outputValue.set(fin);
      	  	context.write(outputKey,outputValue);*/
        }  
}
	/*public static class CaderPartitioner extends
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
	   }*/
	public static class ReduceClass extends Reducer<Text,Text,Text,DoubleWritable>
	   {
	   
		public double max = 0;
	      public double sum = 0;
	      public DoubleWritable sum1 = new DoubleWritable();
	      public DoubleWritable max1 = new DoubleWritable();
	      private Text outputKey = new Text();
	      String fi = "";
	      public void reduce(Text key, Iterable <Text> values, Context context) throws IOException, InterruptedException
	      {
	         max = 0;
	         sum = 0;
			if(key.toString().equals("negative"))
			{
	         for (Text val : values)
	         {
	        	
//	        	outputKey.set(key);
	        	String [] str = val.toString().split(",");
	        	
	        	//max += Integer.parseInt(str[1]);
	        	
	        	sum += Integer.parseInt(str[2]);
	        	
	        	//sum += max;
	        	sum1.set(sum);
	         }
			}
			else if(key.toString().equals("positive"))
			{
				for (Text val : values)
		         {
		        	
//		        	outputKey.set(key);
		        	String [] str = val.toString().split(",");
		        	
		        	max += Integer.parseInt(str[1]);
		        	
		        	//sum += Integer.parseInt(str[2]);
		        	
		        	//sum += max;
		        	max1.set(max);
		         }
			}
			else
			{
				int v=0;
			}
	         
			if(key.toString().equals("negative"))
			{
	      context.write(key, sum1);
			}
			if(key.toString().equals("positive"))
			{
				context.write(key, max1);
			}
	      
	      }
	       protected void cleanup(Context context) throws IOException, InterruptedException
	        {
	         String p = max1.toString();
	         String r = sum1.toString();
	         double su = Double.parseDouble(r);
	        double p1 = Double.parseDouble(p);
	         double form = (p1 + su) * 100;
	         double s=p1-su;
	         double s1=form/s;
	          
	         DoubleWritable ne = new DoubleWritable();
	         ne.set(s1);
	         //double perc = form * 100;
	         
	        
	            //outputKey.set(fi);	
	        	 context.write(new Text("sentiment"), ne);
	         	

				
	        // }
	      }
	   
	   }
	
  public static void main(String[] args) 
                  throws IOException, ClassNotFoundException, InterruptedException {
    
	Configuration conf = new Configuration();
	conf.set("mapreduce.output.textoutputformat.separator", ",");
	Job job = Job.getInstance(conf);
    job.setJarByClass(review.class);
    job.setJobName("Map Side Join");
    job.setMapperClass(MyMapper.class);
    job.addCacheFile(new Path(args[1]).toUri()); // /dictionary/AFINN.txt
    //job.addCacheFile(new Path(args[2]).toUri());
    //job.setPartitionerClass(CaderPartitioner.class);
    job.setReducerClass(ReduceClass.class);
    //job.setNumReduceTasks(0);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(DoubleWritable.class);
    
    FileInputFormat.addInputPath(job, new Path(args[0])); /*/dictionary/feedback1.txt*/
    FileOutputFormat.setOutputPath(job, new Path(args[2]));
    
    job.waitForCompletion(true);
    
    
  }
}