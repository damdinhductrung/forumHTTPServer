package ttl.intern.project.forumHTTPServer.validation;

import io.vertx.ext.web.api.validation.ParameterType;
import io.vertx.ext.web.api.validation.impl.HTTPRequestValidationHandlerImpl;

public class GetArticleRequestValidationHandler extends HTTPRequestValidationHandlerImpl{
	public GetArticleRequestValidationHandler() {
		this.addHeaderParamWithPattern("Authorization", "^Bearer [A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$", true);
		this.addPathParam("articleid", ParameterType.GENERIC_STRING);
	}
}
