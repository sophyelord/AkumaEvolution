package test;

import java.util.Arrays;
import java.util.Random;

 class SimpleTest1 {

	public static void main(String[] args) {
	
		//Simple random search
		Random r = new Random();
		
		float[][] population = new float[100][100];
		float[] best = null;
		
		float bestScore = Float.NEGATIVE_INFINITY;
		
		while (true) {
			
			//Offspring
			for (float[] fa : population) {
				
				for (int i = 0 ; i < fa.length ; i++) {
					fa[i] = r.nextFloat()*2 - 1;
				}
				
			}
			
			//Evaluate
			for (int j = 0 ; j < population.length ; j++) {
				
				float sc = 0;
				float error = 0;
				for (int i = 0 ; i < population[j].length ; i++) {
					
					float e = (float) (Math.sin(i) - population[j][i]);
					
					if (e < 0)
						error += -e;
					else
						error += e;
					
				}
				
				sc  = -error;
				if (sc > bestScore) {
					bestScore = sc;
					best = Arrays.copyOf(population[j], population[j].length);
					System.out.println(sc);
				}
				
			}
			
			//Termination criteria
			if (bestScore > -40)
				break;
		
		}
		
		
		for (float f : best) {
			System.out.println(f);
		}
		
		
	}
}
