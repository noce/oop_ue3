import java.util.Date;
import java.util.Iterator;


public class Test{
	public static void main(String[] args){
	BlockPretty b1 = new BlockPretty(2);
//	String t1 = b1.transform("public class NoPretty extends BlockPretty{public NoPretty(){super(0);}}");
	String t2 = b1.transform("class A{ B c; ;D f { c.f(d) /*x*/; /*y*/} }");
	System.out.println(t2);
	}
}