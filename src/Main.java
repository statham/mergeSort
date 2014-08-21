import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

	public static void main(String[] args) throws IOException{
		if (args.length < 1){
			//no file given in command line
			System.out.println("Something went wrong");
			System.exit(1);
		}
		//get file included in command line
		File file = new File(args[0]);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);	
		String line;
		while((line = bufferedReader.readLine()) != null){
			//sort line
			String[] numbers = line.split(" ");
			List<Double> nums = new ArrayList<Double>();
			for (String number : numbers){
				nums.add(Double.parseDouble(number));
			}
			List<Double> sortedLine = mergeSort(nums);
			for(int i = 0; i < sortedLine.size(); i++){
				System.out.printf("%.3f", sortedLine.get(i));
				if (i != sortedLine.size() - 1){
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
		fileReader.close();
		System.exit(0);
	}
	
	public static List<Double> mergeSort(List<Double> numbers){
		if (numbers.size() < 2){
			//bottom out, return
			return numbers;
		}
		//split and recurse down
		int middle = numbers.size()/2;
		List<Double> left = new ArrayList<Double>(numbers.subList(0, middle));
		List<Double> right = new ArrayList<Double>(numbers.subList(middle, numbers.size()));
		List<Double> sortedLeft = mergeSort(left);
		List<Double> sortedRight = mergeSort(right);
		return merge(sortedLeft, sortedRight);
	}
	
	public static List<Double> merge(List<Double> left, List<Double> right){
		List<Double> mergedList = new ArrayList<Double>();
		while(left.size() != 0 && right.size() != 0){
			if(left.get(0) <= right.get(0)){
				mergedList.add(left.get(0));
				left.remove(0);
			}
			else{
				mergedList.add(right.get(0));
				right.remove(0);
			}
		}
		if (left.size() != 0){
			for (int i = 0; i < left.size(); i++){
				mergedList.add(left.get(i));
			}
		}
		else {
			for (int i = 0; i < right.size(); i++){
				mergedList.add(right.get(i));
			}
		}
		return mergedList;
		
	}

}
