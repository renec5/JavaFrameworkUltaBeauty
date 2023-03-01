package pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JustATest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String name = "Rene";
		String rev = new StringBuilder(name).reverse().toString();
		System.out.println("the reversed value is: " + rev);
		
		int[] nums = new int[] {1, 6, 500, 1000, 2, 3, 4, 5};
		for(int num : nums) {
			System.out.println(num);
		}
		
		int[] n = new int[3];
		ArrayList<Integer> nu = new ArrayList<>();
		nu.add(1);
		nu.add(2);
		nu.add(33);
		System.out.println(nu);
		
		System.out.println(name.substring(2));
		int mayor = nums[0];
		for (int i = 1; i< nums.length; i++) {
			if (nums[i] > mayor) {
				mayor = nums[i];
			}
		}
		System.out.println("el numero mayor es: " + mayor);
		
		int []numeros = new int [] {1, 2, 3, 4, 5};
		int mayor1= numeros[0];
		
		Map<String, Integer> namesEdad = new HashMap<String, Integer>();
		namesEdad.put("Rene", 40);
		namesEdad.put("Susana", 36);
		System.out.println(namesEdad);
		
		Date date = new Date();
		System.out.println(date);
		DateFormat fmtDate = new SimpleDateFormat("HH-mm-ss");
		System.out.println("hora " + fmtDate.format(date));
		

	}

}
