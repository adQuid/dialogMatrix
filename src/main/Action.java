package main;

public class Action {

	ActionType type;
	Object[] params;
	
	public Action(ActionType type, Object[] params) {
		super();
		this.type = type;
		this.params = params;
	}

	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}
		
	public boolean isContradiction(Action other) {
		if(other != null && other.hasTopicAsParam() && this.hasTopicAsParam()) {
			Topic otherTopic = (Topic)(other.getParams()[0]);
			Topic thisTopic = (Topic)(this.getParams()[0]);
			return !otherTopic.getPerspective().equals(thisTopic.getPerspective());
		}else{
			return false;
		}
	}
	
	private boolean hasTopicAsParam() {
		return type == ActionType.inform ||
				type == ActionType.transition;
	}
}
