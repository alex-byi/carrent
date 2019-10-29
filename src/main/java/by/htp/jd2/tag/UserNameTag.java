package by.htp.jd2.tag;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * custom tag for print information about user role
 *
 * @author alexey
 */
public class UserNameTag extends TagSupport implements Serializable {

    private static final long serialVersionUID = 2165644125276866180L;
    private String username;
    private String type;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().write(type + "\n");
        } catch (IOException ex) {
            throw new JspTagException(ex.getMessage());
        }
        return EVAL_PAGE;
    }

    @Override
    public int doStartTag() throws JspException {
        try {

            JspWriter out = pageContext.getOut();
            out.write(username);
        } catch (IOException ex) {
            throw new JspTagException(ex.getMessage());
        }
        return EVAL_BODY_INCLUDE;

    }


}