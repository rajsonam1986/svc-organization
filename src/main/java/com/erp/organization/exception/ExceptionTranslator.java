package com.erp.organization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator {
    @ExceptionHandler(CustomParameterizedException.class)
    public ResponseEntity<ErrorVM> processParameterizedValidationError(CustomParameterizedException ex) {
        return buildResponseEntity(ex, ex.getErrorVM().getMessage(), HttpStatus.BAD_REQUEST);
    }

    ResponseEntity<ErrorVM> buildResponseEntity(Throwable ex, String message, HttpStatus responseStatus) {
        final ResponseEntity.BodyBuilder builder = commonBodyBuilderParts(ex, message, responseStatus);

        final ErrorVM errorVM;
        if (ex instanceof CustomParameterizedException) {
            errorVM = ((CustomParameterizedException) ex).getErrorVM();
        }
        else if (ex instanceof RethrownRemoteCallException) {
            errorVM = ((RethrownRemoteCallException) ex).getErrorVM();
        }
        else {
            errorVM = new ErrorVM(message);
        }

       // enhanceErrorVM(ex, builder, errorVM);

        return builder.body(errorVM);
    }
    private ResponseEntity.BodyBuilder commonBodyBuilderParts(Throwable exception, String message, HttpStatus status) {
        final ResponseEntity.BodyBuilder builder = ResponseEntity.status(status);
        builder.header("X-docdok-message", message);
        return builder;
    }

   /* void enhanceErrorVM(Throwable ex, final ResponseEntity.BodyBuilder builder, final ErrorVM errorVM) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof DocdokRequester) {
            DocdokRequester requester = (DocdokRequester) auth.getPrincipal();
            if (requester.hasRole("kc_showDebug")) {
                log.debug("Adding exception details to response because user has role kc_showDebug");
                errorVM.addExceptionDetails(ex);
                builder.header("X-docdok-exception-time", LocalDateTime.now().toString());
                builder.header("X-docdok-exception-message", ex.getMessage().replaceAll("\\r|\\n", " "));
                builder.header("X-docdok-exception-type", ex.getClass().getName());
            }
        }
    }*/

}
