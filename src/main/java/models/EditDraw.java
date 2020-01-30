package models;

import javafx.scene.shape.Shape;

public class EditDraw {
	private EditMode editMode;
	private Shape actualShape;
	
	public EditMode getEditMode() {
		return editMode;
	}

	public void setEditMode(EditMode editMode) {
		this.editMode = editMode;
	}

	public Shape getActualShape() {
		return actualShape;
	}

	public void setActualShape(Shape actualShape) {
		this.actualShape = actualShape;
	}

	public EditDraw() {
		this.actualShape = null;
		this.editMode = EditMode.SELECTION;
	}
}
