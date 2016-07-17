/**
 *
 */

import java.sql.Timestamp;

/**
 * @author Wolle
 *
 */
public class FindPrimes2
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        new FindPrimes2().run();
    }


    /**
     *
     */
    public FindPrimes2()
    {
        // TODO Auto-generated constructor stub
    }

    public void run()
    {
        final long maxValue = Long.MAX_VALUE;
        final int maxPrimes = 10000000;
        final long[] primes = new long[maxPrimes];
        final long[] hoch2 = new long[maxPrimes];
        int size = 0;

        int nextLog = 100000;
        int logInterval = 100000;

        long start = System.currentTimeMillis();
        long logstart = start;

        System.out.println("Start at "+new Timestamp(start));

        primes[size++] = 2;
        for (long n = 3; n < maxValue; n+=2)
        {
            boolean prime = true;
            for (int p = 0; p < size; ++p)
            {
                final long prm = primes[p];
                if (hoch2[p] == 0)
                {
                    hoch2[p] = prm * prm;
                }
                if (hoch2[p] > n)
                {
                    break;
                }
                if (n % prm == 0)
                {
//                    System.out.println("No Prime: "+n+" by "+d);
                    prime = false;
                    break;
                }
            }
            if (prime)
            {
                primes[size++] = n;
                //System.out.println("Prime: "+n+" added as "+primes.size()+" prime number");
                if (size >= nextLog)
                {
                    long logend = System.currentTimeMillis();
                    System.out.println("found "+size+" primes in "+(logend-logstart)/1000.0+" seconds - "+primes[size-1]);
                    nextLog += logInterval;
                    logstart = logend;
                }
                if (size >= maxPrimes)
                {
                    break;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("End at "+new Timestamp(end));
        System.out.println("Duration was "+(end-start)/1000.0+" seconds");
    }


}
