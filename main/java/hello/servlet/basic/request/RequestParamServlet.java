package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");

//        Enumeration<String> parameterNames = request.getParameterNames();
//        while(parameterNames.hasMoreElements()){
//            String paramName = parameterNames.nextElement();
//            System.out.println(paramName + "=" + request.getParameter(paramName));
//        }

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        System.out.println("request.getParameter(username) = " + username);

        String age = request.getParameter("age");
        System.out.println("request.getParameter(age) = " + age);
        System.out.println();

        // GET 쿼리파라미터 방식으로 요청메시지를 보낼때, 하나의 파라미터 이름에 여러 값들을 넣어서 보낼 수 있다.
        // http://localhost:8080/request-param?username=hello&age=20&username=hello2  이렇게 url을 작성해서 서버에 요청 데이터로 넘겨줄 수 있다.
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        System.out.println("request.getParameterValues(username)");
        String[] usernames = request.getParameterValues("username"); // usernames = username이라는 파라미터 이름에 해당하는 값들
        for(String name : usernames){
            System.out.println("username = " + name);
        }
        System.out.println();

        resp.getWriter().write("ok");
    }
}
