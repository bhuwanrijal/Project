//Student User Class

public class StudentUser
{
	private int StudentID;
	private double ClassTestMarks;
	private double Assignment3Marks;
	private double Assignment4Marks;
	private double FinalExamMarks;
	private double TotalMarks;
	private String StudentGrade; 
	
	public void setStudentID(int ID)
	{
		StudentID = ID;
		return; 
	}
	
	public void setClassTestMarks(double Marks)
	{
		ClassTestMarks = Marks;
		return; 
	}
	
	public void setAssignment3Marks(double Marks)
	{
		Assignment3Marks = Marks;
		return; 
	}
	
	public void setAssignment4Marks(double Marks)
	{
		Assignment4Marks = Marks;
		return; 
	}
	
	public void setFinalExamMarks(double Marks)
	{
		FinalExamMarks = Marks; 
		
		TotalMarks = ClassTestMarks + Assignment3Marks + Assignment4Marks + FinalExamMarks;
		
		if (FinalExamMarks < 25)
			StudentGrade = "F";
		else
			setStudentGrade();
		
		return; 
	}
	
	
	public int getStudentID()
	{
		return StudentID; 
	}
	
	public double getClassTestMarks()
	{
		return ClassTestMarks;
	}
	
	public double getAssignment3Marks()
	{
		return Assignment3Marks;
	}
	
	public double getAssignment4Marks()
	{
		return Assignment4Marks;
	}
	
	public double getFinalExamMarks()
	{
		return FinalExamMarks;
	}
		
	public double getTotalMarks()
	{
		return TotalMarks;
	}
	
	public String getStudentGrade()
	{
		return StudentGrade; 
	}
	
	private void setStudentGrade()
	{
		if ((TotalMarks >= 80) && (TotalMarks <= 100))
					StudentGrade = "HD";
		else if ((TotalMarks>= 70) && (TotalMarks<= 79.99))			 
					StudentGrade = "D";
		else if ((TotalMarks>= 60) && (TotalMarks<= 69.99))	
					StudentGrade = "C";
		else if ((TotalMarks>= 50) && (TotalMarks<= 59.99))					
					StudentGrade = "P";
		else if ((TotalMarks>= 0) && (TotalMarks<= 49.99)) 
					StudentGrade = "F";
					
	}
}
