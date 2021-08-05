import java.util.ArrayList;
import java.util.Random;

public class C206_CaseStudy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<CCA> ccaList = new ArrayList<CCA>();
		CCA cca1 = new CCA(01, "Rugby", "Strong and mighty boys are welcome!", "30", "Tuesday", "6pm-9pm", "School Field", "Peeros");
		CCA cca2 = new CCA(02, "Basketball", "The fittest and greatest shall be invited!", "20", "Friday", "5pm-8pm", "Basketball Court", "Yao Min");
		ccaList.add(cca1);
		ccaList.add(cca2);
		
		ArrayList<Student> studentList = new ArrayList<Student>();
		Student student1 = new Student(01,"Ezekiel", "P6", "6D", "Mr Lim Yan", "Angus Rehu", "angus@gamil.com", "12 Adis Road, Parc Sophia", "85950014");
		Student student2 = new Student(02,"Srinivas", "P4", "4N", "Mr Desmond", "Makcik rebus", "bagus@gamil.com", "Jurong West blk 53 #03-29", "99880076");
		studentList.add(student1);
		studentList.add(student2);

		Admin admin = new Admin("SystemAdmin ", "admin", "admin", 1, "Admin");
		Coordinator coordin = new Coordinator("Coordinator", "coordin", "coordin", 1, "Coordinator");
		Student student = new Student(01,"Ezekiel", "P6", "6D", "Mr Lim Yan", "Angus Rehu", "angus@gamil.com", "12 Adis Road, Parc Sophia", "85950014");
		
		while (true) {
			C206_CaseStudy.MainMenu();
			int staffChoice = Helper.readInt("Enter choice > ");
			
			//ADMIN
			if (staffChoice == 1) {
					C206_CaseStudy.loginMenu();
					
					String uName = Helper.readString("Enter username > ");
					String uPassword = Helper.readString("Enter password > ");
					boolean isAdmin = C206_CaseStudy.doadminLogin(admin, uName, uPassword);
					if (isAdmin == false) {
						System.out.println("Either your username or password was incorrect. Please try again!");
					}				
					while (isAdmin) {
						C206_CaseStudy.adminMenu();
						int choice = Helper.readInt("Enter choice > ");
						
						//VIEW STUDENT ACCOUNTS
						if (choice == 1) {
							String allstudentList = C206_CaseStudy.studentListToString(studentList);
							System.out.println(allstudentList);
							System.out.println("Total number of students: " + C206_CaseStudy.getallstudentList(studentList));

						//EDIT STUDENT ACCOUNT
						} else if (choice == 2) {
							String allstudentList = C206_CaseStudy.studentListToString(studentList);
							System.out.println(allstudentList);
							int EditStudentID = Helper.readInt("Enter Student ID > ");
							String StudentListDetails = C206_CaseStudy.getStudentListById(studentList, EditStudentID);
							if (!StudentListDetails.isEmpty()) {
								System.out.println(StudentListDetails);
								String NameUpdate = Helper.readString("Enter Name > ");
								String GradeUpdate = Helper.readString("Enter Grade > ");
								String ClassnameUpdate = Helper.readString("Enter Classname > ");
								String ClassteacherUpdate = Helper.readString("Enter Classteacher > ");
								String parentNameUpdate = Helper.readString("Enter Parent Name > ");
								String parentEmailUpdate = Helper.readString("Enter Parent Email > ");
								String addressUpdate = Helper.readString("Enter Address > ");
								String contactNoUpdate = Helper.readString("Enter Contact Number > ");
								
								boolean isEdited = C206_CaseStudy.editStudentStatus(studentList, EditStudentID, NameUpdate, GradeUpdate, ClassnameUpdate, ClassteacherUpdate, parentNameUpdate, parentEmailUpdate, addressUpdate, contactNoUpdate);
								if (isEdited == true) {
									StudentListDetails = C206_CaseStudy.getStudentListById(studentList, EditStudentID);
									System.out.println(String.format("Student details of " + EditStudentID + " had been Updated"));
									System.out.println(StudentListDetails);
								} else {
									System.out.println("The inputs you entered is invalid.");				
								}
							}	 else {
								System.out.println("That Student ID does not exist!");
							}
							
						//DELETE STUDENT ACCOUNT
						} else if (choice == 3) {
							String allstudentList = C206_CaseStudy.studentListToString(studentList);
							System.out.println(allstudentList);
							
							int deleteStudentID = Helper.readInt("Enter Student ID > ");

							String Studentdetails = C206_CaseStudy.getStudentListById(studentList, deleteStudentID);

							if (!Studentdetails.isEmpty()) {
								System.out.println(Studentdetails);
								char toDelete = Helper.readChar("Do you wish to delete this student account?(y/n) > ");

								if (toDelete == 'y') {
									boolean deleted = C206_CaseStudy.removeStudent(studentList, deleteStudentID);

									if (deleted == true) {
										System.out.println(String.format("Student id %d account was deleted successfully.", deleteStudentID));
									} else {
										System.out.println("Something went wrong, account was not deleted.");
									}
								}
							} else {
								System.out.println("That Student ID does not exist!");
							}

						//ADD A CCA
						} else if (choice == 4) {	
							String allccaList = C206_CaseStudy.CCAListToString(ccaList);
							System.out.println(allccaList);
							
							int id = Helper.readInt("Enter ID number > ");
							String ccaName = Helper.readString("Enter CCA Name > ");
							String description = Helper.readString("Enter CCA Description > ");
							String classsize = Helper.readString("Enter Class Size > ");
							String dayofweek = Helper.readString("Enter CCA Day of Week > ");
							String time = Helper.readString("Enter CCA time > ");
							String venue = Helper.readString("Enter CCA Venue > ");
							String nameofInstructor = Helper.readString("Enter CCA Instructor Name > ");
							
							CCA newCCA = new CCA(id, ccaName, description, classsize, dayofweek, time, venue, nameofInstructor);
							boolean result = C206_CaseStudy.addCCA(ccaList, newCCA);
							if (result == true) {
								System.out.println("CCA added!");
							} else {
								System.out.println("CCA NOT added, you must include all details!");
							}

						//DELETE A CCA
						} else if (choice == 5) {
							
							String allccaList = C206_CaseStudy.CCAListToString(ccaList);
							System.out.println(allccaList);
							
							int deleteCCAID = Helper.readInt("Enter CCA ID > ");

							String CCAdetails = C206_CaseStudy.getCCAListById(ccaList, deleteCCAID);

							if (!CCAdetails.isEmpty()) {
								System.out.println(CCAdetails);
								char toDelete = Helper.readChar("Do you wish to delete this CCA?(y/n) > ");

								if (toDelete == 'y') {
									boolean deleted = C206_CaseStudy.removeCCA(ccaList, deleteCCAID);

									if (deleted == true) {
										System.out.println(String.format("CCA ID no.%s was deleted successfully.", deleteCCAID));
									} else {
										System.out.println("Something went wrong, CCA was not deleted.");
									}
								}
							} else {
								System.out.println("That CCA ID does not exist!");
							}
							
						//EDIT A CCA
						} else if (choice == 6) {
							
							String allccaList = C206_CaseStudy.CCAListToString(ccaList);
							System.out.println(allccaList);
							
							int EditCCAID = Helper.readInt("Enter CCA ID > ");
							String CCAdetails = C206_CaseStudy.getCCAListById(ccaList, EditCCAID);
							if (!CCAdetails.isEmpty()) {
								System.out.println(CCAdetails);
								String ccaNameUpdate = Helper.readString("Enter CCA Name > ");
								String descriptionUpdate = Helper.readString("Enter CCA Description > ");
								String classsizeUpdate = Helper.readString("Enter Class Size > ");
								String dayofweekUpdate = Helper.readString("Enter CCA Day of Week > ");
								String timeUpdate = Helper.readString("Enter CCA time > ");
								String venueUpdate = Helper.readString("Enter CCA Venue > ");
								String nameofInstructorUpdate = Helper.readString("Enter CCA Instructor Name > ");
						
								boolean isEdited = C206_CaseStudy.editCCAStatus(studentList, EditCCAID, ccaNameUpdate, descriptionUpdate, classsizeUpdate, dayofweekUpdate, timeUpdate, venueUpdate, nameofInstructorUpdate);
								if (isEdited) {
									CCAdetails = C206_CaseStudy.getCCAListById(ccaList, EditCCAID);
									System.out.println(String.format("CCA details of ID no.", EditCCAID," has been Updated"));
									System.out.println(CCAdetails);
								} else {
									System.out.println("The inputs you entered is invalid.");
								}
							} else {
								System.out.println("That CCA ID does not exist!");
							}

					// View all CCA
					}else if (choice == 7){
						String allccaList = C206_CaseStudy.CCAListToString(ccaList);
						System.out.println(allccaList);
					
						//QUIT	
					} else if (choice == 8) {
						isAdmin = false;
						System.out.println("Goodbye!");
					
					//ERROR
					} else {
						System.out.println("Invalid choice");
						
						}
					}
					
			//COORDINATOR		
			} else if (staffChoice == 2) {
				C206_CaseStudy.loginMenu();
				String uName = Helper.readString("Enter username > ");
				String uPassword = Helper.readString("Enter password > ");
				boolean isCoordin = C206_CaseStudy.docoordinLogin(coordin, uName, uPassword);
				if (isCoordin == false) {
					System.out.println("Either your username or password was incorrect. Please try again!");
				}				
				while (isCoordin) {
					C206_CaseStudy.coordinatorMenu();
					int choice = Helper.readInt("Enter choice > ");
					
					//ADD CCA
					if (choice == 1) {
						int id = Helper.readInt("Enter ID number > ");
						String allccaList = C206_CaseStudy.CCAListToString(ccaList);
						System.out.println(allccaList);
						String ccaName = Helper.readString("Enter CCA Name > ");
						String description = Helper.readString("Enter CCA Description > ");
						String classsize = Helper.readString("Enter Class Size > ");
						String dayofweek = Helper.readString("Enter CCA Day of Week > ");
						String time = Helper.readString("Enter CCA time > ");
						String venue = Helper.readString("Enter CCA Venue > ");
						String nameofInstructor = Helper.readString("Enter CCA Instructor Name > ");
						
						CCA newCCA = new CCA(id, ccaName, description, classsize, dayofweek, time, venue, nameofInstructor);
						
						boolean result = C206_CaseStudy.addCCA(ccaList, newCCA);
						
						if (result == true) {
							System.out.println("CCA added!");
						} else {
							System.out.println("CCA NOT added, you must include all details!");
						}
					
					//DELETE CCA
					} else if (choice == 2) {
						String allccaList = C206_CaseStudy.CCAListToString(ccaList);
						System.out.println(allccaList);
						int deleteCCAId = Helper.readInt("Enter CCA ID > ");
						
						String CCAdetails = C206_CaseStudy.getCCAListById(ccaList, deleteCCAId);
						
						if (!CCAdetails.isEmpty()) {
							System.out.println(CCAdetails);
							char toDelete = Helper.readChar("Do you wish to delete this CCA?(y/n) > ");

							if (toDelete == 'y') {
								boolean deleted = C206_CaseStudy.removeCCA(ccaList, deleteCCAId);

								if (deleted == true) {
									System.out.println(String.format("CCA ID no.%s was deleted successfully.", deleteCCAId));
								} else {
									System.out.println("Something went wrong, CCA was not deleted.");
								}
							} 
						} else {
							System.out.println("That CCA ID does not exist!");
						}
						
					//EDIT CCA
					} else if (choice == 3) {		
						String allccaList = C206_CaseStudy.CCAListToString(ccaList);
						System.out.println(allccaList);
						String CCAdetails = C206_CaseStudy.CCAListToString(ccaList);
						System.out.println(CCAdetails);
						int EditCCAId = Helper.readInt("Enter CCA ID > ");
						if (!CCAdetails.isEmpty()) {							
							String ccaNameUpdate = Helper.readString("Enter CCA Name > ");
							String descriptionUpdate = Helper.readString("Enter CCA Description > ");
							String classsizeUpdate = Helper.readString("Enter Class Size > ");
							String dayofweekUpdate = Helper.readString("Enter CCA Day of Week > ");
							String timeUpdate = Helper.readString("Enter CCA time > ");
							String venueUpdate = Helper.readString("Enter CCA Venue > ");
							String nameofInstructorUpdate = Helper.readString("Enter CCA Instructor Name > ");
					
							boolean isEdited = C206_CaseStudy.editCCAStatus(studentList, EditCCAId, ccaNameUpdate, descriptionUpdate, classsizeUpdate, dayofweekUpdate, timeUpdate, venueUpdate, nameofInstructorUpdate);
							if (isEdited) {
								CCAdetails = C206_CaseStudy.getCCAListById(ccaList, EditCCAId);
								System.out.println(String.format("CCA details of ID no."+ EditCCAId+" has been Updated"));
								System.out.println(CCAdetails);
							} else {
								System.out.println("The inputs you entered is invalid.");
							}
						} else {
							System.out.println("That CCA ID does not exist!");
						}
					
					
					}else if (choice == 4) {
					String allccaList = C206_CaseStudy.CCAListToString(ccaList);
					System.out.println(allccaList);
	
					//QUIT
					}else if (choice == 5) {
						isCoordin = false;
						System.out.println("Goodbye!");
					
					//ERROR
					} else {
						System.out.println("Invalid choice");
					}
				}
				
			//STUDENT/PARENT
			} else if (staffChoice == 3) {
				C206_CaseStudy.loginMenu();
				
				int uName = Helper.readInt("Enter student ID > ");
				String uPassword = Helper.readString("Enter CCA registration ID > ");
				boolean isStudent = C206_CaseStudy.dostudentLogin(student, uName, uPassword);
				if (isStudent == false) {
					System.out.println("Either your student ID or registration ID was incorrect. Please try again!");
				}				
				while (isStudent) {
					C206_CaseStudy.studentMenu();
					int choice = Helper.readInt("Enter choice > ");
					
					//ADD CCA
					if (choice == 1) {
						String CCAdetails = C206_CaseStudy.CCAListToString(ccaList);
						System.out.println(CCAdetails);
						String ccaChoice = Helper.readString("Enter CCA name > ");
						boolean isValid = false;
						for (int i = 0; i < ccaList.size(); i++) {
							if (ccaList.get(i).getTitle().equalsIgnoreCase(ccaChoice) && studentList.get(i).getStudentID() == uName) {
								studentList.get(i).setCca(ccaChoice.toUpperCase());
								System.out.println("Dear " + studentList.get(i).getName() + ", your CCA " + ccaList.get(i).getTitle() + "has been successfully added!");
								String studentDetails = C206_CaseStudy.getStudentListById(studentList, uName);
								System.out.println(studentDetails);
								isValid = true;
								break;

							} 
						}
						if(isValid == false) {
								System.out.println("Not added successfully!");
							}						
							 					
					//DROP CCA
					} else if (choice == 2) {
						String studentDetails = C206_CaseStudy.getStudentListById(studentList, uName);
						for (int i = 0; i < studentList.size(); i++) {
							if (!studentDetails.isEmpty()) {
								System.out.println(studentDetails);
								char toDrop = Helper.readChar("Do you wish to drop this CCA?(y/n) > ");
								
								if (toDrop == 'y') {
									studentList.get(i).setCca(null);
									System.out.println(String.format("Dear %s, your CCA %s has been dropped successfully.", studentList.get(i).getName(), ccaList.get(i).getTitle()));
									studentDetails = C206_CaseStudy.getStudentListById(studentList, uName);
									System.out.println(studentDetails);
								}
							} else {
								System.out.println("That CCA does not exist!");
							}
						}
						
						 
					//QUIT
					} else if (choice == 3) {
						isStudent = false;
						System.out.println("Goodbye!");
					//ERROR
					} else {
						
					}
				}
			
			//REGISTER
			} else if (staffChoice == 4) {
				int StudentID = Helper.readInt("Enter Student ID > ");
				String Name = Helper.readString("Enter Name > ");
				String Grade = Helper.readString("Enter Grade > ");
				String Classname = Helper.readString("Enter Classname > ");
				String Classteacher = Helper.readString("Enter Classteacher > ");
				String parentName = Helper.readString("Enter Parent Name > ");
				String parentEmail = Helper.readString("Enter Parent Email > ");
				String address = Helper.readString("Enter Address > ");
				String contactNo = Helper.readString("Enter Contact Number > ");
				Student newSchedule = new Student(StudentID, Name, Grade, Classname, Classteacher, parentName,  parentEmail,  address,  contactNo);
				boolean result = C206_CaseStudy.addStudent(studentList, newSchedule);
				if (result == true) {
					student = C206_CaseStudy.randomID(newSchedule);
					System.out.println("Registration successful!");
					System.out.println("Your CCA registration ID: " + student.getRegistrationID());
				} else {
					System.out.println("Details NOT added, you must include all details!");
				}

			} else {
				System.out.println("Invalid choice. Try Again!");
			}
		}
	}
	
//---------------------------------------------------------------------METHODS---------------------------------------------------------------------------------------------		
	public static Student randomID(Student student) {
		// Complete code here
		String theChoices = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
		String reset = "";
		Random rand = new Random();
		for (int i = 0; i < 5; i++) { // make it to 5 characters
			int outcome = rand.nextInt(62);
			char output = theChoices.charAt(outcome);
			reset += output;
		}
			reset = String.format("%s", reset);
			student.setRegistrationID(reset);

		return student;
	}	
	
	private static boolean editCCAStatus(ArrayList<Student> studentList, int editCCAID, String nameUpdate,
			String gradeUpdate, String classnameUpdate, String classteacherUpdate, String parentNameUpdate,
			String parentEmailUpdate, String addressUpdate) {
		
			for (int i = 0; i < studentList.size(); i++) {
				Student s = studentList.get(i);
			}
			return true;
		}


	private static boolean removeCCA(ArrayList<CCA> ccaList, int deleteCCAID) {
		boolean a = false;
		for (int i = 0; i < ccaList.size(); i++) {
			CCA s = ccaList.get(i);
			if (s.getID() == deleteCCAID) {
				ccaList.remove(i);
				a = true;
				break;
			}
		}
		return a;
	}


	private static String getCCAListById(ArrayList<CCA> ccaList, int deleteCCAID) {
		String output = "";

		for (int i = 0; i < ccaList.size(); i++) {
			CCA cca = ccaList.get(i);

			if (cca.getID() == deleteCCAID) {
				output += String.format("%-3s %-15s %-50s %-14s %-15s %-10s %-20s %-10s\n", "ID", "NAME", "DESCRIPTION", "CLASS SIZE", "DAY OF WEEK ", "TIME", "VENEUE", "INSTRUCTOR");
				output += String.format("%-3s %-15s %-50s %-14s %-15s %-10s %-20s %-10s\n", cca.getID(), cca.getTitle(), cca.getDescription(), cca.getSize(), cca.getDay(),cca.getTime(), cca.getVenue(), cca.getInstructorName());
				break;
			}
		}

		return output;
	}
	
	private static String CCAListToString(ArrayList<CCA> ccaList) {
		String output = "";
		output += String.format("%-3s %-15s %-50s %-14s %-15s %-10s %-20s %-10s\n", "ID", "NAME", "DESCRIPTION", "CLASS SIZE", "DAY OF WEEK ", "TIME", "VENEUE", "INSTRUCTOR");
		for (int i = 0; i < ccaList.size(); i++) {
			CCA cca = ccaList.get(i);				
			output += String.format("%-3s %-15s %-50s %-14s %-15s %-10s %-20s %-10s\n", cca.getID(), cca.getTitle(), cca.getDescription(), cca.getSize(), cca.getDay(),cca.getTime(), cca.getVenue(), cca.getInstructorName());
			break;
		}
		return output;
	}


	private static boolean addCCA(ArrayList<CCA> ccaList, CCA newCCA) {
		boolean r = false;
		if (newCCA.getTitle().isEmpty() == true || newCCA.getDescription().isEmpty() == true || newCCA.getSize().isEmpty() == true || newCCA.getDay().isEmpty() == true || newCCA.getTime().isEmpty() == true || newCCA.getVenue().isEmpty() == true || newCCA.getInstructorName().isEmpty() == true) {
			r = false;

		} else {
			r = true;
			ccaList.add(newCCA);
		}
		return r;
	}


	private static boolean removeStudent(ArrayList<Student> studentList, int deleteStudentID) {
		boolean a = false;
		for (int i = 0; i < studentList.size(); i++) {
			Student s = studentList.get(i);
			if (s.getStudentID() == deleteStudentID) {
				studentList.remove(i);
				a = true;
				break;
			}
		}
		return a;
	}


	private static boolean editStudentStatus(ArrayList<Student> studentList, int studentId, String nameUpdate,
			String gradeUpdate, String classnameUpdate, String classteacherUpdate, String parentNameUpdate,
			String parentEmailUpdate, String addressUpdate, String contactNoUpdate) {
		// TODO Auto-generated method stub
		boolean rightStatus = false;
		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).getStudentID() == studentId) {
				studentList.get(i).setName(nameUpdate);
				studentList.get(i).setGrade(gradeUpdate);
				studentList.get(i).setClassname(classnameUpdate);
				studentList.get(i).setClassteacher(classteacherUpdate);
				studentList.get(i).setParentName(parentNameUpdate);
				studentList.get(i).setParentEmail(parentEmailUpdate);
				studentList.get(i).setAddress(addressUpdate);
				studentList.get(i).setParentNumber(contactNoUpdate);
				rightStatus = true;
			}			
		}
		return rightStatus;
	}


	private static String getStudentListById(ArrayList<Student> studentList, int studentID) {
		String output = "";

		for (int i = 0; i < studentList.size(); i++) {
			Student s = studentList.get(i);

			if (s.getStudentID() == studentID) {
				output += String.format("%-3s %-12s %-8s %-12s %-15s %-12s %-17s %-30s %-15s\n", "ID", "NAME", "GRADE", "CLASS NAME", "CLASS TEACHER ", "PARENT NAME", "PARENT EMAIL", "ADDRESS", "CONTACT NO");
				output += String.format("%-3s %-12s %-8s %-12s %-15s %-12s %-17s %-30s %-15s\n", s.getStudentID(), s.getName(), s.getGrade(), s.getClassname(), s.getClassteacher(),s.getParentName(), s.getParentEmail(), s.getAddress(), s.getParentNumber());
				break;
			}
		}

		return output;
	}


	private static boolean addStudent(ArrayList<Student> studentList, Student newSchedule) {
		boolean r = false;
		if (newSchedule.getName().isEmpty() == true || newSchedule.getGrade().isEmpty() == true || newSchedule.getClassname().isEmpty() == true || newSchedule.getClassteacher().isEmpty() == true || newSchedule.getParentName().isEmpty() == true || newSchedule.getParentEmail().isEmpty() == true || newSchedule.getAddress().isEmpty() == true || newSchedule.getParentNumber().isEmpty() == true) {
			r = false;

		} else {
			r = true;
			studentList.add(newSchedule);
		}
		return r;
	}


	private static int getallstudentList(ArrayList<Student> studentList) {
		
		return studentList.size();
	}



	private static String studentListToString(ArrayList<Student> studentList) {
		String output = "";
		output += String.format("%-3s %-12s %-8s %-12s %-15s %-15s %-20s %-15s %-30s %-10s\n", "ID", "NAME", "GRADE", "CLASS NAME", "CLASS TEACHER ", "PARENT NAME", "PARENT EMAIL", "PARENT CONTACT", "ADDRESS", "CCA");
		for (int i = 0; i < studentList.size(); i++) {
			Student s = studentList.get(i);
			output += String.format("%-3s %-12s %-8s %-12s %-15s %-15s %-20s %-15s %-30s %-10s\n", s.getStudentID(), s.getName(), s.getGrade(), s.getClassname(), s.getClassteacher(),s.getParentName(), s.getParentEmail(), s.getParentNumber(), s.getAddress(), s.getCca());
		}
		return output;
	}

	private static boolean doadminLogin(Admin admin, String uName, String uPassword) {
		if (uName.equals(admin.getUsername()) && (uPassword.equals(admin.getPassword()))) {
			return true;
		} else {
			return false;
		} 
	}
	
	private static boolean docoordinLogin(Coordinator coordin, String uName, String uPassword) {
		if (uName.equals(coordin.getUsername()) && (uPassword.equals(coordin.getPassword()))) {
			return true;
		} else {
			return false;
		} 
	}

	private static boolean dostudentLogin(Student student, int uStudentID, String uRegID) {
		if (uStudentID == student.getStudentID() && (uRegID.equals(student.getRegistrationID()))) {
			return true;
		} else {
			return false;
		} 
	}
	
	public static void loginMenu() {
		Helper.line(30, "-");
		System.out.println("LOGIN");
		Helper.line(30, "-");
	}
	
	public static void MainMenu() {
		Helper.line(42, "-");
		System.out.println("WELCOME TO SCHOOL CCA REGISTRATION SYSTEM");
		Helper.line(42, "-");
		
		System.out.println("1. Admin login");
		System.out.println("2. CCA coordinator login");
		System.out.println("3. Student/Parent login");
		System.out.println("4. Register (for student and parent)");
	}

	public static void adminMenu() {

		Helper.line(30, "-");
		System.out.println("STUDENT/PARENT DETAILS - ADMIN");
		Helper.line(30, "-");

		System.out.println("1. View all student and parent");
		System.out.println("2. Update student details");
		System.out.println("3. Remove student account");
		System.out.println("4. Add CCA");
		System.out.println("5. Delete CCA");
		System.out.println("6. Update CCA");
		System.out.println("7. View All CCA");
		System.out.println("8. Quit");

	}
	
	public static void coordinatorMenu() {

		Helper.line(30, "-");
		System.out.println("CCA DETAILS - COORDINATOR");
		Helper.line(30, "-");

		System.out.println("1. Add CCA");
		System.out.println("2. Delete CCA");
		System.out.println("3. Update CCA");
		System.out.println("4. View All CCA");
		System.out.println("5. Quit");

	}
	
	public static void studentMenu() {

		Helper.line(30, "-");
		System.out.println("CCA DETAILS - Student");
		Helper.line(30, "-");
		
		System.out.println("1. Add CCA");
		System.out.println("2. Drop CCA");		
		System.out.println("3. Quit");		

	}
}