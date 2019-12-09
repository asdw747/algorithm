/**
 * Copyright (C) 2013, Xiaomi Inc. All rights reserved.
 */

package mars.utils.phone;

import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used to create an binary file which used to find some data according to a simple
 * index (int).
 * <p/>
 * This class allows multiple kinds of data. For example, two kinds of data should be built
 * together, one is an integer and a string, the other is two strings.
 * <p/>
 * For each kind of data, the index can be grouped to improve performance and reduce the size of
 * binary file. for example, the index is ranged as [0-100], [10000-20000], [50000, 100000], it can
 * be added as 3 groups.
 * <h3>Usage</h3> The {@link Builder} is used to creates binary file, and {@link Reader} is used to
 * read the binary file.
 * <h4>Builder</h4> The below codes shows how to create a indexed file, which has 2 data, 3 groups.
 * <p/>
 * <pre>
 *     DirectIndexedFile.Builder builder = DirectIndexedFile.build();
 *     builder.addKind("", ""), // adds kind first with two strings as data, empty strings are the default values
 *     builder.newGroup();
 *     for (int i = 50000; i < 100000; ++i) {
 *         builder.add(i, data1[i], data2[i]);
 *     }
 *     builder.newGroup();
 *     for (int i = 0; i < 100; ++i) {
 *         builder.add(i, data1[i], data2[i]);
 *     }
 *     builder.newGroup();
 *     for (int i = 10000; i < 20000; ++i) {
 *         builder.add(i, data1[i], data2[i]);
 *     }
 *     builder.write(file);
 * </pre>
 * <p/>
 * <h4>Reader</h4> The below codes shows how to read a indexed file.
 * <p/>
 * <pre>
 * DirectIndexedFile.Reader reader = DirectIndexedFile.open(file);
 * Object[] data = reader.get(0, 99); // gets all data from kind 0, index 99
 * Object singleDate = reader.get(0, 99, 1); // gets the second data from kind 0, index 99
 * reader.close();
 * </pre>
 */
public class DirectIndexedFile {
    private static final String LOG_TAG = "DensityIndexFile: ";
    private static final boolean DEBUG = false;

    /**
     * Only for extends, please don't instantiate this class.
     *
     * @throws InstantiationException always when this class is instantiated.
     */
    protected DirectIndexedFile() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    private static class IndexGroupDescriptor {
        int mMinIndex;
        int mMaxIndex;
        long mOffset;

        private IndexGroupDescriptor(int minIndex, int maxIndex, long offset) {
            mMinIndex = minIndex;
            mMaxIndex = maxIndex;
            mOffset = offset;
        }

        private static IndexGroupDescriptor read(DataInput i) throws IOException {
            int minIndex = i.readInt();
            int maxIndex = i.readInt();
            long offset = i.readLong();
            return new IndexGroupDescriptor(minIndex, maxIndex, offset);
        }

        private int write(DataOutput o) throws IOException {
            if (o != null) {
                o.writeInt(mMinIndex);
                o.writeInt(mMaxIndex);
                o.writeLong(mOffset);
            }
            return 4 + 4 + 8;
        }
    }

    private static class DataItemDescriptor {
        private enum Type {
            BYTE,
            SHORT,
            INTEGER,
            LONG,
            STRING,
            BYTE_ARRAY,
            SHORT_ARRAY,
            INTEGER_ARRAY,
            LONG_ARRAY,
        }

        private static byte[] sBuffer = new byte[1024];

        private Type mType;
        private byte mIndexSize;
        private byte mLengthSize;
        private byte mOffsetSize;
        private long mOffset;

        private static byte[] aquireBuffer(int size) {
            synchronized (DataItemDescriptor.class) {
                if (sBuffer == null || sBuffer.length < size) {
                    sBuffer = new byte[size];
                }
                byte[] bs = sBuffer;
                sBuffer = null;
                return bs;
            }
        }

        private static void releaseBuffer(byte[] buffer) {
            synchronized (DataItemDescriptor.class) {
                if (buffer != null && (sBuffer == null || sBuffer.length < buffer.length)) {
                    sBuffer = buffer;
                }
            }
        }

        private DataItemDescriptor(Type type, byte indexSize, byte lengthSize, byte offsetSize,
                long offset) {
            mType = type;
            mIndexSize = indexSize;
            mLengthSize = lengthSize;
            mOffsetSize = offsetSize;
            mOffset = offset;
        }

        private static DataItemDescriptor read(DataInput i) throws IOException {
            Type type = Type.values()[i.readByte()];
            byte indexSize = i.readByte();
            byte lengthSize = i.readByte();
            byte offsetSize = i.readByte();
            long offset = i.readLong();
            return new DataItemDescriptor(type, indexSize, lengthSize, offsetSize, offset);
        }

        private int write(DataOutput o) throws IOException {
            if (o != null) {
                o.writeByte(mType.ordinal());
                o.writeByte(mIndexSize);
                o.writeByte(mLengthSize);
                o.writeByte(mOffsetSize);
                o.writeLong(mOffset);
            }
            return 2 + 2 + 8;
        }

        private int writeOffsets(DataOutput o, List<Object> dataItems) throws IOException {
            if (o == null || mOffsetSize == 0 || mLengthSize == 0) {
                int maxLength = 0;
                int offset = 4 * dataItems.size();
                for (Object dataItem : dataItems) {
                    int length = 0;
                    switch (mType) {
                        case STRING:
                            length = ((String) dataItem).getBytes().length;
                            offset += length;
                            break;
                        case BYTE_ARRAY:
                            length = ((byte[]) dataItem).length;
                            offset += length;
                            break;
                        case SHORT_ARRAY:
                            length = ((short[]) dataItem).length;
                            offset += 2 * length;
                            break;
                        case INTEGER_ARRAY:
                            length = ((int[]) dataItem).length;
                            offset += 4 * length;
                            break;
                        case LONG_ARRAY:
                            length = ((long[]) dataItem).length;
                            offset += 8 * length;
                            break;
                    }
                    if (maxLength < length) {
                        maxLength = length;
                    }
                }
                mLengthSize = getSizeOf(maxLength);
                offset += mLengthSize * dataItems.size();
                mOffsetSize = getSizeOf(offset);
            }

            int written = mOffsetSize * dataItems.size();
            if (o != null) {
                int offset = written;
                for (Object dataItem : dataItems) {
                    writeAccordingToSize(o, mOffsetSize, offset);
                    switch (mType) {
                        case STRING:
                            offset += mLengthSize + ((String) dataItem).getBytes().length;
                            break;
                        case BYTE_ARRAY:
                            offset += mLengthSize + ((byte[]) dataItem).length;
                            break;
                        case SHORT_ARRAY:
                            offset += mLengthSize + 2 * ((short[]) dataItem).length;
                            break;
                        case INTEGER_ARRAY:
                            offset += mLengthSize + 4 * ((int[]) dataItem).length;
                            break;
                        case LONG_ARRAY:
                            offset += mLengthSize + 8 * ((long[]) dataItem).length;
                            break;
                    }
                }
            }
            return written;
        }

        private int writeDataItems(DataOutput o, List<Object> dataItems) throws IOException {
            int written = 0;
            switch (mType) {
                case BYTE:
                    if (o != null) {
                        o.writeByte((Byte) dataItems.get(0));
                    }
                    written += 1;
                    break;
                case SHORT:
                    if (o != null) {
                        o.writeShort((Short) dataItems.get(0));
                    }
                    written += 2;
                    break;
                case INTEGER:
                    if (o != null) {
                        o.writeInt((Integer) dataItems.get(0));
                    }
                    written += 4;
                    break;
                case LONG:
                    if (o != null) {
                        o.writeLong((Long) dataItems.get(0));
                    }
                    written += 8;
                    break;
                case STRING:
                    if (o != null) {
                        o.writeInt(dataItems.size());
                    }
                    written += 4;
                    written += writeOffsets(o, dataItems);

                    for (Object dataItem : dataItems) {
                        String d = (String) dataItem;
                        byte[] bs = d.getBytes();
                        if (o != null) {
                            writeAccordingToSize(o, mLengthSize, bs.length);
                            for (byte b : bs) {
                                o.writeByte(b);
                            }
                        }
                        written += mLengthSize + bs.length;
                    }
                    break;
                case BYTE_ARRAY:
                    if (o != null) {
                        o.writeInt(dataItems.size());
                    }
                    written += 4;
                    written += writeOffsets(o, dataItems);

                    for (Object dataItem : dataItems) {
                        byte[] d = (byte[]) dataItem;
                        if (o != null) {
                            writeAccordingToSize(o, mLengthSize, d.length);
                            o.write(d);
                        }
                        written += mLengthSize + d.length;
                    }
                    break;
                case SHORT_ARRAY:
                    if (o != null) {
                        o.writeInt(dataItems.size());
                    }
                    written += 4;
                    written += writeOffsets(o, dataItems);

                    for (Object dataItem : dataItems) {
                        short[] d = (short[]) dataItem;
                        if (o != null) {
                            writeAccordingToSize(o, mLengthSize, d.length);
                            for (short s : d) {
                                o.writeShort(s);
                            }
                        }
                        written += mLengthSize + 2 * d.length;
                    }
                    break;
                case INTEGER_ARRAY:
                    if (o != null) {
                        o.writeInt(dataItems.size());
                    }
                    written += 4;
                    written += writeOffsets(o, dataItems);

                    for (Object dataItem : dataItems) {
                        int[] d = (int[]) dataItem;
                        if (o != null) {
                            writeAccordingToSize(o, mLengthSize, d.length);
                            for (int i : d) {
                                o.writeInt(i);
                            }
                        }
                        written += mLengthSize + 4 * d.length;
                    }
                    break;
                case LONG_ARRAY:
                    if (o != null) {
                        o.writeInt(dataItems.size());
                    }
                    written += 4;
                    written += writeOffsets(o, dataItems);

                    for (Object dataItem : dataItems) {
                        long[] d = (long[]) dataItem;
                        if (o != null) {
                            writeAccordingToSize(o, mLengthSize, d.length);
                            for (long l : d) {
                                o.writeLong(l);
                            }
                        }
                        written += mLengthSize + 8 * d.length;
                    }
                    break;
                default:
                    break;
            }
            return written;
        }

        private Object readSingleDataItem(InputFile file, int dataItemIndex) throws IOException {
            Object ret = null;
            byte[] buf = null;

            long basePos = file.getFilePointer();
            if (dataItemIndex != 0) {
                file.seek(basePos + mOffsetSize * dataItemIndex);
            }
            file.seek(basePos + readAccordingToSize(file, mOffsetSize));

            switch (mType) {
                case STRING: {
                    int length = (int) readAccordingToSize(file, mLengthSize);
                    buf = aquireBuffer(length);
                    file.readFully(buf, 0, length);
                    ret = new String(buf, 0, length);
                    break;
                }
                case BYTE_ARRAY:
                    buf = new byte[(int) readAccordingToSize(file, mLengthSize)];
                    file.readFully(buf);
                    ret = buf;
                    buf = null;
                    break;
                case SHORT_ARRAY: {
                    short[] d = new short[(int) readAccordingToSize(file, mLengthSize)];
                    ret = d;
                    for (int j = 0; j < d.length; ++j) {
                        d[j] = file.readShort();
                    }
                    break;
                }
                case INTEGER_ARRAY: {
                    int[] d = new int[(int) readAccordingToSize(file, mLengthSize)];
                    ret = d;
                    for (int j = 0; j < d.length; ++j) {
                        d[j] = file.readInt();
                    }
                    break;
                }
                case LONG_ARRAY: {
                    long[] d = new long[(int) readAccordingToSize(file, mLengthSize)];
                    ret = d;
                    for (int j = 0; j < d.length; ++j) {
                        d[j] = file.readLong();
                    }
                    break;
                }
            }
            releaseBuffer(buf);
            return ret;
        }

        private Object[] readDataItems(InputFile file) throws IOException {
            Object[] ret = null;
            switch (mType) {
                case BYTE:
                    ret = new Object[1];
                    ret[0] = file.readByte();
                    break;
                case SHORT:
                    ret = new Object[1];
                    ret[0] = file.readShort();
                    break;
                case INTEGER:
                    ret = new Object[1];
                    ret[0] = file.readInt();
                    break;
                case LONG:
                    ret = new Object[1];
                    ret[0] = file.readLong();
                    break;
                case STRING:
                case BYTE_ARRAY:
                case SHORT_ARRAY:
                case INTEGER_ARRAY:
                case LONG_ARRAY:
                    ret = new Object[file.readInt()];
                    ret[0] = readSingleDataItem(file, 0);
                    break;
                default:
                    break;
            }
            return ret;
        }

        private static void writeAccordingToSize(DataOutput o, int size, long data)
                throws IOException {
            switch (size) {
                case 1:
                    o.writeByte((int) data);
                    break;
                case 2:
                    o.writeShort((int) data);
                    break;
                case 4:
                    o.writeInt((int) data);
                    break;
                case 8:
                    o.writeLong(data);
                    break;
                default:
                    throw new IllegalArgumentException("Unsuppoert size " + size);
            }
        }

        private static long readAccordingToSize(DataInput i, int size) throws IOException {
            long data;
            switch (size) {
                case 1:
                    data = i.readByte();
                    break;
                case 2:
                    data = i.readShort();
                    break;
                case 4:
                    data = i.readInt();
                    break;
                case 8:
                    data = i.readLong();
                    break;
                default:
                    throw new IllegalArgumentException("Unsuppoert size " + size);
            }
            return data;
        }

        private static byte getSizeOf(int length) {
            byte sizeOf = 0;
            for (long size = 2 * length; size > 0; size = size >> 8) {
                sizeOf++;
            }

            if (sizeOf == 3) {
                sizeOf = 4;
            } else if (sizeOf > 4 && sizeOf < 8) {
                sizeOf = 8;
            }

            return sizeOf;
        }
    }

    private static class DescriptionPair {
        private long mIndexGroupDescriptionOffset;
        private long mDataItemDescriptionOffset;

        private DescriptionPair(long indexGroupDescriptionOffset, long dataItemDescriptionOffset) {
            mIndexGroupDescriptionOffset = indexGroupDescriptionOffset;
            mDataItemDescriptionOffset = dataItemDescriptionOffset;
        }

        private static DescriptionPair read(DataInput i) throws IOException {
            long indexGroupDescriptionOffset = i.readLong();
            long dataItemDescriptionOffset = i.readLong();
            return new DescriptionPair(indexGroupDescriptionOffset, dataItemDescriptionOffset);
        }

        private int write(DataOutput o) throws IOException {
            if (o != null) {
                o.writeLong(mIndexGroupDescriptionOffset);
                o.writeLong(mDataItemDescriptionOffset);
            }
            return 8 + 8;
        }
    }

    private static class FileHeader {
        private final static byte[] FILE_TAG = new byte[] {
                'I', 'D', 'F', ' '
        };

        private final static int CURRENT_VERSION = 2;

        private DescriptionPair[] mDescriptionOffsets;
        private int mDataVersion;

        private FileHeader(int dataCount, int dataVersion) {
            mDescriptionOffsets = new DescriptionPair[dataCount];
            mDataVersion = dataVersion;
        }

        private static FileHeader read(DataInput i) throws IOException {
            byte[] fileTag = new byte[FILE_TAG.length];
            for (int index = 0; index < fileTag.length; ++index) {
                fileTag[index] = i.readByte();
            }
            if (!Arrays.equals(fileTag, FILE_TAG)) {
                throw new IOException("File tag unmatched, file may be corrupt");
            }
            int version = i.readInt();
            if (version != CURRENT_VERSION) {
                throw new IOException("File version unmatched, please upgrade your reader");
            }

            int count = i.readInt();
            int dataVersion = i.readInt();
            FileHeader header = new FileHeader(count, dataVersion);
            for (int k = 0; k < count; ++k) {
                header.mDescriptionOffsets[k] = DescriptionPair.read(i);
            }
            return header;
        }

        private int write(DataOutput o) throws IOException {
            int written = FILE_TAG.length + 4 + 4 + 4;
            if (o != null) {
                o.write(FILE_TAG);
                o.writeInt(CURRENT_VERSION);
                o.writeInt(mDescriptionOffsets.length);
                o.writeInt(mDataVersion);
            }
            for (DescriptionPair mDescriptionOffset : mDescriptionOffsets) {
                written += mDescriptionOffset.write(o);
            }
            return written;
        }
    }

    private interface InputFile extends DataInput {
        public void seek(long offset) throws IOException;

        public void close() throws IOException;

        public long getFilePointer() throws IOException;
    }

    // class used to hold RandomAccessFile that can be used as an InputFile.
    private static class DataInputRandom implements InputFile {
        private RandomAccessFile mFile;

        DataInputRandom(RandomAccessFile file) {
            mFile = file;
        }

        @Override
        public boolean readBoolean() throws IOException {
            return mFile.readBoolean();
        }

        @Override
        public byte readByte() throws IOException {
            return mFile.readByte();
        }

        @Override
        public char readChar() throws IOException {
            return mFile.readChar();
        }

        @Override
        public double readDouble() throws IOException {
            return mFile.readDouble();
        }

        @Override
        public float readFloat() throws IOException {
            return mFile.readFloat();
        }

        @Override
        public void readFully(byte[] dst) throws IOException {
            mFile.readFully(dst);
        }

        @Override
        public void readFully(byte[] dst, int offset, int byteCount) throws IOException {
            mFile.readFully(dst, offset, byteCount);
        }

        @Override
        public int readInt() throws IOException {
            return mFile.readInt();
        }

        @Override
        public String readLine() throws IOException {
            return mFile.readLine();
        }

        @Override
        public long readLong() throws IOException {
            return mFile.readLong();
        }

        @Override
        public short readShort() throws IOException {
            return mFile.readShort();
        }

        @Override
        public int readUnsignedByte() throws IOException {
            return mFile.readUnsignedByte();
        }

        @Override
        public int readUnsignedShort() throws IOException {
            return mFile.readUnsignedShort();
        }

        @Override
        public String readUTF() throws IOException {
            return mFile.readUTF();
        }

        @Override
        public int skipBytes(int count) throws IOException {
            return mFile.skipBytes(count);
        }

        @Override
        public void seek(long offset) throws IOException {
            mFile.seek(offset);
        }

        @Override
        public void close() throws IOException {
            mFile.close();
        }

        @Override
        public long getFilePointer() throws IOException {
            return mFile.getFilePointer();
        }
    }

    // class used to hold InputStream that can be used as an InputFile.
    private static class DataInputStream implements InputFile {
        private InputStream mInputFile;
        private long mInputPos;

        DataInputStream(InputStream stream) {
            mInputFile = stream;
            mInputFile.mark(0);
            mInputPos = 0;
        }

        @Override
        public boolean readBoolean() throws IOException {
            mInputPos += 1;
            return mInputFile.read() != 0;
        }

        @Override
        public byte readByte() throws IOException {
            mInputPos += 1;
            return (byte) mInputFile.read();
        }

        @Override
        public char readChar() throws IOException {
            byte b[] = new byte[2];
            char c = 0;
            mInputPos += 2;
            if (mInputFile.read(b) == 2) {
                c = (char) (b[1] & 0xff);
                c |= (b[0] << 8) & 0xff00;
            }
            return c;
        }

        @Override
        public double readDouble() throws IOException {
            throw new IOException();
        }

        @Override
        public float readFloat() throws IOException {
            throw new IOException();
        }

        @Override
        public void readFully(byte[] dst) throws IOException {
            int len = mInputFile.read(dst);
            mInputPos += len;
        }

        @Override
        public void readFully(byte[] dst, int offset, int byteCount) throws IOException {
            int len = mInputFile.read(dst, offset, byteCount);
            mInputPos += len;
        }

        @Override
        public int readInt() throws IOException {
            byte b[] = new byte[4];
            int i = 0;
            mInputPos += 4;
            if (mInputFile.read(b) == 4) {
                i = b[3] & 0xff;
                i |= (b[2] << 8) & 0xff00;
                i |= (b[1] << 16) & 0xff0000;
                i |= (b[0] << 24) & 0xff000000;
            }
            return i;
        }

        @Override
        public String readLine() throws IOException {
            throw new IOException();
        }

        @Override
        public long readLong() throws IOException {
            byte b[] = new byte[8];
            long l = 0;
            mInputPos += 8;
            if (mInputFile.read(b) == 8) {
                l = b[7] & 0xff;
                l |= (b[6] << 8) & 0xff00l;
                l |= (b[5] << 16) & 0xff0000l;
                l |= (b[4] << 24) & 0xff000000l;
                l |= ((long) b[3] << 32) & 0xff00000000l;
                l |= ((long) b[2] << 40) & 0xff0000000000l;
                l |= ((long) b[1] << 48) & 0xff000000000000l;
                l |= ((long) b[0] << 56) & 0xff00000000000000l;
            }
            return l;
        }

        @Override
        public short readShort() throws IOException {
            byte b[] = new byte[2];
            short s = 0;
            mInputPos += 2;
            if (mInputFile.read(b) == 2) {
                s = (short) (b[1] & 0xff);
                s |= (b[0] << 8) & 0xff00;
            }
            return s;
        }

        @Override
        public int readUnsignedByte() throws IOException {
            mInputPos += 1;
            return (byte) mInputFile.read();
        }

        @Override
        public int readUnsignedShort() throws IOException {
            byte b[] = new byte[2];
            short s = 0;
            mInputPos += 2;
            if (mInputFile.read(b) == 2) {
                s = (short) (b[1] & 0xff);
                s |= (b[0] << 8) & 0xff00;
            }
            return s;
        }

        @Override
        public String readUTF() throws IOException {
            throw new IOException();
        }

        @Override
        public int skipBytes(int count) throws IOException {
            count = (int) mInputFile.skip(count);
            mInputPos += count;
            return count;
        }

        @Override
        public void seek(long offset) throws IOException {
            mInputFile.reset();
            if (mInputFile.skip(offset) == offset) {
                mInputPos = offset;
            } else {
                throw new IOException("Skip failed");
            }
        }

        @Override
        public void close() throws IOException {
            mInputFile.close();
        }

        @Override
        public long getFilePointer() throws IOException {
            return mInputPos;
        }
    }

    /**
     * Class to read indexed file.
     */
    public static class Reader {
        private static class IndexData {
            private IndexGroupDescriptor[] mIndexGroupDescriptions;
            private DataItemDescriptor[] mDataItemDescriptions;
            private Object[][] mDataItems;
            private int mSizeOfItems;
        }

        private InputFile mFile;
        private FileHeader mHeader;
        private IndexData[] mIndexData;

        private Reader(InputStream inputstream) throws IOException {
            mFile = new DataInputStream(inputstream);
            constructFromFile("assets");
        }

        private Reader(String file) throws IOException {
            mFile = new DataInputRandom(new RandomAccessFile(file, "r"));
            constructFromFile(file);
        }

        private void constructFromFile(String file) throws IOException {
            long time = System.currentTimeMillis();
            try {
                mFile.seek(0);
                mHeader = FileHeader.read(mFile);

                mIndexData = new IndexData[mHeader.mDescriptionOffsets.length];
                for (int k = 0; k < mHeader.mDescriptionOffsets.length; ++k) {
                    mIndexData[k] = new IndexData();
                    mFile.seek(mHeader.mDescriptionOffsets[k].mIndexGroupDescriptionOffset);
                    int IndexGroupDescriptionCount = mFile.readInt();
                    mIndexData[k].mIndexGroupDescriptions = new IndexGroupDescriptor[IndexGroupDescriptionCount];
                    for (int i = 0; i < IndexGroupDescriptionCount; ++i) {
                        mIndexData[k].mIndexGroupDescriptions[i] = IndexGroupDescriptor.read(mFile);
                    }

                    mFile.seek(mHeader.mDescriptionOffsets[k].mDataItemDescriptionOffset);
                    int dataItemDescriptionCount = mFile.readInt();
                    mIndexData[k].mSizeOfItems = 0;
                    mIndexData[k].mDataItemDescriptions = new DataItemDescriptor[dataItemDescriptionCount];
                    for (int i = 0; i < dataItemDescriptionCount; ++i) {
                        mIndexData[k].mDataItemDescriptions[i] = DataItemDescriptor.read(mFile);
                        mIndexData[k].mSizeOfItems += mIndexData[k].mDataItemDescriptions[i].mIndexSize;
                    }

                    mIndexData[k].mDataItems = new Object[dataItemDescriptionCount][];
                    for (int i = 0; i < dataItemDescriptionCount; ++i) {
                        mFile.seek(mIndexData[k].mDataItemDescriptions[i].mOffset);
                        mIndexData[k].mDataItems[i] = mIndexData[k].mDataItemDescriptions[i]
                                .readDataItems(mFile);
                    }
                }
            } catch (IOException e) {
                close();
                throw e;
            }
            if (DEBUG) {
                time = System.currentTimeMillis() - time;
                System.out.println(LOG_TAG + time + "ms used to load file " + file);
            }
        }

        public int getDataVersion() {
            if (mHeader == null) {
                return -1;
            }

            return mHeader.mDataVersion;
        }

        /**
         * Gets the data in given data {@code kind}, given {@code index} and given {@code dataIndex}
         * .
         *
         * @param kind      the kind of data.
         * @param index     the data index.
         * @param dataIndex the index inner data.
         * @return the given data specified by {@code kind}, {@code index} and {@code dataIndex}. Or
         * the default values when not found the given {@code index}.
         */
        public synchronized Object get(int kind, int index, int dataIndex) {
            if (mFile == null) {
                throw new IllegalStateException("Get data from a corrupt file");
            }
            if (kind < 0 || kind >= mIndexData.length) {
                throw new IllegalArgumentException(
                        "Kind " + kind + " out of range[0, " + mIndexData.length + ")");
            }
            if (dataIndex < 0 || dataIndex >= mIndexData[kind].mDataItemDescriptions.length) {
                throw new IllegalArgumentException("DataIndex " + dataIndex + " out of range[0, "
                        + mIndexData[kind].mDataItemDescriptions.length + ")");
            }

            long time = System.currentTimeMillis();

            long offset = offset(kind, index);
            Object ret = null;
            if (offset < 0) {
                ret = mIndexData[kind].mDataItems[dataIndex][0];
            } else {
                try {
                    mFile.seek(offset);
                    for (int i = 0; i <= dataIndex; ++i) {
                        switch (mIndexData[kind].mDataItemDescriptions[i].mType) {
                            case BYTE:
                                ret = mFile.readByte();
                                break;
                            case SHORT:
                                ret = mFile.readShort();
                                break;
                            case INTEGER:
                                ret = mFile.readInt();
                                break;
                            case LONG:
                                ret = mFile.readLong();
                                break;
                            case STRING:
                            case BYTE_ARRAY:
                            case SHORT_ARRAY:
                            case INTEGER_ARRAY:
                            case LONG_ARRAY:
                                try {
                                    int dataItemIndex = (int) DataItemDescriptor
                                            .readAccordingToSize(mFile,
                                                    mIndexData[kind].mDataItemDescriptions[i].mIndexSize);
                                    if (i == dataIndex) {
                                        ret = readSingleDataItem(kind, dataIndex, dataItemIndex);
                                    }
                                } catch (IOException e) {
                                    throw new IllegalStateException(
                                            "File may be corrupt due to invalid data index size",
                                            e);
                                }
                                break;
                            default:
                                throw new IllegalStateException("Unknown type "
                                        + mIndexData[kind].mDataItemDescriptions[i].mType);
                        }
                    }
                } catch (IOException e) {
                    throw new IllegalStateException("Seek data from a corrupt file", e);
                }
            }

            if (DEBUG) {
                time = System.currentTimeMillis() - time;
                System.out.println(
                        LOG_TAG + time + "ms used to get data(" + kind + ", " + index + ", " +
                                dataIndex
                                + ")");
            }
            return ret;
        }

        /**
         * Gets the data in given data {@code kind}, given {@code index}.
         *
         * @param kind  the kind of data.
         * @param index the data index.
         * @return the given data specified by {@code kind} and {@code index}. Or
         * the default values when not found the given {@code index}.
         */
        public synchronized Object[] get(int kind, int index) {
            if (mFile == null) {
                throw new IllegalStateException("Get data from a corrupt file");
            }
            if (kind < 0 || kind >= mIndexData.length) {
                throw new IllegalArgumentException("Cannot get data kind " + kind);
            }

            long time = System.currentTimeMillis();

            long offset = offset(kind, index);
            Object[] ret = new Object[mIndexData[kind].mDataItemDescriptions.length];
            if (offset < 0) {
                for (int i = 0; i < ret.length; ++i) {
                    ret[i] = mIndexData[kind].mDataItems[i][0];
                }
                return ret;
            } else {
                try {
                    mFile.seek(offset);
                    for (int i = 0; i < ret.length; ++i) {
                        switch (mIndexData[kind].mDataItemDescriptions[i].mType) {
                            case BYTE:
                                ret[i] = mFile.readByte();
                                break;
                            case SHORT:
                                ret[i] = mFile.readShort();
                                break;
                            case INTEGER:
                                ret[i] = mFile.readInt();
                                break;
                            case LONG:
                                ret[i] = mFile.readLong();
                                break;
                            case STRING:
                            case BYTE_ARRAY:
                            case SHORT_ARRAY:
                            case INTEGER_ARRAY:
                            case LONG_ARRAY:
                                try {
                                    int dataItemIndex = (int) DataItemDescriptor
                                            .readAccordingToSize(mFile,
                                                    mIndexData[kind].mDataItemDescriptions[i].mIndexSize);
                                    long curPos = mFile.getFilePointer();
                                    ret[i] = readSingleDataItem(kind, i, dataItemIndex);
                                    mFile.seek(curPos);
                                } catch (IOException e) {
                                    throw new IllegalStateException(
                                            "File may be corrupt due to invalid data index size",
                                            e);
                                }
                                break;
                            default:
                                throw new IllegalStateException("Unknown type "
                                        + mIndexData[kind].mDataItemDescriptions[i].mType);
                        }
                    }
                } catch (IOException e) {
                    throw new IllegalStateException("Seek data from a corrupt file", e);
                }
            }

            if (DEBUG) {
                time = System.currentTimeMillis() - time;
                System.out.println(
                        LOG_TAG + time + "ms used to get data(" + kind + ", " + index + ")");
            }
            return ret;
        }

        /**
         * Closes the current indexed file.
         */
        public synchronized void close() {
            if (mFile != null) {
                try {
                    mFile.close();
                } catch (IOException e) {
                    // ignore
                }
            }

            mFile = null;
            mHeader = null;
            mIndexData = null;
        }

        private Object readSingleDataItem(int kind, int dataIndex, int dataItemIndex)
                throws IOException {
            if (mIndexData[kind].mDataItems[dataIndex][dataItemIndex] == null) {
                mFile.seek(mIndexData[kind].mDataItemDescriptions[dataIndex].mOffset + 4);
                mIndexData[kind].mDataItems[dataIndex][dataItemIndex] = mIndexData[kind].mDataItemDescriptions[dataIndex]
                        .readSingleDataItem(mFile, dataItemIndex);
            }
            return mIndexData[kind].mDataItems[dataIndex][dataItemIndex];
        }

        private long offset(int kind, int index) {
            IndexGroupDescriptor id = null;
            int min = 0, max = mIndexData[kind].mIndexGroupDescriptions.length;
            while (min < max) {
                int found = (max + min) / 2;
                if (mIndexData[kind].mIndexGroupDescriptions[found].mMinIndex > index) {
                    max = found;
                } else if (mIndexData[kind].mIndexGroupDescriptions[found].mMaxIndex <= index) {
                    min = found + 1;
                } else {
                    id = mIndexData[kind].mIndexGroupDescriptions[found];
                    break;
                }
            }

            long offset = -1;
            if (id != null) {
                offset = id.mOffset + (index - id.mMinIndex) * mIndexData[kind].mSizeOfItems;
            }
            return offset;
        }
    }

    /**
     * Class to build indexed file.
     */
    public static class Builder {
        private class Item implements Comparable<Item> {
            private int mIndex;
            private Object[] mObjects;

            private Item(int index, Object[] objects) {
                mIndex = index;
                mObjects = objects;
            }

            @Override
            public int hashCode() {
                return mIndex;
            }

            @Override
            public boolean equals(Object o) {
                boolean result = false;
                if (o == this) {
                    result = true;
                } else if (o instanceof Item) {
                    result = mIndex == ((Item) o).mIndex;
                }
                return result;
            }

            @Override
            public int compareTo(Item another) {
                return mIndex - another.mIndex;
            }
        }

        private class DataItemHolder {
            private HashMap<Object, Integer> mMap = new HashMap<Object, Integer>();
            private ArrayList<Object> mList = new ArrayList<Object>();

            private Integer put(Object obj) {
                Integer i = mMap.get(obj);
                if (i == null) {
                    i = mList.size();
                    mMap.put(obj, i);
                    mList.add(obj);
                }
                return i;
            }

            private int size() {
                return mList.size();
            }

            private ArrayList<Object> getAll() {
                return mList;
            }
        }

        private static class IndexData {
            private Item mDefaultValue;

            private HashMap<Integer, Item> mDataMap;
            private ArrayList<ArrayList<Item>> mIndexDataGroups;
            private ArrayList<DataItemHolder> mDataItemHolders;

            private DataItemDescriptor[] mDataItemDescriptions;
            private IndexGroupDescriptor[] mIndexGroupDescriptions;

            private IndexData(int dataItemCount) {
                mDataMap = new HashMap<Integer, Item>();
                mDataItemHolders = new ArrayList<DataItemHolder>();
                mIndexDataGroups = new ArrayList<ArrayList<Item>>();
                mDataItemDescriptions = new DataItemDescriptor[dataItemCount];
            }
        }

        private FileHeader mFileHeader;
        private ArrayList<IndexData> mIndexDataList;
        private IndexData mCurrentIndexData;
        private int mFileDataVersion;

        private Builder(int dataVersion) {
            mIndexDataList = new ArrayList<IndexData>();
            mFileDataVersion = dataVersion;
        }

        /**
         * Adds kind of data.
         *
         * @param objects the default values for this kind of data.
         */
        public void addKind(Object... objects) {
            mCurrentIndexData = new IndexData(objects.length);
            mIndexDataList.add(mCurrentIndexData);

            for (int i = 0; i < objects.length; ++i) {
                mCurrentIndexData.mDataItemHolders.add(new DataItemHolder());

                DataItemDescriptor.Type type;
                byte indexSize = 1;
                if (objects[i] instanceof Byte) {
                    type = DataItemDescriptor.Type.BYTE;
                    indexSize = 1;
                    mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                } else if (objects[i] instanceof Short) {
                    type = DataItemDescriptor.Type.SHORT;
                    indexSize = 2;
                    mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                } else if (objects[i] instanceof Integer) {
                    type = DataItemDescriptor.Type.INTEGER;
                    indexSize = 4;
                    mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                } else if (objects[i] instanceof Long) {
                    type = DataItemDescriptor.Type.LONG;
                    indexSize = 8;
                    mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                } else if (objects[i] instanceof String) {
                    type = DataItemDescriptor.Type.STRING;
                    objects[i] = mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                } else if (objects[i] instanceof byte[]) {
                    type = DataItemDescriptor.Type.BYTE_ARRAY;
                    objects[i] = mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                } else if (objects[i] instanceof short[]) {
                    type = DataItemDescriptor.Type.SHORT_ARRAY;
                    objects[i] = mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                } else if (objects[i] instanceof int[]) {
                    type = DataItemDescriptor.Type.INTEGER_ARRAY;
                    objects[i] = mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                } else if (objects[i] instanceof long[]) {
                    type = DataItemDescriptor.Type.LONG_ARRAY;
                    objects[i] = mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                } else {
                    throw new IllegalArgumentException(
                            "Unsupported type of the [" + i + "] argument");
                }

                mCurrentIndexData.mDataItemDescriptions[i] = new DataItemDescriptor(type, indexSize,
                        (byte) 0,
                        (byte) 0, 0);
            }

            mCurrentIndexData.mDefaultValue = new Item(-1, objects);
        }

        /**
         * Adds new group of data.
         */
        public void newGroup() {
            if (mCurrentIndexData.mIndexDataGroups.size() == 0
                    || mCurrentIndexData.mIndexDataGroups
                    .get(mCurrentIndexData.mIndexDataGroups.size() - 1).size() != 0) {
                mCurrentIndexData.mIndexDataGroups.add(new ArrayList<Item>());
            }
        }

        /**
         * Adds new group and its data.
         *
         * @param indexes the indexes for data of this group.
         * @param objects the all data of this group.
         */
        public void addGroup(int[] indexes, Object[][] objects) {
            checkCurrentIndexingDataKind();

            if (indexes.length == objects.length) {
                newGroup();
                for (int i = 0; i < indexes.length; ++i) {
                    add(indexes[i], objects[i]);
                }
            } else {
                throw new IllegalArgumentException("Different number between indexes and objects");
            }
        }

        /**
         * Adds data into the current group.
         *
         * @param index   the index for the data.
         * @param objects the data specified by the {@code index}.
         */
        public void add(int index, Object... objects) {
            checkCurrentIndexingGroup();

            if (mCurrentIndexData.mDataItemDescriptions.length != objects.length) {
                throw new IllegalArgumentException("Different number of objects inputted, "
                        + mCurrentIndexData.mDataItemDescriptions.length + " data expected");
            }

            for (int i = 0; i < objects.length; ++i) {
                switch (mCurrentIndexData.mDataItemDescriptions[i].mType) {
                    case BYTE:
                        if (!(objects[i] instanceof Byte)) {
                            throw new IllegalArgumentException("Object[" + i + "] should be byte");
                        }
                        break;
                    case SHORT:
                        if (!(objects[i] instanceof Short)) {
                            throw new IllegalArgumentException("Object[" + i + "] should be short");
                        }
                        break;
                    case INTEGER:
                        if (!(objects[i] instanceof Integer)) {
                            throw new IllegalArgumentException("Object[" + i + "] should be int");
                        }
                        break;
                    case LONG:
                        if (!(objects[i] instanceof Long)) {
                            throw new IllegalArgumentException("Object[" + i + "] should be long");
                        }
                        break;
                    case STRING:
                        if (!(objects[i] instanceof String)) {
                            throw new IllegalArgumentException(
                                    "Object[" + i + "] should be String");
                        }
                        objects[i] = mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                        mCurrentIndexData.mDataItemDescriptions[i].mIndexSize = DataItemDescriptor
                                .getSizeOf(mCurrentIndexData.mDataItemHolders.get(i).size());
                        break;
                    case BYTE_ARRAY:
                        if (!(objects[i] instanceof byte[])) {
                            throw new IllegalArgumentException(
                                    "Object[" + i + "] should be byte[]");
                        }
                        objects[i] = mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                        mCurrentIndexData.mDataItemDescriptions[i].mIndexSize = DataItemDescriptor
                                .getSizeOf(mCurrentIndexData.mDataItemHolders.get(i).size());
                        break;
                    case SHORT_ARRAY:
                        if (!(objects[i] instanceof short[])) {
                            throw new IllegalArgumentException(
                                    "Object[" + i + "] should be short[]");
                        }
                        objects[i] = mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                        mCurrentIndexData.mDataItemDescriptions[i].mIndexSize = DataItemDescriptor
                                .getSizeOf(mCurrentIndexData.mDataItemHolders.get(i).size());
                        break;
                    case INTEGER_ARRAY:
                        if (!(objects[i] instanceof int[])) {
                            throw new IllegalArgumentException("Object[" + i + "] should be int[]");
                        }
                        objects[i] = mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                        mCurrentIndexData.mDataItemDescriptions[i].mIndexSize = DataItemDescriptor
                                .getSizeOf(mCurrentIndexData.mDataItemHolders.get(i).size());
                        break;
                    case LONG_ARRAY:
                        if (!(objects[i] instanceof long[])) {
                            throw new IllegalArgumentException(
                                    "Object[" + i + "] should be long[]");
                        }
                        objects[i] = mCurrentIndexData.mDataItemHolders.get(i).put(objects[i]);
                        mCurrentIndexData.mDataItemDescriptions[i].mIndexSize = DataItemDescriptor
                                .getSizeOf(mCurrentIndexData.mDataItemHolders.get(i).size());
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported type of objects " + i + ", "
                                + mCurrentIndexData.mDataItemDescriptions[i].mType + " expected");
                }
            }

            Item item = new Item(index, objects);
            mCurrentIndexData.mDataMap.put(index, item);
            mCurrentIndexData.mIndexDataGroups.get(mCurrentIndexData.mIndexDataGroups.size() - 1)
                    .add(item);
        }

        /**
         * Writes all data into given file.
         *
         * @param file the path to write the indexed data.
         * @throws IOException if an I/O error occurs when writing data
         */
        public void write(String file) throws IOException {
            build();

            DataOutputStream dos = null;
            boolean done = false;
            try {
                dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                writeAll(dos);
                done = true;
            } finally {
                if (dos != null) {
                    dos.close();
                }

                if (!done) {
                    if (new File(file).delete()) {
                        System.err.println("Cannot delete file " + file);
                    }
                }
            }
        }

        private void checkCurrentIndexingDataKind() {
            if (mCurrentIndexData == null) {
                throw new IllegalArgumentException("Please add a data kind before adding group");
            }
        }

        private void checkCurrentIndexingGroup() {
            checkCurrentIndexingDataKind();
            if (mCurrentIndexData.mIndexDataGroups.size() == 0) {
                throw new IllegalArgumentException("Please add a data group before adding data");
            }
        }

        private void build() {
            int dataCount = mIndexDataList.size();
            mFileHeader = new FileHeader(dataCount, mFileDataVersion);

            for (int k = 0; k < dataCount; ++k) {
                IndexData idk = mIndexDataList.get(k);
                mFileHeader.mDescriptionOffsets[k] = new DescriptionPair(0, 0);

                int length;
                for (length = 0; length < idk.mIndexDataGroups.size(); ++length) {
                    if (idk.mIndexDataGroups.get(length).size() == 0) {
                        break;
                    }
                }

                idk.mIndexGroupDescriptions = new IndexGroupDescriptor[length];
                for (int i = 0; i < idk.mIndexGroupDescriptions.length; ++i) {
                    ArrayList<Item> items = idk.mIndexDataGroups.get(i);
                    Collections.sort(items);

                    int minIndex = items.get(0).mIndex;
                    int maxIndex = items.get(items.size() - 1).mIndex + 1;
                    idk.mIndexGroupDescriptions[i] = new IndexGroupDescriptor(minIndex, maxIndex,
                            0);
                }
            }

            try {
                writeAll(null);
            } catch (IOException e) {
                // never happen
            }
        }

        private int writeAll(DataOutput o) throws IOException {
            int written = 0;
            for (int k = 0; k < mFileHeader.mDescriptionOffsets.length; ++k) {
                written += mFileHeader.write(o);
                mFileHeader.mDescriptionOffsets[k].mIndexGroupDescriptionOffset = written;

                IndexData idk = mIndexDataList.get(k);
                if (o != null) {
                    o.writeInt(idk.mIndexGroupDescriptions.length);
                }
                written += 4;
                for (int i = 0; i < idk.mIndexGroupDescriptions.length; ++i) {
                    written += idk.mIndexGroupDescriptions[i].write(o);
                }

                mFileHeader.mDescriptionOffsets[k].mDataItemDescriptionOffset = written;
                if (o != null) {
                    o.writeInt(idk.mDataItemDescriptions.length);
                }
                written += 4;
                for (int i = 0; i < idk.mDataItemDescriptions.length; ++i) {
                    written += idk.mDataItemDescriptions[i].write(o);
                }

                for (int i = 0; i < idk.mDataItemDescriptions.length; ++i) {
                    idk.mDataItemDescriptions[i].mOffset = written;
                    written += idk.mDataItemDescriptions[i]
                            .writeDataItems(o, idk.mDataItemHolders.get(i).getAll());
                }

                for (int i = 0; i < idk.mIndexGroupDescriptions.length; ++i) {
                    idk.mIndexGroupDescriptions[i].mOffset = written;
                    if (o == null) {
                        int sizeOfItem = 0;
                        for (int m = 0; m < idk.mDataItemDescriptions.length; ++m) {
                            sizeOfItem += idk.mDataItemDescriptions[m].mIndexSize;
                        }
                        written += (idk.mIndexGroupDescriptions[i].mMaxIndex -
                                idk.mIndexGroupDescriptions[i].mMinIndex)
                                * sizeOfItem;
                    } else {
                        for (int j = idk.mIndexGroupDescriptions[i].mMinIndex;
                             j < idk.mIndexGroupDescriptions[i].mMaxIndex; ++j) {
                            Item item = idk.mDataMap.get(j);
                            if (item == null) {
                                item = idk.mDefaultValue;
                            }
                            for (int m = 0; m < idk.mDataItemDescriptions.length; ++m) {
                                if (idk.mDataItemDescriptions[m].mIndexSize == 1) {
                                    o.writeByte((Integer) item.mObjects[m]);
                                } else if (idk.mDataItemDescriptions[m].mIndexSize == 2) {
                                    o.writeShort((Integer) item.mObjects[m]);
                                } else if (idk.mDataItemDescriptions[m].mIndexSize == 4) {
                                    o.writeInt((Integer) item.mObjects[m]);
                                } else if (idk.mDataItemDescriptions[m].mIndexSize == 8) {
                                    o.writeLong((Long) item.mObjects[m]);
                                }
                                written += idk.mDataItemDescriptions[m].mIndexSize;
                            }
                        }
                    }
                }
            }
            return written;
        }
    }

    /**
     * Creates a new {@link Builder} instance.
     */
    public static Builder build(int dataVersion) {
        return new Builder(dataVersion);
    }

    /**
     * Opens an indexed file as a {@link Reader}.
     *
     * @throws IOException when an I/O error occurs while reading
     */
    public static Reader open(String file) throws IOException {
        return new Reader(file);
    }

    /**
     * Opens an indexed file as a {@link Reader}.
     *
     * @throws IOException when an I/O error occurs while reading
     */
    public static Reader open(InputStream inpuStream) throws IOException {
        return new Reader(inpuStream);
    }
}
