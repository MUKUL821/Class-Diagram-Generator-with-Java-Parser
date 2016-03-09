package assignment.parser.ParserC;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ParseMethods extends ParseFile {
	 public ParseMethods(CompilationUnit cu) {
		// TODO Auto-generated constructor stub
		    System.out.println("yipiee1");
	}


	public static void main(String[] args) throws Exception {
		 ParseFile obj = new ParseFile();
		 //returning the cu file from each .java file
	      CompilationUnit cu= obj.getFileCompilationUnit();
	        // visit and print the methods names
	    
	        //new MethodVisitor().visit(cu, null);
	      List<TypeDeclaration> types = cu.getTypes();
	      System.out.println("types"+types);
	        for (TypeDeclaration type : types) {
	            List<BodyDeclaration> members = type.getMembers();
	            for (BodyDeclaration member : members) {
	                if (member instanceof MethodDeclaration) {
	                    MethodDeclaration method = (MethodDeclaration) member;
	              System.out.println("member of class    "+method +"class"+type.getName());
	                }
	            }
	        }
	      
	    }
    

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes. 
     */
    private static class MethodVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {
        	 
            // here you can access the attributes of the method.
            // this method will be called for all methods in this 
            // CompilationUnit, including inner class methods
        	
        	// 1-modifier=public AND 2-MODIFIER =PRIVATE
            System.out.println("in visit methods"+n.getName()+n.getType()+n.getModifiers()+"n n is"+n);
            super.visit(n, arg);
        }
    }
}
