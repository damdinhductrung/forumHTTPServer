package ttl.intern.project.forumHTTPServer.validation;

import io.vertx.ext.web.api.validation.ParameterType;
import io.vertx.ext.web.api.validation.impl.HTTPRequestValidationHandlerImpl;

public class SaveArticleRequestValidationHandler extends HTTPRequestValidationHandlerImpl{
	public SaveArticleRequestValidationHandler() {
		this.addHeaderParamWithPattern("Authorization", "^Bearer [A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$", true);
		this.addFormParam("title", ParameterType.GENERIC_STRING, true);
		this.addFormParam("content", ParameterType.GENERIC_STRING, true);
	}
}
