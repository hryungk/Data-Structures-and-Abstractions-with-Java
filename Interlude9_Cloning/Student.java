/** A class that represents a student. */
public class Student implements Cloneable
{
    private Name    fullName;
    private String  id;  // Identification number

    /** Constructor: Initializes the name and sets the ID. */
    public Student()
    {
        fullName = new Name();
        id = "";
    } // end default constructor
    public  Student(Name studentName, String studentId)
    {
        fullName = studentName;
        id = studentId;
    } // end constructor

    /** Sets the name and ID number of the student.
     * @param studentName   A string that is the desired name.
     * @param studentId     A string that is the desired student ID. */
    public void setStudent(Name studentName, String studentId)
    {
        setName(studentName);   // Or fullName = studentName;
        setId(studentId);       // Or id = studentId;
    } // end setStudent
    
    /** Sets the name
     * @param studentName A string that is the desired name. */
    public void setName(Name studentName)
    {
        fullName = studentName;
    } // end setName
    
    /** Gets the name
     * @return A string containing the first and last names. */
    public Name getName()
    {
        return fullName;
    } // end getName
    
    /** Sets the student ID
     * @param studentId A string that is the desired student ID. */
    public void setId(String studentId)
    {
        id = studentId;
    } // end setId
    
    /** Gets the student ID
     * @return A string containing the student ID. */
    public String getId()
    {
        return id;
    } // end getId
    
    public String toString()
    {
        return id + " " + fullName.toString();
    } // end toString
    
    public Object clone()
    {
        Student theCopy = null;
        
        try 
        {
            theCopy = (Student) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new Error(e.toString());
        }
        theCopy.fullName = (Name) fullName.clone();
        return theCopy;
    } // end clone
} // end Student
