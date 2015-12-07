package com.github.servlet3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/servlet/demo", asyncSupported = true)
public class AsyncDemoServlet extends HttpServlet {
  /**
  * 
  */
  private static final long serialVersionUID = 9124009939971132424L;

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    resp.setContentType("text/html;charset=UTF-8");
    AsyncContext ctx = req.startAsync();
    ctx.addListener(new AsyncListener() {
      public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("AsyncListener onComplete");
        event.getSuppliedResponse().getWriter().println("异步调用完成……"); 
      }

      @Override
      public void onError(AsyncEvent event) throws IOException {
        System.out.println("AsyncListener onError");
        event.getSuppliedResponse().getWriter().println("异步调用出错……"); 
      }

      @Override
      public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("AsyncListener onStartAsync");
        event.getSuppliedResponse().getWriter().println("异步调用开始……"); 
      }

      @Override
      public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("AsyncListener onTimeout");
        event.getSuppliedResponse().getWriter().println("异步调用超时……"); 
      }
    });
    PrintWriter out = resp.getWriter();
    out.println("进入Servlet的时间：" + new Date() + ".");
    out.flush();

    // 在子线程中执行业务调用，并由其负责输出响应，主线程退出
    new Thread(new Executor(ctx)).start();

    out.println("结束Servlet的时间：" + new Date() + ".");
    out.flush();
  }
}

