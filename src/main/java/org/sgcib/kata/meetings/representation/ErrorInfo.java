package org.sgcib.kata.meetings.representation;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ErrorInfo {

    @Setter @Getter
    private String code;
    @Setter @Getter
    private String message;
    @Setter @Getter
    private String description;

    public ErrorInfo(String code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public ErrorInfo() {
    }
}
