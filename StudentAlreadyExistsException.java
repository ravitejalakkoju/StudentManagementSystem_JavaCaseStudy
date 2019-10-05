
public class StudentAlreadyExistsException extends Exception {
	//created a string variable
	String str;
	//constructor for this class
	StudentAlreadyExistsException(){
		//initialized string 
		str = "Student Already Exists , no need to enroll again";
	}
	//overRiding toString() method to return str
	public String toString(){
		return str;
	}
}
