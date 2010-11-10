
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
	String t4 = b3.transform("class A{ static main{ /*this is an exemple*/ x.z();    /*abc*/; if(f){g  ; if(h){ku}}" +
			"				 else { ch; }     }}   ");
	System.out.println("ShortPretty:" + "\n" + t3);
	System.out.println("ShortPretty:" + "\n" + t4);
	
	//NoPretty
	Pretty b4 = new NoPretty();
	String t5 = b4.transform("class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }");
	System.out.println("NoPretty:" + "\n" + t5);
	
	//MorePretty
	Pretty b5 = new MorePretty();
	String t6 = b5.transform("class A{ B c; ;D f { c.f(d)  {  /*x*/;{ /*y*/} }");
	System.out.println("MorePretty:" + "\n" + t6);
	
	//VarPretty
	Pretty b6 = new VarPretty(true); //longPretty
	String t7 = b6.transform("class A{ B c; ;D f { c.f(d)  {  /*x*/;{ /*y*/} }");
	System.out.println("VarPretty(long, without depth):" + "\n" + t7);
	Pretty b8 = new VarPretty(true, 2); //longPretty
	String t9 = b8.transform("class A{ B c; ;D f { c.f(d)  {  /*x*/;{ /*y*/} }");
	System.out.println("VarPretty(long, with depth):" + "\n" + t9);
	Pretty b7 = new VarPretty(false); //longPretty
	String t8 = b7.transform("class A{ B c; ;D f { c.f(d)  {  /*x*/;{ /*y*/} }");
	System.out.println("VarPretty(short):" + "\n" + t8);
	
	}
}