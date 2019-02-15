package ttl.intern.project.forumHTTPServer.validation;

import io.vertx.ext.web.api.validation.ParameterType;
import io.vertx.ext.web.api.validation.impl.HTTPRequestValidationHandlerImpl;

public class UpdateArticleRequestValidationHandler extends HTTPRequestValidationHandlerImpl{
	public UpdateArticleRequestValidationHandler() {
		this.addHeaderParamWithPattern("Authorization", "^Bearer [A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$", true);
		this.addPathParam("articleid", ParameterType.GENERIC_STRING);
		this.addFormParam("content", ParameterType.GENERIC_STRING, true);
	}
}
