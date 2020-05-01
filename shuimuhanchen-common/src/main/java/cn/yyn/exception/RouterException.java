package cn.yyn.exception;


import org.apache.commons.lang3.StringUtils;

public class RouterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msgShow;

    public RouterException(Object... msg) {
        super(StringUtils.join(msg,"-"));
        this.msgShow = getMessage();
    }


    public String getMsgShow() {
        return msgShow;
    }

    public void setMsgShow(String msgShow) {
        this.msgShow = msgShow;
    }

}
