package objectInterface.samples;

import java.util.Random;

import objectInterface.ObjectToolkit;

public class FloatVectorToolkit implements ObjectToolkit<Float[]> {

	private int vectorSize;
	private Random r;
	
	private float domainBias;
	private float domainRange;
	
	public FloatVectorToolkit(int vectorSize, float domainStart, float domainEnd) {
	
		this.vectorSize = vectorSize;
		this.r = new Random();
		
		this.domainRange = domainEnd-domainStart;
		this.domainBias = domainStart;
	}
	
	@Override
	public Float[] constructor() {
		return new Float[vectorSize];
	}

	@Override
	public void randomize(Float[] object) {
		
		for (int i = 0 ; i < object.length ; i++) {
			
			object[i] = r.nextFloat()*domainRange + domainBias;
			
		}
	}


}
