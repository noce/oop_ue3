//hab die jez LongPretty erweitern lassen
public class ShortPretty extends LinePretty{
	public ShortPretty(int depth){
		super(depth);
	}

	@Override
	protected void writeOpenBrace(StringBuilder buf) {
		buf.append(afterTokenWhitespace);
		buf.append("{");
	}
}