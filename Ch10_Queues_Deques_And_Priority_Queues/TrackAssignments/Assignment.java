package TrackAssignments;

import java.sql.Date;

public class Assignment implements Comparable<Assignment>
{
    private String course;  // The course code
    private String task;    // A description of the assignment
    private Date date;      // The due date
    
    public Assignment(String newCourse, String newTask, Date newDate)
    {
        course = newCourse;
        task = newTask;
        date = newDate;        
    } // end constructor    
    
    public String getCourseCode()
    {
        return course;
    } // end getcourseCode
    
    public String getTask()
    {
        return task;
    } // end getTask
    
    public Date getDueDate()
    {
        return date;
    } // end getDueDate
    
    public int compareTo(Assignment other)
    {
        return date.compareTo(other.date);
    } // end compareTo    
    
    public String toString()
    {
        String result = "Course Number: " + course;
        result += "\t Due date: " + date;
        result += "\t Task: " + task;
        
        return result;
    } // end toString
} // end Assignment
