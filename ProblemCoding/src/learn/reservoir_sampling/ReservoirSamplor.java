package learn.reservoir_sampling;

import java.util.*;



/**
 * 
 * @author Xiaowei GAO
 * @date 2014年8月20日
 * @description 本代码实现了蓄水池算法的基本思想，算法的目的是从源数据中等概率的抽出m个样本
 * 程序中以固定长度的列表作为示例，实际上，蓄水池算法可以处理流式数据，事先未知源数据大小
 * 对前m个样本，直接放入候选集，从m+1开始，会以 m/index的概率用当前index位置的元素替换掉候选集里的一个随机位置的元素
 * 
 * @ClassName ReservoirSamplor
 *
 */
public class ReservoirSamplor {
	static Random r = new Random();
	static int SOURCE_MAX = 100;
	static int SELECT_COUNT = 5;
	static int TEST_COUNT = 10000;
	
	static List<Integer> sampler(List<Integer> source, int select_count){
		List<Integer> selected = new ArrayList<Integer>();
		int total = source.size();
		for(int i = 0; i < total; i++){
			if(i < select_count){
				selected.add(source.get(i));
			}else{
				//生成一个[0,i]的随机数
				int index = r.nextInt(i + 1);
				//如果随机生成的index比select_count小，就用当前未知的元素替换掉候选集中index位置的元素
				//可以看到，发生替换操作的概率为 select_count/i
				if(index < select_count){
					selected.set(index, source.get(i));
				}
			}
		}
		return selected;
	}
	
	public static void main(String[] args) {
		List<Integer> source = new ArrayList<Integer>();
		// 准备原始数据
		for(int i = 0; i < SOURCE_MAX; i++){
			source.add(i+1);
		}
		
		// key 为源数据中被选中的元素的值， value 为该元素在独立重复实验中被选中的次数
		Map<Integer, Integer> stat = new TreeMap<Integer, Integer>();
		
		// 做 TEST_COUNT 次独立重复实验，统计源数据中每个元素被选中的次数
		for(int i = 0; i < TEST_COUNT; i++){
			List<Integer> sample_result = ReservoirSamplor.sampler(source, SELECT_COUNT);
			
			for(Integer num : sample_result){
				if(stat.containsKey(num)){
					stat.put(num, stat.get(num) + 1);
				}else{
					stat.put(num, 1);
				}
			}
		}
		
		for(Map.Entry<Integer, Integer> entry : stat.entrySet()){
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
	}
}
