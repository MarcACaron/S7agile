package models;


public abstract class AbstractShapeTranfo {
	public static final int FLIPH = 0;
	public static final int FLIPV = 1;

	protected int level;

	//next element in chain or responsibility
	protected AbstractShapeTranfo nextTransfo;

	public void setNextTransfo(AbstractShapeTranfo nextTransfo){
		this.nextTransfo = nextTransfo;
	}
	
	public void transfoShape(int level, CustomShape shape) {
		
		if (level == this.level) {
			this.applyTransfo(shape);
		}
		
		else if (this.nextTransfo != null) {
			this.nextTransfo.transfoShape(level, shape);
		}
		
	}

	protected abstract void applyTransfo(CustomShape shape);
}
