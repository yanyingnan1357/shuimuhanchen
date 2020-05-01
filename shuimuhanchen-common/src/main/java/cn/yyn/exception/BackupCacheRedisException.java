package cn.yyn.exception;

public class BackupCacheRedisException extends RuntimeException {

    public BackupCacheRedisException(String message, Throwable cause) {
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

