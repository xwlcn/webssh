package online.webssh.test;

import java.util.Stack;

public class Test {
	
	public static void main(String[] args) {
		Stack<String> s = new Stack<>();
		s.push("/");
		s.push("root");
		s.push("abc");
		for (String str : s) {
			System.out.println(str);
		}
	}
}
