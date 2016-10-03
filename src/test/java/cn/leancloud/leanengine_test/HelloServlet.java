package cn.leancloud.leanengine_test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.internal.impl.JavaRequestSignImplementation;

import cn.leancloud.LeanEngine;

@WebServlet(name = "HelloServlet", urlPatterns = {"/hello"}, loadOnStartup = 1)
public class HelloServlet extends HttpServlet {

  private static final long serialVersionUID = 8165232904011951622L;

  @Override
  public void init() throws ServletException {
    super.init();
    LeanEngine.register(AllEngineFunctions.class);
    LeanEngine.register(AllEngineHook.class);
    LeanEngine.register(AllIMHook.class);
    AVOSCloud.initialize("CHmmO5noAiJp8SlXhPRFhfKX-gzGzoHsz", "hyDApeA504iNoifStd2QblbL",
        "QPHl44Dh8U7cRys06fluHErr");
    JavaRequestSignImplementation.instance().setUseMasterKey(true);
  }

  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    out.println("<html><head><title>Hello World!</title></head>");
    out.println("<body><h1>Hello World!</h1></body></html>");
  }
}
