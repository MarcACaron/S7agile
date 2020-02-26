package models;


public class FlipHTransfo extends AbstractShapeTranfo {

	public FlipHTransfo() {
		this.level = FLIPH;
	}
	
	@Override
	protected void applyTransfo(CustomShape shape) {
		
		shape.flipShape(this.level, false);
		
	}

}
