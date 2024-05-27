package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import domain.interfaces.IGoodPlaces;
import view.MainView;

public abstract class AbstractUseCaseController {

    protected MainView mainView;
	protected IGoodPlaces app;

	private int stateToChangeTo;
	private int currentState;
	private Map<Integer, List<AbstractStateTransition>> states;
	private boolean executingUseCase;
	
	public static final int ANY_STATE = 0;
	public static final int BEGIN = 1;

	public AbstractUseCaseController() {
		states = new HashMap<>();
	}
	
	public abstract static class AbstractStateTransition {
		private int fromState;
		private int toState;
		private boolean succeeded;
		
		public AbstractStateTransition(int fromState, int toState) {
			this.fromState = fromState;
			this.toState = toState;
			succeeded = true;
		}
		
		public boolean canChangeToNextState() {
			return true;
		}
		
		// @pre: canChangeToNextState();
		public abstract void changeToNextState();
		
		// @pre: changeSucceeded() && !isFinalState()
		public int getNextState() {
			return toState;
		}
		
		public int getFromState() {
			return fromState;
		}

		public boolean changeSucceeded() {
			return succeeded;
		}
		
		public void emitError() {
		}
	}
	
	public void addState(AbstractStateTransition stateTransition) {
		if (!states.containsKey(stateTransition.getFromState())) {
			states.put(stateTransition.getFromState(), new LinkedList<>());
		}
		states.get(stateTransition.getFromState()).add(stateTransition);
	}

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

	public void setApp(IGoodPlaces app) {
		this.app = app;
	}

	public void init() {
		currentState = BEGIN;
	}
	
	public abstract boolean preConditionVerified();
	public abstract void preConditionError();

	public boolean executingUseCase() {
		return executingUseCase;
	}
	
	boolean executeUseCase(int stateToChangeTo) {
		executingUseCase = true;
		this.stateToChangeTo = stateToChangeTo;
		int state = currentState;
		boolean error = false;
		boolean stop = false;
		do {
			AbstractStateTransition stateTransition = getStateTransition(state);
			if (stateTransition == null) {
				stop = true;
			} else {
				if (stateTransition.canChangeToNextState()) {
					stateTransition.changeToNextState();
					if (stateTransition.changeSucceeded()) {
						state = stateTransition.getNextState();
					} else {
						error = true;
					}
				} else {
					stateTransition.emitError();
					error = true;
				}
			}
		} while (state != stateToChangeTo && !stop && !error);

		if (!stop && !error) {
			currentState = state;
		}
		executingUseCase = false;
		return !error && !stop;
	}
	
	public int currentState() {
		return currentState;
	}

	public int stateToChangeTo() {
		return stateToChangeTo;
	}

	private AbstractStateTransition getStateTransition(int state) {
		// try wildcard transition
		List<AbstractStateTransition> transitions = states.get(ANY_STATE);
		if (transitions != null) {
			for (AbstractStateTransition transition : transitions) {
				if (transition.canChangeToNextState()) {
					return transition;	
				}
			}
		}
		// try specific transition 
		transitions = states.get(state);
		if (transitions != null) {
			for (AbstractStateTransition transition : transitions) {
				if (transition.canChangeToNextState()) {
					return transition;
				}
			}
			if (transitions.isEmpty()) {
				return transitions.get(0);
			}
		}                                    
		return null;
	}

	/**
	 * Update the controller (called after popAndUpdate)
	 */
	public void update() {
	}
}
