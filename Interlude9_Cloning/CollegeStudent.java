/** A class that represents a college student. */
public class CollegeStudent extends Student implements Cloneable
{
    private int     year;   // Year of graduation 
    private String  degree; // Degree sought
    
    public CollegeStudent() 
    {
        super();    // Must be first statement in constructor 
        year = 0;
        degree = "";
    } // end default constructor
    
    public CollegeStudent(Name studentName, String studentId,
                          int graduationYear, String degreeSought)
    {
        super(studentName, studentId); // Must be first 
        year = graduationYear;
        degree = degreeSought;
    } // end constructor
    
    public void setStudent(Name studentName, String studentId,
                           int graduationYear, String degreeSought)
    {
        setName(studentName); // NOT fullName = studentName;
        setId(studentId);     // NOT id = studentId;
    // Or setStudent(studentName, studentId); (see Segment C.16)
    
        year = graduationYear;
        degree = degreeSought;
    } // end setStudent
    
    //<The methods setYear, getYear, setDegree, and getDegree go here.> ...
    
    public String toString()
    {
        return super.toString() + ", " + degree + ", " + year; 
    } // end toString
    
    public Object clone()
    {
        CollegeStudent theCopy = (CollegeStudent) super.clone();        
        return theCopy;
    } // end clone
} // end CollegeStudent