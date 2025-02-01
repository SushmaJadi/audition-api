package com.audition.configuration;

import io.micrometer.tracing.Tracer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;


@Component
public class ResponseHeaderInjector extends OncePerRequestFilter {

    private final Tracer tracer;

    public ResponseHeaderInjector(Tracer tracer) {
        this.tracer = tracer;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(!response.getHeaderNames().contains("trace-id")){
            Optional.ofNullable(tracer.currentTraceContext().context())
                    .ifPresent(context->{
                        response.setHeader("trace-id", context.traceId());
                        response.setHeader("span-id", context.spanId());
                        });
        }

    }

    // TODO Inject openTelemetry trace and span Ids in the response headers.



}
