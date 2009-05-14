package org.oslo.ocl20.bridge4emf;

public class EvaluateResult {
	private Object value;
	private String name;
	
	
	public EvaluateResult(String name, Object result)
	{
		setValue(result);
		setName(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		if (value == null)
		{
			System.out.println("NULLLLL");
			this.value = "-";
		}
		else
			this.value = value;
	}
	
	public String toString()
	{
		return name + " - " + value;
	}
}
