package controller;

import view.Constants;

public abstract class AbstractAuthenticatedUseCaseController extends AbstractUseCaseController {

	@Override
	public boolean preConditionVerified() {
		return mainView.isUserLoggedIn();
	}

	@Override
	public void preConditionError() {
		mainView.showError(Constants.USE_CASE_PRECONDITION_ERROR, Constants.USE_CASE_PRECONDITION_RECOVERY);		
	}
}
