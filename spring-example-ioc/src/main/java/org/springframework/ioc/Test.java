package org.springframework.ioc;

public class Test {
	public static void main(String[] args) {
		String a = "abc";
		String b = "def";
		System.out.println("abcdef" == (a + b));//false 因为stringbuilder


		String c = "abc";
		String d = "def";
		System.out.println("abcdef" == (c + d)); //true 编译器ast 抽象语法树 如果在一个方法里 2个final 常量 相加 解析到 会直接放入常量池里
		System.out.println((c + d));

		String e = "abc";
		String f = "def";
		String g = e+f;
		String h  = g.intern();

		System.out.println(g == h); //true


	}

}
