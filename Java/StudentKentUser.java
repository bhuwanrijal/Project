//The program uses StudentUser Class
//This Program reads marks  from a text file and populate the array of StudentUser Object
//Take the Final Exam Marks from the user and store it in the Array of StudentUser Object
//Calculate Total Marks for each student and issue grades (student must score 25 out of 50 for the final exam, otherwise fail)
//Sort the student table using Total Marks in Descending order 
//Display on the screen and write an output file  

import java.util.Scanner;
import java.io.*; 

public class StudentKentUser 
{
	public static void main(String args[])
	{
		int Num;
		double FEMarks; 
		 		
		Scanner in = new Scanner(System.in);
		
		//Number of Element in the Array 
		System.out.print("Enter Total Number of Students in OODP Class:");
		Num = in.nextInt(); 
		
		//Declaring Array of StudnetUser Class 
		StudentUser[] OODP = new StudentUser[Num];
	
		//Reading input from File and populating the OODP Array 
		ReadFile(OODP); 
		
		//Entering Final Exam Marks in the OODP Array 
		for (int i = 0; i < OODP.length; i++)
		{
			System.out.print("\nEnter Final Exam Marks for Student ID "+(i+1)+":");
			FEMarks = in.nextDouble();

			OODP[i].setFinalExamMarks(FEMarks); 
		}
			
		
		DisplayRecord(OODP);     //Display the OODP Marks Table (unsorted) 
		
		SortRecord(OODP);
		
		DisplayRecord(OODP);    //Display the OODP Marks Table (sorted) 
		
		WriteFile(OODP);       //Writing the Sorted OODP Marks Table in the output file 
		
		System.exit(0); 
		
	}
	
	
	public static void ReadFile(StudentUser[] RS)
	{
		int Id; 
		double Task2Marks, Task3Marks, Task4Marks; 
		
		try
		{
			Scanner Fin = new Scanner(new File("studentmarks.txt"));  
		
			for (int i = 0; i < RS.length; i++)
			{
				RS[i] = new StudentUser(); 
				Id = Fin.nextInt();
				Task2Marks = Fin.nextDouble();
				Task3Marks = Fin.nextDouble();
				Task4Marks = Fin.nextDouble();
						
				RS[i].setStudentID(Id);
				RS[i].setClassTestMarks(Task2Marks);
				RS[i].setAssignment3Marks(Task3Marks);
				RS[i].setAssignment4Marks(Task4Marks);
			}
			
			System.out.println("\nStudent Records Read From File Successfully... ");
					
		}
		catch (Exception e) 
		{
			// Diagnosing exceptions: Identifies which method causes the bug
						
			System.out.println("\nFile Not Found !!!");
			System.exit(0);
		}
		
	}
	
	public static void WriteFile(StudentUser [] RS)
	{
		
		try
		{
			//open output file
				
			FileWriter Fout = new FileWriter("OODPStudentRecord.txt");
			PrintWriter Pout = new PrintWriter(Fout);
		
			Pout.println("\n\nThe Student Record \n\n");
			Pout.print("ID \tClass Test   Assignment-3   Assignment-4   Final Exam   Total Marks   Grade ");
		
			for (int j = 0; j < RS.length ; j++)
			{
				Pout.println(); 
				Pout.format("%d"  , RS[j].getStudentID()); 
				Pout.format("%15.2f" , RS[j].getClassTestMarks()); 
				Pout.format("%15.2f" , RS[j].getAssignment3Marks()); 
				Pout.format("%15.2f" , RS[j].getAssignment4Marks());
				Pout.format("%15.2f" , RS[j].getFinalExamMarks());
				Pout.format("%10.2f" , RS[j].getTotalMarks());
				Pout.format("%10s" , RS[j].getStudentGrade());
			
				Pout.flush(); 
			}
		
			System.out.println("\n\nOODP Grades are updated in the output file 'OODPStudentRecord.txt'");
		
			//Closing the output file 
			Pout.close(); 
		}
		catch (Exception e)
		{
			e.printStackTrace(); 
		}
			
	}
	
	
	public static void DisplayRecord(StudentUser [] RS)
	{
		System.out.println("\n\nThe Student Record \n\n");
		System.out.print("ID \tClass Test   Assignment-3   Assignment-4   Final Exam   Total Marks   Grade ");
		
		for (int j = 0; j < RS.length ; j++)
		{
			System.out.println(); 
			System.out.format("%d"  , RS[j].getStudentID()); 
			System.out.format("%15.2f" , RS[j].getClassTestMarks()); 
			System.out.format("%15.2f" , RS[j].getAssignment3Marks()); 
			System.out.format("%15.2f" , RS[j].getAssignment4Marks());
			System.out.format("%15.2f" , RS[j].getFinalExamMarks());
			System.out.format("%10.2f" , RS[j].getTotalMarks());
			System.out.format("%10s" , RS[j].getStudentGrade());
		}
	}
	
	
	public static void SortRecord(StudentUser [] RS)
	{
		StudentUser Swap = new StudentUser();
		
		for(int i= 0; i < RS.length; i++)
		{
			for(int j = i + 1; j < RS.length; j++)
			{
				if (RS[i].getTotalMarks() < RS[j].getTotalMarks())
				{
					Swap = RS[i]; 
					RS[i] = RS[j];
					RS[j] = Swap;
				}
			}
		}
			
		System.out.println("\n\nStudent Records Are Sorted... ");
		
	}
		
}

	