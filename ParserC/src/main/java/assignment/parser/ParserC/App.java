package assignment.parser.ParserC;


import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.github.javaparser.ast.CompilationUnit;

/**
 * Hello world! //the main class to start with everything...........MAIN CLASS
 *
 */
public class App
{ 
    public static void main( String[] args ) throws Exception
    {
    	  String allclass="";
    	 
    	
       System.out.println( "Hello World!" );
   // String[] files= {"A","B","C","D"};//D:\\CMPE202\\CASES\\uml-parser-test-1\\uml-parser-test-1\\
    // String[] files={"ClassA", "ClassB"}; forcase-3
       //{"A1","A2","B1","B2","C1","C2","P"};---case 2
       //{ConcreteObserver","ConcreteSubject","Observer","Optimist","Pessimist","Subject","TheEconomy}-case 4
       //{"Component","ConcreteComponent","ConcreteDecoratorA","ConcreteDecoratorB","Decorator","Tester"} -case5
       String[] files ={"ConcreteObserver","ConcreteSubject","Observer","Optimist","Pessimist","Subject","TheEconomy"};;
		String path="D:\\CMPE202\\CASES\\uml-parser-test-4\\uml-parser-test-4\\";
		
        ParseFile obj =new ParseFile();
        ParseClass objc =new ParseClass();
    
  
   //for(String file:files)
     JSONObject eachclass = null ;
  for(int i=0;i<files.length;i++)
        {
	    CompilationUnit cu = obj.getFileCompilationUnit(files[i],path);
	  eachclass = objc.ClassDetails(cu);
	  if(i!=0)
	    allclass+= ",class-end"+eachclass;
	  else   allclass+= eachclass;

	   //for variables..................................................... 
  
	  //INCLUDE THIS IN CODEPARSER JAVAA LATTER 
	 
	 	
	   }
  
	//System.out.println("My partial List"+parsedString);
	System.out.println("My partial arraylist List"+allclass);
    new CodeParser(allclass);
   
   
   
        //COODE TO GET THE FINAL LIST 
        //new MethodVisitor().visit(cu, null);
 	    /*** 
 	      List<TypeDeclaration> types = cu.getTypes();
 	  //    System.out.println("types"+types);
 	     JSONArray classess= new JSONArray();
 	        for (TypeDeclaration type : types) 
 	        {       //CLASS---------------------------------------------type
 	        	List<BodyDeclaration> members = type.getMembers();
 	        	
 	            for (BodyDeclaration member : members) 
 	            {
 	            	//METHODS----------------------------------------------- member
 	            	 JSONObject methodEach = new JSONObject();	
 	            	 JSONArray methodparam = new JSONArray();
 	            	 JSONArray methodvar = new JSONArray();
 	                if (member instanceof ConstructorDeclaration) {
 	                	MethodDeclaration method = (MethodDeclaration) member;
 	                    //Putting the method name in one json
 	                    methodEach.put("name",method.getName());
 	                    methodEach.put("mrt", method.getType());//mrt--method return type
 	                    //for parameter we need one iteration ..
 	                    //for VARIABLES we need one iteration
 	          //    System.out.println("member of class    "+method +"class"+type.getName());
 	                }
 	            }
 	             
 	        }****/
        
    }
}
