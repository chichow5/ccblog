package com.example.ccblog.util;

import org.apache.commons.lang3.StringUtils;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class MyUtils {
    public static String md2h5(String mdcontent){
        if (StringUtils.isBlank(mdcontent)){
            return "";
        }
        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(mdcontent);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(context -> new LinkAttributeProvider())
                .extensions(extensions).build();
        String h5content = renderer.render(document);
        //h5content = Commons.emoji(h5content);
        return h5content;
    }

    static class LinkAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
        }
    }
}
