package edu.uwm.cs351;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class BST {

	private static class Node {
		String key;
		Node left, right;
		Node(String k) {
			key = k;
			left = right = null;
		}
		Node (String k, Node l, Node r) {
			key = k;
			left = l;
			right = r;
		}
	}
	private static Node n(String k) {
		return new Node(k);
	}
	private static Node n(String k, Node l, Node r) {
		return new Node(k,l,r);
	}
	private Node root;
	
	public BST() {}

	// Task 1: HEIGHT
	
	private static int doHeight(Node n) {
		return 0; // TODO: Fix this code
	}

	public static class TestDoHeight extends TestCase {
		public void testNull() {
			assertEquals(0,doHeight(null));
		}
		public void testLeaf() {
			assertEquals(1,doHeight(n("pabst")));
		}
		public void test2L() {
			assertEquals(2,doHeight(n("pabst",n("leinie"),null)));
		}
		public void test2R() {
			// illegal tree, but doHeight doesn't care
			assertEquals(2,doHeight(n("pabst",null,n("leinie"))));
		}
		public void test2LR() {
			assertEquals(2,doHeight(n("pabst",n("leinie"),n("miller"))));
		}
		public void test3L() {
			assertEquals(3,doHeight(n("pabst",n("miller",n("leinie"),null),null)));
		}
		public void test3R() {
			assertEquals(3,doHeight(n("pabst",n("miller"),n("rainier",null,n("stream")))));
		}
		public void test3LR() {
			assertEquals(3,doHeight(n("pabst",n("miller",n("leinie"),null),n("schlitz"))));
		}
		public void test3Full() {
			assertEquals(3,doHeight(n("pabst",n("miller",n("leinie"),n("blatz")),n("schlitz",n("steam"),n("rainier")))));
		}
	}
	
	
	// TASK 2: TREE RANGE
	
	private Set<String> range() {
		Set<String> result = new HashSet<String>();
		//TODO
		return result;
	}
	
	private static void doTreeRange(Node n, String lo, String hi, Set<String> results) {
		// TODO
	}

	private static Set<String> checkRange(Node n, String lo, String hi) {
		Set<String> result = new HashSet<String>();
		doTreeRange(n,lo,hi,result);
		return result;
	}

	private static Set<String> s(String... elems) {
		Set<String> result = new HashSet<String>();
		for (String e : elems) {
			result.add(e);
		}
		return result;
	}
	
	public static class TestCheckTree extends TestCase {
		public BST tree;

		@Override
		protected void setUp() throws Exception {
			super.setUp();
			tree = new BST();
		}
		
		public void testNull() {
			assertEquals(s(),checkRange(null,null,"pabst"));
			assertEquals(s(),checkRange(null,"miller","pabst"));
			assertEquals(s(),checkRange(null,"miller",null));
			assertEquals(s(),checkRange(null,null,null));
		}
		
		public void testLeaf() {
			Node node = n("leinie");
			assertEquals(s("leinie<-(null,pabst)"),checkRange(node,null,"pabst"));
			assertEquals(s("leinie<-(miller,pabst)"),checkRange(node,"miller","pabst"));
			assertEquals(s("leinie<-(miller,null)"),checkRange(node,"miller",null));
			assertEquals(s("leinie<-(null,null)"),checkRange(node,null,null));
		}
		
		public void test2L() {
			Node node = n("schlitz",n("leinie"),null);
			assertEquals(s("schlitz<-(null,pabst)","leinie<-(null,schlitz)"),checkRange(node,null,"pabst"));
			assertEquals(s("schlitz<-(miller,pabst)","leinie<-(miller,schlitz)"),checkRange(node,"miller","pabst"));
			assertEquals(s("schlitz<-(miller,null)","leinie<-(miller,schlitz)"),checkRange(node,"miller",null));
			assertEquals(s("schlitz<-(null,null)","leinie<-(null,schlitz)"),checkRange(node,null,null));			
		}

		public void test2R() {
			Node node = n("leinie",null,n("schlitz"));
			assertEquals(s("schlitz<-(leinie,pabst)","leinie<-(null,pabst)"),checkRange(node,null,"pabst"));
			assertEquals(s("schlitz<-(leinie,pabst)","leinie<-(miller,pabst)"),checkRange(node,"miller","pabst"));
			assertEquals(s("schlitz<-(leinie,null)","leinie<-(miller,null)"),checkRange(node,"miller",null));
			assertEquals(s("schlitz<-(leinie,null)","leinie<-(null,null)"),checkRange(node,null,null));			
		}

		public void test2LR() {
			Node node = n("leinie",n("blatz"),n("schlitz"));
			assertEquals(s("schlitz<-(leinie,pabst)","leinie<-(null,pabst)","blatz<-(null,leinie)"),checkRange(node,null,"pabst"));
			assertEquals(s("schlitz<-(leinie,pabst)","leinie<-(miller,pabst)","blatz<-(miller,leinie)"),checkRange(node,"miller","pabst"));
			assertEquals(s("schlitz<-(leinie,null)","leinie<-(miller,null)","blatz<-(miller,leinie)"),checkRange(node,"miller",null));
			assertEquals(s("schlitz<-(leinie,null)","leinie<-(null,null)","blatz<-(null,leinie)"),checkRange(node,null,null));			
		}
		
		public void testRoot() {
			tree.root = n("leinie",n("blatz"),n("schlitz"));
			assertEquals(s("schlitz<-(leinie,null)","leinie<-(null,null)","blatz<-(null,leinie)"),tree.range());
		}
		
		public void testRoot3Full() {
			tree.root = n("pabst",n("miller",n("leinie"),n("blatz")),n("schlitz",n("steam"),n("rainier")));	
			Set<String> expected = s(
					"pabst<-(null,null)",
					"miller<-(null,pabst)",
					"leinie<-(null,miller)",
					"blatz<-(miller,pabst)",
					"schlitz<-(pabst,null)",
					"steam<-(pabst,schlitz)",
					"rainier<-(schlitz,null)");
			assertEquals(expected,tree.range());
		}
	}
	
	private static Set<String> findInRange(Node n, String lo, String hi) {
		Set<String> result = new HashSet<String>();
		doFindInRange(n,lo,hi,result);
		return result;
	}
	
	private static void doFindInRange(Node n, String lo, String hi, Set<String> results) {
		if (n != null) {
			if (lo != null && lo.compareTo(n.key) >= 0) {
				doFindInRange(n.right,lo,hi,results);
			} else if (hi != null && hi.compareTo(n.key) <= 0) {
				doFindInRange(n.left,lo,hi,results);
			} else {
				results.add(n.key);
				doFindInRange(n.right,lo,hi,results);
				doFindInRange(n.left,lo,hi,results);
			}
		}
	}
	
	public static class TestFindInRange extends TestCase {
		public void testNull() {
			assertEquals(s(),findInRange(null,null,null));
			assertEquals(s(),findInRange(null,"miller","pabst"));
		}
		
		public void testLeaf() {
			assertEquals(s(),findInRange(n("leinie"),"miller","pabst"));
			assertEquals(s(),findInRange(n("leinie"),"blatz","lakefront"));
			assertEquals(s("leinie"),findInRange(n("leinie"),"lakefront","miller"));
			assertEquals(s(),findInRange(n("leinie"),null,"coors"));
			assertEquals(s("leinie"),findInRange(n("leinie"),null,"schlitz"));
			assertEquals(s("leinie"),findInRange(n("leinie"),"best",null));
			assertEquals(s(),findInRange(n("leinie"),"redhook",null));
			assertEquals(s("leinie"),findInRange(n("leinie"),null,null));
		}
		
		public void testSkip() {
			// we check that the algorithm correctly ignores subtrees that
			// couldn't have anything relevant in them assuming the tree is well 
			// formed.  We test with a badly formed tree.
			Node node = n("leinie",n("schlitz"),n("bud"));
			assertEquals(s(),findInRange(node,"miller","pabst"));
			assertEquals(s(),findInRange(node,"blatz","lakefront"));
			assertEquals(s(),findInRange(node,null,"coors"));
			assertEquals(s(),findInRange(node,"redhook",null));
		}
		
		public void test3Full() {
			Node node = n("pabst",n("leinie",n("blatz"),n("miller")),n("schlitz",n("rainier"),n("steam")));	
			assertEquals(s(),findInRange(node,"miller","pabst"));
			assertEquals(s(),findInRange(node,"blatz","lakefront"));
			assertEquals(s("leinie"),findInRange(node,"lakefront","miller"));
			assertEquals(s("blatz"),findInRange(node,null,"coors"));
			assertEquals(s("leinie","miller","blatz","pabst","rainier"),findInRange(node,null,"schlitz"));
			assertEquals(s("leinie","miller","pabst","schlitz","rainier","steam"),findInRange(node,"coors",null));
			assertEquals(s("schlitz","steam"),findInRange(node,"redhook",null));
			assertEquals(s("blatz","leinie","miller","pabst","schlitz","rainier","steam"),findInRange(node,null,null));			
		}
	}
}
