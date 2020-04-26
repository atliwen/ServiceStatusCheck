package com.atliwen.domain;

import java.util.regex.Pattern;

public class ResponseEntity<T> {
    static final String SUCCESSCODE = "0000";
    private final String status;
    private final String describe;
    private final T body;
    static Pattern pattern = Pattern.compile("^[0|1][0|1][0-9][0-9][0-9][0-9]");

    public ResponseEntity(T body) {
        this(body, "0000", (String)null);
    }

    public ResponseEntity(T body, String status) {
        this(body, status, (String)null);
    }

    public ResponseEntity(T body, String status, String describe) {
        this.status = this.VerificationStatus(status);
        this.describe = describe;
        this.body = body;
    }

    public String getStatus() {
        return this.status;
    }

    public String getDescribe() {
        return this.describe;
    }

    public T getBody() {
        return this.body;
    }

    protected String VerificationStatus(String status) {
        return status;
    }
}
