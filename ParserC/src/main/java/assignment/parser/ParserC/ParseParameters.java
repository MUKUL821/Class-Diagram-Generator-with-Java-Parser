package assignment.parser.ParserC;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import com.github.javaparser.ast.body.Parameter;
public class ParseParameters  extends ParseFile {
	 public static void main(String[] args) throws Exception {
		 ParseFile obj =new ParseFile();
		 //returning the cu file from each .jva file
	      CompilationUnit cu= obj.getFileCompilationUnit();
	        // visit and print the methods names
	        System.out.println("yipiee1");
	        new ParameterVisitor().visit(cu, null);
	      
	    }

	    /**
	     * Simple visitor implementation for visiting MethodDeclaration nodes. 
	     */
	    private static class ParameterVisitor extends VoidVisitorAdapter {

	        @Override
	        public void visit(Parameter n, Object arg) {
	        	
	            // here you can access the attributes of the method.
	            // this method will be called for all methods in this 
	            // CompilationUnit, including inner class methods
	            System.out.println(n.isVarArgs()+" isVarArgs "+n.getType()+"  type");
	            super.visit(n, arg);
	        }
	    }
}
