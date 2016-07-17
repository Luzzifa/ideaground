/**
 * copyright 2016 jforge.net, luzzifa.de, Wolfgang Deifel
 */
import java.sql.Timestamp

/**
*
* @author Wolle
*/
object FindPrimes
{
    val maxValue = Long.MAX_VALUE
    val maxPrimes = 10000000
    val primes = LongArray(maxPrimes, { 0L })
    val hoch2 = LongArray(maxPrimes, { 0L })
    var size = 0

    val logInterval = 100000
    var nextLog = logInterval

    val start = System.currentTimeMillis()
    var logstart = start

    @JvmStatic
    fun main(args: Array<String>)
    {
        println("Start at " + Timestamp(start))

        addPrime(2)
        //for (var n = 3; n < maxValue; n+=2)
        for (n: Long in 3..maxValue step 2)
        {
            if (isPrime(n))
            {
                addPrime(n)
            }
        }
        val end = System.currentTimeMillis()
        println("End at " + Timestamp(end))
        println("Duration was " + (end - start) / 1000.0 + " seconds")
    }

    fun isPrime(n: Long): Boolean
    {
        for (p: Int in 0..size)
        {
            val prm: Long = primes[p]
            if (hoch2[p] == 0L)
            {
                hoch2[p] = prm * prm
            }
            if (hoch2[p] > n)
            {
                return true
            }
            if (n % prm == 0L)
            {
                return false
            }
        }
        return false
    }

    fun addPrime(n: Long)
    {
        primes[size++] = n
        if (size >= nextLog)
        {
            val logend = System.currentTimeMillis()
            println("found " + size + " primes in " + (logend - logstart) / 1000.0 + " seconds - " + primes[size - 1])
            nextLog += logInterval
            logstart = logend
        }
    }

}