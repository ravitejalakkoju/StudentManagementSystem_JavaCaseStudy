import java.util.*;
public class Tester {
Tester(){
    //creating a scanner class
	Scanner inputStream = new Scanner(System.in);
	//to add extra Departments to existing ones
	System.out.println("Enter extra departments other than CSE,ECE,Civil(y)");
		System.out.println("Enter number of Departments you want to enter");
		int n = inputStream.nextInt();//took number of departments you want to add
		if(n!=0)
		System.out.println("deptName  deptHead");
		//created to string arrays to take names of department and its Head
		String[] dNames = new String[n];
		String[] dHeads = new String[n];
		//taking input for names and heads
		for(int i=0;i<n;i++){
			dNames[i] = inputStream.next();
			dHeads[i] = inputStream.next();
		}
		//creating object for StudentManagement Class
		StudentManagement SMS  = new StudentManagement(dNames,dHeads);
		//creating boolean check value to iterate loop
		boolean check = true;
		//creating loop for choices
		while(check){
		    //options
			System.out.println("1.Enroll Student\n2.DisplayStudentsInDepartment\n3.DisplayTotalStudents\n4.Exit");
			//taking option
			int choice = inputStream.nextInt();
			//selecting option
			switch(choice){
			case 1: {
			    //for enrolling student 
				System.out.println("Enter Student Details :");
				//taking ID of student
				System.out.print("Id: ");
				long ID = inputStream.nextLong();
				System.out.print("Name :");
				//taking Name of student
				String name = inputStream.next();
				System.out.print("Credits :");
				//taking Credits of student
				int Credits = inputStream.nextInt();
				//creating student object
				StudentDetails student = new StudentDetails(ID,name,Credits);
				System.out.print("Enter Department Name :\n");
				for(int i=0;i<SMS.depts.size();i++){
					System.out.println((i+1) + SMS.depts.get(i).getDeptName());
				}
				//taking department Name
				int dept = inputStream.nextInt();
				//enrolling student
				SMS.enrollStudent(student, SMS.depts.get(dept-1).getDeptName());
				};break;
			case 2:{
			    //for getting list of student in a particular department
				System.out.println("Enter Department Name to get list of students :");
				//list of departments available 
				for(int i=0;i<SMS.depts.size();i++){
					System.out.println((i+1) + SMS.depts.get(i).getDeptName());
				}
				//taking department number from choice
				int dept = inputStream.nextInt();
				//displaying students
				SMS.displayStudents(SMS.depts.get(dept-1).getDeptName());
				};break;
			case 3:
			{
			    //for displaying students from all branches
			    //creating an ArrayList of totalList to store students
				ArrayList<StudentDetails> totalList = new ArrayList<StudentDetails>();
				for(int i=0;i<SMS.depts.size();i++){
				    //getting students from a particular department
					ArrayList<StudentDetails> deptStuList = SMS.depts.get(i).getStudents();
					//if the list returned from dept is empty it shows the following Output
					if(deptStuList.isEmpty()){
						System.out.println("----------------------------------------------------------------------");
						System.out.println("           No Students Enrolled Yet in " + SMS.depts.get(i).getDeptName() + "    ");
						System.out.println("----------------------------------------------------------------------");
					}
					//if list is not empty add the student data to totalList
					for(int j=0;j<deptStuList.size();j++){
					    //adding student to totalList
						totalList.add(deptStuList.get(j));
					}
				}
				//sorting the list based on credits
				SMS.sort(totalList);
				for(int i=(totalList.size()-1);i>=0;i--){
					String deptName = null;
                    String deptHead = null;
					for(int j=0;j<SMS.depts.size();j++){
						ArrayList<StudentDetails> temp = SMS.depts.get(j).getStudents();
						if(SMS.depts.get(j).contains(temp, totalList.get(i))){
							deptName = SMS.depts.get(j).getDeptName();
                            deptHead = SMS.depts.get(j).getDeptHead();
						}
					}
					//displaying the student data
					System.out.println("Student Name:"+totalList.get(i).getStudentName()+"\t"+"ID :"+totalList.get(i).getStudentID()+"\t  "+"Credits :"+totalList.get(i).getCreditScore()+"\tDepartment :"+deptName+" , HOD :"+deptHead);
				}
			};break;
			case 4:
			    //to exit change the check value to false form true
				check = false;
				break;
			}
		}
}
public static void main(String args[]){
    //creating tester object
	new Tester();
}
}
