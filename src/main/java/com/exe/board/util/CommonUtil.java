package com.exe.board.util;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {//�۵� ��ũ
	
	public String markdown(String markdown) {
		
		Parser parser = Parser.builder().build();
		
		Node document = parser.parse(markdown);
		
		HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
		
		return htmlRenderer.render(document);
		
		
	}
	
	
}
