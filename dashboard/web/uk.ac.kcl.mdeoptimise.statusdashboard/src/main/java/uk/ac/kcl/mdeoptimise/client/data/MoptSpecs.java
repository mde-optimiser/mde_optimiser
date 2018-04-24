package uk.ac.kcl.mdeoptimise.client.data;

import java.io.Serializable;

/**
 * Encloses MOPT Specs data, model and metamodel names.
 * @author Tamara
 *
 */
public class MoptSpecs implements Serializable {

	private static final long serialVersionUID = 1L;
	private String moptId;
	private String model;
	private String metamodel;
	
	public MoptSpecs() {}

	public MoptSpecs(String moptId, String model, String metamodel) {
		super();
		setMoptId(moptId);
		setModel(model);
		setMetamodel(metamodel);
	}
	
	public String getMoptId() {
		return moptId;
	}

	public void setMoptId(String moptId) {
		this.moptId = moptId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMetamodel() {
		return metamodel;
	}

	public void setMetamodel(String metamodel) {
		this.metamodel = metamodel;
	}
}
