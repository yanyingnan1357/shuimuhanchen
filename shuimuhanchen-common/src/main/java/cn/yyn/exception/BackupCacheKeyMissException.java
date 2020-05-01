package cn.yyn.exception;

public class BackupCacheKeyMissException extends RuntimeException {

    public BackupCacheKeyMissException(String message, Throwable cause) {
        super(message, cause);
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

