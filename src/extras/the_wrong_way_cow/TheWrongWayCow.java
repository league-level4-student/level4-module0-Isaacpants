//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

import java.util.HashMap;

public class TheWrongWayCow {

	static HashMap<String, int[]> location = new HashMap<String, int[]>();
	
	static int x = 0;
	static int y = 0;

	public static int[] findWrongWayCow(final char[][] field) {
		 int counter1 = 0;
		 int counter2 = 0;
		 int counter3 = 0;
		 int counter4 = 0;
		// Fill in the code to return the x,y coordinate position of the
		// head (letter 'c') of the wrong way cow!
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] == 'o') {

					if (!(i == 0)) {
						// left to right
						if ((field[i - 1][j] == 'c' && field[i + 1][j] == 'w')) {
							location.put("east", new int[] { j,i-1});
							counter1++;

						}
					}
					if ((j != field[i].length - 1)) {
						if ((j != 0)) {
							// bottom to top
							if ((field[i][j + 1] == 'c' && field[i][j - 1] == 'w')) {
								location.put("north", new int[] { j + 1 ,i });
								counter2++;

							}
						}
					}
					if (!(j == 0)) {
						// top to bottom
						if ((field[i][j - 1] == 'c' && field[i][j + 1] == 'w')) {
							location.put("south", new int[] { j - 1 ,i  });

							counter3++;

						}
					}

					if (!(i == field.length - 1)) {
						// right to left
						if ((field[i + 1][j] == 'c' && field[i - 1][j] == 'w')) {
							location.put("west", new int[] {j, i + 1});
							counter4++;

						}
					}

				} // counterCounter();

			}

		}
		if (counter1 == 1) {
			return location.get("east");
		}
		if (counter2 == 1) {
			return location.get("north");
		}
		if (counter3 == 1) {
			return location.get("south");
		}
		if (counter4 == 1) {
			return location.get("west");
		}
		System.out.println(counter1);
		System.out.println(counter2);
		System.out.println(counter3);
		System.out.println(counter4);
//		int i = 0;
//		if(counter.get(i)==0) {
//			 i++;
//			 
//		}

		return null;

	}
//static void counterCounter() {
//		
//		for (int i = 0; i < counter.size(); i++) {
//			if(counter.get(i)>counter.get(i++)) {
//				int k = counter.get(i);
//				counter.add(i, counter.get(i+1));
//				counter.add(i+1, k);
//			}
//		}
//	}
}
