package org.alunev.cubes;



public class ByteUtils {
    public static byte[] getReversedArray(byte[] bytes) {
        byte[] reversed = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            reversed[i] = bytes[bytes.length - 1 - i];
        }

        return reversed;
    }
}
