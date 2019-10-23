package by.htp.jd2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author alexey
 * filter
 */
public class CharacterEncodingFilter implements Filter {

    private String code = "UTF-8";

    @Override
    public void destroy() {
        code = null;
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        String codeRequest = arg0.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            arg0.setCharacterEncoding(code);
            arg1.setCharacterEncoding(code);
            arg2.doFilter(arg0, arg1);
        }

    }

    @Override
    public void init(FilterConfig config) {
        code = config.getInitParameter("encoding");
    }

}
