package main;

public class ActionBuilder {

	public static Action buildInformAction(Topic topic) {
		Object[] params = new Object[1];
		params[0] = topic;
		return new Action(ActionType.inform,params);
	}
	
	public static Action buildInquireAction(Subject subject) {
		Object[] params = new Object[1];
		params[0] = subject;
		return new Action(ActionType.inquire,params);
	}
	
	public static Action buildTransitionAction(Topic topic) {
		Object[] params = new Object[1];
		params[0] = topic;
		return new Action(ActionType.transition,params);
	}
	
	public static Action buildSilenceAction() {
		return new Action(ActionType.silence,new Object[0]);
	}
	
}
