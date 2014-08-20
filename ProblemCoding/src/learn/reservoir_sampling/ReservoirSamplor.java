package learn.reservoir_sampling;

import java.util.*;



/**
 * 
 * @author Xiaowei GAO
 * @date 2014��8��20��
 * @description ������ʵ������ˮ���㷨�Ļ���˼�룬�㷨��Ŀ���Ǵ�Դ�����еȸ��ʵĳ��m������
 * �������Թ̶����ȵ��б���Ϊʾ����ʵ���ϣ���ˮ���㷨���Դ�����ʽ���ݣ�����δ֪Դ���ݴ�С
 * ��ǰm��������ֱ�ӷ����ѡ������m+1��ʼ������ m/index�ĸ����õ�ǰindexλ�õ�Ԫ���滻����ѡ�����һ�����λ�õ�Ԫ��
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
				//����һ��[0,i]�������
				int index = r.nextInt(i + 1);
				//���������ɵ�index��select_countС�����õ�ǰδ֪��Ԫ���滻����ѡ����indexλ�õ�Ԫ��
				//���Կ����������滻�����ĸ���Ϊ select_count/i
				if(index < select_count){
					selected.set(index, source.get(i));
				}
			}
		}
		return selected;
	}
	
	public static void main(String[] args) {
		List<Integer> source = new ArrayList<Integer>();
		// ׼��ԭʼ����
		for(int i = 0; i < SOURCE_MAX; i++){
			source.add(i+1);
		}
		
		// key ΪԴ�����б�ѡ�е�Ԫ�ص�ֵ�� value Ϊ��Ԫ���ڶ����ظ�ʵ���б�ѡ�еĴ���
		Map<Integer, Integer> stat = new TreeMap<Integer, Integer>();
		
		// �� TEST_COUNT �ζ����ظ�ʵ�飬ͳ��Դ������ÿ��Ԫ�ر�ѡ�еĴ���
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
