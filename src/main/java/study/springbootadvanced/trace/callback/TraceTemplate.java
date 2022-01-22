package study.springbootadvanced.trace.callback;

import study.springbootadvanced.trace.TraceStatus;
import study.springbootadvanced.trace.logtrace.LogTrace;

public class TraceTemplate {

    private final LogTrace trace;

    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(String message, TraceCallback<T> callback){
        TraceStatus status = null;
        try{
            status = trace.begin(message);

            //로직호출
            T result = callback.call();

            trace.end(status);
            return result;

        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
