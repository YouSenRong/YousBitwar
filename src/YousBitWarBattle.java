/**
 * author: you
 * date: 2018/04/29
 */

import java.io.File;
import java.io.FileInputStream;

public class YousBitWarBattle {
	
	//比特大战的回合数
	public int Round;
	//比特大战成员1
	public YousBitWarPlayer yousBitWarPlayer_1;
	//比特大战成员2
	public YousBitWarPlayer yousBitWarPlayer_2;
	//统计得分用的数组
	private static final int reference_array[] = {1, 5, 0, 3};
	
	public static void main(String args[])
	{
		
		YousBitWarBattle yousBitWarBattle = new YousBitWarBattle();
		yousBitWarBattle.initial("strategy_1.txt", "strategy_3.txt", 20);
		yousBitWarBattle.war(yousBitWarBattle.Round);
		
		for(Integer i: yousBitWarBattle.yousBitWarPlayer_1.get_Strategy_record())
		{
			System.out.print(i + " ");
		}
		System.out.println();
		for(Integer i: yousBitWarBattle.yousBitWarPlayer_2.get_Strategy_record())
		{
			System.out.print(i + " ");
		}
		System.out.println();
		yousBitWarBattle.score_statistics();
		System.out.println(yousBitWarBattle.yousBitWarPlayer_1.get_Score());
		System.out.println(yousBitWarBattle.yousBitWarPlayer_2.get_Score());
		
	}
	
	/**
	 * 初始化函数
	 * @param strategy_1_path 对战的一方采用的策略的路径
	 * @param strategy_2_path 对战的另一方采用的策略的路径
	 * @param round 进行对战的回合数
	 */
	public void initial(String strategy_1_path, String strategy_2_path, int round)
	{
		//类成员变量初始化
		this.Round = round;
		this.yousBitWarPlayer_1 = new YousBitWarPlayer(strategy_1_path);
		this.yousBitWarPlayer_2 = new YousBitWarPlayer(strategy_2_path);
		this.yousBitWarPlayer_1.get_Strategy_record().add(0, 1);
		this.yousBitWarPlayer_2.get_Strategy_record().add(0, 1);
		
	}
	
	/**
	 * 双方进行一次大战
	 * @param round 双方进行一次大战的回合数
	 * @return
	 */
	public int war(int round)
	{
		for(int current = 1; current <= round; current++)
		{
			this.battle(current);
		}
		return 1;
	}
	
	/**
	 * 双方进行一次对战的函数
	 * @param current 当前战争中的第几次对战
	 * @return 成功battle则返回1
	 */
	private int battle(int current)
	{
		File file_1 = new File(this.yousBitWarPlayer_1.get_Strategy_path());
		this.yousBitWarPlayer_1.yousBitWarParser.sent_message(current, this.yousBitWarPlayer_2.get_Strategy_record().get(current-1));
		try {
			FileInputStream fileInputStream_1 = new FileInputStream(file_1);
			this.yousBitWarPlayer_1.yousBitWarParser.ReInit(fileInputStream_1);
			this.yousBitWarPlayer_1.yousBitWarParser.program();
			int result = this.yousBitWarPlayer_1.yousBitWarParser.get_result();
			this.yousBitWarPlayer_1.get_Strategy_record().add(current, result);
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		File file_2 = new File(this.yousBitWarPlayer_2.get_Strategy_path());
		this.yousBitWarPlayer_2.yousBitWarParser.sent_message(current, this.yousBitWarPlayer_1.get_Strategy_record().get(current-1));
		try {
			FileInputStream fileInputStream_2 = new FileInputStream(file_2);
			this.yousBitWarPlayer_2.yousBitWarParser.ReInit(fileInputStream_2);
			this.yousBitWarPlayer_2.yousBitWarParser.program();
			int result = this.yousBitWarPlayer_2.yousBitWarParser.get_result();
			this.yousBitWarPlayer_2.get_Strategy_record().add(current, result);
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		return 1;
	}
	
	/**
	 * 统计比特大战后的分数
	 * @return
	 */
	public int score_statistics()
	{
		int record_size = this.yousBitWarPlayer_1.get_Strategy_record().size();
		for(int i = 1; i < record_size; i++)
		{
			int x = this.yousBitWarPlayer_1.get_Strategy_record().get(i);
			int y = this.yousBitWarPlayer_2.get_Strategy_record().get(i);
			//以下是通过数组快速访问的方式，进行获取对应应增加的分数
			this.yousBitWarPlayer_1.update_Score(YousBitWarBattle.reference_array[2*x + y]);
			this.yousBitWarPlayer_2.update_Score(YousBitWarBattle.reference_array[2*y + x]);
		}
		return 1;
	}

}
