import java.util.ArrayList;
import java.util.Scanner;
//creating StudentDetails class
class StudentDetails {
    //creating private variables student ID,Name,credits
	private long studentID;
	private String studentName;
	private int creditScore;
	StudentDetails(long studentID,String studentName,int creditScore){
        //initializing variable from sent parameters
        //this keyword used to indicate that it is of present class variable
		this.studentID = studentID;
		this.studentName = studentName;
		this.creditScore = creditScore;
	}
    //since variables are private we create public methods to get data in private variables
	public long getStudentID(){
		return this.studentID;
	}
	public String getStudentName(){
		return this.studentName;
	}
	public int getCreditScore(){
		return this.creditScore;
	}
//compareTo method compares details from presents student class and other student class's ID and returns '0' if equal and '1' if not
	public int compareTo(StudentDetails o) {
		// TODO Auto-generated method stub
		int cmp = Long.compare(this.studentID, o.getStudentID());
		if(cmp == 0){
			return 0;
		}
		else{
			return 1;
		}
	}
//compare method compares details from presents student class and other student class's credit scores and returns '0' if equal ,'-1' if present class credit value is lesser and '-1' if present class credit value is greater 
    public int compare(StudentDetails o) {
		// TODO Auto-generated method stub
		
        int cmp = Integer.compare(this.creditScore, o.getCreditScore());
		if(cmp == 0){
            int cmpr = Long.compare(this.studentID, o.getStudentID());
			if(cmpr < 0){
                return -1;
            }
            else{
                return -1;
            }
		}
		else if(cmp < 0){
			return -1;
		}
        else{
            return 1;
        }
	}
}
class Department{
    //creating private variables Department name,head,ArrayList of students in department
	private String deptName;
	private String deptHead;
    public ArrayList<StudentDetails> students;
	Department(String deptName,String deptHead){
        //initializing variable from sent parameters
		this.deptName = deptName;
		this.deptHead = deptHead;
        students =  new ArrayList<StudentDetails>();
	}
	public String getDeptName(){
		return this.deptName;
	}
	public String getDeptHead(){
		return this.deptHead;
	}
    //contains method checks if the list containts the student or not
    public boolean contains(ArrayList<StudentDetails> list,StudentDetails student){
	for(int i=0;i<list.size();i++){
		if(list.get(i).compareTo(student)==0){
			return true;
		}
	}
	return false;
    }
    public boolean addStudent(StudentDetails student){
        //todo
        //it student is alreadu present student won't be added and it returns false
		if(contains(students,student)){
			return false;
		}
        //if student is not present student will be added to students
		else{
			students.add(student);
			return true;
		}
    }
    //returns arrayList of students in this department
    public ArrayList<StudentDetails> getStudents(){
        return students;
    }
    //checks if department names of this and other department are same or not and return true or false
	public boolean equals(Department o){
		return this.deptName.equals(o.getDeptName());
	}
}
public class StudentManagement implements StudentManagementSystem {
    //creating arrayList of departments and IDs
	ArrayList<Department> depts;
	ArrayList<Long> IDs;
StudentManagement(){
    //initializing department ArrayList with some 3 default depts
	depts = new ArrayList<Department>();
	depts.add(new Department("CSE","Thammireddy"));
	depts.add(new Department("ECE","Nikhil Bharadwaj"));
	depts.add(new Department("Civil","Arvind Shetty"));
	IDs = new ArrayList<Long>();
}
    //constructor to add new departments
StudentManagement(String[] deptNames,String[] deptHeads){
	this();
	for(int i=0;i<deptNames.length;i++){
		depts.add(new Department(deptNames[i],deptHeads[i]));
	}
}
    //to print data in studentDetails type array List
public void printArrayList(ArrayList<StudentDetails> sd){
	for(int i=(sd.size()-1);i>=0;i--){
		System.out.println("Student Name:"+sd.get(i).getStudentName()+"\t"+"ID :"+sd.get(i).getStudentID()+"\t  "+"Credits :"+sd.get(i).getCreditScore());
		}
	}
    //to enroll a student 
public boolean enrollStudent(StudentDetails student,String deptName) {
    //creating a temporary department based on deptName
	Department newDept = new Department(deptName," ");
	for(int i=0;i<depts.size();i++){
        //checking if newDept deptName equals to deptName present any of Departments present in depts
		if(depts.get(i).equals(newDept)){
			newDept = depts.get(i);
		}
	}
    //if deptHead in newDept is still a space no department is found with such name and returns false
	if(newDept.getDeptHead().equals(" ")){
		System.out.println("No Department Found");
		return false;
	}
    //checks if ID is already entered previously
	if(IDs.contains(student.getStudentID())){
		try{
            //throws studentalreadyexistsexception 
            throw new StudentAlreadyExistsException();
        }catch(StudentAlreadyExistsException e){
            System.out.println(e);
            //returns false as student is already enrolled
            return false;
        }
	}
	else{
        //adds students id as he is new
		IDs.add(student.getStudentID());
	}
    //student is added in department
	newDept.addStudent(student);
    //returns true as student is enrolled
    return true;
}
public ArrayList<StudentDetails> getStudentsByDepartment(String deptName){
	Department newDept = new Department(deptName," ");
	for(int i=0;i<depts.size();i++){
        //checking between departmentNames and newDept is converted to respective department
		if(depts.get(i).equals(newDept)){
			newDept = depts.get(i);
		}
	}
    //returns students in a department
	return newDept.getStudents();
}
public void sort(ArrayList<StudentDetails> list){
    //sorting the list based on credits
        for(int i=0;i<list.size();i++){
            for(int j=i+1;j<list.size();j++){
                //comparing credits in student details
                //in ascending order (check compare function in student details)
                if(list.get(i).compare(list.get(j)) == 1){
                    StudentDetails temp  = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
public void displayStudents(String deptName){
    //to display students  in a department
	ArrayList<StudentDetails> displayList = getStudentsByDepartment(deptName);
    //if list is empty display the belwo output
	if(displayList.isEmpty()){
		System.out.println("----------------------------------------------------------------------");
		System.out.println("           No Students Enrolled Yet in " + deptName + "    ");
		System.out.println("----------------------------------------------------------------------");
	}
    //if list s=is not empty is sorts it bases on credit score and displays the output in descending order
	sort(displayList);
	printArrayList(displayList);
}
}