package javaTest;

import java.util.Arrays;

public class LambdaTest {
	public static void main(String[] args) {
		Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
		
		Arrays.asList( "a", "b", "d" ).forEach( e -> {
		    System.out.print( e );
		    System.out.print( e );
		} );
	}

}
