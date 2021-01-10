package ua.payments.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class DateTimeTag extends BodyTagSupport {
    private static final Logger logger = LogManager.getLogger(DateTimeTag.class);

    @Override
    public int doAfterBody() throws JspException {
        BodyContent bc = getBodyContent();
        final String bcString = bc.getString().trim();
        final String[] split = bcString.split(" ");
        final String dateString = split[0];
        final String[] date = dateString.split("-");
        final String timeString = split[1].substring(0, 5);
        final JspWriter out = bc.getEnclosingWriter();
        try {
            out.print(date[2] + "." + date[1] + "." + date[0] + "<br>" + timeString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
