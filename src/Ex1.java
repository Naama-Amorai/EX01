//package src;

import java.util.Arrays;

/**
 * Introduction to Computer Science 2026, Ariel University,
 * Ex1: arrays, static functions and JUnit
 * https://docs.google.com/document/d/1GcNQht9rsVVSt153Y8pFPqXJVju56CY4/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 *
 * This class represents a set of static methods on a polynomial functions - represented as an array of doubles.
 * The array {0.1, 0, -3, 0.2} represents the following polynomial function: 0.2x^3-3x^2+0.1
 * This is the main Class you should implement (see "add your code below")
 *
 * @author boaz.benmoshe

 */
public class Ex1 {
    /**
     * Epsilon value for numerical computation, it serves as a "close enough" threshold.
     */
    public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
    /**
     * The zero polynomial function is represented as an array with a single (0) entry.
     */
    public static final double[] ZERO = {0};

    /**
     * Computes the f(x) value of the polynomial function at x.
     *
     * @param poly - polynomial function
     * @param x
     * @return f(x) - the polynomial function value at x.
     */
    public static double f(double[] poly, double x) {
        double ans = 0;
        for (int i = 0; i < poly.length; i++) {
            double c = Math.pow(x, i);
            ans += c * poly[i];
        }
        return ans;
    }

    /**
     * Given a polynomial function (p), a range [x1,x2] and an epsilon eps.
     * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps,
     * assuming p(x1)*p(x2) <= 0.
     * This function should be implemented recursively.
     *
     * @param p   - the polynomial function
     * @param x1  - minimal value of the range
     * @param x2  - maximal value of the range
     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
     * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
     */
    public static double root_rec(double[] p, double x1, double x2, double eps) {
        double f1 = f(p, x1);
        double x12 = (x1 + x2) / 2;
        double f12 = f(p, x12);
        if (Math.abs(f12) < eps) {
            return x12;
        }
        if (f12 * f1 <= 0) {
            return root_rec(p, x1, x12, eps);
        } else {
            return root_rec(p, x12, x2, eps);
        }
    }

    /**
     * This function computes a polynomial representation from a set of 2D points on the polynom.
     * The solution is based on: //	http://stackoverflow.com/questions/717762/how-to-calculate-the-vertex-of-a-parabola-given-three-points
     * Note: this function only works for a set of points containing up to 3 points, else returns null.
     *
     * @param xx
     * @param yy
     * @return an array of doubles representing the coefficients of the polynom - where each value in the array represents the coefficient of X and the index represents the power of X.
     */
    public static double[] PolynomFromPoints(double[] xx, double[] yy) {
        double[] ans = ZERO;
        int lx = xx.length;
        int ly = yy.length;
        if (xx != null && yy != null && lx == ly && lx > 1 && lx < 4) {
            double x1 = xx[0], x2 = xx[1];
            double y1 = yy[0], y2 = yy[1];
            if (lx == 2) {
                if (x2 == x1){
                    return null;
                }
                ans = new double[2];
                double m = (y2 - y1) / (x2 - x1);
                ans[1] = m;
                double n = y1 - x1 * m;
                ans[0] = n;
            } else {
                double x3 = xx[2], y3 = yy[2];
                double denom = (x1 - x2) * (x1 - x3) * (x2 - x3);
                if (denom == 0){
                    return null;
                }
                double A = (x3 * (y2 - y1) + x2 * (y1 - y3) + x1 * (y3 - y2)) / denom;
                double B = (x3 * x3 * (y1 - y2) + x2 * x2 * (y3 - y1) + x1 * x1 * (y2 - y3)) / denom;
                double C = (y1 * x2 * x3 * (x2 - x3) + y2 * x3 * x1 * (x3 - x1) + y3 * x1 * x2 * (x1 - x2)) / denom;
                ans = new double[]{C, B, A};
            }
        }
        return ans;
    }

    /**
     * Two polynomials functions are equal if and only if they have the same values f(x) for n+1 values of x,
     * where n is the max degree (over p1, p2) - up to an epsilon (aka EPS) value.
     *
     * @param p1 first polynomial function
     * @param p2 second polynomial function
     * @return true iff p1 represents the same polynomial function as p2, within a deviation of epsilon.
     */
    public static boolean equals(double[] p1, double[] p2) {
        boolean ans = true;
        if (p1.length > p2.length) {
            double[] replace = p2;
            p2 = p1;
            p1 = replace;
        }
        int l2 = p2.length;
        for (int i = 0; i <= l2; i++) {
            double x = Math.random() * 10;
            if (Math.abs(f(p1, x) - f(p2, x)) >= EPS) {
                ans = false;
                return ans;
            }
        }

        return ans;
    }

    /**
     * Computes a String representing the polynomial function.
     * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
     * @param poly the polynomial function represented as an array of doubles
     * @return ans - a String representing the polynomial function:
     */

    public static String poly(double[] poly) {
        String ans = "";
        int i = 0;
        if (poly.length == 0 || poly == null) {
            ans = "0";
        }
        else {
            for ( i = poly.length - 1; i >= 2; i--) {
                if (poly[i] != 0) {
                    ans = ans + poly[i] + "X^" + i;
                }
                if (poly[i - 1] > 0) {
                    ans = ans + "+";
                }
            }
        }
        if (poly.length >= 2) {
            if (poly[1] != 0) {
                ans = ans + poly[1] + "X";
                if (poly[0] > 0) {
                    ans = ans + "+";
                }
            }
        }
        if (poly[0] != 0) {
            ans = ans + poly[0];
        }
        return ans;
    }

    /**
     * Given two polynomial functions (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
     * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
     *
     * @param p1  - first polynomial function
     * @param p2  - second polynomial function
     * @param x1  - minimal value of the range
     * @param x2  - maximal value of the range
     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
     * @return an x value (x1<=x<=x2) for which |p1(x) - p2(x)| < eps.
     */
    public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
        if (x1 > x2) {// make sure x2 is bigger
            double replace = x2; x2 = x1; x1 = replace;
        }
        double x12 = (x1 + x2) / 2;
        double f1 = f(p1 , x1) - f(p2 , x1);
        double f12 = f(p1 , x12)- f(p2 , x12);
        if (Math.abs(f12) < eps) {
            return x12;
        }
        if (f12 * f1 <= 0) {
            return sameValue( p1,  p2, x1, x12, eps);
        }
        else {
            return sameValue( p1,  p2, x12, x2, eps);
        }
    }

    /**
     * Given a polynomial function (p), a range [x1,x2] and an integer with the number (n) of sample points.
     * This function computes an approximation of the length of the function between f(x1) and f(x2)
     * using n inner sample points and computing the segment-path between them.
     * assuming x1 < x2.
     * This function should be implemented iteratively (none recursive).
     *
     * @param p                - the polynomial function
     * @param x1               - minimal value of the range
     * @param x2               - maximal value of the range
     * @param numberOfSegments - (A positive integer value (1,2,...).
     * @return the length approximation of the function between f(x1) and f(x2).
     * X
     */
    public static double length(double[] p, double x1, double x2, int numberOfSegments) {
        double ans = 0;
//        if (x1 > x2) {// make sure x2 is bigger
//            double replace = x2; x2 = x1; x1 = replace;
//        }
        double delta = (x2 - x1) / numberOfSegments;
        for (double i = x1 ; i < x2 ; i += delta){
            double y = (f(p , i +delta)) - (f(p , i));
            double distance = Math.sqrt(Math.pow(y , 2) + Math.pow(delta ,2));
            ans += distance;
        }
        return ans;
    }

    /**
     * Given two polynomial functions (p1,p2), a range [x1,x2] and an integer representing the number of Trapezoids between the functions (number of samples in on each polynom).
     * This function computes an approximation of the area between the polynomial functions within the x-range.
     * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
     *
     * @param p1                - first polynomial function
     * @param p2                - second polynomial function
     * @param x1                - minimal value of the range
     * @param x2                - maximal value of the range
     * @param numberOfTrapezoid - a natural number representing the number of Trapezoids between x1 and x2.
     * @return the approximated area between the two polynomial functions within the [x1,x2] range.
     */
    public static double area(double[] p1, double[] p2, double x1, double x2, int numberOfTrapezoid) {
        double ans = 0;
        double delta = (x2 - x1) / numberOfTrapezoid;
        for (double i = x1; i < x2; i += delta) {
            double y1 = (f(p2, i)) - (f(p1, i));
            double y2 = (f(p2, i + delta)) - (f(p1, i + delta));
            if ( y1 > 0 && y2<0 || y2>0 && y1<0){
               double point = sameValue(p1 , p2 ,i , i + delta , EPS);
                double size1 =  Math.abs(y1) * Math.abs(point - i) / 2;
                double size2 =  Math.abs(y2) * Math.abs(i + delta - point) / 2;
                ans += size1 + size2;
            }
            else {
                double size = Math.abs(y1 + y2) / 2 * delta;
                ans += size;
            }
        }
        return ans;
    }

    /**
     * This function computes the array representation of a polynomial function from a String
     * representation. Note:given a polynomial function represented as a double array,
     * getPolynomFromString(poly(p)) should return an array equals to p.
     *
     * @param p - a String representing polynomial function.
     * @return the array that represents the polynomial given as a string, where each value in the array represents the coefficient of X and the index represents the power of X
     */
    public static double[] getPolynomFromString(String p) {
        double[] ans = ZERO;//  -1.0x^2 +3.0x +2.0
        int i = 0;
        p = p.replaceAll(" ", "");
        p = p.toUpperCase();
        if (!isokpoly(p) || (p.length() == 1 && p.charAt(0) == '0')){
            return null;
        }
        if (p.indexOf('X') != -1 && p.indexOf('^') != -1)
        {
            ans = new double[power_value(p, (p.indexOf('^')) + 1) + 1 ];
        }
        else if (p.indexOf('X') != -1){
            ans = new double[2];
        }
        else {
            ans = new double[1];
        }
        while (p.indexOf('X') != -1) {
            double value = valueCoefficient(p , 0);
            int place = power_value(p,p.indexOf('X')+ 2 );
            ans [place] = value;
            int cutString = nextsign(p.substring(1));
            if (cutString == 0){
                p = "";
            }
            else {
                p = p.substring(cutString);
            }
        }
        if (!p.equals("")) {
            ans[0] = valueCoefficient(p, 0);
        }
        return ans;
    }
	/**
	 * This function computes the polynomial function which is the sum of two polynomial functions (p1,p2)
     * p1 = {1 ,2, 3}
     * P2 = {4,7, -3, 13}
     * ans = {5 , 9 , 0 , 13}
     * if (l1>l2) {replace
        double [] l=l1
        l1=l2
        l2=l
     }
     *ans = copy(l2)
     *for (int i = 0 ; i <l1 ; i++ {
        ans [i] += p1 [i];
     }
	 * @param p1
	 * @param p2
	 * @return ans -the array that represents the sum of two polynomial functions
	 */
	public static double[] add(double[] p1, double[] p2) {
        int l1 = p1.length;
        int l2 = p2.length;
        if (l1 > l2){
            double [] replace = p2;
            p2 = p1;
            p1 = replace;
        }
        double [] ans = Arrays.copyOf(p2 , p2.length);
        for (int i = 0; i < p1.length ; i++){
            ans[i] += p1[i];
        }
        ans = compact(ans);
		return ans;
	}
	/**
	 * This function computes the polynomial function which is the multiplication of two polynoms (p1,p2)
     * l1 = p1.length
     * l2= p2.length
     * if l1>l2 {  #make sure p2 is longer if the length is not equal
        replce p2 to p1
        replace p1 to p2
     * }
     * double [] ans = [l1+l2-1]
     * for i= o to i=l2-1{
     *     for j=0 to l=l1-1{
     *         ans[i+j] += p2[i]*p1[j]
     *     }
     * }
     * return ans
     *
	 * @param p1
	 * @param p2
	 * @return ans -the array that represents the polynomial that is the multiplication of two polynomial functions
	 */
	public static double[] mul(double[] p1, double[] p2) {
        int l1 = p1.length;
        int l2 = p2.length;
        if (l1 > l2) {
            double [] replace = p2;
            p2 = p1;
            p1 = replace;
            l1 = p1.length;
            l2 = p2.length;
        }
        double [] ans = new double [l1 + l2 -1];
            for (int i = 0 ; i < l2 ; i++ ){    // i is the index of p2
                for (int j = 0 ; j < l1 ; j++){ //j is the index of p1
                    ans [i+j] += p1[j] * p2[i];
                }
            }
        return ans;
	}
	/**
	 * This function computes the derivative of the p0 polynomial function.
     * input (poly)
     * if (poly!=null && poly.length>1{
        int len= poly.length
        ans = new doule [len-1]
        for(int i = 0 ; i< ans.length ; i++){
            ans[i] = poly[i+1] * (i+1)
     }
     }
     return ans
	 * @param po
	 * @return ans - the array that represents the derivative of Polynom po
	 */
	public static double[] derivative (double[] po) {
        double[] ans = ZERO;
        if (po == null || po.length == 0){
            return null;
        }
        else if (po.length == 1){
            ans = new double[]{0};
        }
        else {
            ans = new double [po.length-1];
            for (int i = 0; i <= po.length - 2; i++) {
                ans[i] = po[i + 1] * (i + 1);
            }
        }
		return ans;
	}

    /**
     *The function Compacts the given polynomial array by removing any trailing zeros.
     *
     * @param p the polynomial array to compact
     * @return ans - a new array containing only the coefficients up to the last non-zero term. If the polynomial is all zeros, returns {0}.
     * If there are no trailing zeros, the returned array is identical to the input array.
     */
    public static double[] compact (double[] p) {
        double[] ans = ZERO;
        int i = p.length-1;
        while ( p[i] == 0 && i != 0){
                i--;
        }
        if (i == 0 && p[i] == 0){
            ans = new double[] {0};
            return ans;
        }
        ans = new double [i+1];
        for (int n = 0 ; n < ans.length ; n++){
            ans [n] = p[n];
        }
        return ans;
    }

    /**
     * This function Checks whether all characters in the input string are valid as part of a polynomial.
     * @param s a String representing a polynomial
     * @return true or false.
     */
    public static boolean isokpoly (String s) {
        boolean ans = true;
        for (int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if (c != ' ' && c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7'
                    && c != '8' && c != '9' && c != '+' && c != '-' && c != '.' && c != '^' && c != 'x' && c != 'X' ) {
                ans= false;
                return ans;
            }
        }
        return ans;
    }

    /**
     * This function extracts the exponent of the first X (starting from the given index) in a polynomial string.
     * @param s the string representing a polynomial or part of a polynomial (e.g., "3x^2+4x", "x", "5").
     * @param start_search_index the index in the string from which to start searching for the power value.
     * @return the value of the power of X in the term:
     * - returns 0 if the term contains no 'x' or 'X'.
     * - returns 1 if 'x' or 'X' is present but there is no '^' indicating an explicit exponent.
     * - returns the integer value following '^' up to the next '+' or '-' or end of string.
     */
    public static int power_value (String s, int start_search_index){
        String value = "";
        if (s.indexOf('X') == -1 && s.indexOf('x') == -1){
            return 0;
        }
        if ((s.indexOf('X') != -1 || s.indexOf('x') != -1 ) && s.indexOf('^') == -1){
            return 1;
        }
        for ( int i = start_search_index; i < s.length(); i++ ){
            if (s.charAt(i) == '+' || s.charAt(i) == '-'){
                if (value.equals("")){
                    return 1;
                }
                return Integer.valueOf(value);
            }
            else {
                value = value + s.charAt(i);
            }

        }
        return Integer.valueOf(value) ;
    }

    /**
     * This function extracts the coefficient of the first X (starting from the given index) in a polynomial string.
     * @param s the string representing a polynomial or part of a polynomial (e.g., "3.7x^2+4x", "x", "5").
     * @param start_search_index the index in the string from which to start searching for the coefficient value.
     * @return the value of the coefficient of X as a double:
     * - returns 1 if the first character at start_search_index is 'X'.
     * - returns -1 if the first characters are "-X".
     * - returns the numeric value parsed from the characters starting at start_search_index up to the next 'X', or all remaining characters if no 'X' is found.
     *  */

    public static double valueCoefficient(String s ,int start_search_index ) {
        String value= "";
        int indexofX = s.indexOf('X' , start_search_index);
        if (indexofX == start_search_index) {
            return 1;
        }
        if (indexofX == -1) {
            for (int i = 0; i < s.length() ; i++) {
                value = value + s.charAt(i);
            }
        }
        else {
            for (int i = start_search_index; i < indexofX; i++) {
                value = value + s.charAt(i);
            }
        }
        if (value.equals("-")) {
            value = "-1";
        }
        return Double.valueOf(value);
    }

    /**
     * This function finds the position of the next '+' or '-' sign in the given string.
     * @param p the string representing a polynomial or part of a polynomial.
     * @return :
     * - returns 0 if no '+' or '-' is found.
     * - otherwise, returns the index of the first occurrence of '+' or '-' plus one.
     */
    public static int nextsign (String p){
        int cutString = 0;
        if (p.indexOf('+') == -1 && p.indexOf('-') == -1 ){
            return cutString;
        }
        if (p.indexOf('+') == -1){
            cutString = p.indexOf('-');
        }
        else if (p.indexOf('-') == -1){
            cutString = p.indexOf('+');
        }
        else {
            cutString = Math.min(p.indexOf('+'), p.indexOf('-'));
        }
        return cutString + 1;
    }
    }

