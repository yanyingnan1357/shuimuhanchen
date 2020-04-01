package cn.yyn.common;

import com.vladsch.flexmark.Extension;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;

import java.util.Arrays;

/**
 * Markdown转Html工具类
 * 在数据库中保存的是md源码，而返回前台前端希望的是直接拿到html代码，这样能很方便的输出
 * @author:yyn
 */
public class Markdown2HtmlUtil {
    /**
     * 将markdown源码转换成html返回
     *
     * @param markdown md源码
     * @return html代码
     */
    public static String markdown2html(String markdown) {
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        options.set(Parser.EXTENSIONS, Arrays.asList(new Extension[]{TablesExtension.create()}));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(markdown);
        return renderer.render(document);
    }
}
