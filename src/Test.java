
public class Test{
	static private int run=0, success=0;
	
	/*Gegenbeispiele:
	 * 1. Line Pretty erbt von BlockPretty:
	 * 		sobalt BlockPretty Parameter von newlines bekommt(Konstruktor) stimmts nicht
	 * 		da LinePretty diesen Parameter nicht hat
	 * 
	 * 2.NoPretty implementiert Pretty direkt:
	 * 		NoPretty ist ein Sonderfall von BLockPretty nur dass eben als Paramter 0 übergeben wird.
	 * 
	 * 3. Short & LongPretty erben von VarPretty
	 * 		geht nicht da erst zur Laufzeit feststeht, ob es ein Long oder Short wird
	 * 
	 * 4. BlockPretty erbt von LinePretty
	 * 		geht nicht da LinePretty viel mehr Funktionalität implementiert hat
	 */

	static void expectOutput(Pretty p, String in, String expected) {
		run++;
		System.out.print("### Testing " + p.getClass().toString() + " with input:\n`");
		System.out.print(in);
		System.out.print("`\nOutput:\n`");
		String actual = p.transform(in);
		System.out.print(actual);
		if (actual.equals(expected)) {
			System.out.println("`\nTransformation matches expected output:)");
			success++;
		} else {
			System.out.print("`\n!!!!! Doesnt match !!11 \n:( I expected:\n`");
			System.out.print(expected);
			System.out.println("`");
		}
		System.out.println("--------------------------------\n");
	}

	public static void main(String[] args){
		//LongPretty
		String in1 = "public class NoPretty extends BlockPretty{public NoPretty(){super(0);}}";
		String in2 = "class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }";
		String in3 = "class A { static main{ /*this is an exemple*/ x.z();    /*abc*/; if(f){g  ; if(h){ku}}" +
		"				 else { ch; }     }}   ";

		Pretty b1 = new LongPretty(2);
		expectOutput(b1, in1, "public class NoPretty extends BlockPretty\n{\n  public NoPretty()\n  {\n    super(0);\n  }\n}");
		expectOutput(b1, in2, "class A\n{\n  B c;\n  ;\n  D f\n  {\n    c.f(d) /*x*/;\n    /*y*/\n  }\n}");
		expectOutput(b1, "class A{  B c;  ;  D f  {    c", "class A\n{\n  B c;\n  ;\n  D f\n  {\n    c");
		expectOutput(b1, ".f(d) /*x*/;    /*y*/  }}", ".f(d) /*x*/;\n    /*y*/\n  }\n}");

		LinePretty b2 = new LongPretty(4);
		expectOutput(b2, in2, "class A\n{\n    B c;\n    ;\n    D f\n    {\n        c.f(d) /*x*/;\n        /*y*/\n    }\n}");

		//ShortPretty
		Pretty b3 = new ShortPretty(2);
		expectOutput(b3, in1, "public class NoPretty extends BlockPretty {\n  public NoPretty() {\n    super(0);\n  }\n}");
		expectOutput(b3, in2, "class A {\n  B c;\n  ;\n  D f {\n    c.f(d) /*x*/;\n    /*y*/\n  }\n}");
		expectOutput(b3, in3, "class A {\n  static main {\n    /*this is an exemple*/ x.z();\n    /*abc*/;\n    if(f) {\n      g;\n      if(h) {\n        ku\n      }\n    }\n    else {\n      ch;\n    }\n  }\n}");

		//NoPretty
		Pretty b4 = new NoPretty();
		expectOutput(b4, in1, in1);
		expectOutput(b4, in2, in2);
		expectOutput(b4, in3, in3);

		//MorePretty
		MorePretty b5 = new MorePretty(2);
		expectOutput(b5, in1, "public class NoPretty extends BlockPretty\n{\n  public NoPretty()\n  {\n    super(0);\n  }\n}");
		System.out.println("Open Braces: " + b5.open());
		expectOutput(b5, in2, "class A\n{\n  B c;\n  ;\n  D f\n  {\n    c.f(d) /*x*/;\n    /*y*/\n  }\n}");
		System.out.println("Open Braces: " + b5.open());
		b5.reset();
		expectOutput(b5, in3, "class A {\n  static main {\n    /*this is an exemple*/ x.z();\n    /*abc*/;\n    if(f) {\n      g;\n      if(h) {\n        ku\n      }\n    }\n    else {\n      ch;\n    }\n  }\n}");
		System.out.println("Open Braces: " + b5.open());

		//VarPretty
		VarPretty b6 = new VarPretty(2); //longPretty
		b6.setMode(true);
		b6.setDepth(3);
		Pretty b7 = b6;
		expectOutput(b7, in1, "public class NoPretty extends BlockPretty\n{\n  public NoPretty()\n  {\n    super(0);\n  }\n}");
		expectOutput(b7, in2, "class A\n{\n  B c;\n  ;\n  D f\n  {\n    c.f(d) /*x*/;\n    /*y*/\n  }\n}");
		expectOutput(b7, in3, "class A\n{\n  static main\n  {\n    /*this is an exemple*/ x.z();\n    /*abc*/;\n    if(f)\n    {\n      g;\n      if(h)\n      {\n        ku\n      }\n    }\n    else\n    {\n      ch;\n    }\n  }\n}");
		b7.reset();
		expectOutput(b7, in1, "public class NoPretty extends BlockPretty\n{\n  public NoPretty()\n  {\n    super(0);\n  }\n}");
		expectOutput(b7, in2, "class A\n{\n  B c;\n  ;\n  D f\n  {\n    c.f(d) /*x*/;\n    /*y*/\n  }\n}");
		expectOutput(b7, in3, "class A\n{\n  static main\n  {\n    /*this is an exemple*/ x.z();\n    /*abc*/;\n    if(f)\n    {\n      g;\n      if(h)\n      {\n        ku\n      }\n    }\n    else\n    {\n      ch;\n    }\n  }\n}");

		//BlockPretty
		Pretty b8 = new BlockPretty(3);
		expectOutput(b8, in1, "public class NoPretty extends BlockPretty{public NoPretty(){super(0);}\n\n\n}\n\n\n");
		expectOutput(b8, in2, "class A{ B c; ;D f { c.f(d) /*x*/; /*y*/}\n\n\n }\n\n\n");
		expectOutput(b8, in3, "class A { static main{ /*this is an exemple*/ x.z();    /*abc*/; if(f){g  ; if(h){ku}\n\n\n}\n\n\n				 else { ch; }\n\n\n     }\n\n\n}\n\n\n   ");

		System.out.println("================================\nSucceeded: " + success + "/" + run);
	}
}