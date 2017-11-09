package com.zsc.csg.test;

public class InnerClass {
	
	class Content {
		private int i=11;
		public int value(){
			return i;
		}
	}
	class Destion {
		private String lable;
		Destion(String lables){lable=lables;}
		String readline(){return lable;}
	}
	public static void main(String[] args) {
		InnerClass ic = new InnerClass();
		InnerClass.Content inc = ic.new Content();
		InnerClass.Destion ind = ic.new Destion("hello");
		System.out.println(ind.readline()+inc.value());
	}
}
