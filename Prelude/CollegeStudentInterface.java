/** An interface for a class of college students */
public interface CollegeStudentInterface 
{
    /** Sets the name and ID number of the student.
     * @param studentName   A Name object that is the desired name.
     * @param studentId     A string that is the desired student ID. */
    public void setStudent(Name studentName, String studentId,
                           int graduationYear, String degreeSought); 
    
    /** Returns a string that describes this college student object
     * @return A string containing description of this college student. */
    public String toString(); 
} // end CollegeStudentInterface
