/* LinePretty can have 3 states:
 * 
 * LINE_START: eats whitespace; indents and transitions to IN_TOKEN when it hits a non whitespace
 *             takes care of some edge cases with { and }
 * IN_TOKEN: outputs until it hits } or { or ; or a whitespace
 *           transitions to AFTER_TOKEN
 * AFTER_TOKEN: ; => eat whitespace before, transition to LINE_START
 *              { => call writeOpenBrace, transition to LINE_START
 *              } => call writeCloseBrace, transiton to LINE_START
 */
abstract class LinePretty extends BlockPretty {
	abstract protected void writeOpenBrace(StringBuilder buf);
	
	private enum LineState {
		LINE_START, // start of line; in this state whitespace should be stripped
		IN_TOKEN,
		AFTER_TOKEN // count whitespace, write if no ; follows
	}
	private LineState state = LineState.LINE_START;

	// valid in AFTER_TOKEN
	protected StringBuilder afterTokenWhitespace = new StringBuilder();
	
	protected int openBraces;
	protected int depth;

	//post: beginning of file state
	@Override
	public void reset() {
		super.reset();
		state = LineState.LINE_START;
		afterTokenWhitespace.setLength(0);
		openBraces = 0;
	}
	
	public LinePretty(int depth) {
		super(0);
		this.depth = depth;
		reset();
	}
	
	//post: appends a linebreak + indentation to buf
	protected void writeIndent(StringBuilder buf) {
		buf.append('\n');
		for (int i=0;i < openBraces * depth;i++) {
			buf.append(' ');
		}
	}
	
	//post: openBraces reflects count of { minus count of } since last reset
	//      state as in class doc
	//      afterTokenWhitespace holds whitespace that needs to be inserted before next regular token
	//
	// in String may be incomplete and/or continue previous transformation
	@Override
	public String transform(String in) {
		StringBuilder ret = new StringBuilder();
		
		// increment at last line, `continue parse_loop;` is the idiom to stay on current char
		int i = 0;
		parse_loop:
		while (i < in.length()) {
			char cur = in.charAt(i);

			switch (state) {
			case LINE_START:
				if (Character.isWhitespace(cur)) {
					// eat whitespace nom nom
					break;
				} else if (cur == '}') {
					openBraces--;
					writeIndent(ret);
					writeCloseBrace(ret);
					// stay in LINE_START, preventing superflous \n
					break;
				} else if (cur == '{') {
					// just indent and append the { right away instead if writeOpenBrace, since we only get here on ";{"
					writeIndent(ret);
					ret.append(cur);
					openBraces++;
					// stay in LINE_START, preventing superflous \n
					break;
				} else if (ret.length() > 0) {
					// don't write \n at start of file
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
				// write regular tokens
				ret.append(cur);
				break;
			case AFTER_TOKEN:
				if (Character.isWhitespace(cur)){
					// save whitespaces, to append when hitting next regular token
					afterTokenWhitespace.append(cur);
				} else if (cur == ';') {
					ret.append(cur);
					// eat saved whitespace
					afterTokenWhitespace.setLength(0);
					state = LineState.LINE_START;
				} else if (cur == '{') {
					writeOpenBrace(ret);
					openBraces++;
					// eat saved whitespace
					afterTokenWhitespace.setLength(0);
					state = LineState.LINE_START;
				} else if (cur == '}') {
					// eat saved whitespace, let LINE_START handle the {
					afterTokenWhitespace.setLength(0);
					state = LineState.LINE_START;
					continue parse_loop;
				} else {
					// regular token, append saved whitespace
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