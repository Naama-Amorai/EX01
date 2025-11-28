//package assignments.Ex1;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.PanelUI;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  * Introduction to Computer Science 2026, Ariel University,
 *  * Ex1: arrays, static functions and JUnit
 *
 * This JUnit class represents a JUnit (unit testing) for Ex1-
 * It contains few testing functions for the polynomial functions as define in Ex1.
 * Note: you should add additional JUnit testing functions to this class.
 *
 * @author boaz.ben-moshe
 */

class Ex1Test {
	static final double[] P1 ={2,0,3, -1,0}, P2 = {0.1,0,1, 0.1,3};
	static double[] po1 = {2,2}, po2 = {-3, 0.61, 0.2};;
	static double[] po3 = {2,1,-0.7, -0.02,0.02};
	static double[] po4 = {-3, 0.61, 0.2};
    @Test
    public void testpolyfrom2points(){
        double [] xx1 = new double[] {0, 2};
        double [] yy1 = new double[] {1, 5};
        double [] xx2 = new double[] {2, 4};
        double [] yy2 = new double[] {3.1, 5.7};
        double [] exapted1 = new double[] {1 ,2};
        double [] exapted2 = new double[] {0.5, 1.3};
        double [] actual1 = Ex1.PolynomFromPoints(xx1 ,yy1);
        double [] actual2 = Ex1.PolynomFromPoints(xx2 ,yy2);
        assertArrayEquals(exapted1, actual1, Ex1.EPS);
        assertArrayEquals(exapted2, actual2, Ex1.EPS);
    }
    @Test
    public void testpolyfrom3points(){
        double [] xx1 = new double[] {0, 1, 2};
        double [] yy1 = new double[] {1, 0, 3};
        double [] xx2 = new double[] {0, 1, -1};
        double [] yy2 = new double[] {-0.6, 1.1, -5.7};
        double [] exapted1 = new double[] {1.0, -3.0, 2.0};
        double [] exapted2 = new double[] {-0.6, 3.4, -1.7};
        double [] actual1 = Ex1.PolynomFromPoints(xx1 ,yy1 );
        double [] actual2 = Ex1.PolynomFromPoints(xx2 ,yy2 );
        assertArrayEquals(exapted1, actual1, Ex1.EPS);
        assertArrayEquals(exapted2, actual2, Ex1.EPS);
    }


    @Test
    public void testDerivative() {
        double [] p = {1,2,3};
        double [] d = Ex1.derivative(p);
        double [] a = {4};
        double [] b = Ex1.derivative(a);
        if (d[0] != 2 || d[1] != 6){
            fail("devirative error");
        }
        if (b[0] != 0){
            fail("devirative error");
        }
    }
    @Test
    public void testDerivative2() {
        double [] a = null;
        double [] b = {};
        double [] c = Ex1.derivative(b);
        double [] d = Ex1.derivative(a);
        if (d != null){
            fail("devirative error");
        }
        if ( c != null){
            fail("devirative error");
        }
    }


 	@Test
	/**
	 * Tests that f(x) == poly(x).
	 */
	void testF() {
		double fx0 = Ex1.f(po1, 0);
		double fx1 = Ex1.f(po1, 1);
		double fx2 = Ex1.f(po1, 2);
		assertEquals(fx0, 2, Ex1.EPS);
		assertEquals(fx1, 4, Ex1.EPS);
		assertEquals(fx2, 6, Ex1.EPS);
	}
	@Test
	/**
	 * Tests that p1(x) + p2(x) == (p1+p2)(x)
	 */
	void testF2() {
		double x = Math.PI;
		double[] po12 = Ex1.add(po1, po2);
		double f1x = Ex1.f(po1, x);
		double f2x = Ex1.f(po2, x);
		double f12x = Ex1.f(po12, x);
		assertEquals(f1x + f2x, f12x, Ex1.EPS);
	}
	@Test
	/**
	 * Tests that p1+p2+ (-1*p2) == p1
	 */
	void testAdd() {
		double[] p12 = Ex1.add(po1, po2);
		double[] minus1 = {-1};
		double[] pp2 = Ex1.mul(po2, minus1);
		double[] p1 = Ex1.add(p12, pp2);
		assertTrue(Ex1.equals(p1, po1));
	}
	@Test
	/**
	 * Tests that p1+p2 == p2+p1
	 */
	void testAdd2() {
		double[] p12 = Ex1.add(po1, po2);
		double[] p21 = Ex1.add(po2, po1);
		assertTrue(Ex1.equals(p12, p21));
	}
	@Test
	/**
	 * Tests that p1+0 == p1
	 */
	void testAdd3() {
		double[] p1 = Ex1.add(po1, Ex1.ZERO);
		assertTrue(Ex1.equals(p1, po1));
	}
    @Test
    public void testAdd4(){
        double [] p1 = {1,2,3,4,5};
        double [] p2 = {1,2};
        double [] p12 = {2,4,3,4,5};
        assertArrayEquals(p12,Ex1.add(p1,p2));
    }
    @Test
    public void testAddCompact(){
        double [] p3 = {1,2,3,4,5};
        double [] p4 = {1,-2,3,-4,-5};
        double [] p34 = {2,0,6};
        assertArrayEquals(p34,Ex1.add(p3,p4));
    }
    @Test
    public void testAddCompactNull(){
        double [] p3 = {1,2,3,4,5};
        double [] p4 = {-1,-2,-3,-4,-5};
        double [] pzero = {0};
        assertArrayEquals(pzero,Ex1.add(p3,p4));
    }


	@Test
	/**
	 * Tests that p1*0 == 0
	 */
	void testMul1() {
		double[] p1 = Ex1.mul(po1, Ex1.ZERO);
		assertTrue(Ex1.equals(p1, Ex1.ZERO));
	}
	@Test
	/**
	 * Tests that p1*p2 == p2*p1
	 */
	void testMul2() {
		double[] p12 = Ex1.mul(po1, po2);
		double[] p21 = Ex1.mul(po2, po1);
		assertTrue(Ex1.equals(p12, p21));
	}
    @Test
    public void testmul3 (){
        double [] p1 = new double[] {1,2,3};
        double [] p2 = new double[] {0,0,0,1.5};
        double [] exapted = new double[] {0,0,0,1.5,3,4.5};
        double [] actual= Ex1.mul(p1, p2);
        assertArrayEquals(exapted, actual);

    }
	@Test
	/**
	 * Tests that p1(x) * p2(x) = (p1*p2)(x),
	 */
	void testMulDoubleArrayDoubleArray() {
		double[] xx = {0,1,2,3,4.1,-15.2222};
		double[] p12 = Ex1.mul(po1, po2);
		for(int i = 0;i<xx.length;i=i+1) {
			double x = xx[i];
			double f1x = Ex1.f(po1, x);
			double f2x = Ex1.f(po2, x);
			double f12x = Ex1.f(p12, x);
			assertEquals(f12x, f1x*f2x, Ex1.EPS);
		}
	}
	@Test
	/**
	 * Tests a simple derivative examples - till ZERO.
	 */
	void testDerivativeArrayDoubleArray() {
		double[] p = {1,2,3}; // 3X^2+2x+1
		double[] pt = {2,6}; // 6x+2
		double[] dp1 = Ex1.derivative(p); // 2x + 6
		double[] dp2 = Ex1.derivative(dp1); // 2
		double[] dp3 = Ex1.derivative(dp2); // 0
		double[] dp4 = Ex1.derivative(dp3); // 0
		assertTrue(Ex1.equals(dp1, pt));
		assertTrue(Ex1.equals(Ex1.ZERO, dp3));
		assertTrue(Ex1.equals(dp4, dp3));
	}
    @Test
    public void testgetPolynomFromString(){
        String p = "5.2x^6-4X ^2-3X+2";
        double [] p_exepted = new double [] {2,-3,-4,0,0,0,5.2};
        String m = "7.4x ^2-X";
        double [] m_exepted = new double [] {0,-1 ,7.4}; //
        String error = "5.2a^6-4X ^2-3X+2";
        assertArrayEquals(p_exepted , Ex1.getPolynomFromString(p));
        assertArrayEquals(m_exepted , Ex1.getPolynomFromString(m));
        if (Ex1.getPolynomFromString(error) != null){
            fail("error");
        }
    }
	@Test
	/** 
	 * Tests the parsing of a polynom in a String like form.
	 */
	public void testFromString() {
		double[] p = {-1.1,2.3,3.1}; // 3.1X^2+ 2.3x -1.1
		String sp2 = "3.1x^2 +2.3x- 1.1";
		String sp = Ex1.poly(p);
		double[] p1 = Ex1.getPolynomFromString(sp);
		double[] p2 = Ex1.getPolynomFromString(sp2);
		boolean isSame1 = Ex1.equals(p1, p);
		boolean isSame2 = Ex1.equals(p2, p);
		if(!isSame1) {fail();}
		if(!isSame2) {fail();}
		assertEquals(sp, Ex1.poly(p1));
	}

    @Test
    public void testpoly1 () {
        double [] r = new double[] {-8, 1, 2, 0, -5, 3};
        String r_exepted = "3.0X^5-5.0X^4+2.0X^2+1.0X-8.0";
        assertEquals(r_exepted , Ex1.poly(r));
    }
    @Test
    public void testpoly2 () {
        double [] x = new double[] {-8};
        double [] y = new double[] {3 , 0};
        String x_exepted = "-8.0";
        String y_exepted = "3.0" ;
        assertEquals(x_exepted , Ex1.poly(x));
        assertEquals(y_exepted , Ex1.poly(y));
    }
    @Test
    public void testpoly3 () {
        double [] m = new double[] {0 , -7};
        double [] z = new double[] {-4 , 3};
        String m_exepted ="-7.0X";
        String z_exepted ="3.0X-4.0";
        assertEquals(m_exepted , Ex1.poly(m));
        assertEquals(z_exepted , Ex1.poly(z));
    }
	@Test
	/**
	 * Tests the equality of pairs of arrays.
	 */
	public void testEquals() {
		double[][] d1 = {{0}, {1}, {1,2,0,0}};
		double[][] d2 = {Ex1.ZERO, {1+ Ex1.EPS/2}, {1,2}};
		double[][] xx = {{-2* Ex1.EPS}, {1+ Ex1.EPS*1.2}, {1,2, Ex1.EPS/2}};
		for(int i=0;i<d1.length;i=i+1) {
			assertTrue(Ex1.equals(d1[i], d2[i]));
		}
		for(int i=0;i<d1.length;i=i+1) {
			assertFalse(Ex1.equals(d1[i], xx[i]));
		}
	}

	@Test
	/**
	 * Tests is the sameValue function is symmetric.
	 */
	public void testSameValue2() {
		double x1=-4, x2=0;
		double rs1 = Ex1.sameValue(po1,po2, x1, x2, Ex1.EPS);
		double rs2 = Ex1.sameValue(po2,po1, x1, x2, Ex1.EPS);
		assertEquals(rs1,rs2, Ex1.EPS);
	}

    @Test
    public void testlength (){
        double x1=-8, x2=8;
        double[] p = {-4, 0 ,1} ;
        double resultexepted = 129.838;
        assertEquals(resultexepted , Ex1.length(p , x1 ,x2 , 16 ) , Ex1.EPS);
    }
    @Test
    public void testlength2 (){
        double x1 = -5, x2 = 4;
        double[] p = {-2, 0 ,-1, 2} ;
        double resultexepted = 387.829;
        assertEquals(resultexepted , Ex1.length(p , x1 ,x2 , 12 ) , Ex1.EPS);
    }
	@Test
	/**
	 * Test the area function - it should be symmetric.
	 */
	public void testArea() {
		double x1=-4, x2=0;
		double a1 = Ex1.area(po1, po2, x1, x2, 100);
		double a2 = Ex1.area(po2, po1, x1, x2, 100);
		assertEquals(a1,a2, Ex1.EPS);
}
	@Test
	/**
	 * Test the area f1(x)=0, f2(x)=x;
	 */
	public void testArea2() {
		double[] po_a = Ex1.ZERO;
		double[] po_b = {0,1};
		double x1 = -1;
		double x2 = 2;
		double a1 = Ex1.area(po_a,po_b, x1, x2, 1);
		double a2 = Ex1.area(po_a,po_b, x1, x2, 2);
		double a3 = Ex1.area(po_a,po_b, x1, x2, 3);
		double a100 = Ex1.area(po_a,po_b, x1, x2, 100);
		double area =2.5;
		assertEquals(a1,area, Ex1.EPS);
		assertEquals(a2,area, Ex1.EPS);
		assertEquals(a3,area, Ex1.EPS);
		assertEquals(a100,area, Ex1.EPS);
	}
	@Test
	/**
	 * Test the area function.
	 */
	public void testArea3() {
		double[] po_a = {2,1,-0.7, -0.02,0.02};
		double[] po_b = {6, 0.1, -0.2};
		double x1 = Ex1.sameValue(po_a,po_b, -10,-5, Ex1.EPS);
		double a1 = Ex1.area(po_a,po_b, x1, 6, 8);
		double area = 58.5658;
		assertEquals(a1,area, Ex1.EPS);
	}

@Test
    public void testcompact () {
        double [] p1 = {0};
        double [] p2 = {0,1,2,3,0,0,0,1,2,3};
        double [] p3 = {0,1,2,3,0,0,0};
        double [] p4 = {0,1,2,3};
        double [] exepted1 ={0};
        double [] exepted2 ={0,1,2,3,0,0,0,1,2,3};
        double [] exepted34 ={0,1,2,3};
        assertArrayEquals(Ex1.compact(p1),exepted1);
        assertArrayEquals(Ex1.compact(p2),exepted2);
        assertArrayEquals(Ex1.compact(p3),exepted34);
        assertArrayEquals(Ex1.compact(p4),exepted34);

}

@Test
    public void testokpoly (){
        String p1 = "2x^2-3X+4.5";
        boolean ansp1 = true;
        String p2 = "2x^2-3a+4.5*2";
        boolean ansp2 = false;
        if (!Ex1.isokpoly(p1)){
            fail("error");
        }
        if (Ex1.isokpoly(p2)){
        fail("error");
         }
}
@Test
    public void testnextsign(){
        String p1 = "52.4+2X" , p2 = "6789X" , p3 = "-1";
        int expected1 = 5 , expected2 = 0 , expected3 = 1;
        assertEquals(expected1 , Ex1.nextsign(p1));
        assertEquals(expected2 , Ex1.nextsign(p2));
        assertEquals(expected3 , Ex1.nextsign(p3));
}

@Test
    public void testvalueCoefficient(){
        String p1 = "52.4X+2X^2" , p2 = "6.7X" , p3 = "-X" , p4 = "4+X";
        double expected1 = 2 , expected2 = 6.7 , expected3 = -1 , expected4 = 1;
        assertEquals(expected1 , Ex1.valueCoefficient(p1 , 5));
        assertEquals(expected2 , Ex1.valueCoefficient(p2 , 0));
        assertEquals(expected3 , Ex1.valueCoefficient(p3 , 0));
        assertEquals(expected4 , Ex1.valueCoefficient(p4, 2));
    }

@Test
    public void testvaluepower(){
        String p1 = "52.4X^3+2X" , p2 = "6.7x^12" , p3 = "124" , p4="13x";
    int expected1 = 3 , expected2 = 12 , expected3 = 0 , expected4 = 1;
        assertEquals(expected1 , Ex1.power_value(p1 , (p1.indexOf('^')) + 1));
        assertEquals(expected2 , Ex1.power_value(p2 , (p2.indexOf('^')) + 1 ));
        assertEquals(expected3 , Ex1.power_value(p3 , (p3.indexOf('^')) + 1));
        assertEquals(expected4 , Ex1.power_value(p4 , 0));
    }
}
