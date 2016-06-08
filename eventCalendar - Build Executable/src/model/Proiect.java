package model;

public class Proiect {
	public static void main(String[] args) {
		HelloImpl h1 = new HelloImpl(), h2 = h1;
		h1.message = "Salut";
		System.out.println(h2.sayHello());
	}
}

interface Hello {
	String sayHello();
}

class HelloImpl implements Hello {
	static String message = "Hello";

	public String sayHello() {
		return message;
	}
}