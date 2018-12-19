package my;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 用二维数组模拟路径选择
 * @author LiZhenhua
 *
 */
public class pathFinding {
	
	//两种二维数组的赋值方式
	int[][] block = new int[4][2];
	int[][] zone = {{1,2,3},{1,2,3}};
	
	
	/**
	 * 第三种二维数组的赋值方式
	 * @param a
	 */
	public static int[][] setValue(int[][] a) {
		int va = 1;
		for (int i = 0; i < a.length; ++i) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = va;
				va++;
			}

		}
		for (int x = 0; x < a.length; x++) { // 定位行
			for (int y = 0; y < a[x].length; y++) { // 定位每行的元素个数
				System.out.print(a[x][y] + " ");
			}
			System.out.println("\n");
		}
		return a;
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		int[][] block = new int[10][10];
		block = setValue(block);		
		List<elementIndex> blockElements = new ArrayList<elementIndex>();// 障碍元素
		int[] blockValue = {22,24,34,44,23,45,46,36,37};
		
		for(int i=0;i<blockValue.length;i++){
			blockElements.add(getElementIndex(block, blockValue[i]));
		}
		int begin = 33;
		int end = 35;
		List<elementIndex> pathElements = new ArrayList<elementIndex>(); // 路线元素		
		List<elementIndex> pathElement = getPath(block, begin, end, blockElements,pathElements);
		System.out.println("开始："+begin+"结束："+end);
		System.out.println("障碍元素：");
		for (int i = 0; i < blockValue.length; i++) {
			System.out.print(blockValue[i]+" ");
		}
		System.out.println();
		for (elementIndex elementIndex : pathElement) {
			System.out.println("路径："+elementIndex.value);
		}		
	}
	
	public static List<elementIndex> getPath(int[][] block,int begin,int target,List<elementIndex> blockElements,List<elementIndex> pathElements) throws CloneNotSupportedException{
		
		List<elementIndex> elements = getRoundElement(block, begin,blockElements); //周围元素
		elementIndex beginElement = getElementIndex(block, begin);  //开始元素
		blockElements.add(beginElement); //障碍元素添加
		elementIndex closedElement = getCloseElements(block, elements,beginElement, target,blockElements);  //最近的元素
		if(closedElement == null){
			return pathElements;
		}else{
			pathElements.add(closedElement);
			return getPath(block, closedElement.value, target, blockElements,pathElements);
		}		
	}
	
	
	
	
	/**
	 * 已知两个点，一个障碍，求两个点的最短路径
	 */
	public static List<Integer> findPath(int[][] 区域,int[] 障碍,int 起点,int 终点){
		List<Integer> result = new ArrayList<Integer>();
		System.out.println("4,2的值："+区域[4][2]);
		return result;
	}
	
	/**
	 * 得到一个元素周围的元素
	 * @throws CloneNotSupportedException 
	 */
	public static List<elementIndex> getRoundElement(int[][] 区域,int element,List<elementIndex> blockElement) throws CloneNotSupportedException{
		List<elementIndex> elements = new ArrayList<elementIndex>();
		elementIndex e = getElementIndex(区域, element);
		
		//23
		e.x = e.x -1;
		if(ifElementExit(区域, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}			
		}
		//22
		e.y = e.y - 1;
		if(ifElementExit(区域, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		//24
		e.y = e.y + 2;
		if(ifElementExit(区域, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		//34
		e.x = e.x+1;
		if(ifElementExit(区域, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		//32
		e.y = e.y - 2;
		if(ifElementExit(区域, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		//42
		e.x = e.x+1;
		if(ifElementExit(区域, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		
		//43
		e.y = e.y + 1;
		if(ifElementExit(区域, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		
		//44
		e.y = e.y + 1;
		if(ifElementExit(区域, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		
		return elements;
		
	}
	
	/**
	 * 得到元素下标列表中，最靠近值的元素下标们
	 * @param elements
	 * @param element
	 * @return
	 */
	public static elementIndex getCloseElements(int[][] 区域,List<elementIndex> elements,elementIndex beginElement,int target,List<elementIndex> blockElements){
		elementIndex index = getElementIndex(区域, target); //目标元素
		
		Collections.sort(elements, new Comparator<elementIndex>(){
			@Override
			public int compare(elementIndex element1, elementIndex element2) {
				int x = Math.abs(element1.x - index.x);
				int y = Math.abs(element1.y - index.y);
				double v1 = Math.sqrt(x * x + y * y);
				
				int x2 = Math.abs(element2.x - index.x);
				int y2 = Math.abs(element2.y - index.y);
				double v2 = Math.sqrt(x2 * x2 + y2 * y2);				
				// TODO Auto-generated method stub
	              if(v1 < v2){
	                    return -1;  
	                }  
	                if(v1 == v2){
	                    return 0;  
	                }  
	                return 1;  
			}			
		});
		
		List<elementIndex> randomIndex = new ArrayList<pathFinding.elementIndex>();
		boolean canRandom = false;
		for (int i = 0; i < elements.size(); i++) {
			elementIndex element1 = elements.get(i);
			int x = Math.abs(element1.x - beginElement.x);
			int y = Math.abs(element1.y - beginElement.y);
			double v1 = Math.sqrt(x * x + y * y);
			if(v1 == 1){
				if(elements.get(i).value == index.value){
					return null;
				}else{
					canRandom = true;
					randomIndex.add(elements.get(i));
				}				
			}
			
		}
		if(canRandom){
			Random ran = new Random();
			elementIndex element = randomIndex.remove(ran.nextInt(randomIndex.size()));
			for (int i = 0; i < randomIndex.size(); i++) {
				blockElements.add(randomIndex.get(i));
			}
			
			return element;
		}
		return null;		
	}
	
	/**
	 * 得到元素下标
	 * @param 区域
	 * @param element
	 */
	public static elementIndex getElementIndex(int[][] 区域,int element){
		elementIndex res = null;
		   for(int x = 0; x<区域.length; x++){  //定位行  
	            for(int y = 0; y<区域[x].length; y++){  //定位每行的元素个数  
	            	if(区域[x][y] == element){
	            		res = new elementIndex(x, y);
	            		res.setValue(element);
	            	}
	            }  
	        }
		   return res;
	}
	
	/**
	 * 判断某个下标是否存在
	 * @param 区域
	 * @param index
	 * @return
	 */
	public static boolean ifElementExit(int[][] 区域,elementIndex elementIndex){
		boolean isExit = false;
		
		try{
			int value = 区域[elementIndex.x][elementIndex.y];
			elementIndex.value = value;
			isExit = true;
		} catch (Exception e) {
		}
		return isExit;
	}
	
	private static class elementIndex implements Cloneable{
		int x = 0;
		int y = 0;
		int value = -1;
		public elementIndex(int x,int y) {
			this.x = x;
			this.y = y;
		}
		
		public void setValue(int value){
			this.value  = value;
		}
		
		@Override
		protected elementIndex clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return (elementIndex)super.clone();
		}
		
	}
	
	public static boolean isContain(List<elementIndex> list,elementIndex e){
		for (elementIndex elementIndex : list) {
			if(elementIndex.value == e.value){
				return true;
			}
		}
		return false;
		
	}
	

}
