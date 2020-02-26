package models;


public class FlipVTransfo extends AbstractShapeTranfo {
	
	public FlipVTransfo() {
		this.level = FLIPV;
	}

	@Override
	protected void applyTransfo(CustomShape shape) {
		shape.flipShape(this.level, false);
	}

}
