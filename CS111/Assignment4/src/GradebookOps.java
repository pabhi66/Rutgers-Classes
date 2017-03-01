public class GradebookOps
{
	public static int findStudent(String studentName, String[] allStudentNames, boolean alphabetical)
	{
		for(int i=0;i<allStudentNames.length; i++){
			if (allStudentNames[i].equals(studentName))
				return i;
		}
		
		return -1;
	}

	public static double computeGrade(int studentIndex, int[][] scoreTable, int[] itemPointValues)
	{
		double grade, total = 0, max = 0;

		for(int i=0; i<scoreTable[studentIndex].length; i++){
			total = 0;
			max = 0;
			for(int j=0; j<itemPointValues.length; j++){
				if(scoreTable[studentIndex][i] == -1){
					i++;
					j++;
				}
				total += scoreTable[studentIndex][i];
				max += itemPointValues[j];
			}
		}
		grade = total/max * 100;
		return grade;
	}

	public static double[] computeAllGrades(int[][] scoreTable, int[] itemPointValues)
	{
		double[] allGrades = new double[scoreTable.length];
		double total=0, max=0;
		for(int i=0; i<scoreTable.length; i++){
			total = 0;
			max = 0;
			for(int k=0; k<scoreTable[i].length; k++) {
					if (scoreTable[i][k] == -1)
						continue;
					total += scoreTable[i][k];
					max += itemPointValues[k];
			}
			allGrades[i] = total / max * 100;
		}

		return allGrades;
	}

	public static double computeClassAverage(int item, int[][] scoreTable)
	{
		int length = scoreTable.length;
		if(item <0 || item >= scoreTable[0].length)
			return Double.NaN;
		double average = 0.0;
		for(int i=0; i < scoreTable.length; i++) {
			if(scoreTable[i][item] == -1) {
				length--;
				continue;
			}
			average += (double)scoreTable[i][item];
		}

		return average / length;
	}
}
