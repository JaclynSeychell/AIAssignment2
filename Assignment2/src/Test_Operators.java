import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_Operators {

	
	
	@Test
	public void Operators_NegationTest() {
		
		Stack<Boolean> lStack = new Stack<Boolean>(); 
		
		lStack.push(false);
		Operator lOp = new Operator(Operator.NEGATION_SYMBOL);
		boolean lResult = lOp.eval(lStack);
		
		assertEquals(true, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
	}
	
	@Test
	public void Operators_ConjunctionTest() {

		Stack<Boolean> lStack = new Stack<Boolean>();
		Operator lOp;
		boolean lResult;
		
		lStack.push(false);
		lStack.push(false);
		lOp = new Operator(Operator.CONJUNCTION_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(false, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
		lStack.push(false);
		lStack.push(true);
		lOp = new Operator(Operator.CONJUNCTION_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(false, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
		lStack.push(true);
		lStack.push(false);
		lOp = new Operator(Operator.CONJUNCTION_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(false, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
		lStack.push(true);
		lStack.push(true);
		lOp = new Operator(Operator.CONJUNCTION_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(true, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
	}
	
	@Test
	public void Operators_DisjunctionTest() {

		Stack<Boolean> lStack = new Stack<Boolean>();
		Operator lOp;
		boolean lResult;
		
		lStack.push(false);
		lStack.push(false);
		lOp = new Operator(Operator.DISJUNCTION_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(false, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
		lStack.push(false);
		lStack.push(true);
		lOp = new Operator(Operator.DISJUNCTION_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(true, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
		lStack.push(true);
		lStack.push(false);
		lOp = new Operator(Operator.DISJUNCTION_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(true, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
		lStack.push(true);
		lStack.push(true);
		lOp = new Operator(Operator.DISJUNCTION_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(true, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
	}
	
	@Test
	public void Operators_ImplicationTest() {
		
		Stack<Boolean> lStack = new Stack<Boolean>();
		Operator lOp;
		boolean lResult;
		
		lStack.push(false);
		lStack.push(false);
		lOp = new Operator(Operator.IMPLICATION_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(true, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
		lStack.push(false);
		lStack.push(true);
		lOp = new Operator(Operator.IMPLICATION_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(true, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
		lStack.push(true);
		lStack.push(false);
		lOp = new Operator(Operator.IMPLICATION_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(false, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
		lStack.push(true);
		lStack.push(true);
		lOp = new Operator(Operator.IMPLICATION_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(true, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
	}
	
	@Test
	public void Operators_EquivalenceTest() {

		Stack<Boolean> lStack = new Stack<Boolean>();
		Operator lOp;
		boolean lResult;
		
		lStack.push(false);
		lStack.push(false);
		lOp = new Operator(Operator.EQUIVALENCE_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(true, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
		lStack.push(false);
		lStack.push(true);
		lOp = new Operator(Operator.EQUIVALENCE_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(false, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
		lStack.push(true);
		lStack.push(false);
		lOp = new Operator(Operator.EQUIVALENCE_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(false, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
		lStack.push(true);
		lStack.push(true);
		lOp = new Operator(Operator.EQUIVALENCE_SYMBOL);
		lResult = lOp.eval(lStack);
		
		assertEquals(true, lResult);			// check the the returned result is correct
		assertEquals(lResult, lStack.pop());	// check the returned result is the same as that pushed to the stack
		
	}
	
	@Test
	public void Operators_StackManipulationTest() {

		Stack<Boolean> lStack = new Stack<Boolean>();
		Operator lOp;
		boolean lResult;
		
		lStack.push(true);
		lStack.push(false);
		lStack.push(true);
		// true, false, true
		
		lOp = new Operator(Operator.DISJUNCTION_SYMBOL);
		assertEquals(true, lOp.eval(lStack) );	// true, true
		assertEquals(2, lStack.size() );
		
		lOp = new Operator(Operator.NEGATION_SYMBOL);
		assertEquals(false, lOp.eval(lStack) );	// false, true
		assertEquals(2, lStack.size() );
		
		lOp = new Operator(Operator.CONJUNCTION_SYMBOL);
		assertEquals(false, lOp.eval(lStack) );	// false
		assertEquals(1, lStack.size() );
		
	}

}
