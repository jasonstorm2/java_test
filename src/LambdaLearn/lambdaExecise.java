package LambdaLearn;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class lambdaExecise {
	public static void main(String[] args) {
		lambdaExecise thisClass = new lambdaExecise();
		System.out.println("求和结果："+thisClass.addUp(Arrays.asList(1,2,3).stream()));
		char a = 'a';//a97
		char b = 70;//A65  Z90
		
		System.out.println(b);
		System.out.println((int)'A');
		
		
		System.out.println(thisClass.getNameNation(new ArrayList<Artist>(){{
			add(new Artist("john", "china"));
			add(new Artist("bell", "canada"));
			}}));
		
		List<String> ls = new ArrayList<String>(){{
			add(new String("aBBB"));add(new String("dddAc"));	
			}};
			
			thisClass.reduceCode();
			
			List<Object> ll =  map(Arrays.asList(1,2,3).stream(), (aa)->{
				return String.valueOf(aa)+"lll";
			});
			System.out.println("用reduce实现map功能："+ll.toString());
			List<Integer> ldl =  filter(Arrays.asList(1,2,3).stream(),(aaa)->aaa>=2);
			System.out.println("用reduce实现filter功能："+ldl.toString());
//			Collection
//			MyCustomList m = new MyCustomList();
			
			Artists ar = new Artists(Stream.of(new Artist("jason", "china")).collect(Collectors.toList()));
			ar.getArtistName(0);
			
			
			/**************第五章****************/
			//1 a. 转换大写的map 方法；
			List<String> collected2 = Stream.of("a", "b", "hello")
					.map(String::toUpperCase).collect(Collectors.toList());
			//1 b. 使用reduce 实现count 方法；
			collected2.stream().reduce((a1,b1)->{
				return a1+b1;
			});
			int va = 0;
			System.out.println(collected2.stream().reduce((a1,b1)->{
				return a1+b1;
			}).get());
			//1 c. 使用flatMap 连接列表。
			
			
			//2 a.找出名字最长的艺术家
			//方法1
			Comparator<String> comparator = Comparator.comparing(name->name.length());
			Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
					"George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
			names.max(comparator).get();
			//方法2
			Stream<String> names2 = Stream.of("John Lennon", "Paul McCartney",
					"George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
			names2.reduce("",(acc, x) -> {
		        		if(acc.length()<x.length()){		        			
		        			acc=x;
		        			}
		        		return acc;		        	
		            });	
			//方法3
			Stream<String> names3 = Stream.of("John Lennon", "Paul McCartney",
					"George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
			names3.collect(Collectors.maxBy(comparator)).get();
	}
	
	//1.常用流操作
	/**
	 * 求和函数， 计算流中所有数之和
	 * @param nums
	 * @return
	 */
	public int addUp(Stream<Integer> nums){		
		return nums.reduce(0,(a,b)->a+b);		
	}
	
	/**
	 * 接受艺术家列表作为参数，返回一个字符串列表，其中包含艺术家的姓名和国籍；
	 * @param str
	 * @return
	 */
	
	public List<String> getNameNation(List<Artist> str){
	;
		System.out.println(	str.stream()
		        .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
		        .collect(toList()));
		return str.stream().map((s)->s.getName()+s.getNationality()).collect(Collectors.toList());		
	}
	
	/**
	 * 接受专辑列表作为参数，返回一个由最多包含3 首歌曲的专辑组成的列表。
	 * @param album
	 * @return
	 */
	public List<Album> getAlbumList(List<Album> album){
		return album.stream().filter((a)->a.getTracks().count()>2).collect(toList());		
	}
	
	//2.迭代
	/**
	 * 迭代。修改如下代码，将外部迭代转换成内部迭代：
	 * @param art
	 */
	public int Itera(List<Artist> art){
		Integer total =  0;
		/***foreach测试***/
//		art.stream().forEach((a)->{total = (int) (a.getMembers().count()+total);});
		return (int) art.stream().flatMap((a)->a.getMembers()).count();
	}
	
	//3.求职
	boolean anyMatch(Predicate<String> predicate){
		String s = null;
		return predicate.test(s);	
	}
	
	//4.高阶函数
	//是，因为返回的是一个函数
	
	//5.纯函数
	//有副作用，因为使用了外部的变量，而改变量默认为final值，无法改变
	
	
	//6. 计算一个字符串中小写字母的个数
	
	public static int getNums(String str){
		return (int) str.chars().filter(Character::isLowerCase).count();
	}
	
	//在一个字符串列表中，找出包含最多小写字母的字符串
	
	public String getStrShort(List<String> str){
		return str.stream().max(Comparator.comparing(lambdaExecise::getNums)).get();			
	}
	
	public void reduceCode(){
		Stream.of(1,2,3,4).reduce((a,b)->a+10).get();
		System.out.println(Stream.of(1,2,3,4).reduce((a,b)->a+10).get());
	}
	
	/**
	 * 进阶1.用reduce实现map的功能
	 * @param stream 要处理的Stream
	 * @param mapper 对Stream做出的处理
	 * @return
	 */
    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
    	List<String> newssssAcc = new ArrayList<>();
        return stream.reduce(
        		
        	// 1.初始值
        	new ArrayList<O>(),

        	// 2.处理一个元素
        	(acc, x) -> {
        	List<O> newAcc = new ArrayList<>(acc);
        	// function 参数x，返回类型O
        	newAcc.add(mapper.apply(x));
            return newAcc;
            },
            // 3.元素和上一个元素拼装
            (List<O> left, List<O> right) -> {
        	List<O> newLeft = new ArrayList<>(left);
        	newLeft.addAll(right);
            return newLeft;
        });
    }
    
  
    /**
     * 只用reduce 和Lambda 表达式写出实现Stream 上的filter 操作的代码，如果不想返回Stream，可以返回一个List
     * @param stream
     * @param mapper
     * @return
     */
    public static <I> List<I> filter(Stream<I> stream, Predicate<I> predicate) {    	
        return stream.reduce(        		
            	// 1.初始值
            	new ArrayList<I>(),

            	// 2.处理一个元素
            	(acc, x) -> {
            	List<I> newAcc = new ArrayList<>(acc);
            	// predicate 参数x，返回类型一个布尔值
            	if(predicate.test(x)){
            		newAcc.add(x);
            	}           	
            	
                return newAcc;
                },
                // 3.元素和上一个元素拼装
                (List<I> left, List<I> right) -> {
            	List<I> newLeft = new ArrayList<>(left);
            	newLeft.addAll(right);
                return newLeft;
            });
    	
    }

}

/**
 * 
 * @author Administrator
 * 艺术家结构体
 */
class Artist {
    
    private String name;
    private List<Artist> members;
    private String nationality;
    
    public Artist(String name, String nationality) {
        this(name, Collections.emptyList(), nationality);
    }

    public Artist(String name, List<Artist> members, String nationality) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(members);
        Objects.requireNonNull(nationality);
        this.name = name;
        this.members = new ArrayList<>(members);
        this.nationality = nationality;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the members
     */
    public Stream<Artist> getMembers() {
        return members.stream();
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    public boolean isSolo() {
        return members.isEmpty();
    }
        
    public static boolean isSolo2(Artist a){
    	return false;
    }
    
    public static boolean isSolo3(Artist a){
    	return false;
    }
    
    public boolean isSolo4(){
    	return false;
    }

    public boolean isFrom(String nationality) {
        return this.nationality.equals(nationality);
    }

    @Override
    public String toString() {
        return getName();
    }

    public Artist copy() {
        List<Artist> members = getMembers().map(Artist::copy).collect(toList());
        return new Artist(name, members, nationality);
    }

}


/**
 * 专辑结构体
 * @author Administrator
 *
 */
class Album implements Performance{
    private String name;
    private List<Track> tracks;
    private List<Artist> musicians;

    public Album(String name, List<Track> tracks, List<Artist> musicians) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(tracks);
        Objects.requireNonNull(musicians);

        this.name = name;
        this.tracks = new ArrayList<>(tracks);
        this.musicians = new ArrayList<>(musicians);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    public String getName2(String valu) {
    	name = name + valu;
        return name;
    }


    /**
     * @return the tracks
     */
    public Stream<Track> getTracks() {
        return tracks.stream();
    }

    /**
     * Used in imperative code examples that need to iterate over a list
     */
    public List<Track> getTrackList() {
        return unmodifiableList(tracks);
    }

    /**
     * @return the musicians
     */
    public Stream<Artist> getMusicians() {
        return musicians.stream();
    }

    /**
     * Used in imperative code examples that need to iterate over a list
     */
    public List<Artist> getMusicianList() {
        return unmodifiableList(musicians);
    }

    public Artist getMainMusician() {
        return musicians.get(0);
    }

    public Album copy() {
        List<Track> tracks = getTracks().map(Track::copy).collect(toList());
        List<Artist> musicians = getMusicians().map(Artist::copy).collect(toList());
        return new Album(name, tracks, musicians);
    }
}

class Track { 
    private final String name;
    private final int length;

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }
    public String getName() {
        return name;
    }
    public int getLength() {
        return length;
    }

    public Track copy() {
        return new Track(name, length);
    }
}

interface Performance {

	public String getName();

	public Stream<Artist> getMusicians();

	public default Stream<Artist> getAllMusicians() {
		return getMusicians().flatMap(
				artist -> concat(Stream.of(artist), artist.getMembers()));
	};	
}
class Per implements Performance {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<Artist> getMusicians() {
		// TODO Auto-generated method stub
		return null;
	}

}

class Artists {
	private List<Artist> artists;

	public Artists(List<Artist> artists) {
		this.artists = artists;
	}

	public Optional<Artist> getArtist(int index) {
		if (index < 0 || index >= artists.size()) {
			return Optional.empty();
		}
		return Optional.of(artists.get(index));
	}

	
	public String getArtistName(int index) {
		Optional<Artist> artist = getArtist(index);
//		if(artist.isPresent()){
//			return artist.get().getName();
//		}else{
//			return "none";
//		}	
		System.out.println("Optional map 的结果：");
		System.out.println(artist.map(a->1));
		System.out.println(artist.map(a->getArtist(index+1)));
		// Optional<Artist> ------> Optional<String>
		System.out.println(artist.map(Artist::getName));
		
		
        return artist.map(Artist::getName).orElse("unknown");
	}
}