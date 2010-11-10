//haut gleich alle leerzeilen bzw. Zeilen umbr�che raus
//Ersetzungsmap: key: das zu ersetzende Zeichen, value: die einzuf�genden Zeichen
//beeinhaltet die Logik zum ersetzen
//Mehode: addReplacement(regex pattern, String replacement)


/* LinePretty can have 3 states:
 * 
 * LINE_START: eats whitespace; indents and transitions to IN_TOKEN when it hits a non whitespace
 * IN_TOKEN: outputs until it hits } or { or ; or a whitespace
 *           transitions to AFTER_TOKEN
 * AFTER_TOKEN: ; => eat whitespace before, transition to LINE_START
 *              { => call writeOpenBrace, transition to LINE_START
 *              } => 
 * 
 */
abstract class LinePretty extends BlockPretty {
	abstract protected void writeOpenBrace(StringBuilder buf);
	
	private enum LineState {
		LINE_START, // start of line; in this state whitespace should be stripped
		IN_TOKEN,
		AFTER_TOKEN // count whitespace, write if no ; follows
	}
	private LineState state;

	// valid in AFTER_TOKEN
	protected StringBuilder afterTokenWhitespace = new StringBuilder();
	
	protected int openBraces;
	protected int depth;

	@Override
	public void reset() {
		state = LineState.LINE_START;
		afterTokenWhitespace.setLength(0);
		openBraces = 0;
	}
	
	public LinePretty(int depth) {
		super(1);
		this.depth = depth;
		reset();
	}
	
	protected void writeIndent(StringBuilder buf) {
		buf.append('\n');
		for (int i=0;i < openBraces * depth;i++) {
			buf.append(' ');
		}
	}
	
	@Override
	public String transform(String in) {
		StringBuilder ret = new StringBuilder();
		
		// increment at last line, continue; to stay on char
		int i = 0;
		parse_loop:
		while (i < in.length()) {
			char cur = in.charAt(i);

			switch (state) {
			case LINE_START:
				if (Character.isWhitespace(cur)) {
					break;
				} else if (cur == '}') {
					writeIndent(ret);
					writeCloseBrace(ret);
					state = LineState.IN_TOKEN;
					break;
				} else if (ret.length() > 0) {
					writeIndent(ret);
				}
				state = LineState.IN_TOKEN;
				continue parse_loop;
			case IN_TOKEN:
				if ( cur == '{' ||
					 cur == '}' ||
					 cur == ';' ||
					 Character.isWhitespace(cur)) {
					state = LineState.AFTER_TOKEN;
					continue parse_loop;
				}
				ret.append(cur);
				break;
			case AFTER_TOKEN:
				if (Character.isWhitespace(cur)){
					afterTokenWhitespace.append(cur);
				} else if (cur == ';') {
					ret.append(cur);

					afterTokenWhitespace.setLength(0);
					state = LineState.LINE_START;
				} else if (cur == '{') {
					writeOpenBrace(ret);
					openBraces++;
	
					afterTokenWhitespace.setLength(0);
					state = LineState.LINE_START;
				} else if (cur == '}') {
					openBraces--;
					writeIndent(ret);
					ret.append(cur);
					
					afterTokenWhitespace.setLength(0);
					state = LineState.IN_TOKEN;
				} else {
					ret.append(afterTokenWhitespace);
					ret.append(cur);

					afterTokenWhitespace.setLength(0);
					state = LineState.IN_TOKEN;
				}
				break;
			}
			i++;
		}
		
		return ret.toString();
	}

}