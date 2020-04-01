package cn.yyn.postCache.exception;

public class KryoCodecException extends RuntimeException {

    private static final long serialVersionUID = 9172336149805414947L;

    public KryoCodecException(Throwable cause) {
        super(cause.getMessage(), cause);
        setStackTrace(cause.getStackTrace());
    }
}
