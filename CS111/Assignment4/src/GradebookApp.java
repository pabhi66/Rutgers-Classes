import java.io.*;

public class GradebookApp
{
	public static void main(String[] args) throws IOException
	{
		String gradesFilename;
		int[] itemPointValues;
		String[] allStudentNames;
		int[][] scoreTable;
		double[] grades;
		boolean alphabetized;
		
		// read grades file
		gradesFilename = (args.length > 0) ? args[0] : "grades.txt";
		int[] arraySizes = computeArraySizes(gradesFilename);
		itemPointValues = new int[arraySizes[0]];
		allStudentNames = new String[arraySizes[1]];
		scoreTable = new int[arraySizes[1]][arraySizes[0]];
		readGradesFile(gradesFilename, itemPointValues, allStudentNames, scoreTable);
		alphabetized = false;
		
		// compute overall grades
		grades = GradebookOps.computeAllGrades(scoreTable, itemPointValues);
		
		while (true)
		{
			int choice = getMenuChoice();
			
			if (choice == 1) // Display all students' scores and grades
			{
				printGradebook(itemPointValues, allStudentNames, scoreTable, grades);
			}
			else if (choice == 2) // Display one student's scores and grade
			{
				System.out.println("What is the student's name?");
				String studentName = IO.readString();
				int studentIndex = GradebookOps.findStudent(studentName, allStudentNames, alphabetized);
				if (studentIndex == -1)
				{
					System.out.println("Student not found");
					continue;
				}
				printStudent(studentIndex, allStudentNames, scoreTable, grades, allStudentNames[studentIndex].length() + 1);
			}
			else if (choice == 3) // Assign score
			{
				System.out.println("What is the student's name?");
				String studentName = IO.readString();
				int studentIndex = GradebookOps.findStudent(studentName, allStudentNames, alphabetized);
				if (studentIndex == -1)
				{
					System.out.println("Student not found");
					continue;
				}
				System.out.println("Which graded item?");
				int item = IO.readInt();
				System.out.println("Enter score:");
				int score = IO.readInt();

				scoreTable[studentIndex][item] = score;
				grades[studentIndex] = GradebookOps.computeGrade(studentIndex, scoreTable, itemPointValues);
			}
			else if (choice == 4) // Get class average on a particular graded item
			{
				System.out.println("Which graded item?");
				int item = IO.readInt();
				
				double average = GradebookOps.computeClassAverage(item, scoreTable);
				System.out.println("Average: " + average);
			}else if (choice == 7) // Quit
			{
				return;
			}
		}
	}

	private static String readNextLine(BufferedReader br) throws IOException
	{
		String line;
		
		while ((line = br.readLine()) != null)
		{
			line = line.trim();
			if ( ! line.equals("") )
			{
				return line;
			}
		}

		return null;
	}
	
	private static int[] computeArraySizes(String gradesFilename) throws IOException
	{
		BufferedReader br;
		String line;
		int[] results;
		
		br = new BufferedReader(new FileReader(gradesFilename));
		line = readNextLine(br);
		if (line == null)
		{
			throw new IOException("Empty grades file");
		}
		
		results = new int[2];
		results[0] = line.split("\\s+").length - 1;
		results[1] = 0;
		
		while ((line = readNextLine(br)) != null)
		{
			results[1]++;
		}
		
		br.close();		
		return results;
	}
	
	public static void readGradesFile(
		String gradesFilename,
		int[] itemPointValues, String[] allStudentNames, int[][] scoreTable)
		throws IOException
	{
		BufferedReader br;
		String line;
		
		br = new BufferedReader(new FileReader(gradesFilename));
		
		line = readNextLine(br);
		parseGradesFileLine(line, itemPointValues);
		
		for (int studentIndex = 0 ; studentIndex < allStudentNames.length ; studentIndex++)
		{
			line = readNextLine(br);
			allStudentNames[studentIndex] = parseGradesFileLine(line, scoreTable[studentIndex]);
		}
		
		br.close();
	}
	
	private static String parseGradesFileLine(String line, int[] numericResults)
	{
		String[] fields;
		
		fields = line.split("\\s+");
		for (int fieldIndex = 1 ; fieldIndex < fields.length ; fieldIndex++)
		{
			if (fields[fieldIndex].equalsIgnoreCase("exc"))
			{
				numericResults[fieldIndex - 1] = -1;
			}
			else
			{
				numericResults[fieldIndex - 1] = Integer.parseInt(fields[fieldIndex]);
			}
		}
		return fields[0];
	}
	
	public static int getMenuChoice() 
	{
		System.out.println();
		System.out.println("Menu:");
		System.out.println("1. Display all students' scores and grades");
		System.out.println("2. Display one student's scores and grade");
		System.out.println("3. Assign score");
		System.out.println("4. Get class average on a particular graded item");
		System.out.println("7. Quit");
		System.out.println();

		System.out.print("Choice (1-7)? ");
		int choice = IO.readInt();
		while (choice < 1 || choice > 7) 
		{
			System.out.print("That is not a valid menu option. Pick 1-7. ");
			choice = IO.readInt();
		}
		return choice;
	}
	
	private static void printGradebook(
		int[] itemPointValues, String[] allStudentNames, int[][] scoreTable, double[] grades)
	{
		int maxlength = allStudentNames[0].length();
		for (int i = 1 ; i < allStudentNames.length ; i++)
		{
			maxlength = Math.max(maxlength, allStudentNames[i].length());
		}
		
		printAndTab("", maxlength+1);
		for (int item = 0 ; item < itemPointValues.length ; item++)
		{
			printAndTab(item);
		}
		System.out.println("overall grade");
		
		for (int studentIndex = 0 ; studentIndex < allStudentNames.length ; studentIndex++)
		{
			printStudent(studentIndex, allStudentNames, scoreTable, grades, maxlength+1);
		}
	}
	
	private static void printStudent(
		int studentIndex, String[] allStudentNames, int[][] scoreTable, double[] grades,
		int tabwidth)
	{
		printAndTab(allStudentNames[studentIndex], tabwidth);
		for (int item = 0 ; item < scoreTable[0].length ; item++)
		{
			printAndTab(scoreTable[studentIndex][item]);
		}
		System.out.println(grades[studentIndex]);
	}
	
	private static void printAndTab(String s, int tabwidth)
	{
		System.out.print(s);
		for (int i = 0 ; i < tabwidth - s.length() ; i++)
		{
			System.out.print(' ');
		}
	}
	
	private static void printAndTab(int i)
	{
		if (i < 0)
		{
			System.out.print("exc");
		}
		else if (i < 10)
		{
			System.out.print("  " + i);
		}
		else if (i < 100)
		{
			System.out.print(" " + i);
		}
		else
		{
			System.out.print(i);
		}
		
		System.out.print(" ");
	}
}
