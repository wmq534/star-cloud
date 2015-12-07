package com.github.requestserialid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MerchantResponseWrapper extends HttpServletResponseWrapper {
	private ByteArrayOutputStream buffer = null;
	private ServletOutputStream out = null;
	private PrintWriter writer = null;

	public MerchantResponseWrapper(HttpServletResponse resp) throws IOException {
		super(resp);
		buffer = new ByteArrayOutputStream();// 真正存储数据的流
		out = new WapperedOutputStream(buffer);
		writer = new PrintWriter(new OutputStreamWriter(buffer,resp.getCharacterEncoding()));
	}

	// 重载父类获取outputstream的方法
	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return out;
	}

	// 重载父类获取writer的方法
	@Override
	public PrintWriter getWriter() throws UnsupportedEncodingException {
		return writer;
	}

	// 重载父类获取flushBuffer的方法
	@Override
	public void flushBuffer() throws IOException {
		if (out != null) {
			out.flush();
		}
		if (writer != null) {
			writer.flush();
		}
	}

	@Override
	public void reset() {
		buffer.reset();
	}

	public String getContent() throws IOException {
		flushBuffer();// 将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据
		return new String(buffer.toByteArray());
	}

	// 内部类，对ServletOutputStream进行包装
	static class WapperedOutputStream extends ServletOutputStream {
		private ByteArrayOutputStream bos = null;

		public WapperedOutputStream(ByteArrayOutputStream stream)
				throws IOException {
			bos = stream;
		}

		@Override
		public void write(int b) throws IOException {
			bos.write(b);
		}

        @Override
        public boolean isReady() {
          // TODO Auto-generated method stub
          return false;
        }
    
        @Override
        public void setWriteListener(WriteListener arg0) {
          // TODO Auto-generated method stub
          
        }
	}
}
