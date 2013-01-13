package de.cgz.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import de.cgz.data.types.InjectStatement;
import de.cgz.data.types.MutableSimpleDataObject;
import de.cgz.data.types.MutableSimpleDataObjectImpl;
import de.cgz.data.types.Statement;
import de.cgz.data.types.TypeFactory;
import de.cgz.data.types.collection.collection.DataCollection;
import de.cgz.data.types.collection.collection.ListDataCollection;
import de.cgz.data.types.collection.collection.OrderedDataCollection;


public class CollectionTest {
	
	private static final TypeFactory tf = TypeFactory.getInstance();
	
	private ListDataCollection<Integer> numberList = TypeFactory.getInstance().createListDataCollection(new Integer[]{222, 374, 323, 509, 409, 436, 606, 614, 53, 289, 756, 564, 52, 980, 287, 677, 191, 342, 623, 799, 709, 660, 323, 810, 479, 266, 197, 457, 272, 254, 548, 909, 417, 319, 963, 125, 794, 928, 727, 845, 37, 964, 181, 122, 693, 943, 650, 8, 924, 375, 572, 400, 721, 734, 293, 626, 800, 565, 449, 219, 468, 310, 701, 410, 720, 390, 519, 884, 603, 701, 724, 994, 321, 476, 380, 925, 574, 985, 922, 406, 429, 290, 303, 459, 863, 748, 399, 41, 32, 902, 723, 891, 147, 439, 61, 669, 917, 721, 899, 191, 975, 944, 610, 185, 21, 667, 267, 0, 251, 630, 267, 226, 151, 657, 293, 523, 610, 913, 122, 717, 593, 313, 913, 973, 360, 781, 905, 192, 837, 490, 314, 458, 98, 173, 964, 510, 356, 799, 432, 655, 337, 161, 899, 369, 182, 162, 24, 557, 926, 474, 772, 72, 255, 802, 421, 343, 159, 883, 748, 992, 972, 519, 554, 888, 376, 167, 471, 637, 819, 450, 93, 663, 355, 863, 851, 424, 838, 936, 358, 836, 200, 643, 617, 879, 976, 438, 181, 354, 244, 517, 867, 298, 501, 907, 958, 765, 830, 483, 943, 313, 70, 810, 408, 507, 525, 564, 353, 465, 402, 599, 345, 133, 99, 599, 732, 662, 718, 927, 699, 164, 860, 657, 98, 352, 470, 474, 938, 166, 143, 517, 559, 184, 793, 394, 700, 112, 707, 619, 217, 489, 248, 675, 783, 826, 211, 731, 962, 265, 982, 299, 821, 608, 482, 377, 852, 479, 993, 361, 906, 46, 127, 721, 947, 612, 759, 559, 572, 745, 274, 257, 231, 92, 306, 657, 266, 842, 604, 755, 406, 396, 380, 932, 870, 187, 275, 754, 822, 883, 283, 18, 858, 417, 483, 978, 296, 466, 205, 979, 710, 178, 733, 418, 925, 22, 733, 963, 352, 566, 243, 258, 21, 98, 860, 880, 393, 664, 689, 983, 83, 360, 815, 363, 846, 180, 846, 876, 378, 150, 935, 35});

	public CollectionTest() {
//		Random rnd = new Random();
//		Integer[] numbers = new Integer[330];
//		for (int i = 0; i < numbers.length; i++) {
//			numbers[i] = rnd.nextInt(1000);
//		}
//		StringBuilder builder = new StringBuilder("new Integer[]{");
//		for(Integer i : numbers) {
//			builder.append(i).append(", ");
//		}
//		String str = builder.substring(0, builder.length()-2);
//		str += "};";
//		System.out.println(str);
	}
	
	
	
//interface DataCollection	
//	public int size();
//	public boolean isEmpty();
//	public boolean contains(final T element);
//	
//	public List<T> toList();
//	public Set<T> toSet();
//	public T[] toArray();
//	
//	public Object forEach(final Statement<T> statement);
//	public <R> R forEach(final Class<? extends R> returnType, final Statement<T> statement);
//	public <M> M inject(final M mem, final InjectStatement<T, M> statement);
//	public <R> DataCollection<R> collect(final Class<? extends R> returnType, final Statement<T> statement);
//	
//	public T find(final Statement<T> statement);
//	public DataCollection<T> find_all(final Statement<T> statement);
//	
//	public  DataCollection<T> intersection(final DataCollection<T> other);
//	public  DataCollection<T> difference(final DataCollection<T> subtrahend);
//	public  DataCollection<T> union(final DataCollection<T> addend);	
//	
//	public T first();
//	public T last();
//	
//	public DataCollection<T> sort();
//	public DataCollection<T> sort(final Comparator<T> comp);	
	
	@Test
	public void sizeTest() {
		assertEquals(330, numberList.size());
	}
	
	@Test
	public void isEmptyTest() {
		assertFalse(numberList.isEmpty());
		assertTrue(TypeFactory.getInstance().createListDataCollection().isEmpty());
	}
	
	@Test
	public void containsTest() {
		assertTrue(numberList.contains(667));
		assertFalse(numberList.contains(-1));
	}
	
	@Test
	public void conversionTest() {
		Integer[] array = numberList.toArray(new Integer[0]);
		List<Integer> list = numberList.toList();
		Set<Integer> set = numberList.toSet();
		int i=0;
		for (Integer e : numberList) {
			assertTrue(array[i].equals(e));
			assertTrue(list.get(i).equals(e));
			assertTrue(set.contains(e));
			i++;
		}
	}
	
	
	@Test
	public void forEachTest() {

		final MutableSimpleDataObject<Integer> holder = new MutableSimpleDataObjectImpl<Integer>(0);
		numberList.forEach(new Statement<Integer>() {
			int c=0;
			public Object execute(Integer element, int index) {
				assertEquals(index, c);
				holder.setValue(c);
				c++;
				return null;
			}
		});
		
		assertEquals((Integer)(numberList.size()-1), holder.getValue());
		
		Integer fifthtElement = (Integer) numberList.forEach(new Statement<Integer>() {
			int c=0;
			public Object execute(Integer element, int index) {
				if(index == 5) {
					return element;
				}
				c++;
				return null;
			}
		});
		assertEquals(numberList.get(5), fifthtElement);
	}
	
	@Test
	public void injectTest() {
		Integer sum = 0;
		for (Integer e : numberList) {
			sum += e;
		}
		
		Integer sum2 = numberList.inject(0, new InjectStatement<Integer, Integer>() {
			public Integer execute(Integer element, int index, Integer mem) {				
				return mem + element;
			}
		});
		assertEquals(sum, sum2);		
	}
	
	@Test
	public void collectTest() {
		final OrderedDataCollection<String> numsAsStrings = (OrderedDataCollection<String>) numberList.collect(String.class, new Statement<Integer>() {
			public Object execute(Integer element, int index) {
				return ""+element;
			}			
		});
		
		numberList.forEach(new Statement<Integer>() {
			public Object execute(Integer element, int index) {
				assertEquals(element, (Integer)Integer.parseInt(numsAsStrings.get(index)));
				return null;
			}});
	}
	
	@Test
	public void findTest() {
		Integer result = numberList.find(new Statement<Integer>() {
			public Object execute(Integer element, int index) {
				return element.equals(846);
			}});
		assertEquals((Integer)846, result);
	}
	
	@Test
	public void findAllTest() {
		DataCollection<Integer> foundElements = numberList.findAll(new Statement<Integer>() {
			public Object execute(Integer element, int index) {
				return element % 5 == 0;
			}
		});
		foundElements.forEach(new Statement<Integer>() {
			public Object execute(Integer element, int index) {
				assertTrue(element % 5 == 0);
				return null;
			}});
	}

//	public  DataCollection<T> intersection(final DataCollection<T> other);
	@Test
	public void testIntersection() {
		ListDataCollection<Integer> a = tf.createListDataCollection(new Integer[]{1,2,3,4,5,6,7,8,9,10});
		ListDataCollection<Integer> b = tf.createListDataCollection(new Integer[]{        5,6,7,8,9,10,11,12,13,14,15});
		final ListDataCollection<Integer> expected = tf.createListDataCollection(new Integer[]{5,6,7,8,9,10});
		DataCollection<Integer> result = a.intersection(b);
		result.forEach(new Statement<Integer>() {
			public Object execute(Integer element, int index) {
				assertEquals(expected.get(index), element);
				return null;
			}});
	}
	
	@Test
	public void testDifference() {
		ListDataCollection<Integer> a = tf.createListDataCollection(new Integer[]{1,2,3,4,5,6,7,8,9,10});
		ListDataCollection<Integer> b = tf.createListDataCollection(new Integer[]{        5,6,7,8,9,10,11,12,13,14,15});
		final ListDataCollection<Integer> expected = tf.createListDataCollection(new Integer[]{1,2,3,4});		
		DataCollection<Integer> result = a.difference(b);
		
		result.forEach(new Statement<Integer>() {
			public Object execute(Integer element, int index) {
				assertEquals(expected.get(index), element);
				return null;
			}});		
	}
//	public  DataCollection<T> union(final DataCollection<T> addend);
	@Test
	public void unionTest() {
		ListDataCollection<Integer> a = tf.createListDataCollection(new Integer[]{1,2,3,4,5,6,7,8,9,10});
		ListDataCollection<Integer> b = tf.createListDataCollection(new Integer[]{        5,6,7,8,9,10,11,12,13,14,15});
		final ListDataCollection<Integer> expected = tf.createListDataCollection(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15});		
		DataCollection<Integer> result = a.union(b);
		assertEquals(expected.size(), result.size());
		assertEquals(expected.size(), result.toSet().size());
	}
	
	@Test
	public void firstTest() {
		assertEquals((Integer) 222, numberList.first());		
	}
	@Test
	public void lastTest() {
		assertEquals((Integer) 35, numberList.last());		
	}
//	
//	public T first();
//	public T last();

	@Test
	public void sortTest() {
		Integer[] a = new Integer[]{5,6,7,1,4,2,3,9,8};
		Integer[] b = new Integer[]{5,6,7,1,4,2,3,9,8};
		Arrays.sort(b);

		ListDataCollection<Integer> list = (ListDataCollection<Integer>) tf.createListDataCollection(a).sort();
		for (int i = 0; i < b.length; i++) {
			assertEquals(b[i], list.get(i));
		}
	}
//	public DataCollection<T> sort();
//	public DataCollection<T> sort(final Comparator<T> comp);		
	
	
	
	
//	public interface OrderedDataCollection<T> extends DataCollection<T> {
//
//		public int size();
//		public T get(final int index);	
//		public OrderedDataCollection<T> get(final Range<Integer> dataRange);
//		
//		public DataCollection<T> reverse();
//	}	

}
