package LambdaLearn;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Collector.Characteristics;

/**
 * filter 这样只描述Stream，最终不产生新集合的方法叫作惰性求值方法； count 这样最终会从Stream 产生值的方法叫作及早求值方法。
 * 
 * 判断一个操作是惰性求值还是及早求值很简单：只需看它的返回值。如果返回值是Stream， 那么是惰性求值；如果返回值是另一个值或为空，那么就是及早求值
 * 
 * Stream.of("a", "b", "c") 格式，返回格式 Stream<Integer>
 * 
 * Stream<T> 流只能被操作一次，重复操作会报错
 * 
 * @author Administrator
 *
 */
public class lambdaTest {

	static class staticInnerClass {

	}

	public String str = "value1";
	static String name = new String("jason");

	public static void main(String[] args) {

		lambdaTest thisClass = new lambdaTest();
		// 音轨 名字和时长
		Track track1 = new Track("但愿人长久", 4);
		Track track2 = new Track("旋木", 3);
		Track track3 = new Track("流年", 4);
		Track track4 = new Track("挪威深林", 5);
		Track track5 = new Track("容易受伤的女人", 6);

		Track track6 = new Track("告白气球", 3);
		Track track7 = new Track("七里香", 4);
		Track track8 = new Track("安静", 5);
		Track track9 = new Track("最长的电影", 6);

		Track track10 = new Track("恋人未满", 4);
		Track track11 = new Track("super star", 4);
		Track track12 = new Track("紫藤花", 6);

		// 艺术家
		Artist artWangfei = new Artist("王菲", "中国");
		Artist artZhou = new Artist("周杰伦", "台湾");

		Artist artSelina = new Artist("Selina", "台湾");
		Artist artHebe = new Artist("Hebe", "台湾");
		Artist artElla = new Artist("Ella", "台湾");
		List<Artist> sheArtist = new ArrayList<Artist>();
		sheArtist.add(artSelina);
		sheArtist.add(artHebe);
		sheArtist.add(artElla);
		Artist artSHE = new Artist("SHE", sheArtist, "台湾");

		// 王菲
		List<Track> tracksWangfei = new ArrayList<Track>();
		tracksWangfei.add(track1);
		tracksWangfei.add(track2);
		tracksWangfei.add(track3);
		tracksWangfei.add(track4);
		tracksWangfei.add(track5);
		// 周杰伦
		List<Track> tracksZhou = new ArrayList<Track>();
		tracksZhou.add(track6);
		tracksZhou.add(track7);
		tracksZhou.add(track8);
		tracksZhou.add(track9);
		// SHE
		List<Track> tracksSHE = new ArrayList<Track>();
		tracksSHE.add(track10);
		tracksSHE.add(track11);
		tracksSHE.add(track12);
		// 王周
		List<Track> tracksWangZhou = new ArrayList<Track>();
		tracksWangZhou.add(track6);
		tracksWangZhou.add(track7);
		tracksWangZhou.add(track1);

		// 音乐集
		List<Artist> artsWang = new ArrayList<Artist>();
		artsWang.add(artWangfei);

		List<Artist> artsZhou = new ArrayList<Artist>();
		artsZhou.add(artZhou);

		List<Artist> artsSHE = new ArrayList<Artist>();
		artsSHE.add(artSHE);

		List<Artist> artsWangZhou = new ArrayList<Artist>();
		artsWangZhou.add(artWangfei);
		artsWangZhou.add(artZhou);
		Album albumWangfei = new Album("王菲专辑", tracksWangfei, artsWang);
		Album albumZhou = new Album("周杰伦专辑", tracksZhou, artsZhou);
		Album albumSHE = new Album("SHE专辑", tracksSHE, artsSHE);
		Album albumWangZhou = new Album("王菲周杰伦专辑", tracksWangZhou, artsWangZhou);

		// 艺术家集合
		List<Artist> artsMix = new ArrayList<Artist>();
		artsMix.add(artSHE);
		artsMix.add(artElla);
		artsMix.add(artHebe);
		artsMix.add(artSelina);
		artsMix.add(artWangfei);
		artsMix.add(artZhou);

		// 专辑集合
		List<Album> albumMix = new ArrayList<Album>();
		albumMix.add(albumSHE);
		albumMix.add(albumWangfei);
		albumMix.add(albumWangZhou);
		albumMix.add(albumZhou);

		/***************** List 的过滤filter测试 Predicate接口 ******************/
		System.out
				.println("/***************** List 的过滤filter测试 Predicate接口******************/");

		List<String> allArtists = Arrays.asList("a", "ab", "bc", "ad");
		long count = allArtists.stream().filter(artist -> artist.contains("a"))
				.count();
		System.out.println("lambda过滤测试：计算包含a的元素有几个：" + count);
		System.out.println("过滤后的数组不变，因为未赋值：" + allArtists.toString());
		allArtists = allArtists.stream().filter(a -> a.contains("f"))
				.collect(Collectors.toList());
		if (allArtists.isEmpty()) {
			System.out.println("过滤后List<String>为空");
		} else {
			System.out.println("lambda过滤测试：过滤后生成新的list：" + allArtists);
		}

		// 重复操作流会报错
		Stream<String> strStream = Arrays.asList("a", "ab", "bc", "ad")
				.stream();
		strStream.filter(artist -> artist.contains("a"));
		// strStream.filter(artist -> artist.contains("a"));//报错

		/***************** Stream的方法findAny()测试 ******************/
		System.out
				.println("/***************** Stream的方法findAny()测试******************/");
		List<String> allArtists2 = Arrays.asList("ae", "ab", "bc", "ad");
		Optional<String> findAny = allArtists2.stream().findAny();
		String findAnyValue = findAny.get();
		System.out.println("是否随机:" + findAnyValue);

		/***************** Stream的of方法，和collect(toList())方法 ******************/
		System.out
				.println("/***************** Stream的of方法，和collect(toList())方法******************/");

		List<String> collected = Stream.of("a", "b", "c").collect(
				Collectors.toList());

		Set<String> collectSet = Stream.of("a", "b", "c").collect(
				Collectors.toSet());

		System.out.println("collected列表的内容：" + collected.toString());

		/***************** map方法转换内容 Function接口 ******************/
		System.out
				.println("/***************** map方法转换内容 Function接口******************/");

		List<String> collected2 = Stream.of("a", "b", "hello")
				.map(s -> s.toUpperCase()).collect(Collectors.toList());
		System.out.println("collected2列表的内容：" + collected2.toString());

		/***************** flatmap Function接口 ******************/
		System.out
				.println("/***************** flatmap Function接口******************/");
		// flatmap可以把多个Stream合并成一个Stream
		// flatmap 可以把Stream<List<Integer>>格式转换成
		// Stream<Integer>格式再转换成List<Integer>
		List<Integer> together = Stream
				.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
				.flatMap(numbers -> numbers.stream())
				.collect(Collectors.toList());
		System.out.println("together:" + together.toString());

		/***************** min 求最小值 ******************/
		System.out.println("/***************** min 求最小值******************/");

		/** 最小值算法1 **/

		// min返回一个Optional对象
		// 通过调用get 方法可以取出Optional 对象中的值。
		int minValue = Stream.of(1, 2, 3, 4).min(Comparator.comparing(a -> a))
				.get();
		System.out.println("list中的最小值：" + minValue);

		/** 最小值算法2 **/
		System.out.println("list中的最小值："
				+ Stream.of(1, 2, 3, 4)
						.collect(Collectors.summarizingInt(a -> a)).getMin());

		/** 最小值算法3 **/
		Function<Integer, Integer> fuc = a -> a;
		System.out.println("list中的最小值："
				+ Stream.of(1, 2, 3, 4).collect(
						Collectors.minBy(Comparator.comparing(a -> a))));

		/***************** max 求最小值 ******************/

		/** 最大值算法1 **/
		System.out.println("/***************** max 求最大值******************/");
		int maxValue = Stream.of(1, 2, 3, 4).max(Comparator.comparing(a -> a))
				.get();
		System.out.println("list中的最大值：" + maxValue);

		/** 最大值算法2 **/
		System.out.println("list中的最大值："
				+ Stream.of(1, 2, 3, 4)
						.collect(Collectors.summarizingInt(a -> a)).getMax());

		/** 最大值算法3 **/
		System.out.println("list中的最大值："
				+ Stream.of(1, 2, 3, 4).collect(
						Collectors.maxBy(Comparator.comparing(a -> a))));

		/***************** 使用reduce 计算累加数值 ******************/
		System.out
				.println("/***************** 使用reduce 计算累加数值******************/");

		/** 求和算法1 **/
		// reduce 操作可以实现从一组值中生成一个值，Integer identity,为初始值
		int sum1 = Stream.of(1, 2, 3).reduce(10,
				(acc, element) -> acc + element);
		System.out.println("求和算法1：" + sum1);

		/** 求和算法2 **/
		long sum2 = Stream.of(1, 2, 3, 4)
				.collect(Collectors.summarizingInt(a -> a)).getSum();
		System.out.println("求和算法2：" + sum2);

		/** 求和算法3 **/
		int sum3 = Stream.of(1, 2, 3, 4).collect(Collectors.summingInt(a -> a));
		System.out.println("求和算法3：" + sum3);

		/***************** 展开reduce BinaryOperator计算累加 ******************/
		System.out
				.println("/***************** 展开reduce BinaryOperator计算累加******************/");

		BinaryOperator<Integer> accumulator = (acc, element) -> acc + element;
		int res2 = accumulator.apply(
				accumulator.apply(accumulator.apply(0, 1), 2), 3);

		/***************** 测试map ******************/
		System.out.println("/***************** 测试map******************/");
		List<String> result = Stream.of(new lambdaTest()).map(a -> a.str)
				.collect(Collectors.toList());
		System.out.println("使用map转换对象的类型：" + result);

		/***************** Stream foreach测试 ******************/
		System.out
				.println("/***************** Stream foreach测试******************/");
		// 过滤也可以达到该效果
		List<String> rr = new ArrayList<String>();
		// lambda表达式表示输入一个参数，但并不返回，只执行一个代码块
		allArtists.stream().forEach(a -> {
			if (a.contains("a")) {
				rr.add(a);
			}
		});
		System.out.println("使用foreach遍历每个list的元素：" + rr.toString());

		/***************** 双冒号测试测试 ******************/
		System.out.println("/***************** 双冒号测试测试******************/");

		/**
		 * lambda表达式 类方法 的引用 1.lambda代码中实现代码只有一行， 2.该行是一个静态方法
		 * 3.该静态方法的参数与函数式接口的参数一致，且返回值类型相同
		 */
		MyFunction<String> myFun = lambdaTest::doubleTest;		
		String doubleTestRes = myFun.oneMethod("123");
		System.out.println(doubleTestRes);
		/**
		 * lambda表达式 对象方法 的引用 1.双引号左边是对象 2.双引号右边是对象的方法 3.对象的方法的参数和返回值要和函数式接口的一样
		 */
		String abc = "abc";
		MyFunction<String> myFun2 = thisClass::doubleTs;
		System.out.println(myFun2.oneMethod("a"));
		/**
		 * lambda表达式 构造方法 的引用 1.构造函数的返回值是该类的类型 2.函数是接口的参数和构造的参数一致
		 * 
		 */

		IFunction<String> ifun = test2<String>::new;
		test2<String> t2 = ifun.oneMethod("myName");
		System.out.println(t2.getValue());
		
		/**
		 * ???????奇怪的双冒号引用？？
		 */
		// Predicate 输入一个引用T，返回一个布尔值
		//隐式的引用了Artist类的对象？
		Predicate<? super Artist> predicate = Artist::isSolo;
		predicate.test(artWangfei);
		// isSolo2(Artist a)类方法，显示的引用了一个Artist对象？
		Predicate<Artist> predicate2 = Artist::isSolo2;
		
		Predicate<Artist> predicate3 = Artist::isSolo2;
		//隐式的引用
		Function<Artist,Boolean> function = Artist::isSolo3;
		//显示的引用
		Function<Artist,Boolean> function2 = Artist::isSolo4;


		//
		// 可以用双冒号调用本地的静态方法，方法参数和返回值要和该处的函数式接口的一样，这样才不会报错
		System.out.println("双冒号测试："
				+ Stream.of(1, 2, 3, 4).map(lambdaTest::doubleTesst)
						.collect(Collectors.toList()));

		/***************** Optional 空值测试 ******************/
		System.out
				.println("/***************** Optional 空值测试******************/");
		Optional<String> optional = Optional.of("abcd");
		System.out.println("Optional值测试：" + optional.get());
		System.out.println("optional对象的EMPTY对象的值：" + optional.empty());
		optional.ifPresent((re) -> {
			re = re + 1;
			System.out.println("re:" + re);
		});
		System.out.println("Optional值测试,调用ifPresent后（原值：abcd）：："
				+ optional.get());
		System.out.println("过滤测试："
				+ optional.filter((va) -> va.length() > 1).orElse("没有大于9位的数值"));
		System.out.println("过滤测试："
				+ optional.filter((va) -> va.length() > 9).orElse("没有大于9位的数值"));

		// 从源码上看，一下二者的作用是一样的
		Optional<Object> emptyOptional = Optional.empty();
		Optional<Object> alsoEmpty = Optional.ofNullable(null);
		if (emptyOptional.isPresent()) {
			System.out.println("Optional值emptyOptional测试："
					+ emptyOptional.get());
		} else {
			System.out.println("Optional值emptyOptional测试：空");
		}

		if (alsoEmpty.isPresent()) {
			System.out.println("Optional值alsoEmpty测试：" + alsoEmpty.get());
		} else {
			System.out.println("Optional值为空值");
		}
		String strt = null;
		Optional<Object> strtOP = Optional.ofNullable(strt);
		if (strtOP.isPresent()) {
			System.out.println("Optional值strtOP测试：" + strtOP.get());
		} else {
			System.out.println("strtOP是空值");
		}
		Optional<String> strtOP2 = Optional.ofNullable(null);
		Optional<String> strtOP3 = Optional.of("jason");
		System.out.println("orElse测试：" + strtOP3.orElse("lala"));
		// map测试
		Optional<String> cc = optional.map((b) -> b + 1);
		System.out.println("optional使用map方法后（原值：abcd）：" + cc.get());
		// flatmap测试
		cc = optional.flatMap((b) -> {
			Optional<String> rddr = Optional.ofNullable("flatmap");
			return rddr;
		});
		System.out.println("optional使用flatmap方法后（原值：abcd）：" + cc.get());

		/***************** test2 泛型以及lambda测试 ******************/
		System.out
				.println("/***************** test2 泛型以及lambda测试******************/");
		test2<String> tt = new test2<String>("male");

		tt.map((b) -> "female").getValue();
		test2<Integer> ttmap = tt.map(lambdaTest::doubleTesst);
		tt.map2(lambdaTest::doubleTesst);

		System.out.println("tt.map((b)->1).getValue() = "
				+ tt.map((b) -> 1).getValue());
		System.out.println("tt.map(lambdaTest::doubleTesst) = "
				+ tt.map(lambdaTest::doubleTesst).getValue());

		/***************** lambda 集合转换成其他集合 ******************/
		// list<String> 集合转换成 TreeSet<String> 集合
		System.out
				.println("/***************** lambda 集合转换成其他集合******************/");

		List<String> toCollect = Arrays.asList("a", "bb", "bc", "ad");

		TreeSet<String> tree = toCollect.stream().collect(
				Collectors.toCollection(TreeSet::new));
		System.out.println("lambda过滤测试：过滤后生成新的list：" + tree);

		/***************** lambda 集合转换成值 ******************/
		System.out
				.println("/***************** lambda 集合转换成值******************/");

		Optional<Artist> opMax = thisClass.biggestGroup(artsMix.stream());
		System.out.println("最大值：" + opMax.get().getName());
		// 最小值
		Optional<Artist> opMim = thisClass.minGroup(artsMix.stream());
		System.out.println("最小值：" + opMim.get().getName());

		// 平均值
		double aver = thisClass.getAveraging(artsMix.stream());
		System.out.println("数量平均值：" + aver);
		artsMix.stream().forEach(
				a4 -> {
					System.out.println("对象名字：" + a4.getName() + " getMember:"
							+ a4.getMembers().count());
				});

		List<AGroup> groupList = Arrays.asList(new AGroup(5), new AGroup(6),
				new AGroup(7));

		double groupAver = thisClass.getAverage(groupList.stream());
		System.out.println("groupList数量平均值：" + groupAver);

		long sum = thisClass.getSumStatistics(groupList.stream()).getSum();
		System.out.println("求和:" + sum);
		double av = thisClass.getSumStatistics(groupList.stream()).getAverage();
		System.out.println("求平均值:" + av);
		long max = thisClass.getSumStatistics(groupList.stream()).getMax();
		System.out.println("求最大值:" + max);

		/***************** lambda 集合 数据分块 ******************/
		System.out
				.println("/***************** lambda 集合 数据分块******************/");
		// List<Artist> 转换成 Map<Boolean, List<Artist>>
		Map<Boolean, List<Artist>> bandSolo = thisClass.bandsAndSolo(artsMix
				.stream());
		for (Entry<Boolean, List<Artist>> element : bandSolo.entrySet()) {
			if (element.getKey() == true) {
				List<Artist> list = element.getValue();
				list.stream().forEach(ar -> {
					System.out.println("单个歌手：" + ar.getName());
				});
			} else {
				List<Artist> list = element.getValue();
				list.stream().forEach(ar -> {
					System.out.println("多个歌手：" + ar.getName());
				});
			}

		}
		/***************** lambda 集合 数据分组 ******************/
		System.out
				.println("/***************** lambda 集合 数据分组******************/");
		// List<Album> 转换成 Map<Artist, List<Album>>
		Map<Artist, List<Album>> artAlbum = thisClass.albumsByArtist(albumMix
				.stream());
		for (Entry<Artist, List<Album>> element : artAlbum.entrySet()) {
			System.out.println("歌手或乐队：" + element.getKey());
			for (Album al : element.getValue()) {
				System.out.println("专辑的名字：" + al.getName());
			}
		}

		/***************** lambda 集合 组成字符串 ******************/
		System.out
				.println("/***************** lambda 集合 组成字符串******************/");
		System.out.println(thisClass.makeString(artsMix.stream()));

		/***************** lambda 集合 组合收集器 ******************/
		// 从专辑集合中 计算一个艺术家的专辑数量--计算王菲的专辑数量
		// List<Album> --> Stream<Album> ---> Map<Artist, Long>
		Map<Artist, Long> mm = thisClass.numberOfAlbums(albumMix.stream());
		
		mm.forEach((k,v)->{
			System.out.println("artistName:"+k.getName()+" artistAlbumNums:"+v);
		});

		Map<Artist, List<String>> nameOfAlbums = thisClass.nameOfAlbums(albumMix.stream());		
		
		
		/***************** map 放入值，不存在则用替代值 ******************/
		//map.computeIfAbsent 如果存在值，则不放入。。如果不存在值，则在键里填入指定值
		System.out.println("/***************** map 取值，不存在则用替代值 ******************/");
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("key", 2);
		map.computeIfAbsent("key2", a->1);
		map.forEach((k,v)->{
			System.out.println("key:"+k+" value:"+v);
		});
		
		/***************** 重构和定制收集器 ******************/
		
		// 方法1：
		StringBuilder reduced = artsMix.stream().map(Artist::getName)
			.reduce(
				new StringBuilder(), 
				(builder, name) -> {
					if (builder.length() > 0)
						builder.append(", ");
					builder.append(name);
					return builder;
				}, 
				(left, right) -> left.append(right));
		reduced.insert(0, "[");
		reduced.append("]");
		String ArtistName = reduced.toString();
		System.out.println("艺术家的名字："+ArtistName);
		
		// 方法2： 使用StringCombiner类来包装操作
		StringCombiner combined = artsMix
				.stream()
				.map(Artist::getName)
				.reduce(new StringCombiner(", ", "[", "]"),
						StringCombiner::add, 
						StringCombiner::merge);
		String ArtistName2 = combined.toString();
		System.out.println("艺术家的名字2：" + ArtistName2);
		
		// 方法3： 自己重构的收集器
		String construction = artsMix
				.stream()
				.map(Artist::getName).collect(new StringCollector(",", "[", "]"));
		System.out.println("艺术家的名字3：" + construction);
		
		
		/***************** 对收集器的归一化处理 ******************/
		
		String result2 = artsMix.stream().map(Artist::getName)
				.collect(Collectors.reducing(
						new StringCombiner(", ", "[", "]"),
						name -> new StringCombiner(", ", "[", "]").add(name),
						StringCombiner::merge))
				.toString();
	}

	/**
	 * 获取对象中，某个成员变量最大数量的 对象 　找出成员最多的乐队
	 * 
	 * @param artists
	 * @return
	 */
	public Optional<Artist> biggestGroup(Stream<Artist> artists) {
		// 比数量
		Function<Artist, Long> getCount = artist -> artist.getMembers().count();
		// 获取最大数量
		return artists
				.collect(Collectors.maxBy(Comparator.comparing(getCount)));
	}

	/**
	 * 获取对象中，某个成员变量最小数量的 对象
	 * 
	 * @param artists
	 * @return
	 */
	public Optional<Artist> minGroup(Stream<Artist> artists) {
		// 比数量
		Function<Artist, Long> getCount = artist -> artist.getMembers().count();
		// 获取最小数量
		return artists
				.collect(Collectors.minBy(Comparator.comparing(getCount)));
	}

	/**
	 * 获取 专辑乐队数量的平均值
	 * 
	 * @param artists
	 * @return
	 */
	public double getAveraging(Stream<Artist> artists) {
		// ToIntFunction<Artist> mapper = a->(int)a.getMembers().count();
		return artists.collect(Collectors.averagingInt(a -> (int) a
				.getMembers().count()));
	}

	public double getAverage(Stream<AGroup> group) {
		// ToIntFunction<AGroup> mapper = a->a.getNumbers();
		// 获取最小数量
		return group.collect(Collectors.averagingInt(a -> a.getNumbers()));
	}

	/**
	 * 对象中某值 的求和，平均值，最大最小值等操作 求数值的分析
	 * 
	 * @param group
	 * @return
	 */
	public IntSummaryStatistics getSumStatistics(Stream<AGroup> group) {
		// 获取最小数量
		return group.collect(Collectors.summarizingInt(a -> a.getNumbers()));
	}

	/**
	 * 专辑乐队是否是单个人 数据分块， 放于map中 true 单人 false 多人
	 * 
	 * @param artists
	 * @return
	 */
	public Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
		return artists.collect(Collectors.partitioningBy(artist -> artist
				.isSolo()));
	}

	/**
	 * 使用方法引用
	 * 
	 * @param artists
	 * @return
	 */
	public Map<Boolean, List<Artist>> bandsAndSolo2(Stream<Artist> artists) {
		return artists.collect(Collectors.partitioningBy(Artist::isSolo));
	}

	/**
	 * 按专辑当中的主唱对专辑分组
	 * 
	 * Stream --> map
	 * 
	 * 选出相同key的集合，组成map
	 * 
	 * @param albums
	 * @return
	 */
	public Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {		
		return albums.collect(Collectors.groupingBy(album -> album
				.getMainMusician()));
	}

	/**
	 * Stream 流生成 字符串
	 * 
	 * Collectors.joining("分解符", "前缀", "后缀")
	 * 
	 * @param artists
	 * @return
	 */
	public String makeString(Stream<Artist> artists) {
		String result = artists.map(Artist::getName).collect(
				Collectors.joining(", ", "[", "]"));
		return result;
	}

	/**
	 * 使用收集器计算每个艺术家的专辑数
	 * 
	 * @param albums
	 * @return
	 */
	public Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
		return albums.collect(Collectors.groupingBy(
				album -> album.getMainMusician(), Collectors.counting()));
	}

	/**
	 * 使用收集器求每个艺术家的专辑名
	 * 
	 * @param albums
	 * @return
	 */
	public Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {		
		return albums.collect(Collectors.groupingBy(Album::getMainMusician,
				Collectors.mapping(Album::getName, Collectors.toList())));
	}

	static class AGroup {
		public int numbers;
		
		public String groupName;
		
		public String getGroupName(String s){
			return groupName;
		}

		public AGroup(int num) {
			numbers = num;
		}

		public int getNumbers() {
			return numbers;
		}
	}
	
	static class StringCombiner {

	    private final String prefix;
	    private final String suffix;
	    private final String delim;
	    private final StringBuilder builder;

	    public StringCombiner(String delim, String prefix, String suffix) {
	        this.prefix = prefix;
	        this.suffix = suffix;
	        this.delim = delim;
	        this.builder = new StringBuilder();
	    }

	    // BEGIN add
	    public StringCombiner add (String word) {
	        if(!this.areAtStart()) {
	            this.builder.append(delim);
	        }
	        this.builder.append(word);

	        return this;
	    }
	    // END add

	    // BEGIN merge
	    public StringCombiner merge (StringCombiner other) {
	        if(!other.equals(this)) {
	            if(!other.areAtStart() && !this.areAtStart()){
	                other.builder.insert(0, this.delim);
	            }
	            this.builder.append(other.builder);
	        }
	        return this;
	    }
	    // END merge

	    // BEGIN toString
	    @Override
	    public String toString() {
	        return prefix + builder.toString() + suffix;
	    }
	    // END toString

	    // BEGIN areAtStart
	    private boolean areAtStart() {
	        return builder.length() == 0;
	    }
	    // END areAtStart
	}
	
	/**
	 * 自定义 收集器
	 * 
	 * 􀅖 待收集元素的类型，这里是 String； 
	 * 􀅖 累加器的类型 StringCombiner； 
	 * 􀅖 最终结果的类型，这里依然是
	 * String。
	 * 
	 * @author Administrator
	 *
	 */
	static class StringCollector implements Collector<String, StringCombiner, String> {
		private static final Set<Characteristics> characteristics = Collections.emptySet();
		
		public String delim;
		public String prefix;
		public String suffix;
		
		public StringCollector(String delim, String prefix, String suffix){
			this.delim = delim;
			this.prefix = prefix;
			this.suffix = suffix;			
		}		

		/**
		 * 收集器的supplier这是一个工厂方法，用来创建容器，在 这个例子中，就是StringCombiner 
		 * 和reduce 操作中的第一个参数类似，它是后续操作的
		 * 初值
		 */
		@Override
		public Supplier<StringCombiner> supplier() {
			// TODO Auto-generated method stub
			return ()-> new StringCombiner(delim, prefix, suffix);
		}

		/**
		 * 收集器的accumulator 的作用和reduce 操作的第二个参数一样，
		 * 它结合之前操作的结果 和当前值，生成并返回新的值
		 * 这一逻辑已经在StringCombiners 的add 方法中得以实现
		 */
		@Override
		public BiConsumer<StringCombiner, String> accumulator() {
			BiConsumer<StringCombiner, String> bb = (StringCombiner, String)->{
				StringCombiner.add(String);
			};
			// TODO Auto-generated method stub
			return StringCombiner::add;
		}

		/**
		 * combine 方法很像reduce 操作的第三个方法。如果有两个容器，我们需要将其合并 同样，在
		 * 前面的重构中我们已经实现了该功能，直接使用StringCombiner.merge 方法就行了
		 */
		@Override
		public BinaryOperator<StringCombiner> combiner() {
			// TODO Auto-generated method stub
			return StringCombiner::merge;
		}

		@Override
		public Function<StringCombiner, String> finisher() {
			// TODO Auto-generated method stub
			return StringCombiner::toString;
		}

		/**
		 * 关于收集器，还有一点一直没有提及，那就是特征。
		 * 特征是一组描述收集器的对象，框架 可以对其适当优化。characteristics
		 * 方法定义了特征。
		 */
		@Override
		public Set<java.util.stream.Collector.Characteristics> characteristics() {
			// TODO Auto-generated method stub
			 return characteristics;
		}
		
	}

	public static int doubleTesst(Integer ss) {
		return 1;
	}

	public static int doubleTesst(String ss) {
		return 1;
	}

	public static String doubleTest(String ss) {
		ss = "&&&&&&&&&&&&&&&& " + ss + " this is result";
		return ss;
	}
	
	public String doubleTest2(String ss) {
		ss = "&&&&&&&&&&&&&&&& " + ss + " this is result";
		return ss;
	}
	
	public String doubleTest3() {
		String ss = "&&&&&&&&&&&&&&&& " + " this is result";
		return ss;
	}

	public int doubleTesst2() {
		return 1;
	}

	public String doubleTs(String str) {
		return str;
	}
}

class test2<T> {

	private T value = null;

	private static test2<?> a = new test2<>();

	public test2(T value) {
		this.value = Objects.requireNonNull(value);
	}

	public test2() {

	}

	public static test2<?> getTest() {
		return a;
	}

	public T getValue() {
		return value;

	}

	public T getValue(T v) {
		return value;

	}

	/**
	 * 输入一个类型test2，返回另外一个类型的test2
	 * 
	 * @param mapper
	 * @return
	 */
	public <U> test2<U> map(Function<? super T, ? extends U> mapper) {
		U u = mapper.apply(value);
		return new test2<U>(u);
	}

	public <U> test2<U> map2(Function<? super T, ? extends U> mapper) {
		test2<U> tt = new test2<U>();
		return tt;
	}

}

@FunctionalInterface
interface IFunction<T> {
	test2<T> oneMethod(T t);
}
