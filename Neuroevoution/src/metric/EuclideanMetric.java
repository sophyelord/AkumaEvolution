package metric;

import exceptions.InputSizeNotConsistentException;

public class EuclideanMetric implements MetricFunction {

	@Override
	public float distance(float[] a, float[] b) {
		
		if (a == null || b == null)
			throw new NullPointerException("Null float array reference");
		
		if (a.length != b.length)
			throw new InputSizeNotConsistentException("Both arrays must be the same dimension to compute a distance");
		
		float sum = 0;
		
		for (int i = 0; i< a.length ; i++) {
			sum+=Math.pow(a[i] - b[i], 2);
		}
		
		return (float) Math.sqrt(sum);
		
	}

	@Override
	public float norm(float[] a) {
		
		if (a == null)
			throw new NullPointerException("Null float array reference");
		
		float sum = 0;
		
		for (float f : a) {
			sum+=Math.pow(f, 2);
		}
		
		return (float) Math.sqrt(sum);
	}

}
