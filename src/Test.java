
public class Test{
	public static void main(String[] args){
	//LongPretty
	Pretty b1 = new LongPretty(2);
	String t1 = b1.transform("public class NoPretty extends BlockPretty{public NoPretty(){super(0);}}");
	String t2 = b1.transform("class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }");
	System.out.println("LongPretty:" + "\n" + t1);
	System.out.println("LongPretty:" + "\n" + t2);
	
	//ShortPretty
	Pretty b2 = new ShortPretty(2);
	String t3 = b2.transform("class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }");
	Pretty b3 = new ShortPretty(4);
	String t4 = b3.transform("class A { static main{ /*this is an exemple*/ x.z();    /*abc*/; if(f){g  ; if(h){ku}}" +
			"				 else { ch; }     }}   ");
	System.out.println("ShortPretty:" + "\n" + t3);
	System.out.println("ShortPretty:" + "\n" + t4);
	
	//NoPretty
	Pretty b4 = new NoPretty();
	String t5 = b4.transform("class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }");
	System.out.println("NoPretty:" + "\n" + t5);
	
	//MorePretty
	MorePretty b5 = new MorePretty(2);
	String t6 = b5.transform("class A{ B c; ;D f { c.f(d)  {  /*x*/;{ /*y*/} }");
	System.out.println("MorePretty:" + "\n" + t6);
	System.out.println("Open Braces: " + b5.open());
	}
	
	//VarPretty
}