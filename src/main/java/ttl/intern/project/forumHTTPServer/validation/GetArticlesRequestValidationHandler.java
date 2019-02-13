package ttl.intern.project.forumHTTPServer.validation;

import io.vertx.ext.web.api.validation.impl.HTTPRequestValidationHandlerImpl;

public class GetArticlesRequestValidationHandler extends HTTPRequestValidationHandlerImpl{
	public GetArticlesRequestValidationHandler() {
		this.addHeaderParamWithPattern("Authorization", "^Bearer [A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$", true);
	}
}
