/** An interface for a class of students */
public interface StudentInterface 
{
    /** Sets the name and ID number of the student.
     * @param studentName   A Name object that is the desired name.
     * @param studentId     A string that is the desired student ID. */
    public void setStudent(NameInterface studentName, String studentId); 
    
    /** Sets the name
     * @param studentName A Name object that is the desired name. */
    public void setName(NameInterface studentName);
    
    /** Gets the name
     * @return A string containing the first and last names. */
    public NameInterface getName();
    
    /** Sets the student ID
     * @param studentId A string that is the desired student ID. */
    public void setId(String studentId);
    
    /** Gets the student ID
     * @return A string containing the student ID. */
    public String getId();
    
    public String toString(); 
} // end StudentInterface
