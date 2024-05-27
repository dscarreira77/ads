package controller;


public abstract class AbstractNotAuthenticatedUseCaseController extends AbstractUseCaseController {

	@Override
	public boolean preConditionVerified() {
		return true;
	}

	@Override
	public void preConditionError() {
		// nothing to show, since the use case can always be executed.
	}
}
