package ttl.intern.project.forumHTTPServer.validation;

import io.vertx.ext.web.api.validation.ParameterType;
import io.vertx.ext.web.api.validation.impl.HTTPRequestValidationHandlerImpl;

public class LoginRequestValidationHandler extends HTTPRequestValidationHandlerImpl{
	public LoginRequestValidationHandler() {
		this.addFormParam("username", ParameterType.GENERIC_STRING, true);
		this.addFormParam("password", ParameterType.GENERIC_STRING, true);
	}
}
