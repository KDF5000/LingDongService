package com.moment.Exception;

import java.util.Locale;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvokeFaultExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception arg0) {
        if(arg0 instanceof WebApplicationException){
            StackTraceElement[] trace = new StackTraceElement[1];
            trace[0] = arg0.getStackTrace()[0];
            arg0.setStackTrace(trace);
            ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            rb.type("application/json;charset=UTF-8");
            rb.entity("请求内容发生错误，请检查参数");
            rb.language(Locale.ENGLISH);
            Response r = rb.build();
            return r;
            }
        return null;
        }
    }