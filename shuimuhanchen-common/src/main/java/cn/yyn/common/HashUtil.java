package cn.yyn.common;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.CRC32;
import org.apache.commons.lang3.Validate;

public abstract class HashUtil {
    private static final ThreadLocal<MessageDigest> MD5_DIGEST = createThreadLocalMessageDigest("MD5");
    private static final ThreadLocal<MessageDigest> SHA_1_DIGEST = createThreadLocalMessageDigest("SHA-1");
    private static SecureRandom random = new SecureRandom();

    public HashUtil() {
    }

    private static ThreadLocal<MessageDigest> createThreadLocalMessageDigest(final String digest) {
        return new ThreadLocal<MessageDigest>() {
            protected MessageDigest initialValue() {
                try {
                    return MessageDigest.getInstance(digest);
                } catch (NoSuchAlgorithmException var2) {
                    throw new RuntimeException("unexpected exception creating MessageDigest instance for [" + digest + "]", var2);
                }
            }
        };
    }

    public static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }

    public static int hashCode(Collection<?> list) {
        if (list == null) {
            return 0;
        } else {
            int hashCode = 1;

            Object obj;
            for(Iterator it = list.iterator(); it.hasNext(); hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode())) {
                obj = it.next();
            }

            return hashCode;
        }
    }

    public static byte[] sha1(byte[] input) {
        return digest(input, get(SHA_1_DIGEST), (byte[])null, 1);
    }

    public static byte[] sha1(String input) {
        return digest(input.getBytes(Charsets.UTF_8), get(SHA_1_DIGEST), (byte[])null, 1);
    }

    public static byte[] sha1(byte[] input, byte[] salt) {
        return digest(input, get(SHA_1_DIGEST), salt, 1);
    }

    public static byte[] sha1(String input, byte[] salt) {
        return digest(input.getBytes(Charsets.UTF_8), get(SHA_1_DIGEST), salt, 1);
    }

    public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
        return digest(input, get(SHA_1_DIGEST), salt, iterations);
    }

    public static byte[] sha1(String input, byte[] salt, int iterations) {
        return digest(input.getBytes(Charsets.UTF_8), get(SHA_1_DIGEST), salt, iterations);
    }

    private static MessageDigest get(ThreadLocal<MessageDigest> messageDigest) {
        MessageDigest instance = (MessageDigest)messageDigest.get();
        instance.reset();
        return instance;
    }

    private static byte[] digest(byte[] input, MessageDigest digest, byte[] salt, int iterations) {
        if (salt != null) {
            digest.update(salt);
        }

        byte[] result = digest.digest(input);

        for(int i = 1; i < iterations; ++i) {
            digest.reset();
            result = digest.digest(result);
        }

        return result;
    }

    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", (long)numBytes);
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    public static byte[] sha1File(InputStream input) throws IOException {
        return digestFile(input, get(SHA_1_DIGEST));
    }

    public static byte[] md5File(InputStream input) throws IOException {
        return digestFile(input, get(MD5_DIGEST));
    }

    private static byte[] digestFile(InputStream input, MessageDigest messageDigest) throws IOException {
        int bufferLength = 8192;
        byte[] buffer = new byte[bufferLength];

        for(int read = input.read(buffer, 0, bufferLength); read > -1; read = input.read(buffer, 0, bufferLength)) {
            messageDigest.update(buffer, 0, read);
        }

        return messageDigest.digest();
    }

    public static int crc32AsInt(String input) {
        return crc32AsInt(input.getBytes(Charsets.UTF_8));
    }

    public static int crc32AsInt(byte[] input) {
        CRC32 crc32 = new CRC32();
        crc32.update(input);
        return (int)crc32.getValue();
    }

    public static long crc32AsLong(String input) {
        return crc32AsLong(input.getBytes(Charsets.UTF_8));
    }

    public static long crc32AsLong(byte[] input) {
        CRC32 crc32 = new CRC32();
        crc32.update(input);
        return crc32.getValue();
    }

    public static int murmur32AsInt(byte[] input) {
        return Hashing.murmur3_32().hashBytes(input).asInt();
    }

    public static int murmur32AsInt(String input) {
        return Hashing.murmur3_32().hashString(input, Charsets.UTF_8).asInt();
        }

    public static int murmur32AsInt(byte[] input, int seed) {
        return Hashing.murmur3_32(seed).hashBytes(input).asInt();
    }

    public static int murmur32AsInt(String input, int seed) {
        return Hashing.murmur3_32(seed).hashString(input, Charsets.UTF_8).asInt();
    }
}
