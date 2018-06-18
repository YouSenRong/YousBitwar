import java.util.ArrayList;
import java.util.HashMap;

public class ExternData {
	
	public HashMap<String, Integer> ID_HashMap = new HashMap<>();
	public HashMap<String, ArrayList<Integer>>  Array_HashMap = new HashMap<>();
	public ArrayList<Integer> Enermy_ArrayList = new ArrayList<>();
	ExternData()
	{}
	
	
	public void Array_HashMap_initial(String key)
	{
		ArrayList<Integer> temp_ArrayList = new ArrayList<>();
		if(Array_HashMap.get(key) == null)
		{
			Array_HashMap.put(key, temp_ArrayList);
		}
	}
	public void Array_HashMap_put(String key, int value)
	{
		if(Array_HashMap.get(key) != null)
		{
			Array_HashMap.get(key).add(value);
		}
	}
	public int Array_HashMap_get(String key, int index)
	{
		if(Array_HashMap.get(key) != null)
		{
			return Array_HashMap.get(key).get(index);
		}
		return -1;
	}

}
