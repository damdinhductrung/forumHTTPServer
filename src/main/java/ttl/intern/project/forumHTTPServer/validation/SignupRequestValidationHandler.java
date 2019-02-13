package ttl.intern.project.forumHTTPServer.validation;

import io.vertx.ext.web.api.validation.ParameterType;
import io.vertx.ext.web.api.validation.impl.HTTPRequestValidationHandlerImpl;

public class SignupRequestValidationHandler extends HTTPRequestValidationHandlerImpl{
	public SignupRequestValidationHandler() {
		this.addFormParam("username", ParameterType.GENERIC_STRING, true);
		this.addFormParam("password", ParameterType.GENERIC_STRING, true);
	}
}
