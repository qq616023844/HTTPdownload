package util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Io {
	public static void unMapBuffer(MappedByteBuffer buffer, Class<? extends FileChannel> channelClass) throws IOException {
	    if (buffer == null) {
	        return;
	    }

	    Throwable throwable = null;
	    try {
	        Method unmap = channelClass.getDeclaredMethod("unmap", MappedByteBuffer.class);
	        unmap.setAccessible(true);
	        unmap.invoke(channelClass, buffer);
	    } catch (NoSuchMethodException e) {
	        throwable = e;
	    } catch (IllegalAccessException e) {
	        throwable = e;
	    } catch (InvocationTargetException e) {
	        throwable = e;
	    }

	    if (throwable != null) {
	        throw new IOException("MappedByte buffer unmap error", throwable);
	    }
	}
}
