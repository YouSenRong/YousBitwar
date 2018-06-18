/**
 * author: you
 * data: 2018/04/29
 */

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class YousBitWarPlayer {
	/**
	 * 这是一个表示比特大战参战成员的类
	 */
	public YousBitWarParser yousBitWarParser;
	private String Strategy_path;
	private ArrayList<Integer> Strategy_record;
	private int Score;
	
	/**
	 * 构造函数
	 * @param strategy_path 采用策略的路径
	 */
	YousBitWarPlayer(String strategy_path)
	{
		this.Strategy_path = strategy_path;
		this.Strategy_record = new ArrayList<>();
		this.Score = 0;
		try
		{
			File file = new File(strategy_path);
			FileInputStream fileInputStream = new FileInputStream(file);
			this.yousBitWarParser = new YousBitWarParser(fileInputStream);

		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * 获取策略路径
	 * @return
	 */
	public String get_Strategy_path()
	{
		return this.Strategy_path;
	}
	
	/**
	 * 获取敌方策略记录
	 * @return
	 */
	public ArrayList<Integer> get_Strategy_record()
	{
		return this.Strategy_record;
	}

//	public void set_Enermy_strategy_record(int index, int value)
//	{
//		this.Enermy_strategy_record.add(index, value);
//	}
	
	/**
	 * 获取取得分数
	 * @return
	 */
	public int get_Score()
	{
		return this.Score;
	}
	
	/**
	 * 更新Socre的值
	 * @param score Score新增的值score
	 * @return
	 */
	public int update_Score(int score)
	{
		this.Score = this.Score + score;
		return this.Score;
	}
	
}
