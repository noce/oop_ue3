
public class Test{
	public static void main(String[] args){
	//LongPretty
	Pretty b1 = new LongPretty(2);
	LinePretty b30 = new LongPretty(2);
	String t1 = b1.transform("public class NoPretty extends BlockPretty{public NoPretty(){super(0);}}");
	String t2 = b1.transform("class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }");
	String t30 = b30.transform("class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }");
	System.out.println("LongPretty(Pretty):" + "\n" + t1);
	System.out.println("LongPretty(Pretty):" + "\n" + t2);
	System.out.println("LongPretty(LineP):" + "\n" + t30);
	
	//ShortPretty
	Pretty b2 = new ShortPretty(2);
	LinePretty b31 = new ShortPretty(2);
	String t3 = b2.transform("class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }");
	String t31 = b31.transform("class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }");
	Pretty b3 = new ShortPretty(4);
	String t4 = b3.transform("class A { static main{ /*this is an exemple*/ x.z();    /*abc*/; if(f){g  ; if(h){ku}}" +
			"				 else { ch; }     }}   ");
	System.out.println("ShortPretty(Pretty):" + "\n" + t3);
	System.out.println("ShortPretty(Pretty):" + "\n" + t4);
	System.out.println("ShortPretty(LineP):" + "\n" + t31);
	
	//NoPretty
	Pretty b4 = new NoPretty();
	String t5 = b4.transform("class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }");
	System.out.println("NoPretty:" + "\n" + t5);
	
	//MorePretty
	MorePretty b5 = new MorePretty(2);
	String t6 = b5.transform("class A{ B c; ;D f { c.f(d)  {  /*x*/;{ /*y*/} }");
	System.out.println("MorePretty:" + "\n" + t6);
	System.out.println("Open Braces: " + b5.open());
	
	//VarPretty
	Pretty b6 = new VarPretty(2); //longPretty
	String t7 = b6.transform("class A{ B c; ;D f { c.f(d)  {  /*x*/;{ /*y*/} }");
	System.out.println("VarPretty(long, without depth):" + "\n" + t7);
	Pretty b8 = new VarPretty(true, 2); //longPretty
	LinePretty b32 = new VarPretty(true, 2); //longPretty
	String t9 = b8.transform("class A{ B c; ;D f { c.f(d)  {  /*x*/;{ /*y*/} }");
	String t32 = b32.transform("class A{ B c; ;D f { c.f(d)  {  /*x*/;{ /*y*/} }");
	System.out.println("VarPretty(Pretty; long, with depth):" + "\n" + t9);
	System.out.println("VarPretty(LineP; long, with depth):" + "\n" + t32);
	
	VarPretty b7 = new VarPretty(false, 4); //shortPretty
	b7.setMode(true);
	b7.setDepth(3);
	String t8 = b7.transform("    \n\n  \t  class A{ B c; ;D f \n \t{ c.f(d)  {  /*x*/;{ /*y*/} }");
	System.out.println("VarPretty(short):" + "\n" + t8);
	b7.reset();
	String t10 = b7.transform("class A{ B c; ;D f { c.f(d)  {  /*x*/;{ /*y*/} }");
	System.out.println("VarPretty(long) after reset:" + "\n" + t10);
	
	//BlockPretty
	Pretty b9 = new BlockPretty(3);
	String t11 = b9.transform("class A{ B c; ;D f { c.f(d)  {  /*x*/;{ /*y*/} }");
	System.out.println("BlockPretty:" + "\n" + t11);
	
	//Sonderfaelle
	
	}
}