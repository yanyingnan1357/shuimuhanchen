package cn.yyn.enums;


/**
 * Created by yyn.
 */
public enum EnumMessageType {

    SELF_MSG("selfMsg", "信息传递自消费"),
    ;
    private String type;
    private String desc;

    EnumMessageType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

}
