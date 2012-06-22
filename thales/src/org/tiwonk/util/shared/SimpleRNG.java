package org.tiwonk.util.shared;

import java.util.Date;


/**
 * SimpleRNG is a simple random number generator based on 
 * George Marsaglia's MWC (multiply with carry) generator.
 * Although it is very simple, it passes Marsaglia's DIEHARD
 * series of random number generator tests.
 * <p>
 * A homegrown random number generator so that we can use the same code in both
 * GWT client and server.
 * </p>
 * <p>
 * This class is based on 
 * <a href="http://www.codeproject.com/Articles/25172/Simple-Random-Number-Generation">open source code</a>
 * from John Cook. * 
 * </p>
 */
public class SimpleRNG implements RandomGenerator
{
    // These values are not magical, just the default values Marsaglia used.
    // Any pair of unsigned integers should be fine.
  
    private  int m_w = 521288629;
    private  int m_z = 362436069;
    

    // The random generator seed can be set three ways:
    // 1) specifying two non-zero unsigned integers
    // 2) specifying one non-zero unsigned integer and taking a default value for the second
    // 3) setting the seed from the system time

    public  SimpleRNG(int u, int v)
    {
      setSeed(u, v);
    }

    private void setSeed(int u, int v) {

      if (u != 0) m_w = u; 
      if (v != 0) m_z = v;
      
    }

    public  SimpleRNG(int u)
    {
        m_w = u;
    }

    public SimpleRNG()
    {
        Date dt = new Date();
        long x = dt.getTime();
        setSeed((int)(x >>> 16), (int)(x % 4294967296L));
    }

    // Produce a uniform random sample from the open interval (0, 1).
    // The method will not return either end point.
    public  double nextDouble()
    {
        // 0 <= u < 2^32
        int u = nextInt();
        // The magic number below is 1/(2^32 + 2).
        // The result is strictly between 0 and 1.
        return (u + 1.0) * 2.328306435454494e-10;
    }

    // This is the heart of the generator.
    // It uses George Marsaglia's MWC algorithm to produce an unsigned integer.
    // See http://www.bobwheeler.com/statistics/Password/MarsagliaPost.txt
    public  int nextInt()
    {
        m_z = 36969 * (m_z & 65535) + (m_z >>> 16);
        m_w = 18000 * (m_w & 65535) + (m_w >>> 16);
        return (m_z << 16) + m_w;
    }
    
    /**
     * Returns the SHA-1 hash of a 64 random bits.
     * This value is suitable for use as a random unique identifier.
     */
    public  String nextUID() {
      String value = Integer.toHexString(nextInt()) + Integer.toHexString(nextInt());
      return HashUtil.SHA1(value);
    }
    
//    
//    // Get normal (Gaussian) random sample with mean 0 and standard deviation 1
//    public  double GetNormal()
//    {
//        // Use Box-Muller algorithm
//        double u1 = nextDouble();
//        double u2 = nextDouble();
//        double r = Math.sqrt( -2.0*Math.log(u1) );
//        double theta = 2.0*Math.PI*u2;
//        return r*Math.sin(theta);
//    }
//    
//    // Get normal (Gaussian) random sample with specified mean and standard deviation
//    public  double GetNormal(double mean, double standardDeviation)
//    {
//        if (standardDeviation <= 0.0)
//        {
//            String msg = "Shape must be positive. Received " + standardDeviation;
//            throw new RuntimeException(msg);
//        }
//        return mean + standardDeviation*GetNormal();
//    }
//    
//    // Get exponential random sample with mean 1
//    public  double GetExponential()
//    {
//        return -Math.log( nextDouble() );
//    }
//
//    // Get exponential random sample with specified mean
//    public  double GetExponential(double mean)
//    {
//        if (mean <= 0.0)
//        {
//            String msg = "Mean must be positive. Received " + mean;
//            throw new RuntimeException(msg);
//        }
//        return mean*GetExponential();
//    }
//
//    public  double GetGamma(double shape, double scale)
//    {
//        // Implementation based on "A Simple Method for Generating Gamma Variables"
//        // by George Marsaglia and Wai Wan Tsang.  ACM Transactions on Mathematical Software
//        // Vol 26, No 3, September 2000, pages 363-372.
//
//        double d, c, x, xsquared, v, u;
//
//        if (shape >= 1.0)
//        {
//            d = shape - 1.0/3.0;
//            c = 1.0/Math.sqrt(9.0*d);
//            for (;;)
//            {
//                do
//                {
//                    x = GetNormal();
//                    v = 1.0 + c*x;
//                }
//                while (v <= 0.0);
//                v = v*v*v;
//                u = nextDouble();
//                xsquared = x*x;
//                if (u < 1.0 -.0331*xsquared*xsquared || Math.log(u) < 0.5*xsquared + d*(1.0 - v + Math.log(v)))
//                    return scale*d*v;
//            }
//        }
//        else if (shape <= 0.0)
//        {
//            String msg = "Shape must be positive. Received " + shape;
//            throw new RuntimeException(msg);
//        }
//        else
//        {
//            double g = GetGamma(shape+1.0, 1.0);
//            double w = nextDouble();
//            return scale*g*Math.pow(w, 1.0/shape);
//        }
//    }
//
//    public  double GetChiSquare(double degreesOfFreedom)
//    {
//        // A chi squared distribution with n degrees of freedom
//        // is a gamma distribution with shape n/2 and scale 2.
//        return GetGamma(0.5 * degreesOfFreedom, 2.0);
//    }
//
//    public  double GetInverseGamma(double shape, double scale)
//    {
//        // If X is gamma(shape, scale) then
//        // 1/Y is inverse gamma(shape, 1/scale)
//        return 1.0 / GetGamma(shape, 1.0 / scale);
//    }
//
//    public  double GetWeibull(double shape, double scale)
//    {
//        if (shape <= 0.0 || scale <= 0.0)
//        {
//            String msg = "Shape and scale parameters must be positive. Recieved shape " + shape + " and scale" +scale;
//            throw new RuntimeException(msg);
//        }
//        return scale * Math.pow(-Math.log(nextDouble()), 1.0 / shape);
//    }
//
//    public  double GetCauchy(double median, double scale)
//    {
//        if (scale <= 0)
//        {
//            String msg = "Scale must be positive. Received " + scale;
//            throw new RuntimeException(msg);
//        }
//
//        double p = nextDouble();
//
//        // Apply inverse of the Cauchy distribution function to a uniform
//        return median + scale*Math.tan(Math.PI*(p - 0.5));
//    }
//
//    public  double GetStudentT(double degreesOfFreedom)
//    {
//        if (degreesOfFreedom <= 0)
//        {
//            String msg = "Degrees of freedom must be positive. Received " + degreesOfFreedom;
//            throw new RuntimeException(msg);
//        }
//
//        // See Seminumerical Algorithms by Knuth
//        double y1 = GetNormal();
//        double y2 = GetChiSquare(degreesOfFreedom);
//        return y1 / Math.sqrt(y2 / degreesOfFreedom);
//    }
//
//    // The Laplace distribution is also known as the double exponential distribution.
//    public  double GetLaplace(double mean, double scale)
//    {
//        double u = nextDouble();
//        return (u < 0.5) ?
//            mean + scale*Math.log(2.0*u) :
//            mean - scale*Math.log(2*(1-u));
//    }
//
//    public  double GetLogNormal(double mu, double sigma)
//    {
//        return Math.exp(GetNormal(mu, sigma));
//    }
//
//    public  double GetBeta(double a, double b)
//    {
//        if (a <= 0.0 || b <= 0.0)
//        {
//            String msg = "Beta parameters must be positive. Received " + a + " and " + b;
//            throw new RuntimeException(msg);
//        }
//
//        // There are more efficient methods for generating beta samples.
//        // However such methods are a little more efficient and much more complicated.
//        // For an explanation of why the following method works, see
//        // http://www.johndcook.com/distribution_chart.html#gamma_beta
//
//        double u = GetGamma(a, 1.0);
//        double v = GetGamma(b, 1.0);
//        return u / (u + v);
//    }

    @Override
    public int nextInt(int limit) {
      int result = (int)(nextDouble() * (limit+1));
      if (result > limit) {
        result = limit;
      }
      return result;
    }

    @Override
    public String nextString(int length) {
      StringBuilder builder = new StringBuilder(length);
      
      while (builder.length() < length) {
        builder.append(Integer.toHexString(nextInt()));
      }

      String value = builder.toString();
      return value.substring(0, length);
    }

    @Override
    public String nextUid() {
      return nextString(32);
    }
}



/*
 This file is a derivative work adapted from source code written by John D. Cook
 The original source code is available under Code Project Open License (CPOL) 1.02
 from
    http://www.codeproject.com/Articles/25172/Simple-Random-Number-Generation
    
 The derivative work was created June 18, 2012.  It modifies the original in the following 
 ways:
 
   1. Translated from C# into Java, which requires the use of the unsigned shift operator.
   2. Added method nextUID() which returns a random string suitable for use as an identifier.
   3. Automatically seeds the random number generator based on the system date.
   4. Removed static qualifiers so that different instances can have different seed values.
   5. Replaced methods for setting the seed values with constructors.
   6. Implements the RandomGenerator interface to provide an additional layer of abstraction.
   
 */

/*******************************************************************************
 * Copyright 2012 Gregory McFall
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

