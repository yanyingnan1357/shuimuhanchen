package cn.yyn.common;

import cn.yyn.exception.RouterException;
import org.apache.commons.lang3.StringUtils;

public class RouterUtil {
    public static String getTableSuffixByValue(String value) {
        if (StringUtils.isBlank(value)) {
            throw new RouterException("路由主键编码为空");
        }
        //16张表做hash 与操作，只会使值更小 因为位运算同时为1才为1
        return appendZeros(Math.abs(HashUtil.murmur32AsInt(value)) & (16 - 1));
    }


    private static String appendZeros(Integer num) {
        return String.format("%02d", num);
    }

}
