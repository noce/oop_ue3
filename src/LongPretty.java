public class LongPretty extends LinePretty{
	
	public LongPretty(int depth){
		super(depth);
	}

	@Override
	protected void writeOpenBrace(StringBuilder buf) {
		writeIndent(buf);
		buf.append('{');
	}
}