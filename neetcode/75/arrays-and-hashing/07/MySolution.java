import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class MySolution {

    private static final int SIZEOF_CHAR = 1;

    private static final int SIZEOF_INT = 4;

    /**
     * Encode a list of strings into a byte array.
     */
    public byte[] encode(List<String> strs) {
        int bytesRequired = computeBytesRequired(strs);
        byte[] rawBytesBuffer = new byte[bytesRequired];
        ByteBuffer bytesBuffer = ByteBuffer.wrap(rawBytesBuffer);

        for (String s : strs) {
            bytesBuffer.putInt(s.length());
            for (int i = 0; i < s.length(); i++) {
                bytesBuffer.putChar(s.charAt(i));
            }
        }

        return rawBytesBuffer;
    }

    /**
     * Determine how many bytes it would take to encode the input.
     */
    private int computeBytesRequired(List<String> strs) {
        int bytesRequired = 0;
        for (String s : strs) {
            bytesRequired = bytesRequired + SIZEOF_INT;
            bytesRequired += s.length() * SIZEOF_CHAR;
        }
        return bytesRequired;
    }

    /**
     * Decode the byte array (produced by {@link #encode}) into a list of strings.
     */
    public List<String> decode(byte[] rawBytesBuffer) {
        List<String> strings = new ArrayList<>();
        ByteBuffer bytesBuffer = ByteBuffer.wrap(rawBytesBuffer);

        while (bytesBuffer.position() < bytesBuffer.limit()) {
            int size = bytesBuffer.getInt();
            StringBuilder stringBuffer = new StringBuilder(size);
            while (size > 0) {
                char ch = bytesBuffer.getChar();
                stringBuffer.append(ch);
                size--;
            }
            strings.add(stringBuffer.toString());
        }

        return strings;
    }
}