/**
 * author: you
 * date: 2018/04/29
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class YousBitWarDemo {
	
	private HashMap<String, Integer> Total_score_HashMap;
	
	
	YousBitWarDemo()
	{
		Total_score_HashMap = new HashMap<String, Integer>();
	}
	
	public static void main(String args[])
	{
		YousBitWarDemo yousBitWarDemo = new YousBitWarDemo();	
		yousBitWarDemo.user_operate();
	}
	
	private void user_message()
	{
		String text = "*******************************************\n"
				+ "\tWelcome to YousBitWar!!!\n"
				+ "*******************************************\n"
				+ "1. input \"show\" to show the abstract syntax tree of the strategy.\n"
				+ "2. input \"battle\" start to battle.\n"
				+ "3. input \"exit\" to exit program";
		System.out.println(text);
	}
	
	@SuppressWarnings("resource")
	private void user_operate()
	{
		Boolean Exit = false;
		while(!Exit)
		{
			this.user_message();
			Scanner scanner = new Scanner(System.in);
			String operator = scanner.nextLine();
			switch(operator)
			{
			case "show":
				Boolean show_exit = false;
				while(!show_exit)
				{
					String temp_text = "*******************************************\n"
							+ "\tShow Abstract Syntax Tree\n"
							+ "*******************************************\n"
							+ "1. input the strategy file to show abstract syntax tree.\n"
							+ "like \"strategy_1.txt\"\n"
							+ "2. input \"exit\" to exit show";
					System.out.println(temp_text);
					String strategy_name = scanner.nextLine();
					switch(strategy_name)
					{
						case "exit":
							show_exit = true;
							break;
						default:
							File file = new File("strategy\\" + strategy_name);
							try
							{
								FileInputStream fileInputStream = new FileInputStream(file);
								this.show(fileInputStream);
							}
							catch(Exception e)
							{
								System.err.println(e.getMessage());
							}
							break;
					}
					
				}
				break;
			case "battle":
				Boolean battle_exit = false;
				while(!battle_exit)
				{
					String temp_text = "*******************************************\n"
							+ "\t\tBattle\n"
							+ "*******************************************\n"
							+ "1. input the number of round to battle.\n"
							+ "like: 100\n"
							+ "2. input \"exit\" to exit show.";
					System.out.println(temp_text);
					String input = scanner.nextLine();
					switch(input)
					{
					case "exit":
						battle_exit = true;
						break;
					default:
						int round = Integer.parseInt(input);
						File files[] = this.load_strategy("strategy");
						this.syntax_check(files);
						this.tangled_battle(files, round);
						List<Map.Entry<String, Integer>> list = this.sort_Total_score_HashMap();
						this.output_Total_score_HashMap(list);
					}
				}
				break;
			case "exit":
				Exit = true;
				break;
			default:
				System.out.println("input error, please input again.");
			}
		}
	}
	
	private void show(FileInputStream fileInputStream)
	{
		try
	    {
		  YousBitWarJTree yousBitWarJTree = new YousBitWarJTree(fileInputStream);
		  SimpleNode simpleNode = yousBitWarJTree.program();
		  simpleNode.dump("");
		}
		catch (Exception e)
		{
		  System.out.println("NOK.");
		  System.out.println(e.getMessage());
		}
		catch (Error e)
		{
		  System.out.println("Oops.");
		  System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 加载一个文件夹中的所有的文件
	 * @param directory_path 文件夹路径
	 * @return 加载成功返回一个文件数组，不成功返回null;
	 */
	public File[] load_strategy(String directory_path)
	{
		File directory = new File(directory_path);
		if(directory.exists() && directory.isDirectory())
		{
			File[] files = directory.listFiles();
			return files;
		}
		return null;
	}
	
	/**
	 * 对某个文件目录下的所有策略进行遍历混战
	 * @param files 某个文件目录下的所有文件数组
	 */
	private void tangled_battle(File files[], int round)
	{
		for(int i = 0; i < files.length; i++)
		{
			for(int j = i + 1; j < files.length; j++)
			{
				if(files[i].getName().endsWith(".txt"));
				{
					System.out.print(files[i].getName());
					System.out.print(" VS ");
					System.out.print(files[j].getName());
					System.out.print("\t");
					
					YousBitWarBattle yousBitWarBattle = new YousBitWarBattle();
					yousBitWarBattle.initial(files[i].getAbsolutePath(), files[j].getAbsolutePath(), round);
					yousBitWarBattle.war(yousBitWarBattle.Round);
					yousBitWarBattle.score_statistics();
					
					if(!this.Total_score_HashMap.containsKey(files[i].getName()))
					{
						this.Total_score_HashMap.put(files[i].getName(), 0);
					}
					else
					{
						int temp_total_score = this.Total_score_HashMap.get(files[i].getName());
						temp_total_score = temp_total_score + +yousBitWarBattle.yousBitWarPlayer_1.get_Score();
						this.Total_score_HashMap.put(files[i].getName(), temp_total_score);
					}
					if(!this.Total_score_HashMap.containsKey(files[j].getName()))
					{
						this.Total_score_HashMap.put(files[j].getName(), 0);
					}
					else
					{
						int temp_total_score = this.Total_score_HashMap.get(files[j].getName());
						temp_total_score = temp_total_score + +yousBitWarBattle.yousBitWarPlayer_1.get_Score();
						this.Total_score_HashMap.put(files[j].getName(), temp_total_score);
					}
					
					System.out.print(yousBitWarBattle.yousBitWarPlayer_1.get_Score());
					System.out.print(":");
					System.out.print(yousBitWarBattle.yousBitWarPlayer_2.get_Score());
					System.out.println();
//					//输出两两对战策略的记录
//					for(Integer integer: yousBitWarBattle.yousBitWarPlayer_1.get_Strategy_record())
//					{
//						System.out.print(integer + " ");
//					}
//					System.out.println();
//					for(Integer integer: yousBitWarBattle.yousBitWarPlayer_2.get_Strategy_record())
//					{
//						System.out.print(integer + " ");
//					}
//					System.out.println();
				}
			}
		}
	}
	
	private void syntax_check(File files[])
	{
		
		for(File file : files)
		{
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				YousBitWarJTree yousBitWarJTree = new YousBitWarJTree(fileInputStream);
				yousBitWarJTree.program();
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ParseException e)
			{
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	/**
	 * 对每个策略获得的分数进行排序
	 * @return 返回排序后的列表
	 */
	private List<Map.Entry<String, Integer>> sort_Total_score_HashMap()
	{
		Map<String, Integer> map = this.Total_score_HashMap;
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
			//降序排序
			public int compare(Entry<String, Integer> object_1, Entry<String, Integer> object_2)
			{
				return object_2.getValue().compareTo(object_1.getValue());
			}
		});
		
		return list;
	}
	
	/**
	 * 输出排序后的列表
	 * @param list 排序后的列表
	 */
	private void output_Total_score_HashMap(List<Map.Entry<String, Integer>> list)
	{
		for(Map.Entry<String, Integer> mapping : list)
		{
			System.out.println(mapping.getKey() + " Score: " + mapping.getValue());
		}
	}
	
	
}
