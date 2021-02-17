package ru.stepintegrator.task.log;

import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;
import ru.stepintegrator.task.service.InternalLogService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.stepintegrator.task.controller.PersonWebController.PERSON_URL;

@Component
public class ServletFilter implements Filter {

    private final InternalLogService logService;

    public ServletFilter(InternalLogService logService) {
        this.logService = logService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (!httpServletRequest.getRequestURI().equals(PERSON_URL)) {
            chain.doFilter(request, response);
            return;
        }
        ContentCachingResponseWrapper responseCacheWrapperObject = new ContentCachingResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseCacheWrapperObject);

        final String stringResponse = new String(responseCacheWrapperObject.getContentAsByteArray(),
                responseCacheWrapperObject.getCharacterEncoding());
        final String requestUrl = httpServletRequest.getRequestURL() + "?" + httpServletRequest.getQueryString();
        logService.addLogItem(requestUrl, stringResponse);

        responseCacheWrapperObject.copyBodyToResponse();
    }

}
